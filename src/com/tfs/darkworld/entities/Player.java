package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.tfs.darkworld.res.Colors;

public class Player extends GameEntity {

	private Rectangle mPlayerRect;

	public Player(int sw, int sh) {
		super (50, sh - 200, 3);
		mPlayerRect = new Rectangle(mX, mY, 50, 100);
	}

	@Override
	public void update() {
		System.out.println("U player update "+mX+ " "+mY);
		mPlayerRect.x = mX;
		mPlayerRect.y = mY;

	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.setColor(Colors.POMEGRANATE);
		g.fill(mPlayerRect);

	}

}
