package com.tfs.darkworld.entities;

import java.awt.Graphics2D;

import com.tfs.darkworld.res.CommonRasters;

public class Ground extends Box {
	public static final double TILE_WIDTH = 320;
	public static final double TILE_HEIGHT = 320;
	
	public Ground() {
		super(TILE_WIDTH, TILE_HEIGHT);
	}


	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.drawImage(CommonRasters.getGroundTile(),(int) mX,(int) mY,(int) mWidth, (int)mHeight, null);
	}

//
//	@Override
//	public void update() {
//		mX += mDX;
//		mY += mDY;
//	}


	@Override
	public void intersect(GameEntity ge) {
		
	}

}
