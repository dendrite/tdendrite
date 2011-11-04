package com.tdenrite.twitter.parser;

import java.io.Serializable;

public class UserStatus implements Serializable{

	long id;
	String screenName;
	long timeStamp;
	String text;
	long statusId;
	
	public void setId(long id) {
		this.id = id;
	}

	public UserStatus(long id, String screenName, long timeStamp, String text, long statusId){
		this.id = id;
		this.screenName = screenName;
		this.timeStamp = timeStamp;
		this.text = text;
		this.statusId = statusId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
	
	public String toString(){
		return "|id:" + this.id + "|name:" + this.screenName + "|time:" +  this.timeStamp + " statusId:" + this.statusId + "|text:" + this.text;
	}
	
}
