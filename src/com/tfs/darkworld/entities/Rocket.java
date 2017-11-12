package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import com.tfs.darkworld.res.CommonRasters;

import jaco.mp3.player.MP3Player;

public class Rocket extends GameEntity{
	
private static final int ACTION_IDLE = 0;
	
	private ArrayList<BufferedImage[]> sprites;
	
	private int[] numOfFrames = { 3 };
	private int[] frameWidths = { 56 };
	private int[] frameLengths = { 22 };
	private int[] frameIntervals = { 6 };
	
	private Animation animation;
	private boolean isAlive;
	private double distance;
	
	private MP3Player mp3Player;
	private RocketSoundThread rocketSoundThread;

	public Rocket(double x, double y, double dX) {
		super(56, 22, 0);
		
		try {
			int count = 0;
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < numOfFrames.length; i++) {
				BufferedImage[] bi = new BufferedImage[numOfFrames[i]];

				for (int j = 0; j < numOfFrames[i]; j++) {
					bi[j] = CommonRasters.getRocketSheet().getSubimage(j * frameWidths[i], count, frameWidths[i], frameLengths[i])
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
		distance = 100;
		isAlive = true;
		
		intersectionBody.setLeftOffset(0);
		intersectionBody.setUpperOffset(0);
		intersectionBody.setHeight(22);
		intersectionBody.setWidth(56);
		
		mp3Player = new MP3Player(new File("music/explosion.mp3"));
		rocketSoundThread = new RocketSoundThread(mp3Player);
	}
	
	@Override
		public void update() {
			animation.update();
			mX += mDX;
			intersectionBody.updateIntersectionBody(mX, mY);
			if (--distance <= 0){
				isAlive = false;
				rocketSoundThread.start();
				rocketSoundThread = new RocketSoundThread(mp3Player);
			}
		}
	
	@Override
		public void render(Graphics2D g, int sw, int sh) {
			super.render(g, sw, sh);
			g.drawImage(animation.getImage(), (int) mX, (int) mY, null);
		}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub
		
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
	
	private static class RocketSoundThread extends Thread{
		private MP3Player mp3Player;
		
		public RocketSoundThread(MP3Player mp3Player) {
			super();
			this.mp3Player = mp3Player;
		}

		@Override
		public void run() {
			super.run();
			mp3Player.play();
		}
	}

}
