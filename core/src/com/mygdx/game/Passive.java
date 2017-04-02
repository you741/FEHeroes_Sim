package com.mygdx.game;

public class Passive {
	private String name;
	private String desc;
	private char type;
	private int atkInc;
	private int spdInc;
	private int defInc;
	private int resInc;
	private int comAtk;
	private int comSpd;
	private int comDef;
	private int comRes;
	private int icomAtk;
	private int icomSpd;
	private int icomDef;
	private int icomRes;
	
	public Passive(String name, char type, String desc){
		this.name = name;
		this.type = type;
		this.desc = desc;
		atkInc = 0;
		spdInc = 0;
		defInc = 0;
		resInc = 0;
		comAtk = 0;
		comSpd = 0;
		comDef = 0;
		comRes = 0;
		icomAtk = 0;
		icomSpd = 0;
		icomDef = 0;
		icomRes = 0;
	}
	
	public char getType(){
		return type;
	}
	public String getName(){
		return name;
	}
	public String getDesc(){
		return desc;
	}
	public int getAtkInc(){
		return atkInc;
	}
	public int getSpdInc(){
		return spdInc;
	}
	public int getDefInc(){
		return defInc;
	}
	public int getResInc(){
		return resInc;
	}
	public int getCombatAttack(){
		return comAtk;
	}
	public int getCombatSpeed(){
		return comSpd;
	}
	public int getCombatDefense(){
		return comDef;
	}
	public int getCombatResistance(){
		return comRes;
	}
	public int getInitiatingCombatAttack(){
		return icomAtk;
	}
	public int getInitiatingCombatSpeed(){
		return icomSpd;
	}
	public int getInitiatingCombatDefense(){
		return icomDef;
	}
	public int getInitiatingCombatResistance(){
		return icomRes;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public void setAtkInc(int atkInc){
		this.atkInc = atkInc;
	}
	public void setSpdInc(int spdInc){
		this.spdInc = spdInc;
	}
	public void setDefInc(int defInc){
		this.defInc = defInc;
	}
	public void setResInc(int resInc){
		this.resInc = resInc;
	}
	public void setCombatAttack(int comAtk){
		this.comAtk = comAtk;
	}
	public void setCombatSpeed(int comSpd){
		this.comSpd = comSpd;
	}
	public void setCombatDefense(int comDef){
		this.comDef = comDef;
	}
	public void setCombatResistance(int comRes){
		this.comRes = comRes;
	}
	public void setInitialCombatAttack(int icomAtk){
		this.icomAtk = icomAtk;
	}
	public void setInitialCombatSpeed(int icomSpd){
		this.icomSpd = icomSpd;
	}
	public void setInitialCombatDefense(int icomDef){
		this.icomDef = icomDef;
	}
	public void setInitialCombatResistance(int icomRes){
		this.icomRes = icomRes;
	}
	public Passive clone(){
		Passive cloned = new Passive(name,type,desc);
		cloned.setAtkInc(atkInc);
		cloned.setSpdInc(spdInc);
		cloned.setDefInc(defInc);
		cloned.setResInc(resInc);
		cloned.setCombatAttack(comAtk);
		cloned.setCombatSpeed(comSpd);
		cloned.setCombatDefense(comDef);
		cloned.setCombatResistance(comRes);
		cloned.setInitialCombatAttack(icomAtk);
		cloned.setInitialCombatSpeed(icomSpd);
		cloned.setInitialCombatDefense(icomDef);
		cloned.setInitialCombatResistance(icomRes);
		return cloned;
	}
}
