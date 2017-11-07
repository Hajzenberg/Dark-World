package com.tfs.darkworld.labs;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class DoomTransitionTester extends GameFrame{

	private static int COLUMNS = 40;
	
	private int columnWidth;
	
	private BufferedImage logo;
	private BufferedImage background;
	
	private ArrayList<BufferedImage> columns;
	private int[] speeds;
	
	public DoomTransitionTester() {
		super("Doom", 800, 600);
		
		columnWidth = 800 / COLUMNS;
		
		logo = Util.loadImage("design/doom_logo.jpg");
		background = Util.loadImage("design/doom_bg.jpg");
		
		columns = new ArrayList<>();
		speeds = new int[30];
		
		for (int i = 0; i < COLUMNS; i++){
			columns.add(logo.getSubimage(i*columnWidth, 0, columnWidth, 600));
		}
		
		setUpdateRate(60);
		startThread();
	}
	
	public static void main(String[] args)
	{
		new DoomTransitionTester().initGameWindow();
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
		//g.drawImage(logo, 0, 0, null);
		
		for (int i = 0; i < COLUMNS; i++){
			g.drawImage(columns.get(i), i*columnWidth, 0, null);
			g.drawRect(i*columnWidth, 0, columnWidth, 600);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
