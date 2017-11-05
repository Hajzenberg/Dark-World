package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import com.tfs.darkworld.res.CommonRasters;

public class SkyTile extends GameEntity {

	private static float MAX_OFFSET = 1;

	private double scaleSpeed;

	// Alfa offset vrednosti za nebo
	private float deltaAlphaOffset = 0.001f;
	private float alphaOffset = 1f;

	public SkyTile(double scaleSpeed, double dX) {
		super(0, 0, 0);
		this.scaleSpeed = scaleSpeed;
		mDX = dX;
	}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		float[] scales = { 1f, 1f, 1f, alphaOffset };
		float[] offsets = new float[4];
		RescaleOp rop = new RescaleOp(scales, offsets, null);

		float[] scales1 = { 1f, 1f, 1f, MAX_OFFSET - alphaOffset };
		float[] offsets1 = new float[4];
		RescaleOp rop1 = new RescaleOp(scales, offsets, null);

		// g.drawImage(CommonRasters.getLightSky(), rop, (int) mX, (int) mY);
		// g.drawImage(CommonRasters.getDarkSky(), rop1, (int)mX, (int)mY);

		g.drawImage(CommonRasters.getLightSky(), (int) mX, (int) mY, null);
		//g.drawImage(CommonRasters.getDarkSky(), (int) mX, (int) mY, null);
	}

	@Override
	public void update() {
		mX -= scaleSpeed * mDX;
		alphaOffset += deltaAlphaOffset;

		if (alphaOffset <= 0 || alphaOffset >= 1) {
			deltaAlphaOffset = -deltaAlphaOffset;
		}
	}

}
