package com.tfs.darkworld.labs;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.tfs.darkworld.labs.BoxParticleManager.Particle;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class ParticleBoxTransitionTester extends GameFrame {

	private static final int BOX_SIDE_LENGTH = 40;
	private static final int PARTICLE_MAX = 300;
	
	private BoxParticleManager particleManager;

	private BufferedImage logo;
	private BufferedImage background;
	
	private boolean hasStarted;

	public ParticleBoxTransitionTester() {
		super("Doom", 800, 600);

		logo = Util.loadImage("design/doom_logo.jpg");
		background = Util.loadImage("design/doom_bg.jpg");
		particleManager = new BoxParticleManager();
		
		int c = 0;

		for (int i = 0; i < logo.getHeight() / BOX_SIDE_LENGTH; i++) {
			for (int j = 0; j < logo.getWidth() / BOX_SIDE_LENGTH; j++) {
				
				BufferedImage subImage = logo.getSubimage(j * BOX_SIDE_LENGTH, i * BOX_SIDE_LENGTH, BOX_SIDE_LENGTH, BOX_SIDE_LENGTH);
				
				Particle p = new Particle();
				p.image = subImage;
				particleManager.getParts()[c++] =  p;
			}
		}
		
		hasStarted = false;

		setUpdateRate(60);
		startThread();
	}

	public static void main(String[] args) {
		new ParticleBoxTransitionTester().initGameWindow();
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
		if (hasStarted){
			particleManager.onRender((Graphics2D)g.create());
		} else {
			g.drawImage(logo, 0, 0, null);
		}
		
	}

	@Override
	public void update() {
		particleManager.onUpdate();

	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		if (!hasStarted){
			particleManager.showMeTheLove(400, 300, PARTICLE_MAX);
		}
		hasStarted = true;
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
