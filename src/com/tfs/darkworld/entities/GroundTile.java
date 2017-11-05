package com.tfs.darkworld.entities;

import java.awt.Graphics2D;

import com.tfs.darkworld.res.CommonRasters;

public class GroundTile extends Box {
	public static final double TILE_WIDTH = 320;
	public static final double TILE_HEIGHT = 320;
	
	public GroundTile() {
		super(TILE_WIDTH, TILE_HEIGHT);
		intersectionBody.setLeftOffset(0);
		intersectionBody.setUpperOffset(0);
		intersectionBody.setHeight(TILE_HEIGHT);
		intersectionBody.setWidth(TILE_WIDTH);
	}


	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.drawImage(CommonRasters.getGroundTile(),(int) mX,(int) mY,(int) mWidth, (int)mHeight, null);
		super.render(g, sw, sh);
	}


	@Override
	public void intersect(GameEntity ge) {
		
	}

}
