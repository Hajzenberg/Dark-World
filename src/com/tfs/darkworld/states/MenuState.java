package com.tfs.darkworld.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class MenuState extends GameState{

	private Fonts mFonts;
	private int rectCenterX = 800 / 2;
	private int rectCenterY = 600 / 2;
	private float offset = 0.5f;
	private float titleSize = 0;
	private int frames = 0;
	private Rectangle rectStart;
	private Rectangle rectAbout;
	private Rectangle rectExit;
	
	Font titleFont = new Font("Phantom Fingers", Font.PLAIN, 50);
	public MenuState(GameHost host) {
		super(host);
		mFonts = new Fonts();
		rectStart = new Rectangle (rectCenterX - 85, rectCenterY - 25, 200, 100);
		rectAbout = new Rectangle (rectCenterX - 160, rectCenterY + 75, 350, 100);
		rectExit = new Rectangle (rectCenterX - 85, rectCenterY + 175, 150, 100);
		titleSize = mFonts.getFont("Phantom Fingers").getSize();
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
		//System.out.println(" menu "+Thread.currentThread().getName());
		g.drawImage(CommonRasters.getMenuBackgroundImg(), 0, 0,null);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		setTitle(g);
		setStart(g);
		setAbout(g);
		setExit(g);
		
//		g.setTransform(old);
	}

	@Override
	public void update() {
		frames--;
		if(frames <= 0)
		{
			frames = 60;
			offset = -offset;
		}
//		if(frames <= 60)
//		{
//			
//			titleSize = (titleSize + offset) %65;
//			
//		}
//		if(frames < 30)
//		{
//			offset = -offset;
//			titleSize = (titleSize - offset) % 65;
//		}
		titleSize = (titleSize + offset) %100;
		
		
		System.out.println(titleSize);
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		if(rectStart.contains(x, y)){
			
		}
		if (button == GFMouseButton.Left){
			
			if(rectStart.contains(x, y))
			{
				TransitionType transType = TransitionType.values()[3];
				Transition.transitionTo(Strings.GAMEPLAY_SATE, transType, 0.5f);
				
			}
//			TREBAL' ABOUT?!
			if(rectAbout.contains(x,y))
			{
				System.err.println("Kliknuto!");
			}
			if(rectExit.contains(x, y))
			{
				System.exit(1);
			}
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
	private Rectangle getStringBounds(Graphics2D g2, String str, float x, float y) {
		FontRenderContext frc = g2.getFontRenderContext();
		GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
		return gv.getPixelBounds(null, x, y);
	}
	private void setTitle(Graphics2D g){
		g.setColor(Colors.ALIZARIN);
		g.setFont(mFonts.getFont("Phantom Fingers").deriveFont(Font.PLAIN, 48));
		g.drawString(Strings.MENU_SATE, rectCenterX - 150, rectCenterY - 200);
	}
	
	private void setStart(Graphics2D g){
		g.setColor(Colors.ALIZARIN);
		g.setFont(titleFont);
		g.drawString(Strings.START_GAME, 325, 350);
		g.setColor(new Color(0, 0, 0, 1));
		g.drawRect(rectStart.x, rectStart.y, rectStart.width, rectStart.height);
	}
	
	private void setAbout(Graphics2D g){
		g.setColor(Colors.ALIZARIN);
		g.setFont(mFonts.getFont("Phantom Fingers"));
		g.drawString(Strings.ABOUT, 250, 450);
		g.setColor(new Color(0, 0, 0, 1));
		g.drawRect(rectAbout.x, rectAbout.y, rectAbout.width, rectAbout.height);
	}
	
	private void setExit(Graphics2D g){
		g.setColor(Colors.ALIZARIN);
		g.setFont(mFonts.getFont("Phantom Fingers"));
		g.drawString(Strings.EXIT, 340, 550);
		g.setColor(new Color(0, 0, 0, 1));
		g.drawRect(rectExit.x, rectExit.y, rectExit.width, rectExit.height);
	}
}
