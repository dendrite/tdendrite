package com.tdendrite.core;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

     /**
     *
     * @param argv
     */
    public static void main(String[] argv) throws Exception{

       String fileName = "streamPayload";


       SimpleClass sc = new SimpleClass();
       sc.setId("id");
       sc.setName("name");
       sc.setValue( 10L );

        List<SimpleClass> list = new ArrayList<SimpleClass>();
        for(int i = 0;i<1000;i++){
           sc = new SimpleClass();
           sc.setId("id" + i);
           sc.setName("name" + i);
           sc.setValue( 10L + i );

            list.add( sc );

        }

       long bT = System.currentTimeMillis();
       StreamPayload<List<SimpleClass>> sp = new StreamPayload<List<SimpleClass>>( true );
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
        FileOutputStream fos = new FileOutputStream("c:/DATA/TEST/" + fileName);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(sp);
        out.close();


        /*

            Back process

         */
        FileInputStream fis = new FileInputStream("c:/DATA/TEST/" + fileName);
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
