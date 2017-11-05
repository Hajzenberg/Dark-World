package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.image.RescaleOp;
import java.util.ArrayList;

import com.tfs.darkworld.res.CommonRasters;

public class Background extends GameEntity {

	private static int SKY_OFFSET = 100;

	// Array koji cuva tileove
	private ArrayList<BackgroundTile> mForestTileArray;
	private ArrayList<BackgroundTile> mMountainTileArray;

	private float mDeltaAlphaOffset = 0.001f;
	private float mAlphaOffset = 1f;

	private double mScale;

	public Background(int sw, int sh) {
		super(0, 0, 0);

		mForestTileArray = new ArrayList<>();
		mMountainTileArray = new ArrayList<>();

		mMountainTileArray.add(new BackgroundTile(CommonRasters.getMountainBackground().getWidth(),
				CommonRasters.getMountainBackground().getHeight(), CommonRasters.getMountainBackground()));
		
		mForestTileArray.add(new BackgroundTile(CommonRasters.getForestBackground().getWidth(),
				CommonRasters.getForestBackground().getHeight(), CommonRasters.getForestBackground()));

		mScale = 0.2;
	}

	@Override
	public void update() {
		mX -= 0.4;

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

		g.drawImage(CommonRasters.getLightSky(), (int) (mScale * mX), (int) (mY), null);
		g.drawImage(CommonRasters.getMountainBackground(), rop, (int) mX % 1948, (int) mY + SKY_OFFSET);
	}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub
	}
}
