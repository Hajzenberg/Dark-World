package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.tfs.darkworld.res.CommonRasters;

import jaco.mp3.player.h;

public class MenuHand extends GameEntity {

	private BufferedImage handImage;
	private double handSpeedY;
	private double handSpeedX;

	public MenuHand() {
		super(0, 0, 0);

		handImage = CommonRasters.getHandImage();
		mWidth = handImage.getWidth();
		mHeight = handImage.getHeight();
		mX = 603;
		mY = 400;
		
		handSpeedY = 1.7;
		handSpeedX = 1.2;
		
		mDY = handSpeedY;
		mDX = handSpeedX;
	}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		mY += mDY;
		mX += mDX;
		//System.out.println(mDY);
	}
	
	public void onUpdate(int x, int y){
		
		if (y < (int)mY + (int)(mHeight/2 - 20)) {
			mDY = -handSpeedY;
			//System.out.println("mis iznad");
		} else if (y > (int)mY + (int)(mHeight/2 - 20)){
			mDY = handSpeedY;
			//System.out.println("mis ispod");
		} else {
			//System.out.println("AAAAAAAAAAAAAAAAAAAA");
			mDY = 0;
		}
		
		if (x < 400) {
			mDX = -handSpeedX;
			//System.out.println("mis levo");
		} else if (x > 400){
			mDX = handSpeedX;
			//System.out.println("mis desno");
		} else {
			//System.out.println("AAAAAAAAAAAAAAAAAAAA");
			mDX = 0;
		}
		
		if (mY > 490){
			mY = 490;
		}
		
		if (mY < 290){
			mY = 290;
		}
		
		if (mX < 603){
			mX = 603;
		}
		
		if (mX > 633){
			mX = 633;
		}
		
		update();
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		super.render(g, sw, sh);
		g.drawImage(handImage, (int) mX, (int) mY, null);
	}

}
