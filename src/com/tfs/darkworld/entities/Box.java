package com.tfs.darkworld.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.tfs.darkworld.res.Colors;

public class Box extends GameEntity {
	
	private Rectangle box;
	private static final Color boxColor = Colors.MIDNIGHT_BLUE;
	
	public Box(double x, double y, double width, double height, double speed) {
		super(x, y, width, height, speed, 0);
		box = new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.setColor(boxColor);
		g.draw(box);
	}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub
		
	}

}
