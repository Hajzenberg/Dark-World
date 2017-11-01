package com.tfs.darkworld.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class LoadingState extends GameState {

	private int offset = 0;

	private Fonts mFonts;
	
	private BufferedImage img = null;
	

	public LoadingState(GameHost host) {
		super(host);
		mFonts = new Fonts();
		
		try {
		    img = ImageIO.read(new File("src/stuff-200x200.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Loading konstruktor");
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

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.setColor(Colors.MIDNIGHT_BLUE);
		g.fillRect(0, 0, sw, sh);
		g.setColor(Colors.ALIZARIN);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setFont(mFonts.getFont("Phantom Fingers"));

		FontMetrics fm = g.getFontMetrics();

		AffineTransform old = g.getTransform();
		AffineTransform at = new AffineTransform();
		
		Rectangle r = getStringBounds(g, Strings.LOADING_SATE, 150, 150);
		
		System.out.println(r.getWidth() + "  "+ r.getHeight());
		
		//g.drawRect(r.x, r.y, r.width, r.height);

		int x = 0 - r.width / 2;
		int y = 0 - r.height / 2;
		
		//g.drawRect(0, 0, fm.stringWidth(Strings.LOADING_SATE), fm.getHeight());
		
		

		//System.out.println("sw " + sw + " sh " + sh + " x " + x + " y " + y + " str len " + fm.stringWidth(Strings.LOADING_SATE) + "str height "+fm.getHeight());

		at.translate(sw/2, sh/2);
		at.rotate(Math.toRadians(offset));
		//Font f = mFonts.getFont("Phantom Fingers").deriveFont(at);
		//g.setFont(f);
		g.setTransform(at);
		g.drawString(Strings.LOADING_SATE, x, y);
		r.setBounds(x, y, r.width, r.height);
		//g.draw(r);
		g.setTransform(old);
		// things you draw after here will not be rotated

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
		if (button == GFMouseButton.Left){
			host.setState(Strings.MENU_SATE);
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
