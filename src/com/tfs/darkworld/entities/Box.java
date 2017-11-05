package com.tfs.darkworld.entities;

import java.awt.Graphics2D;

public abstract class Box extends GameEntity {

	public Box(double width, double height) {
		super(width, height, 0);
		intersectionBody.setLeftOffset(0);
		intersectionBody.setUpperOffset(0);
		intersectionBody.setHeight(height);
		intersectionBody.setWidth(width);
	}
}
