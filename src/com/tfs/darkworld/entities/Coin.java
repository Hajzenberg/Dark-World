package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.tfs.darkworld.res.CommonRasters;

public class Coin extends GameEntity {
	
	private static final int ACTION_IDLE = 0;
	
	private ArrayList<BufferedImage[]> sprites;
	
	private int[] numOfFrames = { 4 };
	private int[] frameWidths = { 60 };
	private int[] frameLengths = { 60 };
	private int[] frameIntervals = { 10 };
	
	private Animation animation;
	private boolean isCollected;
	
	public Coin(int x, int y, double dX, int frameInterval) {
		super(60, 60, 0);
		
		try {
			int count = 0;
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < numOfFrames.length; i++) {
				BufferedImage[] bi = new BufferedImage[numOfFrames[i]];

				for (int j = 0; j < numOfFrames[i]; j++) {
					bi[j] = CommonRasters.getCoinSheet().getSubimage(j * frameWidths[i], count, frameWidths[i], frameLengths[i])
							.getSubimage(0, 0, frameWidths[i], frameLengths[i]);
				}
				sprites.add(bi);
				count += frameLengths[i];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		
		setAnimation(ACTION_IDLE);
		
		mX = x;
		mY = y;
		mDX = dX;
		
		frameIntervals[0] = (frameInterval == 0) ? 10 : frameInterval;
		
		intersectionBody.setLeftOffset(0);
		intersectionBody.setUpperOffset(0);
		intersectionBody.setHeight(60);
		intersectionBody.setWidth(60);
	}
	
	@Override
	public void update() {
		animation.update();
		mX -= mDX;
		intersectionBody.updateIntersectionBody(mX, mY);
	}
	
	@Override
	public void render(Graphics2D g, int sw, int sh) {
		super.render(g, sw, sh);
		g.drawImage(animation.getImage(), (int) mX, (int) mY, null);
	}

	@Override
	public void intersect(GameEntity ge) {
		
	}
	
	private void setAnimation(int i) {
		animation.setFrames(sprites.get(i));
		animation.setFrameInterval(frameIntervals[i]);
		mWidth = frameWidths[i];
		mHeight = frameLengths[i];
		// System.out.println("SET ANIMATION " + mWidth + " " + mHeight);
	}
	
	public void setCollected(boolean b){
		isCollected = b;
	}
	
	public boolean isCollected(){
		return isCollected;
	}

	public Animation getAnimation() {
		return animation;
	}
	
	

}
