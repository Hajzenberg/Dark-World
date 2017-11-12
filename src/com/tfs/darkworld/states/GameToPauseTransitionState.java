package com.tfs.darkworld.states;

import java.awt.Graphics2D;

import com.tfs.darkworld.loading.transformations.PauseTransformation;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;
import rafgfxlib.Util;

public class GameToPauseTransitionState extends GameState  {
	
	private static final PauseTransformation pTransformation = new PauseTransformation();
	
	public GameToPauseTransitionState(GameHost host) {
		super(host);
	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return Strings.GAME_TO_PAUSE_SATE;
	}

	@Override
	public void resumeState() {
		CommonRasters.setLastTransformedScreenCapture(Util.rasterToImage(pTransformation.process(CommonRasters.getLastScreenCapture().getRaster())));
		Transition.transitionTo(Strings.PAUSE_SATE, TransitionType.Crossfade, 2f);
	}

	@Override
	public void suspendState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.drawImage(CommonRasters.getLastScreenCapture(), 0, 0, null);
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
