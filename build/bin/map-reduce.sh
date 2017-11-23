#!/usr/bin/env bash

source /etc/profile
umask 077

# Java home directory
JAVA_DIR=

# Java opts
JAVA_OPTSS=

MAIN_CLASS= __MAIN_CLASS__

CURRENT_DIR=$(cd "$(dirname "$0")"; pwd)  
cd $CURRENT_DIR

# Installation directory
APP_HOME=$(dirname $CURRENT_DIR)
APP_NAME=map-reduce

CLASSPATH=$APP_HOME/classes
for i in "$APP_HOME"/lib/*.jar; do
   CLASSPATH="$CLASSPATH":"$i"
done

if [ "$JAVA_OPTS" != "" ];  then
   JAVA_OPTSS="$JAVA_OPTS"
else
   echo "warn: use default jvm options"
   JAVA_OPTSS="-ms1024m -mx2048m -Xmn512m -Djava.awt.headless=true"
fi

psid=0

if [ "$JAVA_HOME" != "" ]; then
   JAVA_DIR="$JAVA_HOME"
else
   echo "error: JAVA_HOME is not set"
   exit 1
fi

if [ "$DISCONF_HOST" != "" ]; then
   JAVA_OPTSS="$JAVA_OPTSS -Ddisconf.conf_server_host=$DISCONF_HOST"
fi
if [ "$DISCONF_VER" != "" ]; then
   JAVA_OPTSS="$JAVA_OPTSS -Ddisconf.version=$DISCONF_VER"
fi
if [ "$DISCONF_ENV" != "" ]; then
   JAVA_OPTSS="$JAVA_OPTSS -Ddisconf.env=$DISCONF_ENV"
fi

check_pid() {
   javaps=`ps aux | grep $MAIN_CLASS | grep -v grep`

   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $2}'`
   else
      psid=0
   fi
}

start() {
   check_pid

   if [ $psid -ne 0 ]; then
      echo "warn: $MAIN_CLASS already started! (pid=$psid)"
   else
      echo -n "Starting $MAIN_CLASS..."

      nohup $JAVA_DIR/bin/java $JAVA_OPTSS -Duser.timezone=GTM-8 -Dapp.name=$APP_NAME -classpath $CLASSPATH $MAIN_CLASS $APP_HOME > /dev/null 2>&1 &

      check_pid
      sleep 3

      if [ $psid -ne 0 ]; then
         echo "[OK] (pid=$psid)"
      else
         echo "[Failed]"
      fi
   fi
}

start_foreground() {
   check_pid

   if [ $psid -ne 0 ]; then
      echo "warn: $MAIN_CLASS already started! (pid=$psid)"
   else
      echo -n "Starting $MAIN_CLASS..."

      $JAVA_DIR/bin/java $JAVA_OPTSS -classpath $CLASSPATH $MAIN_CLASS $APP_HOME

      check_pid
      sleep 3

      if [ $psid -ne 0 ]; then
         echo "[OK] (pid=$psid)"
      else
         echo "[Failed]"
      fi
   fi
}

stop() {  
   check_pid  
   if [ $psid -ne 0 ]; then  
      echo -n "Stopping $MAIN_CLASS...(pid=$psid)"  
	  kill $psid
	  sleep 3
      if [ $? -eq 0 ]; then  
         echo "[OK]"  
      else  
         echo "[Failed]"  
      fi  
   
      check_pid  
      if [ $psid -ne 0 ]; then  
         kill -9 $psid
		 if [ $? -ne 0 ]; then  
			stop
		 fi
      fi  
   else   
      echo "warn: $MAIN_CLASS is not running"   
   fi  
}  

status() {
   check_pid

   if [ $psid -ne 0 ]; then
      echo "$MAIN_CLASS is running! (pid=$psid)"
   fi
   echo "$MAIN_CLASS is not running!"
   exit 1
}  
     
info() {  
   echo "System Information:"  
   echo "****************************"  
   echo `head -n 1 /etc/issue`  
   echo `uname -a`  
   echo  
   echo "JAVA_HOME=$JAVA_HOME"  
   echo `$JAVA_HOME/bin/java -version`  
   echo  
   echo "APP_HOME=$APP_HOME"  
   echo "MAIN_CLASS=$MAIN_CLASS" 
   echo "OPTS=$JAVA_OPTSS"    
   echo "****************************"  
}  



case "$1" in
   'start_foreground')
     start_foreground
     ;;
   'start')  
     start
     ;;
   'stop')  
     stop  
     ;;  
   'restart')  
     stop  
     start  
     ;;  
   'status')  
     status

     ;;  
   'info')  
     info  
     ;;  
  *)  
echo "Usage: $0 {start_foreground|start|stop|restart|status|info}"
exit 1  
esac   
exit 0
