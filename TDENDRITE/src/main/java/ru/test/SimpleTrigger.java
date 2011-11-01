package ru.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;

/**
 *
 *
 *
 */
public class SimpleTrigger {

    public String getFileName(){
        String fileName = this.getClass().getResource("/spring/application-context.xml").toExternalForm().substring(5);
        return fileName;
    }

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args){

        SimpleTrigger st = new SimpleTrigger();
//        String fileName = st.getFileName();
//        System.out.println( "FILE_NAME IS = " + fileName );

        //simplePass
        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/application-context.xml", SimpleTrigger.class);
        //ApplicationContext context = new ClassPathXmlApplicationContext( "/spring/application-context.xml" );
        //ApplicationContext context = new ClassPathXmlApplicationContext( "application-context.xml", SimpleTrigger.class );
        ApplicationContext context = new ClassPathXmlApplicationContext( "application-context_non_annotation.xml", SimpleTrigger.class );

        String string = (String)context.getBean( "simplePass" );
        System.out.println( string );

        SimpleBean testBean = (SimpleBean)context.getBean("simpleBean");
        System.out.println(testBean);




        System.out.println( testBean.getParameter01() );

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }// MAIN

}
