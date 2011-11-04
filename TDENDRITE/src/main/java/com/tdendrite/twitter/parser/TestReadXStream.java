package com.tdendrite.twitter.parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.tdendrite.core.SimpleClass;
import com.tdendrite.core.payload.StreamPayload;
import com.tdendrite.core.payload.twitter.UserStatus;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class TestReadXStream {

	
	public static String getJSON(Object object, String parameters, Class clazz){
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.setMode( XStream.NO_REFERENCES );
		System.out.println("---" + object.getClass().getSimpleName());
		xstream.alias( "UserStatus22", clazz);
		
		return xstream.toXML( object );
	
	//return xstream.toXML( userStatusList );		
	}	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
		String fileName2 = "c:/DATA/Twitter/Archive/del/2011.10.11/status25.2011.10.11.23.49.01.074.txt";
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("UserStatusList", UserStatus.class);
		//System.out.println( getString(fileName2) );
		List<UserStatus> list2 = (List<UserStatus>)xstream.fromXML( FileUtils.readFileToString( new File(fileName2)) );
		
		
		
		System.out.println();
		int i = 0;
		for (UserStatus userStatus : list2) {
			System.out.println( userStatus );
			if( i++ > 5 )
			break;
		}
		
		
		String jsonString = FileUtils.readFileToString( new File(fileName2));

		System.out.println( jsonString );
		//List<UserStatus>
		StreamPayload<String> sp = new StreamPayload<String>( false );
        sp.setId("sp_id");
        sp.setTime(new Date().getTime());
        sp.setParameters("JSON|UserStatusList");
        sp.setClassName( "String" );

        sp.setTypedPayload( jsonString );
		
        String fileName = "c:/DATA/Twitter/Archive/del/2011.10.11/status25.2011.10.11.23.49.01.074.txt__2";
        FileOutputStream fos = new FileOutputStream( fileName);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(sp);
        out.close();
        
        
        
    	//public String getUserStatusList(List<UserStatus> userStatusList){
    		xstream = new XStream(new JettisonMappedXmlDriver());
    		xstream.setMode( XStream.NO_REFERENCES );
    		//xstream.alias("UserStatusList", UserStatus.class);
    		String s1 = xstream.toXML( list2 );
    		System.out.println( s1 );
    		
    		
    		System.out.println("\n" + getJSON( list2, "List<UserStatus>", UserStatus.class ) );
    		
    		//return xstream.toXML( userStatusList );		
    	//}
        
        
	}

}
