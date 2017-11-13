package com.tfs.darkworld.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.MenuState.Particle;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class AboutState extends GameState{
	
	private Fonts mFonts;
	private static final int PARTICLE_MAX = 100;
	
	private AffineTransform old;
	private float imgMaxoX = 100;
	private float imgDjoleX = 100;
	private float imgDrazaX = 100;
	private float imgMaxoX_OFF = 0.6f;
	private float imgDrazaX_OFF = 0.6f;
	private float imgDjoleX_OFF = 0.6f;
	private String Djole;
	private String Draza;
	private String Maxo;
	
	private Random random;
	public AboutState(GameHost host) {
		super(host);
		mFonts = new Fonts();
		random = new Random();
		Djole = Strings.DJOLE;
		Draza = Strings.DRAZA;
		Maxo = Strings.MAXO;
	}

	@Override
	public boolean handleWindowClose() {
		return true;
	}

	@Override
	public String getName() {
		return Strings.ABOUT;
	}

	@Override
	public void resumeState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suspendState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(CommonRasters.getMenuBackgroundImg(), 0, 0,null);
		old = g.getTransform();
		fillOvalImg(g, CommonRasters.getDjoleImg(), imgDjoleX, 50);
		fillOvalImg(g, CommonRasters.getDrazaImg(), imgDrazaX, 250);
		fillOvalImg(g, CommonRasters.getMaxoImg(), imgMaxoX, 450);
		
		
		
	}

	@Override
	public void update() {
		if(imgMaxoX > 550 || imgMaxoX < 100)
		{
			imgMaxoX_OFF = -imgMaxoX_OFF;
		}
		imgMaxoX += imgMaxoX_OFF;
		if(imgDjoleX > 550 || imgDjoleX < 100)
		{
			imgDjoleX_OFF = -imgDjoleX_OFF;
		}
		imgDjoleX += imgDjoleX_OFF;
		if(imgDrazaX > 550 || imgDrazaX < 100)
		{
			imgDrazaX_OFF = -imgDrazaX_OFF;
		}
		imgDrazaX += imgDrazaX_OFF;
		
//		updateSkulls();
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		//A au About state na handlekeyup proveri da li je escape i dodaj isto ovo
		Transition.transitionTo(Strings.MENU_SATE, TransitionType.values()[random.nextInt(2)+5], 0.5f);
	}

	@Override
	public void handleMouseMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyDown(int keyCode) {
		switch(keyCode)
		{
		case KeyEvent.VK_ESCAPE:
			Transition.transitionTo(Strings.MENU_SATE, TransitionType.values()[random.nextInt(2)+5], 0.5f);
			break;
		}
		
		
	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub
		
	}
	
	private void fillOvalImg(Graphics2D g, BufferedImage displayImage, float x, float y){
		Rectangle2D rectangle = new Rectangle2D.Float(x,y, displayImage.getWidth(), displayImage.getHeight());
		TexturePaint tp = new TexturePaint(displayImage, rectangle);
		g.setPaint(tp);
		g.fill(new java.awt.geom.Ellipse2D.Float(x, y, displayImage.getWidth(), displayImage.getHeight()));
	}
	
}
