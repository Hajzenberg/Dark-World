package com.tfs.darkworld.states;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class MenuState extends GameState{

	private Fonts mFonts;
	
	public MenuState(GameHost host) {
		super(host);
		mFonts = new Fonts();
	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() {
		return Strings.MENU_SATE;
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
		g.setColor(Colors.ALIZARIN);
		g.fillRect(0, 0, sw, sh);
		g.setColor(Colors.NICE_BLUE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setFont(mFonts.getFont("Phantom Fingers"));
		g.drawString(Strings.MENU_SATE, 250, 250);
		
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
		if (button == GFMouseButton.Left){
			TransitionType transType = TransitionType.values()[3];
			Transition.transitionTo(Strings.GAMEPLAY_SATE, transType, 0.5f);
		}
		
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
