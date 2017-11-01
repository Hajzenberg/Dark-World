package com.tfs.darkworld.states;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;

import com.tfs.darkworld.loading.AsyncLoader;
import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.res.Colors;
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
	
	private Fonts mFonts;

	private AsyncLoader asyncLoader;

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public void setCurrentTask(String currentTask) {
		this.currentTask = currentTask;
	}

//	private static final int STRING_X_0 = 3;
//	private static final int STRING_Y_0 = 59;

	public LoadingState(GameHost host, AsyncLoader loader) {
		super(host);
		this.asyncLoader = loader;
		mFonts = new Fonts();

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
	public void suspendState() {
		// TODO Auto-generated method stub

	}

	// TODO Izvuci promenljive na klasni nivo da ne instanciras objekte u svakom
	// lupu

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.setColor(Colors.MIDNIGHT_BLUE);
		g.fillRect(0, 0, sw, sh);
		g.setColor(Colors.ALIZARIN);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setFont(mFonts.getFont("Phantom Fingers"));

		AffineTransform old = g.getTransform();
//		AffineTransform at = new AffineTransform();

		// Daje 1 piksel manje width i height
//		Rectangle loadMsg = getStringBounds(g, msStrings[offset], 150, 150);

		// System.out.println("getbounds" + r.width + " " + r.height);

//		int messageCenterX = STRING_X_0 - loadMsg.width / 2;
//		int messageCenterY = STRING_Y_0 - loadMsg.height / 2;
//
		int rectCenterX = 800 / 2;
		int rectCenterY = 600 / 2;

//		loadMsg.setBounds(rectCenterX, rectCenterY, loadMsg.width, loadMsg.height);

		// Daje pogresne mere, 3 piksela za width i 18 za height
		// FontMetrics metrics = g.getFontMetrics();
		// System.out.println("metrics" +
		// metrics.stringWidth(Strings.LOADING_SATE) + " " +
		// metrics.getHeight());
//		at.translate(sw / 2, sh / 2);

		// at.rotate(Math.toRadians(offset));
		// koja je fora sa primenom at na sam font?
		// Font f = mFonts.getFont("Phantom Fingers").deriveFont(at);
		// g.setFont(f);
//		g.setTransform(at);
		g.drawString(msStrings[offset], rectCenterX-100, rectCenterY-70);

//		at = new AffineTransform();
//		at.translate(sw / 2, sh / 2);
//		g.setTransform(at);
		g.drawString(percent + " %", rectCenterX-40, rectCenterY+50);
		if (currentTask != null) {
			g.setFont(f2);
			g.drawString(currentTask, rectCenterX-60, rectCenterY + 120);
		}
		// g.draw(r);
		g.setTransform(old);

	}

	Font f2 = new Font("Phantom Fingers", Font.PLAIN, 15);

	private Rectangle getStringBounds(Graphics2D g2, String str, float x, float y) {
		FontRenderContext frc = g2.getFontRenderContext();
		GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
		return gv.getPixelBounds(null, x, y);
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
		if (button == GFMouseButton.Left) {
			TransitionType transType = TransitionType.values()[0];
			Transition.transitionTo(Strings.MENU_SATE, transType, 0.5f);
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
