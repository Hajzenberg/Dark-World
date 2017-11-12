package com.tfs.darkworld.states;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.GameConstants;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class PauseState extends GameState{

	private Fonts mFonts;
	private final String pauseText = "Need a break? Pussy";
	
	public PauseState(GameHost host) {
		super(host);
		mFonts = new Fonts();
		//host.renderSnapshot(canvas, state)
		
	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() {
		return Strings.PAUSE_SATE;
	}

	@Override
	public void resumeState() {
		
	}

	@Override
	public void suspendState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.drawImage(CommonRasters.getLastTransformedScreenCapture(), 0, 0, null);
//		g.setColor(Colors.ALIZARIN);
		g.setColor(Colors.MIDNIGHT_BLUE);
		g.setFont(mFonts.getFont("Phantom Fingers"));
		g.drawString(pauseText, GameConstants.FRAME_WIDTH/2-300, GameConstants.FRAME_HEIGTH/2);
		
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
		if (keyCode == KeyEvent.VK_P){
			TransitionType transType = TransitionType.Crossfade;
			Transition.transitionTo(Strings.GAMEPLAY_SATE, transType, 0.5f);
		}
		
	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub
		
	}

}
