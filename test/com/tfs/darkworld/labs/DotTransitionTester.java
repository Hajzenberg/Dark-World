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

public class DotTransitionTester extends GameFrame {

	

	private BufferedImage logo;
	private WritableRaster raster;
	private int pointCount;
	private int[] rgb = {201, 0, 0};
	private Random random;

	/*
	 * Sadrzi podslike slike na kojoj se vrsi tranzicija Ima ih onoliko koliko
	 * je definisano u parametru COLUMNS
	 */
	private ArrayList<Point> points;

	public DotTransitionTester() {
		super("Doom", 800, 600);

		logo = Util.loadImage("design/doom_logo.jpg");
		
		pointCount = logo.getWidth() * logo.getHeight();
		
		points = new ArrayList<>();

		for (int i = 0; i < logo.getWidth(); i++){
			for (int j = 0; j < logo.getHeight(); j++){
				points.add(new Point(i, j));
			}
		}
		random = new Random();

		setUpdateRate(60);
		startThread();
	}

	public static void main(String[] args) {
		new DotTransitionTester().initGameWindow();
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
		g.drawImage(logo, 0, 0, null);
	}

	@Override
	public void update() {
		
		raster = logo.getRaster();
		
		for (int i = 0; i < 48000; i++){
			
			Point p = points.get(random.nextInt(pointCount));
			pointCount--;
			points.remove(p);
			
			
			raster.setPixel(p.x, p.y, rgb);
		}
		logo = Util.rasterToImage(raster);
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

}
