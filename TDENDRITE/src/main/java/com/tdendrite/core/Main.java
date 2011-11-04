package com.tdendrite.core;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tdendrite.core.payload.StreamPayload;

public class Main {

	/**
	 * 
	 * @param object
	 */
	public static void go(Object object){
		System.out.println("object class" + object.getClass().getTypeParameters() );
		
		System.out.println(object.getClass().getTypeParameters()[0].getName());
		
		
		System.out.println("---");
		
		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println( i + "|" + fields[i].getGenericType() );
		}
		
	}//----
	
     /**
     *
     * @param argv
     */
    public static void main(String[] argv) throws Exception{
    	
    	
    	String PATH = new java.io.File("").getAbsolutePath();
    	System.out.println( PATH );

       String fileName = "streamPayloadio___D____.02";


       SimpleClass sc = new SimpleClass();
       sc.setId("id");
       sc.setName("name");
       sc.setValue( 10L );

       System.out.println("sc.name:" + sc.getClass().getName());
       
        List<SimpleClass> list = new ArrayList<SimpleClass>();
        for(int i = 0;i<1000;i++){
           sc = new SimpleClass();
           sc.setId("id" + i);
           sc.setName("name" + i);
           sc.setValue( 10L + i );

            list.add( sc );

        }
        
        System.out.println("+++++++++++");
        
        Type types = list.getClass().getGenericSuperclass();// .getGenericParameterTypes();
        //Now assuming that the first parameter to the method is of type List<Integer>
        ParameterizedType pType = (ParameterizedType) types;
        Class<?> clazz = (Class<?>) pType.getActualTypeArguments()[0];
        System.out.println(clazz); //prints out java.lang.Integer
        
        
        System.out.println(".......");
    	TestNameClass<List<SimpleClass>> t = new TestNameClass<List<SimpleClass>>();
    	t.pring2(list );
    	System.out.println(".......");
        
        System.out.println("list:" + list.getClass().getName());
        
        go(list);
        
       long bT = System.currentTimeMillis();
       StreamPayload<List<SimpleClass>> sp = new StreamPayload<List<SimpleClass>>( false );
        sp.setId("sp_id");
        sp.setTime(new Date().getTime());
        sp.setParameters("parameters...");
        sp.setClassName( "List<SimpleClass>" );

        sp.setTypedPayload( list );
//         System.out.println( sp );
//        System.out.println();

//        byte[] bt1 = sp.getPayload();
//        System.out.println( bt1.length );
//        byte[] bt2 = zipBytes("file", bt1);
//        System.out.println( bt2.length );



        /*

            save payload

         */
        FileOutputStream fos = new FileOutputStream( PATH + File.separator + fileName);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(sp);
        out.close();
        

        /*

            Back process

         */
        FileInputStream fis = new FileInputStream( PATH + File.separator + fileName);
        ObjectInputStream in = new ObjectInputStream(fis);
        StreamPayload spBack = (StreamPayload)in.readObject();
        in.close();

        //System.out.println( spBack );
        List<SimpleClass> list2 =  (List<SimpleClass>)spBack.getTypedPayLoad();

        long eT = System.currentTimeMillis();




        System.out.println( list2.size() );

//        for(SimpleClass simpleClass : list2){
//            System.out.println( simpleClass );
//        }

        System.out.println("TIME:" + (eT-bT));

    }// MAIN
}
