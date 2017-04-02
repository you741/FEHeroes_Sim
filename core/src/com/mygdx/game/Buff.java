package com.mygdx.game;

public class Buff {
	private int atkBuff;
	private int spdBuff;
	private int defBuff;
	private int resBuff;
	private int dur;
	
	public Buff(int atkBuff, int spdBuff, int defBuff, int resBuff, int dur){
		this.atkBuff = atkBuff;
		this.spdBuff = spdBuff;
		this.defBuff = defBuff;
		this.resBuff = resBuff;
		this.dur = dur;
	}
	
	public int getAtkBuff(){
		return atkBuff;
	}
	public int getSpdBuff(){
		return spdBuff;
	}
	public int getDefBuff(){
		return defBuff;
	}
	public int getResBuff(){
		return resBuff;
	}
	public int getDur(){
		return dur;
	}
	
	public void setAtkBuff(int atkBuff){
		this.atkBuff = atkBuff;
	}
	public void setSpdBuff(int spdBuff){
		this.spdBuff = spdBuff;
	}
	public void setDefBuff(int defBuff){
		this.defBuff = defBuff;
	}
	public void setResBuff(int resBuff){
		this.resBuff = resBuff;
	}
	public void decreaseDur(){
		dur--;
	}
}
