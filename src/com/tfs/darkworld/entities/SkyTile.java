package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.tfs.darkworld.res.CommonRasters;

public class SkyTile extends GameEntity {

	private double scaleSpeed;

	// Alfa offset vrednosti za nebo
	private float deltaAlphaOffset = 0.001f;
	private float alphaOffset = 1f;

	public SkyTile(double scaleSpeed) {
		super(0, 0, 0);
		this.scaleSpeed = scaleSpeed;
	}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		// float[] scales = { 1f, 1f, 1f, mAlphaOffset };
		// float[] offsets = new float[4];
		// RescaleOp rop = new RescaleOp(scales, offsets, null);

		g.drawImage(CommonRasters.getLightSky(), (int) mX, (int) mY, null);
	}

	@Override
	public void update() {
		mX -= (scaleSpeed * mDX);
	}

}
