package com.tfs.darkworld.states;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.tfs.darkworld.loading.AsyncLoader;
import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.GameConstants;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class LoadingState extends GameState {
	private static final String ROTATING_TEXT_ONE = "LOADING .";
	private static final String ROTATING_TEXT_TWO = "LOADING . .";
	private static final String ROTATING_TEXT_THIRD = "LOADING . . .";

	private String[] msStrings = { ROTATING_TEXT_ONE, ROTATING_TEXT_TWO, ROTATING_TEXT_THIRD };

	private int offset = 0;
	private int percent = 0;
	private String currentTask;
	private int skip = 0;
	
	private final int rectCenterX = GameConstants.FRAME_WIDTH / 2;
	private final int rectCenterY = GameConstants.FRAME_HEIGTH / 2;
	
	
	private Fonts mFonts;
	private Font f2 = new Font("Phantom Fingers", Font.PLAIN, 15);
	
	private AsyncLoader asyncLoader;

	public LoadingState(GameHost host, AsyncLoader loader) {
		super(host);
		this.asyncLoader = loader;
		mFonts = new Fonts();
		currentTask = "";
		
		loader.setProgressListener(new IProgressListener() {

			@Override
			public void updateProgress(int progress) {
				LoadingState.this.percent = progress;
			}
		});

		loader.setTaskListener(new ITaskListener() {

			@Override
			public void updateTaskName(String taskName) {
				LoadingState.this.currentTask = taskName;
			}
		});

	}

	@Override
	public boolean handleWindowClose() {
		return true;
	}

	@Override
	public String getName() {
		return Strings.LOADING_SATE;
	}

	@Override
	public void resumeState() {
		asyncLoader.start();
	}

	@Override
	public void suspendState() {}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Colors.MIDNIGHT_BLUE);
		g.fillRect(0, 0, sw, sh);
		g.setColor(Colors.ALIZARIN);
		

		g.setFont(mFonts.getFont("Phantom Fingers"));

//		AffineTransform old = g.getTransform();


		g.drawString(msStrings[offset], rectCenterX-100, rectCenterY-70);
		g.drawString(percent + " %", rectCenterX-40, rectCenterY+50);
		
		g.setFont(f2);
		g.drawString(currentTask, rectCenterX-60, rectCenterY + 120);
//		g.setTransform(old);
	}

	

	
	@Override
	public void update() {
		skip--;
		if (skip <= 0) {
			skip = 60;
			offset = (offset + 1) % msStrings.length;
		}
		if (percent == 100) {
			Transition.transitionTo(Strings.MENU_SATE, TransitionType.Crossfade, 3f);
		}

	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {

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
