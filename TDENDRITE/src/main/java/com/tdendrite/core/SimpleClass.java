package com.tdendrite.core;

import java.io.Serializable;

public class SimpleClass implements Serializable{

	String id;
	String name;
	Long value;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}

	public String toString(){
		return " id:" + this.id + " name:" + this.name + " value:" + this.value;
	}
}