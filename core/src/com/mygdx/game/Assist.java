package com.mygdx.game;

public class Assist {
	private String name;
	
	public Assist(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public Assist clone(){
		return new Assist(name);
	}
}
