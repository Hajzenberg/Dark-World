package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.tfs.darkworld.res.Colors;

public class Background extends GameEntity {

	private Rectangle mGroundRect;
	private Rectangle mSkyRect;
	
	private BufferedImage mForeground;
	private BufferedImage mBackGround;
	
	private double mScale;

	public Background(int sw, int sh) {
		super(0, 0, 0);
		mSkyRect = new Rectangle(0, 0, sw, sh);
		mGroundRect = new Rectangle(0, sh - 100, sw, sh);
		mScale = 0.6;
	}

	@Override
	public void update() {
		//x?
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.setColor(Colors.LIGHT_GREEN);
		g.fill(mSkyRect);
		g.draw(mSkyRect);
		g.setColor(Colors.BROWN);
		g.fill(mGroundRect);
		
		g.drawImage(mBackGround,  (int)(mScale*mX), (int)(mY), null);
		g.drawImage(mForeground,  (int)(mX), (int)(mY), null);
	}

	public Rectangle getGroundRect() {
		return mGroundRect;
	}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub
		
	}
	
	//set x iz gamestate-a

}
