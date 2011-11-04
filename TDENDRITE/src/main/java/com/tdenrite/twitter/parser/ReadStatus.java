package com.tdenrite.twitter.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.List;

import com.then3t.twitter.crawler.UserStatus;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;


public class ReadStatus {

	
	public static String getString(String fileName) throws Exception{
		File file = new File( fileName );
		BufferedReader br = new BufferedReader( new FileReader( file ) );
		String str = "";
		String result = "";
		while( ( str = br.readLine() ) != null ){
			result += new String( str.getBytes(),"UTF-8");;
		}
		br.close();
		return result;
	}
	
	public static void main(String[] args) throws Exception{

		String PATH = new java.io.File("").getAbsolutePath();
		System.out.println( "Started path is : " + PATH );
		String fileName = "c:/DATA/Twitter/Archive/del/2011.10.11/status25.2011.10.11.23.49.01.074.txt.s";
		
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fis);
		List<UserStatus>  list = (List<UserStatus>)in.readObject();
		in.close();
		int i = 0;
		for (UserStatus userStatus : list) {
			System.out.println( userStatus );
			if( i++ > 5 )
			break;
		}
		
		String fileName2 = "c:/DATA/Twitter/Archive/del/2011.10.11/status25.2011.10.11.23.49.01.074.txt";
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("UserStatusList", UserStatus.class);
		//System.out.println( getString(fileName2) );
		List<UserStatus> list2 = (List<UserStatus>)xstream.fromXML( getString(fileName2) );
		
		System.out.println();
		i = 0;
		for (UserStatus userStatus : list2) {
			System.out.println( userStatus );
			if( i++ > 5 )
			break;
		}
		
		
	}
	
}
