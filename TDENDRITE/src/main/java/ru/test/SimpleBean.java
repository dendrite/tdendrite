package ru.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
//import sun.util.resources.LocaleNames_ru;

import javax.annotation.Resource;
import java.util.Locale;

/**
 *
 *
 *
 */
@Component
public class SimpleBean {

    private static final Logger logger = Logger.getLogger( SimpleBean.class );

//    @Resource
//	private ShTimer shTimer;

//    @Resource
//	private MessageSource messageSource;
    private Locale locale;

    public String getParameter01(){

       // shTimer.justPrint();

        locale = Locale.getDefault();

//        logger.debug("country is:" + locale.getCountry());
//        logger.info( "message" );

        System.out.println("country is:" + locale.getCountry());
        System.out.println( "message" );

        //System.out.println( messageSource.getMessage( "test.name", null, locale ) );

        return null;
    }

}
