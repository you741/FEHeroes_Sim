package com.mygdx.game;

public class Special {
	private String name;
	private String desc;
	private int turn;
	
	public Special(String name, String desc, int turn){
		this.name = name;
		this.desc = desc;
		this.turn = turn;
	}
	
	public String getName(){
		return name;
	}
	public String getDesc(){
		return desc;
	}
	public int getTurn(){
		return turn;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public void setTurn(int turn){
		this.turn = turn;
	}
	
	public Special clone(){
		Special cloned = new Special(name,desc,turn);
		return cloned;
	}
}
