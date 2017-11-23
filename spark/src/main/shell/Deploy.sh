#!/usr/bin/env bash
BUILD_HOME=/e/IntillgWorkspace/map-reduce
APP_HOME=/opt/TEST/spark-demo/package
MAIN_CLASS=me.codebase.spark.demo.StandaloneDeployApp
SSH_HOST_PORT=root@10.1.51.238

#cd $BUILD_HOME
#mvn clean package
#scp -vr $BUILD_HOME/build/lib $SSH_HOST_PORT:$APP_HOME
ssh $SSH_HOST_PORT "sed -i 's/__MAIN_CLASS__/$MAIN_CLASS/g' $APP_HOME/bin/map-reduce.sh;
sh $APP_HOME/bin/map-reduce.sh restart; sh $APP_HOME/bin/showlog.sh;"
