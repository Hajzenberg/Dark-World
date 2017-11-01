package com.tfs.darkworld.states;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.tfs.darkworld.entities.Background;
import com.tfs.darkworld.entities.Player;
import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class GameplayState extends GameState {

	private Fonts mFonts;
	
	private Background mBackground;
	private Player mPlayer;
	
	public GameplayState(GameHost host) {
		super(host);
		mFonts = new Fonts();
		
		mBackground = new Background(host.getWidth(), host.getHeight());
		mPlayer = new Player(host.getWidth(), host.getHeight());
	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() {
		return Strings.GAMEPLAY_SATE;
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
		
		mBackground.render(g, sw, sh);
		mPlayer.render(g, sw, sh);

		g.setFont(mFonts.getFont("Phantom Fingers"));
		g.setColor(Colors.POMEGRANATE);
		g.drawString(Strings.GAMEPLAY_SATE, 250, 250);
		
	}

	@Override
	public void update() {
		if(host.isKeyDown(KeyEvent.VK_LEFT)){
			System.out.println("Pritisnuto levo");
			mPlayer.move(-mPlayer.getSpeed(), 0);
		}
		else if(host.isKeyDown(KeyEvent.VK_RIGHT)){
			System.out.println("Pritisnuto levo");
			mPlayer.move(mPlayer.getSpeed(), 0);
		}
		mPlayer.update();
		
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
		
		TransitionType transType = null;
		
		switch (keyCode) {
		case KeyEvent.VK_P:
			transType = TransitionType.values()[2];
			Transition.transitionTo(Strings.PAUSE_SATE, transType, 0.5f);
			break;
		case KeyEvent.VK_ESCAPE:
			transType = TransitionType.values()[4];
			Transition.transitionTo(Strings.MENU_SATE, transType, 0.5f);
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			//mPlayer.move(-mPlayer.getSpeed(), 0);
			break;	
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			//mPlayer.move(mPlayer.getSpeed(), 0);
			break;

		default:
			break;
		}
	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub
		
	}

}
