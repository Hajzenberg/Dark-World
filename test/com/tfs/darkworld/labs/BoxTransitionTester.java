package com.tfs.darkworld.labs;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Random;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class BoxTransitionTester extends GameFrame {

	private static final int BOX_SIDE_LENGTH = 40;

	private ArrayList<Box> boxes;

	private BufferedImage logo;
	private BufferedImage background;
	private Random random;

	public BoxTransitionTester() {
		super("Doom", 800, 600);

		logo = Util.loadImage("design/doom_logo.jpg");
		background = Util.loadImage("design/doom_bg.jpg");

		random = new Random();
		boxes = new ArrayList<>();

		for (int i = 0; i < logo.getHeight() / BOX_SIDE_LENGTH; i++) {
			for (int j = 0; j < logo.getWidth() / BOX_SIDE_LENGTH; j++) {
				boxes.add(new Box(j * BOX_SIDE_LENGTH, i * BOX_SIDE_LENGTH,
						logo.getSubimage(j * BOX_SIDE_LENGTH, i * BOX_SIDE_LENGTH, BOX_SIDE_LENGTH, BOX_SIDE_LENGTH)));
			}
		}

		setUpdateRate(60);
		startThread();
	}

	public static void main(String[] args) {
		new BoxTransitionTester().initGameWindow();
	}

	@Override
	public void handleWindowInit() {

	}

	@Override
	public void handleWindowDestroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.drawImage(background, 0, 0, null);
		
		for (int i = 0; i < boxes.size(); i++) {
			g.drawImage(boxes.get(i).image, (int)boxes.get(i).x, (int)boxes.get(i).y, null);
		}
	}

	@Override
	public void update() {
		
		if (boxes.size() == 1){
			return;
		}
		
		boxes.remove(random.nextInt(boxes.size()-1));
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseMove(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyDown(int keyCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub

	}

	class Box {

		public Box(double x, double y, BufferedImage image) {
			this.x = x;
			this.y = y;
			this.image = image;
		}

		double x;
		double y;
		BufferedImage image;
	}

}
