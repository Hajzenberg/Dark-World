package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import com.tfs.darkworld.res.Colors;

public class Player extends Character {

	//TODO Da li su operacije sada sporije????
	private Rectangle2D mPlayerRect;

	public Player(int sw, int sh) {
		super(50, 100, 50, 100, 3, 3, 3); //y = sh -200
		mPlayerRect = new Rectangle2D.Double(mX, mY, mWidth, mHeight);
		mMaxdY = 6;
		mGravity = 0.3;
		mFalling = true;
		
		//System.out.println(" construct pl "+mX + " "+mY);
	}

	@Override
	public void update() {
		//System.out.println(" update pl "+mX + " "+mY + " " + mWidth + " "+mHeight);
		fall();
		mPlayerRect.setRect(mX, mY, mWidth, mHeight);
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		//System.out.println(" render pl "+mX + " "+mY + " " + mWidth + " "+mHeight);
		g.setColor(Colors.POMEGRANATE);
		g.fill(mPlayerRect);
	}

	public Rectangle2D getPlayerRect() {
		return mPlayerRect;
	}

	
	//TODO Izmestiti ovo najbolje u update, preskociti celu ovu logiku
	//update primi keyevent i tamo direkt radi na rectu, problem
	//je sto ce u tom slucaju morati da se menja GameEntity
	//Razmisli
	public void move(int keyCode) {

		if (keyCode == KeyEvent.VK_LEFT) {
			mX -= mDX;
			//System.out.println("Pritisnuto levo");
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			mX += mDX;
			//System.out.println("Pritisnuto desno");
		}
		
		
	}

	public void fall() {
		if (mFalling) {
			//System.out.println("POZVANO");
			mDY+=mGravity;
			mY+=mDY;
		}
	}

}
