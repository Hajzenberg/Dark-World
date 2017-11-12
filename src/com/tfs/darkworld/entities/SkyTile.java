package com.tfs.darkworld.entities;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import com.tfs.darkworld.res.CommonRasters;

public class SkyTile extends GameEntity {

	private static double MAX_OFFSET = 0.95;

	private double scaleSpeed;

	// Alfa offset vrednosti za nebo
	private double deltaAlphaOffset = - 0.001;
	private double alphaOffset = 0.95;

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
		
		AlphaComposite c = (AlphaComposite) g.getComposite();
		
		
		
		AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (MAX_OFFSET - alphaOffset));
		g.setComposite(composite);
		
		g.drawImage(CommonRasters.getDarkSky(), (int) mX, (int) mY, null);
		
		composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alphaOffset);
		g.setComposite(composite);
		

		g.drawImage(CommonRasters.getLightSky(), (int) mX, (int) mY, null);
		
		
		g.setComposite(c);
	}

	@Override
	public void update() {
		mX -= scaleSpeed * mDX;
		alphaOffset += deltaAlphaOffset;

		if (alphaOffset <= 0.05 || alphaOffset >= 0.95) {
			deltaAlphaOffset = -deltaAlphaOffset;
		}
	}

}
