package me.codebase.scala.javaside;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by chendong on 2017/8/14.
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConf.class);
        context.start();
        System.out.println(System.in); //
    }
}
