package com.tfs.darkworld.states;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;

import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class LoadingState extends GameState {

	private int offset = 0;

	private Fonts mFonts;
	
	private static final int STRING_X_0 = 3;
	private static final int STRING_Y_0 = 59; 
	

	public LoadingState(GameHost host) {
		super(host);
		mFonts = new Fonts();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void suspendState() {
		// TODO Auto-generated method stub

	}
	
	//TODO Izvuci promenljive na klasni nivo da ne instanciras objekte u svakom lupu

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.setColor(Colors.MIDNIGHT_BLUE);
		g.fillRect(0, 0, sw, sh);
		g.setColor(Colors.ALIZARIN);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setFont(mFonts.getFont("Phantom Fingers"));

		AffineTransform old = g.getTransform();
		AffineTransform at = new AffineTransform();

//		Daje 1 piksel manje width i height
		Rectangle r = getStringBounds(g, Strings.LOADING_SATE, 150, 150);
		
		System.out.println("getbounds" + r.width + " " + r.height);

		int messageCenterX = STRING_X_0 - r.width / 2;
		int messageCenterY = STRING_Y_0 - r.height / 2;
		
		int rectCenterX = 0 - r.width / 2;
		int rectCenterY = 0 - r.height / 2;
		
		r.setBounds(rectCenterX, rectCenterY, r.width, r.height);
		
//		Daje pogresne mere, 3 piksela za width i 18 za height
//		FontMetrics metrics = g.getFontMetrics();
//		System.out.println("metrics" + metrics.stringWidth(Strings.LOADING_SATE) + " " + metrics.getHeight());
		

		at.translate(sw / 2, sh / 2);
		at.rotate(Math.toRadians(offset));
		//koja je fora sa primenom at na sam font?
		// Font f = mFonts.getFont("Phantom Fingers").deriveFont(at);
		// g.setFont(f);
		g.setTransform(at);
		g.drawString(Strings.LOADING_SATE, messageCenterX, messageCenterY);
		//g.draw(r);
		g.setTransform(old);

	}

	private Rectangle getStringBounds(Graphics2D g2, String str, float x, float y) {
		FontRenderContext frc = g2.getFontRenderContext();
		GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
		return gv.getPixelBounds(null, x, y);
	}

	@Override
	public void update() {
		offset = (offset + 1) % 360;
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
