package com.tdendrite.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestNameClass<T> {
	
	public void pring(T variable){
		System.out.println("TestNameClass----------------------------------");
		Field[] fields = variable.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Type type = fields[i].getGenericType();
	        System.out.println("type: " + type);
	        if (type instanceof ParameterizedType) {
	            ParameterizedType pt = (ParameterizedType) type;
	            System.out.println("raw type: " + pt.getRawType());
	            System.out.println("owner type: " + pt.getOwnerType());
	            System.out.println("actual type args:");
	            for (Type t : pt.getActualTypeArguments()) {
	                System.out.println("    " + t);
	            }
	        }
			
		}
		System.out.println("TestNameClass----------------------------------");
		
	}
	
	public void pring2(Object variable){
		System.out.println("TestNameClass----------------------------------");
		Field[] fields = variable.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Type type = fields[i].getGenericType();
	        System.out.println("type: " + type);
	        if (type instanceof ParameterizedType) {
	            ParameterizedType pt = (ParameterizedType) type;
	            System.out.println("raw type: " + pt.getRawType());
	            System.out.println("owner type: " + pt.getOwnerType());
	            System.out.println("actual type args:");
	            for (Type t : pt.getActualTypeArguments()) {
	                System.out.println("    " + t);
	            }
	        }
			
		}
		System.out.println("TestNameClass----------------------------------");
		
	}
	

}
