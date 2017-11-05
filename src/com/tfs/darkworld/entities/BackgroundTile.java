package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BackgroundTile extends GameEntity {

	private BufferedImage mImage;

	public BackgroundTile(double width, double height, BufferedImage image) {
		super(width, height, 0);
		
		mImage = image;
		
	}

	@Override
	public void intersect(GameEntity ge) {
		
	}

	public BufferedImage getImage() {
		return mImage;
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(Graphics2D g, int sw, int sh) {
		super.render(g, sw, sh);
	}
	
	

}
