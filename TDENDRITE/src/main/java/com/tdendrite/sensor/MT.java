package com.tdendrite.sensor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MT implements IStreamProcessor {

	private static int count = 0;
	private String string;
	public void update(){
		count++;
		this.string = "" + count;
	}
	
	@Override
	public ByteArrayOutputStream returnOutputStream() throws IOException {
		
		ByteArrayOutputStream f = new ByteArrayOutputStream(); 
		String s = "This should end up in the array " + this.string; 
		byte buf[] = s.getBytes(); 
		f.write(buf); 
		System.out.println("Buffer as a string");
		f.flush();
		f.close();
		return f;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
