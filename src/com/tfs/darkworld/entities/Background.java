package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;

import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.CommonRasters;

import rafgfxlib.Util;

public class Background extends GameEntity {

	private static int SKY_OFFSET = 100;

	private Rectangle mGroundRect;
	private float mDeltaAlphaOffset = 0.001f;
	private float mAlphaOffset = 1f;

	private double mScale;

	public Background(int sw, int sh) {
		super(0, 0, 0, 0, 0, 0);
		mGroundRect = new Rectangle(0, sh - 100, sw, sh);
		mScale = 0.2;
	}

	@Override
	public void update() {
		mX--;

		if (mAlphaOffset >= 1 || mAlphaOffset <= 0) {
			mDeltaAlphaOffset = -1f * mDeltaAlphaOffset;
		}

		mAlphaOffset = mAlphaOffset + mDeltaAlphaOffset;
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		
		float[] scales = { 1f, 1f, 1f, mAlphaOffset };
		float[] offsets = new float[4];
		RescaleOp rop = new RescaleOp(scales, offsets, null);
		
		g.drawImage(CommonRasters.getLightBackground(), (int)(mScale*mX), (int)(mY), null);
		g.drawImage(CommonRasters.getMountainBackground(), rop, (int)mX%1948, (int)mY + SKY_OFFSET);
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
