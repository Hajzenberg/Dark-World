package com.tfs.darkworld.entities;

import java.awt.Graphics2D;

import com.tfs.darkworld.res.CommonRasters;

public class Spikes extends Box{

	public static final int SPIKES_WIDTH = 320;
	public static final int SPIKES_HEIGHT = 60;
	
	private Ground ground;
	
	
	public Spikes() {
		super(SPIKES_WIDTH, SPIKES_HEIGHT+Ground.TILE_HEIGHT);
		ground = new Ground();
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.drawImage(CommonRasters.getSpikes(),(int) mX,(int) mY,(int)SPIKES_WIDTH,(int)SPIKES_HEIGHT, null);
		ground.render(g, sw, sh);
	}
	
	@Override
	public void update() {
		super.update();
		ground.update();
	}
	
	@Override
	public void setDX(double mDX) {
		super.setDX(mDX);
		ground.setDX(mDX);
	}
	
	@Override
	public void setDY(double mDY) {
		super.setDY(mDY);
		ground.setDY(mDY);
	}

	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		ground.setPosition(x, y+SPIKES_HEIGHT);
	}
	
	@Override
	public void intersect(GameEntity ge) {}

}
