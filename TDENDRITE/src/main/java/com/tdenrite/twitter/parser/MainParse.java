package com.tdenrite.twitter.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.then3t.twitter.crawler.UserStatus;

import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.file.TFileInputStream;


public class MainParse {

	
	
	public static String getName(String string){
		return StringUtils.substringBetween(string, "users25.", ".txt.srl");
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

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
		
		TFile tf = new TFile("c:/DATA/Twitter.Archive.2011/twi.2011.10.11.zip");
		//TFile[] entries = (TFile[]) tf.listFiles();
		TFile[] entries = tf.listFiles(new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
			    return fileName.startsWith("status25.");
			}
			});
		for (TFile t : entries) {
			   System.out.print(t.getName() + "|" + getName(t.getName()) + "|");
			   System.out.println(t.isDirectory());
			   TFileInputStream tfis = new TFileInputStream(t);
			   //System.out.println( f.getName() + "\n");
			   in = new ObjectInputStream(tfis);
			   list = (List<UserStatus>)in.readObject();
				i = 0;
				for (UserStatus userStatus : list) {
					System.out.println( userStatus );
					if( i++ > 5 )
					break;
				}
				System.out.println("\n");
			   
		}
		
		
	}

}
