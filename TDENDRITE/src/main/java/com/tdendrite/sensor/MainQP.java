package com.tdendrite.sensor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainQP {

	private IPayloadProcessor ipp;
	
	
	public IPayloadProcessor getIpp() {
		return ipp;
	}


	public void setIpp(IPayloadProcessor ipp) {
		this.ipp = ipp;
	}


	public void do_(){
		
		try {

			String PATH = new java.io.File("").getAbsolutePath();
			System.out.println( PATH );
	        FileOutputStream fos = new FileOutputStream( PATH + File.separator + "object12");
	        ObjectOutputStream out = new ObjectOutputStream(fos);
	        out.writeObject( ipp.resturn() );
	        out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MainQP mqp = new MainQP();
		
		QP qp = new QP();
		
		mqp.setIpp( qp );
		
		qp.update();
		mqp.do_();
		
	}

}
