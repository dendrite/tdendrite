package com.tdendrite.twitter.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;

import com.tdendrite.constants.Constants;
import com.tdendrite.core.payload.StreamPayload;
import com.then3t.twitter.crawler.UserStatus;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import de.schlichtherle.truezip.file.TArchiveDetector;
import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.file.TFileInputStream;
import de.schlichtherle.truezip.file.TFileOutputStream;
import de.schlichtherle.truezip.fs.archive.zip.ZipDriver;
import de.schlichtherle.truezip.socket.sl.IOPoolLocator;


public class MainParse {

	
	
	
	
	
	public static void zipFiles(List<String> list, String zipFileName, String relativePath){
		
		try{

			// Create a buffer for reading the files
			byte[] buf = new byte[1024*8];
			
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream( zipFileName ));			
			out.setLevel( 6 );
			
			// Compress the files
		    //for (int i=0; i<filenames.length; i++) {
		    for (String fileName : list) {
				
		        FileInputStream in = new FileInputStream( relativePath + File.separator + fileName );

		        
		        ZipEntry entry = new ZipEntry( fileName );
		       
		        
		        // Add ZIP entry to output stream.
		        out.putNextEntry( entry );

		        // Transfer bytes from the file to the ZIP file
		        int len;
		        while ((len = in.read(buf)) > 0) {
		            out.write(buf, 0, len);
		        }

		        // Complete the entry
		        out.closeEntry();
		        in.close();
		    }

		    // Complete the ZIP file
		    out.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
//		// These are the files to include in the ZIP file
//		String[] filenames = new String[]{"filename1", "filename2"};
//
//		// Create a buffer for reading the files
//		byte[] buf = new byte[1024];
//
//		try {
//		    // Create the ZIP file
//		    String outFilename = "outfile.zip";
//		    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
//
//		    // Compress the files
//		    for (int i=0; i<filenames.length; i++) {
//		        FileInputStream in = new FileInputStream(filenames[i]);
//
//		        // Add ZIP entry to output stream.
//		        out.putNextEntry(new ZipEntry(filenames[i]));
//
//		        // Transfer bytes from the file to the ZIP file
//		        int len;
//		        while ((len = in.read(buf)) > 0) {
//		            out.write(buf, 0, len);
//		        }
//
//		        // Complete the entry
//		        out.closeEntry();
//		        in.close();
//		    }
//
//		    // Complete the ZIP file
//		    out.close();
//		} catch (IOException e) {
//		}
		
	}
	
	
	
	
	public static Long fileNameToTime(String fileName) throws ParseException{
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").parse( getName(fileName) ).getTime();
	}
	
	public static String getJSON(Object object, String alias, Class clazz){
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.setMode( XStream.NO_REFERENCES );
		xstream.alias( alias, clazz);
		return xstream.toXML( object );		
	}
	
	public static String getUserStatusList(List<UserStatus> userStatusList){
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.setMode( XStream.NO_REFERENCES );
		xstream.alias("UserStatus", UserStatus.class);
		return xstream.toXML( userStatusList );		
	}
	
	public static String getName(String string){
		return StringUtils.substringBetween(string, "status25.", ".txt.srl");
	}
	
	
	
	
	public static StreamPayload<String> convertToStreamPayload(List<UserStatus> list, String fileName){
		StreamPayload<String> sp = null;
		try{
			sp = new StreamPayload<String>(true);
			
			sp.setId( Constants.STREAMPAYLOAD_ID_TWITTER_USERSTATUS );
			sp.setClassName("UserStatus");
			
			String stringUserStatusList = getJSON( list, "UserStatus", UserStatus.class );
			sp.setTypedPayload(stringUserStatusList);
			
			Long time = fileNameToTime(fileName);
			sp.setTime(time);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return sp;
	}
	
	
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

//		File file = new File("/commons/io/project.properties");
//		//List lines = FileUtils.readLines(file, "UTF-8");
//		
//		String string = FileUtils.readFileToString(file, "UTF-8");
	
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

//		File file = new File("archive.zip/entry");
//		File parent = (File) file.getParentFile();
		//TFile.setDefaultArchiveDetector(new TDefaultArchiveDetector("tar|zip"));
		//TFile src = new TFile("");
		
		
		
		TFile tf = new TFile("c:/DATA/Twitter.Archive.2011/twi.2011.11.03.zip");
		

		
		//TFile[] entries = (TFile[]) tf.listFiles();
		TFile[] entries = tf.listFiles(new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
			    return fileName.startsWith("status25.");
			}
			});
		
		
		Arrays.sort(entries, new Comparator<TFile>(){
		    public int compare(TFile f1, TFile f2){
		    	return f1.getName().compareTo(f2.getName());
		    }}
		);
		
		Date date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").parse("2011.10.11.21.03.05.158");
		System.out.println( date + "|" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(date) );
		//System.exit(0);
		ArrayList<Long> timeList = new ArrayList<Long>();
		
		
		
		
		String zipFileName = "c:/DATA/Twitter.Archive.2011/streampayload.twitter2.twi.2011.11.03.zip";
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream( zipFileName ));			
		out.setLevel( 6 );
		
		
		
		
		
		
		for (TFile t : entries) {
			   System.out.println(t.getName() + "|" + getName(t.getName()) + "|");
			   timeList.add(fileNameToTime(t.getName()));
			   

			   
			   //System.out.println(t.isDirectory());
			   TFileInputStream tfis = new TFileInputStream(t);
			   //System.out.println( f.getName() + "\n");
			   in = new ObjectInputStream(tfis);
			   //List<com.tdendrite.core.payload.UserStatus> list2 = (List<com.tdendrite.core.payload.UserStatus>)in.readObject();
			   List<UserStatus> list2 = (List<UserStatus>)in.readObject();
			   
			   //String stringUserStatusList = getJSON( list2, "UserStatus", UserStatus.class );
			   //System.out.println( getName(t.getName()) + "|stringUserStatusList:" + stringUserStatusList );
			   StreamPayload<String> payload = convertToStreamPayload( list2, t.getName() );
			   //System.out.println( payload );
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			// Call this once at application startup to make the ZipDriver a compile time
			// dependency.
//			TFile.setDefaultArchiveDetector(
//			  new TArchiveDetector(
//			  "zip",
//			  new ZipDriver(IOPoolLocator.SINGLETON)));

//			// Here's the work.
//			TFile src = new TFile(this.getMellomStasjon());
//			TFile dst = new TFile(this.getZipFolder(), zipFile + ".zip");
//			TFile.cp_rp(src, dst, TArchiveDetector.NULL);
//			TFile.umount(dst);
//			   
			   
			   
//				TFile dst = new TFile("c:/DATA/Twitter.Archive.2011/streampayload.twitter2.2011.10.11.zip");
//				
//				TFile src = new TFile("twitter."+getName(t.getName())+".streampayload");
//				
//				TFileOutputStream fos = new TFileOutputStream(src);
//				ObjectOutputStream out = new ObjectOutputStream(fos);
//		        out.writeObject(payload);
//		       
//		        //fos.flush();
//		        //fos.close();
//		        out.close();
//		        
//		        //TFile.cp(src, dst );
//		        src.cp(dst);
//		        
//		        dst.umount();
		        
		       //TFile.umount(tOut);
		        //tOut.umount();
			   
			   
			   
			   
			   ZipEntry entry = new ZipEntry( "twitter."+getName(t.getName())+".streampayload" );
		       
		        // Add ZIP entry to output stream.
		        out.putNextEntry( entry ); 
		        
		        ObjectOutputStream oos = new ObjectOutputStream(out);
		        oos.writeObject(payload);
		        oos.flush();
		        //oos.close();
		        out.closeEntry();
		        out.flush();
			   // oos.close();
			   
			   
//			   String zipFileName = "c:/DATA/Twitter.Archive.2011/streampayload.twitter2.2011.10.11.zip";
//			   byte[] buf = new byte[1024*8];
//				
//				ZipOutputStream out = new ZipOutputStream(new FileOutputStream( zipFileName ));			
//				out.setLevel( 6 );
//				
//				ZipEntry entry = new ZipEntry( "twitter."+getName(t.getName())+".streampayload" );
//		       
//		        // Add ZIP entry to output stream.
//		        out.putNextEntry( entry ); 
//		        
//		        ObjectOutputStream oos = new ObjectOutputStream(out);
//		        oos.writeObject(payload);
//		        
//		        out.closeEntry();
//		        out.close();
//			    System.exit(0);
			   
			   
			   
			   
			   
//				i = 0;
//				//for (com.tdendrite.core.payload.UserStatus userStatus : list2) {
//				for(UserStatus userStatus : list2) {
//					System.out.println( userStatus );
//					if( i++ > 5 )
//					break;
//				}
				System.out.println("\n");
			   
		}
		
		
		out.close();
		
		for (int j = 0; j < timeList.size()-1; j++) {
			System.out.println(timeList.get(j+1)-timeList.get(j));
		}
		
		
		
		
		
	}

}
