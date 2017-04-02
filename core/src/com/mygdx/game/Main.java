package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import java.util.*;

public class Main extends ApplicationAdapter implements InputProcessor{
	public static final int EDIT_TEAM = 200;
	public static final int EDIT_ENEMY_TEAM = 201;
	public static final int EDIT_CHARACTER = 202;
	public static final int NEW_CHARACTER = 203;
	public static final int BATTLE = 204;
	public static final int MAIN_MENU = 205;
	public static final int ENEMY_CHARACTER = 206;
	public static final int EDIT_ENEMY_CHARACTER = 207;
	public static final int EDIT_MAP = 208;
	//TERRAIN INDICES
	public static final int GRASS = 0;
	public static final int TREE = 1;
	
	private ArrayList<Weapon> weapons;
	private ArrayList<Terrain> terrain;
	private ArrayList<Hero> heroes;
	private ArrayList<Hero> team;
	private ArrayList<Hero> eteam;
	
	private Texture mainMenuBG;
	private Texture createTeam;
	private Texture addHeroButton;
	private Texture newHeroBG;
	private Texture pickWeapon;
	private Texture weaponBG;
	private Texture allyBackground;
	private Texture enemyBackground;
	private Texture goBack;
	private Texture uparrow;
	private Texture up10arrow;
	private Texture downarrow;
	private Texture down10arrow;
	private Texture changeStats;
	private Texture mapBG;
	private Texture moveHand;
	
	private Terrain[][] map;
	
	private int mode;
	private int charToEdit;
	private ArrayList<Weapon> usableWeapons;
	private int step;
	
	private BitmapFont font;
	
	private Terrain selectedTerr;
	private Hero selectedHero;
	
	//GRAPHIC STUFF
	SpriteBatch batch;
	
	@Override
	public void create () {//Input
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		weapons = new ArrayList<Weapon>();
		createWeapons();
		heroes = new ArrayList<Hero>();
		createHeroes();
		terrain = new ArrayList<Terrain>();
		createTerrain();
		map = new Terrain[6][8];
		for(int i = 0;i < 6;i++){
			for(int j = 0;j < 8;j++){
				map[i][j] = terrain.get(GRASS);
			}
		}
		team = new ArrayList<Hero>();
		eteam = new ArrayList<Hero>();
		mainMenuBG = new Texture("mainMenu.png");
		createTeam = new Texture("CreateTeam.png");
		addHeroButton = new Texture("addHero.png");
		newHeroBG = new Texture("createHero.png");
		pickWeapon = new Texture("pickWeapon.png");
		weaponBG = new Texture("weaponBG.png");
		allyBackground = new Texture("allyBackground.png");
		enemyBackground = new Texture("enemyBackground.png");
		goBack = new Texture("return.png");
		uparrow = new Texture("up.png");
		up10arrow = new Texture("up10.png");
		downarrow = new Texture("down.png");
		down10arrow = new Texture("down10.png");
		changeStats = new Texture("changeStats.png");
		mapBG = new Texture("mapBG.png");
		moveHand = new Texture("move.png");
		mode = MAIN_MENU;
		charToEdit = 0;
		step = 0;
		selectedTerr = terrain.get(GRASS);
		selectedHero = null;
		font = new BitmapFont();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(mode == MAIN_MENU){
			mainMenu();
		} else if(mode == EDIT_TEAM){
			editTeam();
		} else if(mode == NEW_CHARACTER || mode == ENEMY_CHARACTER){
			createHero();
		} else if(mode == EDIT_CHARACTER){
			customizeHero();
		} else if(mode == EDIT_ENEMY_TEAM){
			editEnemyTeam();
		} else if(mode == EDIT_ENEMY_CHARACTER){
			customizeHero();
		} else if(mode == EDIT_MAP){
			editMap();
		}
	}
	
	public void createWeapons(){
		//CREATE WEAPONS
		//SWORDS
		weapons.add(new Weapon("Iron Sword", Weapon.SWORD, 6));
		weapons.add(new Weapon("Steel Sword", Weapon.SWORD, 8));
		weapons.add(new Weapon("Silver Sword",Weapon.SWORD, 11));
		weapons.add(new Weapon("Silver Sword+",Weapon.SWORD, 15));
		
		Weapon armorSlayer = new Weapon("Armory Slayer", Weapon.SWORD, 8, "Effective against armored units");
		armorSlayer.setEffective(new int[]{Hero.ARMORED});
		weapons.add(armorSlayer);
		
		Weapon armorSlayerp = armorSlayer.clone();
		armorSlayerp.setName("Armor Slayer+");
		armorSlayerp.setMight(12);
		weapons.add(armorSlayerp);
		
		Weapon braveSword = new Weapon("Brave Sword", Weapon.SWORD, 5, "Spd-5. Attack twice when initiating combat");
		braveSword.setSpdInc(-5);
		braveSword.setBrave(true);
		weapons.add(braveSword);
		Weapon braveSwordp = braveSword.clone();
		
		braveSwordp.setName("Brave Sword+");
		braveSwordp.setMight(8);
		weapons.add(braveSwordp);
		
		Weapon rubySword = new Weapon("Ruby Sword", Weapon.SWORD, 8, "Gives Atk+20% if weapon-triangle advantage, Atk-20% if disadvantage.");
		rubySword.setTriangle(true);
		weapons.add(rubySword);
		
		Weapon rubySwordp = rubySword.clone();
		rubySwordp.setName("Ruby Sword+");
		rubySwordp.setMight(12);
		weapons.add(rubySwordp);
		
		Weapon killingEdge = new Weapon("Killing Edge", Weapon.SWORD, 7, "Accelerates Special trigger (cooldown count -1)");
		killingEdge.setSpecialModifier(-1);
		weapons.add(killingEdge);
		
		Weapon killingEdgep = killingEdge.clone();
		killingEdgep.setName("Killing Edge+");
		killingEdgep.setMight(11);
		weapons.add(killingEdgep);
		
		Weapon woDao = new Weapon("Wo Dao", Weapon.SWORD, 9, "Increases damage by 10 when Special activates");
		woDao.setSpecialDamage(10);
		weapons.add(woDao);
		
		Weapon woDaop = woDao.clone();
		woDaop.setName("Wo Dao+");
		woDaop.setMight(13);
		weapons.add(woDaop);
		
		Weapon falchion = new Weapon("Falchion", Weapon.SWORD, 16, "Effective against dragons");
		falchion.setEffective(new int[]{Weapon.RSTONE, Weapon.BSTONE, Weapon.GSTONE});
		weapons.add(falchion);
		
		//LANCES
		weapons.add(new Weapon("Iron Lance", Weapon.LANCE, 6));
		weapons.add(new Weapon("Steel Lance", Weapon.LANCE, 8));
		weapons.add(new Weapon("Silver Lance", Weapon.LANCE, 11));
		weapons.add(new Weapon("Silver Lance+", Weapon.LANCE, 15));
		Weapon killerLance = new Weapon("Killer Lance", Weapon.LANCE, 7, "Accelrates special trigger (cooldown count -1)");
		killerLance.setSpecialModifier(-1);
		weapons.add(killerLance);
		Weapon killerLancep = new Weapon("Killer Lance+", Weapon.LANCE, 11, "Accelrates special trigger (cooldown count -1)");
		killerLancep.setSpecialModifier(-1);
		weapons.add(killerLancep);
		Weapon braveLance = new Weapon("Brave Lance", Weapon.LANCE, 5, "Spd -5, Attack twice when initiating combat");
		braveLance.setSpdInc(-5);
		braveLance.setBrave(true);
		weapons.add(braveLance);
		Weapon braveLancep = braveLance.clone();
		braveLancep.setName("Brave Lance+");
		braveLancep.setMight(8);
		weapons.add(braveLancep);
		Weapon sapphireLance = new Weapon("Sapphire Lance", Weapon.LANCE, 8, "Gives ATK+20% if weapon-triangle advantage, ATK-20% if disadvantage.");
		sapphireLance.setTriangle(true);
		weapons.add(sapphireLance);
		Weapon sapphireLancep = new Weapon("Sapphire Lance+", Weapon.LANCE, 12, "Gives ATK+20% if weapon-triangle advantage, ATK-20% if disadvantage.");
		sapphireLancep.setTriangle(true);
		weapons.add(sapphireLancep);
		Weapon heavySpear = new Weapon("Heavy Spear", Weapon.LANCE, 8, "Effective against armored units");
		heavySpear.setEffective(new int[]{Hero.ARMORED});
		Weapon siegmund = new Weapon("Siegmund", Weapon.LANCE, 16, "Grants adjacent allies Atk+3 through their next actions at the start of each turn.");
		siegmund.setAtkBuff(3);
		weapons.add(siegmund);
	}

	public void createHeroes(){
		//creates heroes
		Texture lucinaIcon = new Texture("lucinaface.png"); 
		Hero lucina = new Hero(lucinaIcon,"Lucina",Hero.INFANTRY,Weapon.SWORD);
		heroes.add(lucina);
		
		Texture ephraimIcon = new Texture("ephraimFace.png");
		Hero ephraim = new Hero(ephraimIcon,"Ephraim",Hero.INFANTRY,Weapon.LANCE);
		heroes.add(ephraim);
		
		Texture karelIcon = new Texture("karelFace.png");
		Hero karel = new Hero(karelIcon,"Karel",Hero.INFANTRY,Weapon.SWORD);
		heroes.add(karel);
		
		Texture shannaIcon = new Texture("shannaFace.png");
		Hero shanna = new Hero(shannaIcon,"Shanna",Hero.FLYING,Weapon.LANCE);
		heroes.add(shanna);
	}
	
	public void createTerrain(){
		Terrain grass = new Terrain(new Texture("grass.png"),false,false,false);
		terrain.add(grass);
		Terrain tree = new Terrain(new Texture("tree.png"),true,false,false);
		terrain.add(tree);
	}
	
	public void mainMenu(){
		//screen of the main menu
		batch.begin();
		batch.draw(mainMenuBG, 0, 0);
		batch.end();
		Rectangle editTeam = new Rectangle(0,400,600,200);
		Rectangle editETeam = new Rectangle(0,200,600,200);
		Rectangle editMap = new Rectangle(0,0,600,200);
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if(editTeam.contains(getMx(),getMy())){
				mode = EDIT_TEAM;
			}
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if(editETeam.contains(getMx(),getMy())){
				mode = EDIT_ENEMY_TEAM;
			}
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if(editMap.contains(getMx(),getMy())){
				for(int i = 0;i < team.size();i++){
					team.get(i).setX(i);
				}
				for(int i = 0;i < eteam.size();i++){
					eteam.get(i).setX(i);
					eteam.get(i).setY(7);
				}
				mode = EDIT_MAP;
			}
		}
	}
	
	public void editTeam(){
		//screen for editing team
		batch.begin();
		batch.draw(createTeam,0,0);
		batch.draw(goBack,0,Gdx.graphics.getHeight()-32);
		for(int i = 0;i < team.size();i++){
			batch.draw(team.get(i).getImg(),60+i*100,600);
			Rectangle rect = new Rectangle(60+i*100,600,80,80);
			if(rect.contains(getMx(),getMy())){
				drawHeroInfo(team.get(i),true,new Rectangle(0,300,600,100));
			}
		}
		if(team.size() < 4){
			//handles adding dudes
			batch.draw(addHeroButton,0,0);
		}
		batch.end();
	}
	public void editEnemyTeam(){
		//editing enemy team
		batch.begin();
		batch.draw(createTeam,0,0);
		batch.draw(goBack,0,Gdx.graphics.getHeight()-32);
		for(int i = 0;i < eteam.size();i++){
			batch.draw(eteam.get(i).getImg(),60+i*100,600);
			Rectangle rect = new Rectangle(60+i*100,600,80,80);
			if(rect.contains(getMx(),getMy())){
				drawHeroInfo(eteam.get(i),false,new Rectangle(0,300,600,100));
			}
		}
		if(eteam.size() < 4){
			//handles adding dudes
			batch.draw(addHeroButton,0,0);
		}
		batch.end();
	}
	public void editMap(){
		//edits the map
		batch.begin();
		batch.draw(mapBG,0,0); //draws the background
		batch.draw(goBack,0,Gdx.graphics.getHeight()-32);
		drawMap();//draws the map
		batch.draw(terrain.get(GRASS).getImg(),482,600);
		batch.draw(terrain.get(TREE).getImg(),482,520);
		batch.draw(moveHand, 482, 0);
		if(selectedTerr != null)
			batch.draw(selectedTerr.getImg(), getMx()-32, getMy()-32);
		else{
			batch.draw(moveHand, getMx()-32, getMy()-32);
			if(selectedHero != null){
				batch.draw(selectedHero.getImg(), getMx()-32, getMy()-32);
			}
		}
		for(int i = 0;i < team.size();i++){
			if(team.get(i) == selectedHero) continue;
			batch.draw(team.get(i).getImg(),team.get(i).getX()*75,team.get(i).getY()*75);
		}
		for(int i = 0;i < eteam.size();i++){
			if(eteam.get(i) == selectedHero) continue;
			batch.draw(eteam.get(i).getImg(),eteam.get(i).getX()*75,eteam.get(i).getY()*75);
		}
		batch.end();
	}
	public void createHero(){
		batch.begin();
		batch.draw(newHeroBG,0,0);
		for(int i = 0;i < heroes.size();i++){
			Rectangle rect = new Rectangle(0,510-i*100,600,100);
			if(mode == NEW_CHARACTER)
				drawHeroSmallInfo(heroes.get(i),true,rect);
			else
				drawHeroSmallInfo(heroes.get(i),false,rect);
		}
		batch.end();
	}
	public void customizeHero(){
		//screen for customizing heroes
		Hero hero;
		if(mode == EDIT_CHARACTER)
			hero = team.get(charToEdit);
		else{
			hero = eteam.get(charToEdit);
		}
		
		batch.begin();
		if(step == 0){
			batch.draw(pickWeapon, 0, 0);
			for(int i = 0;i < usableWeapons.size();i++){
				batch.draw(weaponBG, 0, Gdx.graphics.getHeight()-120-i*25);
				font.draw(batch, usableWeapons.get(i).toString(), 15, Gdx.graphics.getHeight()-100-i*25);
			}
		} else if(step == 1){
			batch.draw(changeStats,0,0);
			font.draw(batch, "Atk: "+hero.getAtk(), 50, 550);
			batch.draw(uparrow,120,550);
			batch.draw(up10arrow,155,550);
			batch.draw(downarrow,120,535);
			batch.draw(down10arrow,155,535);
			font.draw(batch, "Spd: "+hero.getSpd(), 350, 550);
			batch.draw(uparrow,420,550);
			batch.draw(up10arrow,455,550);
			batch.draw(downarrow,420,535);
			batch.draw(down10arrow,455,535);
			font.draw(batch, "Def: "+hero.getDef(), 50, 400);
			batch.draw(uparrow,120,400);
			batch.draw(up10arrow,155,400);
			batch.draw(downarrow,120,385);
			batch.draw(down10arrow,155,385);
			font.draw(batch, "Res: "+hero.getRes(), 350, 400);
			batch.draw(uparrow,420,400);
			batch.draw(up10arrow,455,400);
			batch.draw(downarrow,420,385);
			batch.draw(down10arrow,455,385);
			font.draw(batch, "HP: "+hero.getHP(), 200, 250);
			batch.draw(uparrow,270,250);
			batch.draw(up10arrow,305,250);
			batch.draw(downarrow,270,235);
			batch.draw(down10arrow,305,235);
		} else{
			if(mode == EDIT_CHARACTER){
				mode = EDIT_TEAM;	
			} else{
				mode = EDIT_ENEMY_TEAM;
			}
		}
		batch.end();
	}
	public void drawHeroInfo(Hero hero,boolean ally, Rectangle rect){
		//draws hero info
		if(ally){
			batch.draw(allyBackground,rect.x,rect.y);
		} else{
			batch.draw(enemyBackground,rect.x,rect.y);
		}
		font.draw(batch, hero.toString(), rect.x+15, rect.y+rect.height-20);
		font.draw(batch, "HP " + hero.getHP() + "/" + hero.getMaxHP() + " Atk "+hero.getAtk() + " Spd "+hero.getSpd()+" Def "+hero.getDef()+" Res "+hero.getRes(), rect.x+315, rect.y+rect.height-20);
		font.draw(batch, hero.getWeapon().toString(), rect.x+15, rect.y+rect.height-40);
	}
	public void drawHeroSmallInfo(Hero hero,boolean ally, Rectangle rect){
		//draws hero info
		batch.draw(hero.getImg(), rect.x, rect.y);
		if(ally){
			batch.draw(allyBackground,rect.x+80,rect.y);
		} else{
			batch.draw(enemyBackground,rect.x+80,rect.y);
		}
		font.draw(batch, hero.toString(), rect.x+95, rect.y+rect.height-20);
	}
	public void drawMap(){
		//draws the 6 by 8 map
		for(int i = 0;i < 6;i++){
			for(int j = 0;j < 8;j++){
				batch.draw(map[i][j].getImg(),i*75,(8-j-1)*75);
			}
		}
	}
	public void createUsableWeapons(){
		ArrayList<Hero> cteam = team;
		if(mode == EDIT_ENEMY_TEAM || mode == EDIT_ENEMY_CHARACTER){
			cteam = eteam;
		}
		usableWeapons = new ArrayList<Weapon>(); //makes the usable weapons list
		for(int i = 0;i < weapons.size();i++){
			if(weapons.get(i).getType() == cteam.get(charToEdit).getType()){
				usableWeapons.add(weapons.get(i));
			}
		}
	}
	public float getMx(){
		return Gdx.input.getX();
	}
	public float getMy(){
		return Gdx.graphics.getHeight() - Gdx.input.getY();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(mode == EDIT_MAP){
			Rectangle backRect = new Rectangle(0,Gdx.graphics.getHeight()-32,32,32);
			if(backRect.contains(getMx(), getMy())){
				mode = MAIN_MENU;
			}
			Rectangle grassRect = new Rectangle(482,600,75,75);
			Rectangle treeRect = new Rectangle(482,520,75,75);
			Rectangle moveRect = new Rectangle(482,0,75,75);
			if(grassRect.contains(getMx(),getMy())){
				selectedHero = null;
				selectedTerr = terrain.get(GRASS);
			} else if(treeRect.contains(getMx(),getMy())){
				selectedHero = null;
				selectedTerr = terrain.get(TREE);
			} else if(moveRect.contains(getMx(),getMy())){
				selectedTerr = null;
			}
			Rectangle mapRect = new Rectangle(0,0,450,600);
			if(mapRect.contains(getMx(),getMy())){
				int x = (int)(getMx()/75);
				int y = (int)((600-getMy())/75);
				if(selectedTerr != null){
					map[x][y] = selectedTerr;
				} else{
					for(int i = 0;i < team.size();i++){
						if(team.get(i).getX() == x && team.get(i).getY() == y){
							selectedHero = team.get(i);
						}
					}
					for(int i = 0;i < eteam.size();i++){
						if(eteam.get(i).getX() == x && eteam.get(i).getY() == y){
							selectedHero = eteam.get(i);
						}
					}
				}
			}
		}
		if(mode == EDIT_TEAM){
			Rectangle backRect = new Rectangle(0,Gdx.graphics.getHeight()-32,32,32);
			if(backRect.contains(getMx(), getMy())){
				mode = MAIN_MENU;
			}
			for(int i = 0;i < team.size();i++){
				Rectangle rect = new Rectangle(60+i*100,600,80,80);
				if(rect.contains(getMx(),getMy())){
					if(button == Buttons.LEFT){
						charToEdit = i;
						step = 0;
						createUsableWeapons();
						mode = EDIT_CHARACTER;
					} else if(button == Buttons.RIGHT){
						team.remove(i);
						break;
					}
				}
			}
			if(team.size() < 4){
				//handles adding dudes
				Rectangle addHero = new Rectangle(0,0,600,200); //add hero button
				if(addHero.contains(getMx(),getMy())){
					mode = NEW_CHARACTER;
				}
			}
		}
		if (mode == EDIT_ENEMY_TEAM){
			Rectangle backRect = new Rectangle(0,Gdx.graphics.getHeight()-32,32,32);
			if(backRect.contains(getMx(), getMy())){
				mode = MAIN_MENU;
			}
			for(int i = 0;i < eteam.size();i++){
				Rectangle rect = new Rectangle(60+i*100,600,80,80);
				if(rect.contains(getMx(),getMy())){
					if(button == Buttons.LEFT){
						charToEdit = i;
						step = 0;
						createUsableWeapons();
						mode = EDIT_ENEMY_CHARACTER;
					} else if(button == Buttons.RIGHT){
						eteam.remove(i);
						break;
					}
				}
			}
			if(eteam.size() < 4){
				//handles adding dudes
				Rectangle addHero = new Rectangle(0,0,600,200); //add hero button
				if(addHero.contains(getMx(),getMy())){
					mode = ENEMY_CHARACTER;
				}
			}
		}
		if((mode == EDIT_CHARACTER || mode == EDIT_ENEMY_CHARACTER) && step == 0){
			Hero hero;
			if(mode == EDIT_CHARACTER)
				hero = team.get(charToEdit);
			else
				hero = eteam.get(charToEdit);
			for(int i = 0;i < usableWeapons.size();i++){
				Rectangle currentWpn = new Rectangle(0,Gdx.graphics.getHeight()-120-i*25,600,25);				
				if(currentWpn.contains(getMx(),getMy())){
					hero.setWeapon(usableWeapons.get(i));
					step++;
					break;
				}
			}
		}
		if((mode == EDIT_CHARACTER || mode == EDIT_ENEMY_CHARACTER) && step == 1){
			Hero hero;
			if(mode == EDIT_ENEMY_CHARACTER){
				hero = eteam.get(charToEdit);
			} else{
				hero = team.get(charToEdit);
			}
			Rectangle atkup = new Rectangle(120,550,32,16);
			Rectangle atkup10 = new Rectangle(155,550,32,16);
			Rectangle atkdown = new Rectangle(120, 535, 32, 16);
			Rectangle atkdown10 = new Rectangle(155, 535, 32, 16);
			Rectangle spdup = new Rectangle(420,550,32,16);
			Rectangle spdup10 = new Rectangle(455,550,32,16);
			Rectangle spddown = new Rectangle(420,535,32,16);
			Rectangle spddown10 = new Rectangle(455,535,32,16);
			Rectangle defup = new Rectangle(120,400,32,16);
			Rectangle defup10 = new Rectangle(155,400,32,16);
			Rectangle defdown = new Rectangle(120, 385, 32, 16);
			Rectangle defdown10 = new Rectangle(155, 385, 32, 16);
			Rectangle resup = new Rectangle(420,400,32,16);
			Rectangle resup10 = new Rectangle(455,400,32,16);
			Rectangle resdown = new Rectangle(420,385,32,16);
			Rectangle resdown10 = new Rectangle(455,385,32,16);
			Rectangle hpup = new Rectangle(270,250,32,16);
			Rectangle hpup10 = new Rectangle(305,250,32,16);
			Rectangle hpdown = new Rectangle(270,235,32,16);
			Rectangle hpdown10 = new Rectangle(305,235,32,16);
			if(atkup.contains(getMx(),getMy())){
				hero.setAtk(hero.getBAtk()+1);
			} else if(atkup10.contains(getMx(),getMy())){
				hero.setAtk(hero.getBAtk()+10);
			} else if(atkdown.contains(getMx(),getMy())){
				hero.setAtk(hero.getBAtk()-1);
			} else if(atkdown10.contains(getMx(),getMy())){
				hero.setAtk(hero.getBAtk()-10);
			}
			if(spdup.contains(getMx(),getMy())){
				hero.setSpd(hero.getBSpd()+1);
			} else if(spdup10.contains(getMx(),getMy())){
				hero.setSpd(hero.getBSpd()+10);
			} else if(spddown.contains(getMx(),getMy())){
				hero.setSpd(hero.getBSpd()-1);
			} else if(spddown10.contains(getMx(),getMy())){
				hero.setSpd(hero.getBSpd()-10);
			}
			if(defup.contains(getMx(),getMy())){
				hero.setDef(hero.getBDef()+1);
			} else if(defup10.contains(getMx(),getMy())){
				hero.setDef(hero.getBDef()+10);
			} else if(defdown.contains(getMx(),getMy())){
				hero.setDef(hero.getBDef()-1);
			} else if(defdown10.contains(getMx(),getMy())){
				hero.setDef(hero.getBDef()-10);
			}
			if(resup.contains(getMx(),getMy())){
				hero.setRes(hero.getBRes()+1);
			} else if(resup10.contains(getMx(),getMy())){
				hero.setRes(hero.getBRes()+10);
			} else if(resdown.contains(getMx(),getMy())){
				hero.setRes(hero.getBRes()-1);
			} else if(resdown10.contains(getMx(),getMy())){
				hero.setRes(hero.getBRes()-10);
			}
			if(hpup.contains(getMx(),getMy())){
				hero.setHP(hero.getMaxHP()+1);
				hero.setMaxHp(hero.getMaxHP()+1);
			} else if(hpup10.contains(getMx(),getMy())){
				hero.setHP(hero.getMaxHP()+10);
				hero.setMaxHp(hero.getMaxHP()+10);
			} else if(hpdown.contains(getMx(),getMy())){
				hero.setHP(hero.getMaxHP()-1);
				hero.setMaxHp(hero.getMaxHP()-1);
			} else if(hpdown10.contains(getMx(),getMy())){
				hero.setHP(hero.getMaxHP()-10);
				hero.setMaxHp(hero.getMaxHP()-10);
			}
			Rectangle submit = new Rectangle(0,0,600,100);
			if(submit.contains(getMx(), getMy())){
				step++;
			}
		}
		if(mode == NEW_CHARACTER || mode == ENEMY_CHARACTER){
			ArrayList<Hero> cteam = new ArrayList<Hero>();
			if(mode == NEW_CHARACTER) cteam = team;
			if(mode == ENEMY_CHARACTER) cteam = eteam;
			int nextMode = EDIT_CHARACTER;
			if(mode == ENEMY_CHARACTER) nextMode = EDIT_ENEMY_CHARACTER;
			for(int i = 0;i < heroes.size();i++){
				Rectangle rect = new Rectangle(0,510-i*100,600,100);
				if(rect.contains(getMx(),getMy())){
					cteam.add(heroes.get(i).clone()); //finds character to add
					mode = nextMode;
					break;
				}
			}
			if(mode == EDIT_CHARACTER){
				charToEdit = team.size()-1;
				step = 0;
				createUsableWeapons();
			}
			if(mode == EDIT_ENEMY_CHARACTER){
				charToEdit = eteam.size()-1;
				step = 0;
				createUsableWeapons();
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
