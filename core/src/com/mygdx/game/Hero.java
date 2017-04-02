package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import java.util.*;

public class Hero {
	public static final int INFANTRY = 100;
	public static final int CAVALRY = 101;
	public static final int FLYING = 102;
	public static final int ARMORED = 103;
	
	private Texture img;
	private String name;
	
	private int hp,maxhp;
	private int atk;
	private int spd;
	private int def;
	private int res; //stats
	private int atkBuff;
	private int spdBuff;
	private int defBuff;
	private int resBuff; //temporary buffs
	private int comAtk;
	private int comSpd;
	private int comDef;
	private int comRes; //buffs during combat only
	private int icomAtk;
	private int icomSpd;
	private int icomDef;
	private int icomRes;//buffs during combat if initiating only
	private int moveType;
	private int x,y;
	
	private Weapon wpn;
	private int wpnType;
	
	private Passive a;
	private Passive b;
	private Passive c;
	
	private Special spc;
	private Assist ast;
	
	private ArrayList<Buff> buffs;
	
	public Hero(Texture img, String name, int moveType, int wpnType){
		this(img,name,moveType,wpnType,10,0,0,0,0);
	}
	public Hero(Texture img, String name, int moveType, int wpnType, int hp, int atk, int spd, int def, int res){
		this(img,name,moveType,wpnType,hp,atk,spd,def,res,null);
	}
	public Hero(Texture img, String name, int moveType, int wpnType, int hp, int atk, int spd, int def, int res, Weapon wpn){
		this.img = img;
		this.name = name;
		this.moveType = moveType;
		this.wpnType = wpnType;
		this.hp = hp;
		this.maxhp = hp;
		this.atk = atk;
		this.spd = spd;
		this.def = def;
		this.res = res;
		this.wpn = wpn;
		this.atkBuff = 0;
		this.spdBuff = 0;
		this.defBuff = 0;
		this.spdBuff = 0;
		comAtk = 0;
		comSpd = 0;
		comDef = 0;
		comRes = 0;
		icomAtk = 0;
		icomSpd = 0;
		icomDef = 0;
		icomRes = 0;
		a = null;
		b = null;
		c = null;
		spc = null;
		ast = null;
		buffs = new ArrayList<Buff>();
		x=0;
		y=0;
	}
	
	public Texture getImg(){
		return img;
	}
	
	public String getName(){
		return name;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getHP(){
		return hp;
	}
	public int getMaxHP(){
		return maxhp;
	}
	public int getAtk(){
		int finalAtk = atk + wpn.getMight() + atkBuff + wpn.getAtkInc();
		return finalAtk>0?finalAtk:0;
	}
	public int getSpd(){
		int finalSpd = spd + spdBuff + wpn.getSpdInc();
		return finalSpd>0?finalSpd:0;
	}
	public int getDef(){
		int finalDef = def + defBuff + wpn.getDefInc();
		return finalDef>0?finalDef:0;
	}
	public int getRes(){
		int finalRes = res + resBuff + wpn.getResInc();
		return finalRes>0?finalRes:0;
	}
	public int getBAtk(){
		//base attack
		return atk;
	}
	public int getBSpd(){
		//base speed
		return spd;
	}
	public int getBDef(){
		//base defense
		return def;
	}
	public int getBRes(){
		//base res
		return res;
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
		return icomAtk + wpn.getInitiatingCombatAttack() + a.getInitiatingCombatAttack();
	}
	public int getInitiatingCombatSpeed(){
		return icomSpd + wpn.getInitiatingCombatSpeed() + a.getInitiatingCombatSpeed();
	}
	public int getInitiatingCombatDefense(){
		return icomDef + wpn.getInitiatingCombatDefense() + a.getInitiatingCombatDefense();
	}
	public int getInitiatingCombatResistance(){
		return icomRes + wpn.getInitiatingCombatResistance() + a.getInitiatingCombatResistance();
	}
	
	public Weapon getWeapon(){
		return wpn;
	}
	public int getType(){
		return wpnType;
	}
	public int getMoveType(){
		return moveType;
	}
	
	public Passive getA(){
		return a;
	}
	public Passive getB(){
		return b;
	}
	public Passive getC(){
		return c;
	}
	
	public Special getSpecial(){
		return spc;
	}
	public int getSpecialTurn(){
		return spc.getTurn() + wpn.getSpecialModifier();
	}
	public Assist getAssist(){
		return ast;
	}
	public ArrayList<Buff> getBuffs(){
		return buffs;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setHP(int hp){
		this.hp = hp > 1? hp : 1;
	}
	public void setMaxHp(int maxhp){
		this.maxhp = maxhp > 1? maxhp : 1;
	}
	public void setAtk(int atk){
		this.atk = atk>0?atk:0;
	}
	public void setSpd(int spd){
		this.spd = spd>0?spd:0;
	}
	public void setDef(int def){
		this.def = def>0?def:0;
	}
	public void setRes(int res){
		this.res = res>0?res:0;
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
	public void setWeapon(Weapon newWeapon){
		wpn = newWeapon;
	}
	public void setWeaponType(int wpntype){
		this.wpnType = wpntype;
	}
	public void setMoveType(int movetype){
		this.moveType = movetype;
	}
	
	public void setA(Passive a){
		this.a = a;
	}
	public void setB(Passive b){
		this.b = b;
	}
	public void setC(Passive c){
		this.c = c;
	}
	public void setSpecial(Special spc){
		this.spc = spc;
	}
	private void setAssist(Assist ast) {
		this.ast = ast;
	}
	public void setBuffs(ArrayList<Buff> buffs){
		this.buffs = new ArrayList<Buff>();
		for(Buff b: buffs){
			this.buffs.add(b);
		}
	}
	public void unBuff(){
		//unbuffs everything (removes all expired buffs)
		ArrayList<Buff> newBuffs = new ArrayList<Buff>();
		for(Buff b: buffs){
			if(b.getDur() > 0){
				b.decreaseDur();
				newBuffs.add(b);
			}
		}
		buffs = newBuffs; //sets to newBuffs which removed all the expired buffs
	}
	public void addBuff(Buff b){
		//buffs an ally
		buffs.add(b);
	}
	public void setBuffs(){
		//changes the hero's buffs to match his actual buffs (doesn't stack visible buffs)
		int ab = 0, ad = 0, sb = 0, sd = 0, db = 0, dd = 0, rb = 0, rd = 0;
		for(Buff b: buffs){
			if(b.getAtkBuff() > ab){
				ab = b.getAtkBuff();
			} else if(b.getAtkBuff() < ad){
				ad = b.getAtkBuff();
			}
			if(b.getSpdBuff() > sb){
				sb = b.getSpdBuff();
			} else if(b.getSpdBuff() < sd){
				sd = b.getSpdBuff();
			}
			if(b.getDefBuff() > db){
				db = b.getDefBuff();
			} else if(b.getDefBuff() < dd){
				dd = b.getDefBuff();
			}
			if(b.getResBuff() > rb){
				rb = b.getResBuff();
			} else if(b.getResBuff() < rd){
				rd = b.getResBuff();
			}
		}
		setAtkBuff(ab + ad);
		setSpdBuff(sb + sd);
		setDefBuff(db + dd);
		setResBuff(rb + rd);
	}
	public Hero clone(){
		Hero cloned = new Hero(img, name, moveType, wpnType, hp, atk, spd, def, res, wpn);
		cloned.setAtkBuff(atkBuff);
		cloned.setSpdBuff(spdBuff);
		cloned.setDefBuff(defBuff);
		cloned.setResBuff(resBuff);
		cloned.setCombatAttack(comAtk);
		cloned.setCombatSpeed(comSpd);
		cloned.setCombatDefense(comDef);
		cloned.setCombatResistance(comRes);
		cloned.setInitialCombatAttack(icomAtk);
		cloned.setInitialCombatSpeed(icomSpd);
		cloned.setInitialCombatDefense(icomDef);
		cloned.setInitialCombatResistance(icomRes);
		cloned.setA(a);
		cloned.setB(b);
		cloned.setC(c);
		cloned.setSpecial(spc);
		cloned.setAssist(ast);
		cloned.setBuffs(buffs);
		return cloned;
	}
	
	@Override
	public String toString(){
		String typ="";
		if(wpnType == Weapon.SWORD){
			typ = "Sword";
		}
		if(wpnType == Weapon.LANCE){
			typ = "Lance";
		}
		
		return name + "-" + typ;
	}
}
