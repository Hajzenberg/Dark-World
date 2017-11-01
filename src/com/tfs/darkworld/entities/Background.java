package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.tfs.darkworld.res.Colors;

public class Background extends GameEntity {

	Rectangle mGroundRect;
	Rectangle mSkyRect;

	public Background(int sw, int sh) {
		super(0, 0, 0);
		mSkyRect = new Rectangle(0, 0, sw, sh);
		mGroundRect = new Rectangle(0, sh - 100, sw, sh);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.setColor(Colors.LIGHT_GREEN);
		g.fill(mSkyRect);
		g.draw(mSkyRect);
		g.setColor(Colors.BROWN);
		g.fill(mGroundRect);

	}

}
