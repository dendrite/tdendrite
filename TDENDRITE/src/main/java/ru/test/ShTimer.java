package ru.test;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 *
 */
@Component
public class ShTimer {

    public static final int TRIGGER_TIME = 1000;
    public static long value = 0;

    //@Scheduled(fixedRate = TRIGGER_TIME)
    @Scheduled(fixedDelay = TRIGGER_TIME)
    public void generateOut(){
    	try {
    		// sleep randomly current thread for some seconds - just emulate real situation
  		    Thread.sleep( Math.round( Math.random() * 3000 ) );
  	    } catch (InterruptedException e) {
  		    e.printStackTrace();  
  	    }
        System.out.println( "fired generator times:" + value++ + "" );
    }//----------------------------------------------------------------------------------------------

    /**
     *
     */
    public void justPrint(){
        System.out.println(" just print it");
    }

}
