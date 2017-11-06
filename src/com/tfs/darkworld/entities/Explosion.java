package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.tfs.darkworld.res.CommonRasters;

public class Explosion extends GameEntity {
	
	private static final int ACTION_IDLE = 0;

	private ArrayList<BufferedImage[]> sprites;

	private int[] numOfFrames = { 7 };
	private int[] frameWidths = { 118 };
	private int[] frameLengths = { 123 };
	private int[] frameIntervals = { 5 };

	private Animation animation;
	private boolean isAlive;

	public Explosion(double x, double y, double dX) {
		super(118, 123, 0);

		try {
			int count = 0;
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < numOfFrames.length; i++) {
				BufferedImage[] bi = new BufferedImage[numOfFrames[i]];

				for (int j = 0; j < numOfFrames[i]; j++) {
					bi[j] = CommonRasters.getExplosionSheet()
							.getSubimage(j * frameWidths[i], count, frameWidths[i], frameLengths[i])
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
		isAlive = true;

		intersectionBody.setLeftOffset(0);
		intersectionBody.setUpperOffset(0);
		intersectionBody.setHeight(123);
		intersectionBody.setWidth(118);
	}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void update() {
		animation.update();
		mX -= mDX;
		intersectionBody.updateIntersectionBody(mX, mY);
		if (animation.hasPlayedOnce()){
			isAlive = false;
		}
		
	}
	
	@Override
	public void render(Graphics2D g, int sw, int sh) {
		super.render(g, sw, sh);
		g.drawImage(animation.getImage(), (int) mX, (int) mY, null);
		
	}
	
	private void setAnimation(int i) {
		animation.setFrames(sprites.get(i));
		animation.setFrameInterval(frameIntervals[i]);
		mWidth = frameWidths[i];
		mHeight = frameLengths[i];
		// System.out.println("SET ANIMATION " + mWidth + " " + mHeight);
	}
	
	public boolean isAlive(){
		return isAlive;
	}

}
