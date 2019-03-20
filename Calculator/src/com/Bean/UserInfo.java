package com.Bean;

import java.io.UnsupportedEncodingException;

public class UserInfo {
   private String name;
   private String job;
   public UserInfo() {
	   name="";
	   job="";
   }
   public String getName() {
	   return name;
   }
   public void setName(String name) throws Exception{
	   this.name=new String(name.getBytes("ISO-8859-1"),"UTF-8");
   }
   public String getJob() {
	   return job;
   }
   public void setJob() {
	   try {
		this.job=new String(name.getBytes("ISO-8859-1"),"UTF-8");
	} catch (UnsupportedEncodingException e) {
//		 TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
