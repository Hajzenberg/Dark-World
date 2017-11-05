package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BackgroundTile extends GameEntity {

	private BufferedImage image;
	private double scaleSpeed;
	private int topOffset;

	public BackgroundTile(double width, double height, BufferedImage image, double scaleSpeed, int offset, double dx) {
		super(width, height, 0);

		this.image = image;
		this.scaleSpeed = scaleSpeed;
		this.topOffset = offset;
		this.mDX = dx;

	}

	@Override
	public void intersect(GameEntity ge) {

	}

	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void update() {
		// super.update();
		mX -= scaleSpeed * mDX;
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		// super.render(g, sw, sh);
		g.drawImage(image, (int) mX, (int) mY + topOffset, null);
	}

}
