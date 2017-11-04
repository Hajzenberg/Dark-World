package com.tfs.darkworld.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.tfs.darkworld.loading.transformations.GrayTransformation;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.GameConstants;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;
import rafgfxlib.Util;

public class DiedState extends GameState {
	
	private BufferedImage image;
	private GrayTransformation transformation;
	private String reloadString = "Pussy died!";
	private Fonts mFonts;
	
	public DiedState(GameHost host) {
		super(host);
		mFonts = new Fonts();
		transformation = new GrayTransformation();
	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Strings.GAME_TO_RETRY_SATE;
	}
	
	
	
	@Override
	public void resumeState() {
		image = Util.rasterToImage(transformation.process(CommonRasters.getDyingSnapshot().getRaster()));
	}

	@Override
	public void suspendState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.drawImage(image, 0, 0, null);
//		g.setColor(Colors.ALIZARIN);
//		g.setColor(Colors.MIDNIGHT_BLUE);
		g.setColor(Color.WHITE);
		g.setFont(mFonts.getFont("Phantom Fingers"));
		g.drawString(reloadString, GameConstants.FRAME_WIDTH/2-150, GameConstants.FRAME_HEIGTH/2);
		
		
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
		if (keyCode == KeyEvent.VK_R) {
			Transition.transitionTo(Strings.GAMEPLAY_SATE, TransitionType.Crossfade, 1f);
		}
		else if (keyCode == KeyEvent.VK_ESCAPE) {
			Transition.transitionTo(Strings.MENU_SATE, TransitionType.Crossfade, 1f);
		}
	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub
		
	}

}
