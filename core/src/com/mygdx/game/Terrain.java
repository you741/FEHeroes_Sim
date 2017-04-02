package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Terrain {
	private Texture img;
	private boolean hinder;
	private boolean impassable;
	private boolean flyres;
	
	public Terrain(Texture img, boolean hinder, boolean impassable, boolean flyres){
		this.img = img;
		this.hinder = hinder;
		this.impassable = impassable;
		this.flyres = flyres;
	}
	
	public Texture getImg(){
		return img;
	}
	public boolean isHinder(){
		return hinder;
	}
	public boolean isImpassable(){
		return impassable;
	}
	public boolean isFlyingResistant(){
		return flyres;
	}
}
