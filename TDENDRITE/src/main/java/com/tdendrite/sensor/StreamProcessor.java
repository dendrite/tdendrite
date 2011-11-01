package com.tdendrite.sensor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamProcessor {

	private IStreamProcessor sp;
	
	public IStreamProcessor getSp() {
		return sp;
	}

	public void setSp(IStreamProcessor sp) {
		this.sp = sp;
	}

	public void do_(){
		
		try {
			ByteArrayOutputStream baos = this.sp.returnOutputStream();
			
			String PATH = new java.io.File("").getAbsolutePath();
			FileOutputStream os2 = new FileOutputStream( new File( PATH + File.separator + "outputstream1" ) );
			
			os2.write( baos.toByteArray() );
			os2.flush();
			os2.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		MT mt = new MT();
		
		StreamProcessor sp = new StreamProcessor();
		sp.setSp( mt );
		sp.do_();
		
		for (int i = 0; i < 10; i++) {
			mt.update();	
		}
		sp.do_();
		
	}// MAIN
	
}
