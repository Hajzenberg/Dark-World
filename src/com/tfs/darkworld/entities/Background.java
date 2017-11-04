package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.CommonRasters;

public class Background extends GameEntity {

	private static int SKY_OFFSET = 100;
	
	private Rectangle mGroundRect;
	
	private double mScale;

	public Background(int sw, int sh) {
		super(0, 0, 0, 0, 0, 0);
		mGroundRect = new Rectangle(0, sh - 100, sw, sh);
		mScale = 0.2;
	}

	@Override
	public void update() {
		mX--;
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		
		g.drawImage(CommonRasters.getLightBackground(),  (int)(mScale*mX), (int)(mY), null);
		g.drawImage(CommonRasters.getMountainBackground(),  (int)mX, (int)mY + SKY_OFFSET, null);
		//g.setColor(Colors.LIGHT_GREEN);
		//g.fill(mSkyRect);
		//g.draw(mSkyRect);
		g.setColor(Colors.BROWN);
		g.fill(mGroundRect);	
	}

	public Rectangle getGroundRect() {
		return mGroundRect;
	}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub
		
	}
}
