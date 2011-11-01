package com.tdendrite.sensor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tdendrite.core.SimpleClass;
import com.tdendrite.core.StreamPayload;

public class QP implements IPayloadProcessor {

	private static int count = 0;
	
	public void update(){
		count++;
	}
	
	
	@Override
	public StreamPayload<List<SimpleClass>> resturn() {
		
		List<SimpleClass> list = new ArrayList<SimpleClass>();
		for (int i = 0; i < 1000; i++) {
			SimpleClass sc = new SimpleClass();
			sc.setId("id" + i);
			sc.setName( count + "name" + i);
			sc.setValue(10L + i);
			list.add(sc);
		}

		StreamPayload<List<SimpleClass>> sp = new StreamPayload<List<SimpleClass>>(
				false);
		sp.setId("sp_id");
		sp.setTime(new Date().getTime());
		sp.setParameters("parameters...");
		sp.setClassName("List<SimpleClass>");

		sp.setTypedPayload(list);
		return sp;
	}

}
