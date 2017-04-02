package com.mygdx.game;

public class Weapon {
	public static final int SWORD = 0;
	public static final int LANCE = 1;
	public static final int AXE = 2;
	public static final int RSTONE = 3;
	public static final int BSTONE = 4;
	public static final int GSTONE = 5;
	public static final int RTOME = 6;
	public static final int BTOME = 7;
	public static final int GTOME = 8;
	public static final int BOW = 9;
	public static final int DAGGER = 10;
	public static final int STAFF = 11;
	
	private int type;
	private int mt;
	private String name;
	private String effect;
	private int atkInc;
	private int spdInc;
	private int defInc;
	private int resInc;
	private int range;
	private int comAtk;
	private int comSpd;
	private int comDef;
	private int comRes;
	private int icomAtk;
	private int icomSpd;
	private int icomDef;
	private int icomRes;
	private int specialMod;
	private int specialDamage;
	private int[] effective;
	
	//AOE DEBUFFS (to enemies or surrounding enemies)
	private boolean aoeDebuff; //defbuff surrounding enemies
	private boolean debuff; //debuff the target
	private int debuffRange; //range of enemies debuffed
	private int atkDebuff;
	private int spdDebuff;
	private int defDebuff;
	private int resDebuff;
	
	//BUFFS buffs to allies
	private boolean aoeBuff;
	private int atkBuff;
	private int defBuff;
	private int spdBuff;
	private int resBuff;
	
	//EFFECTS
	private boolean triangle;
	private boolean brave;
	
	public Weapon(String name, int type, int mt){
		this(name,type,mt,"");
	}
	public Weapon(String name, int type, int mt, String effect){
		this.type = type;
		this.mt = mt;
		this.name = name;
		this.effect = effect;
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
		specialMod = 0;
		specialDamage = 0;
		effective = new int[0];
		triangle = false;
		brave = false;
		aoeDebuff = false;
		debuff = false;
		debuffRange = 0;
		atkDebuff = 0;
		spdDebuff = 0;
		defDebuff = 0;
		resDebuff = 0;
		setAoeBuff(false);
		setAtkBuff(0);
		setSpdBuff(0);
		setDefBuff(0);
		setResBuff(0);
		range = 1;
		if (type == BOW || type == DAGGER || type == STAFF || type == BTOME || type == RTOME || type == GTOME){
			range = 2;
		}
	}
	
	public int getRange(){
		return range;
	}
	public int getMight(){
		return mt;
	}
	public int getType(){
		return type;
	}
	public String getName(){
		return name;
	}
	public String getEffect(){
		return effect;
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
	public int getSpecialModifier(){
		return specialMod;
	}
	public int getSpecialDamage(){
		return specialDamage;
	}
	public int[] getEffective(){
		return effective;
	}
	public boolean hasAoeDebuff(){
		return aoeDebuff;
	}
	public boolean hasDebuff(){
		return debuff;
	}
	public int getAttackDebuff(){
		return atkDebuff;
	}
	public int getSpeedDebuff(){
		return spdDebuff;
	}
	public int getDefenseDebuff(){
		return defDebuff;
	}
	public int getResistanceDebuff(){
		return resDebuff;
	}
	public int getDebuffRange(){
		return debuffRange;
	}
	public int getResBuff() {
		return resBuff;
	}
	public boolean isBrave(){
		return brave;
	}
	public boolean isTriangle(){
		return triangle;
	}

	public boolean isAoeBuff() {
		return aoeBuff;
	}
	public void setAoeBuff(boolean aoeBuff) {
		this.aoeBuff = aoeBuff;
	}
	public int getAtkBuff() {
		return atkBuff;
	}
	public void setAtkBuff(int atkBuff) {
		this.atkBuff = atkBuff;
	}
	public int getDefBuff() {
		return defBuff;
	}
	public void setDefBuff(int defBuff) {
		this.defBuff = defBuff;
	}
	public int getSpdBuff() {
		return spdBuff;
	}
	public void setSpdBuff(int spdBuff) {
		this.spdBuff = spdBuff;
	}
	public void setMight(int mt){
		this.mt = mt;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setEffect(String eff){
		this.effect = eff;
	}
	public void setRange(int range){
		this.range = range;
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
	public void setSpecialModifier(int specialMod){
		this.specialMod = specialMod;
	}
	public void setSpecialDamage(int specialDamage){
		this.specialDamage = specialDamage;
	}
	public void setEffective(int[] effective){
		this.effective = effective;
	}
	public void setAoeDebuff(boolean aoeDebuff){
		this.aoeDebuff = aoeDebuff;
	}
	public void setDebuff(boolean debuff){
		this.debuff = debuff;
	}
	public void setAttackDebuff(int atkDebuff){
		this.atkDebuff = atkDebuff;
	}
	public void setSpeedDebuff(int spdDebuff){
		this.spdDebuff = spdDebuff;
	}
	public void setDefenseDebuff(int defDebuff){
		this.defDebuff = defDebuff;
	}
	public void setResDebuff(int resDebuff){
		this.resDebuff = resDebuff;
	}
	public void setDebuffRange(int debuffRange){
		this.debuffRange = debuffRange;
	}
	public void setResBuff(int resBuff) {
		this.resBuff = resBuff;
	}
	public void setTriangle(boolean triangle){
		this.triangle = triangle;
	}
	public void setBrave(boolean brave){
		this.brave = brave;
	}
	
	public Weapon clone(){
		//clones weapon
		Weapon cloned = new Weapon(name,type,mt,effect);
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
		cloned.setSpecialModifier(specialMod);
		cloned.setSpecialDamage(specialDamage);;
		cloned.setEffective(effective);
		cloned.setTriangle(triangle);
		cloned.setBrave(brave);
		cloned.setAoeDebuff(aoeDebuff);
		cloned.setDebuff(debuff);
		cloned.setAttackDebuff(atkDebuff);
		cloned.setSpeedDebuff(spdDebuff);
		cloned.setDefenseDebuff(defDebuff);
		cloned.setResDebuff(resDebuff);
		cloned.setDebuffRange(debuffRange);
		return cloned;
	}
	@Override
	public String toString(){
		return name + " Mt: " + mt + " " + effect;
	}
}
