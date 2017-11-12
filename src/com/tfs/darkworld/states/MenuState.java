package com.tfs.darkworld.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Random;

import com.tfs.darkworld.entities.MenuHand;
import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import jaco.mp3.player.MP3Player;
import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class MenuState extends GameState{

	private Fonts mFonts;
	private int rectCenterX = 800 / 2;
	private int rectCenterY = 600 / 2;
	private Rectangle rectStart; private Color startFlag  =  Colors.ALIZARIN;
	private Rectangle rectAbout; private Color aboutFlag =  Colors.ALIZARIN;
	private Rectangle rectExit;  private Color exitFlag  =  Colors.ALIZARIN;
	private float scaleDelta = 0.0006f;
	private float currentScale = 1f;
	private AffineTransform old;
	private Random random;
	private String aboutString;
	private int aboutClickCounter;
	private int aboutX;
	private int aboutY;
	
	private MP3Player mp3Player;
	
	private Particle[] parts = new Particle[PARTICLE_MAX];
	private static final int PARTICLE_MAX = 100;
	
	private MenuHand menuHand;
	
	public MenuState(GameHost host) {
		super(host);
		mFonts = new Fonts();
		random = new Random();
		
		menuHand = new MenuHand();
		
		mp3Player = new MP3Player(new File("music/mercy_in_darkness.mp3"));
		mp3Player.setRepeat(true);
		
		aboutString = Strings.ABOUT;
		aboutX = 250;
		aboutY = 450;
		
		rectStart = new Rectangle (rectCenterX - 85, rectCenterY - 25, 200, 100);
		rectAbout = new Rectangle (rectCenterX - 160, rectCenterY + 75, 350, 100);
		rectExit = new Rectangle (rectCenterX - 85, rectCenterY + 175, 150, 100);
		for(int i = 0 ; i< PARTICLE_MAX; i++)
		{
			parts[i] = new Particle();
		}
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
		mp3Player.play();
		
	}

	@Override
	public void suspendState() {
		for (Particle particle : parts) {
			particle.life = 0;
		}
		
		mp3Player.pause();
	}
	
	@Override
	public void render(Graphics2D g, int sw, int sh) {
		//System.out.println(" menu "+Thread.currentThread().getName());
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(CommonRasters.getMenuBackgroundImg(), 0, 0,null);
		//menuHand.render(g, sw, sh);
		old = g.getTransform();
		setTitle(g);
		setStart(g, startFlag);
		setAbout(g, aboutFlag);
		setExit(g,  exitFlag);
		setSkulls((Graphics2D) g.create());
		//g.setTransform(old);
		menuHand.render(g, sw, sh);

	}

	@Override
	public void update() {
		if (currentScale > 1.3 || currentScale < 1) {
			scaleDelta *= -1;
		}

		updateSkulls();
		
		menuHand.onUpdate(host.getMouseX(), host.getMouseY());
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		
		if(button == GFMouseButton.Left)
		{
			if(rectStart.contains(x, y)||rectAbout.contains(x, y) || rectExit.contains(x, y))
			{
				//genSkulls(x, y, 10.0f, 200, 10);
			} else {
				genSkulls((int)menuHand.getX()+ 65, (int)menuHand.getY() - 8, 12.0f, 200, 12);
			}
			
		}
	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		
		if (button == GFMouseButton.Left){
			
			if(rectStart.contains(x, y))
			{
				Transition.transitionTo(Strings.GAMEPLAY_SATE, TransitionType.values()[random.nextInt(2)+5], 0.5f);
				
			}
//			TREBAL' ABOUT?!
			if(rectAbout.contains(x,y))
			{
				//Otkomentarisi samo ovo ispod
				//Transition.transitionTo(Strings.ABOUT_SATE, TransitionType.values()[random.nextInt(2)+5], 0.5f);
				
				//A au About state na handlekeyup proveri da li je escape i dodaj isto ovo
				//Transition.transitionTo(Strings.ABOUT_SATE, TransitionType.values()[random.nextInt(2)+5], 0.5f);
				System.err.println("Kliknuto!");
			}
			if(rectExit.contains(x, y))
			{
				System.exit(1);
			}
		}
		/////
		aboutClickCounter++;
		if (aboutClickCounter > 30){
			aboutString = "KAKO BILO UTUZLI MAKSO?";
			aboutX = 55;
			rectAbout = new Rectangle (aboutX, aboutY - 75, 709, 97);
		}
		
	}

	@Override
	public void handleMouseMove(int x, int y) {
		Point2D p = new Point(x, y);
		if(rectStart.contains(p)) 	 startFlag = Colors.POMEGRANATE;
		else 						 startFlag = Colors.ALIZARIN;
		
		if(rectAbout.contains(p)) aboutFlag = Colors.POMEGRANATE;
		else 						 aboutFlag = Colors.ALIZARIN;
		
		if(rectExit.contains(p))  exitFlag = Colors.POMEGRANATE;
		else 						 exitFlag = Colors.ALIZARIN;
		
		
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
		g.setFont(mFonts.getFont("Phantom Fingers"));
		currentScale += scaleDelta;
		
		AffineTransform at = new AffineTransform();
		at.scale(currentScale, currentScale);
		g.setTransform(at);
		Rectangle2D rect = getStringBounds(g, Strings.MENU_SATE, 0,0);
		int x = rectCenterX - (int)rect.getWidth()/2;
		int y = rectCenterY - (int)rect.getHeight()/2 - 100;
		
		g.drawString(Strings.MENU_SATE, x, y);
		
		g.setTransform(old);
	}
	
	private void setStart(Graphics2D g, Color c){
		g.setColor(c);
		g.setFont(mFonts.getFont("Phantom Fingers"));
		g.drawString(Strings.START_GAME, 325, 350);
		g.setColor(new Color(0, 0, 0, 1));
		g.drawRect(rectStart.x, rectStart.y, rectStart.width, rectStart.height);
	}
	
	private void setAbout(Graphics2D g, Color c){
		g.setColor(c);
		g.setFont(mFonts.getFont("Phantom Fingers"));
		g.drawString(aboutString, aboutX, aboutY);
		g.setColor(new Color(0, 0, 0, 1));
		g.drawRect(rectAbout.x, rectAbout.y, rectAbout.width, rectAbout.height);
	}
	
	private void setExit(Graphics2D g, Color c){
		g.setColor(c);
		g.setFont(mFonts.getFont("Phantom Fingers"));
		g.drawString(Strings.EXIT, 340, 550);
		g.setColor(new Color(0, 0, 0, 1));
		g.drawRect(rectExit.x, rectExit.y, rectExit.width, rectExit.height);
	}
	
//	private Color getColorForButtons(){
//		Point2D p = new Point(super.host.getMouseX(), super.host.getMouseY());
//		if(startFlag){
//			this.handleMouseMove((int)p.getX(),(int)p.getY());
//			return Colors.LIGHT_GREEN;
//		}
//		else if(aboutFlag)
//		{
//			this.handleMouseMove((int)p.getX(),(int)p.getY());
//			return Colors.LIGHT_GREEN;
//		}
//		
//		else if(exitFlag)
//		{
//			this.handleMouseMove((int)p.getX(),(int)p.getY());
//			return Colors.LIGHT_GREEN;
//		}
//		
//		return Colors.ALIZARIN;
//	}
//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------PARTICLES---------------------------------------------------------------------------
//	-----------------------------------------------------SKULLS-----------------------------------------------------------------------------
//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------------------------------------------------------------------------------
	public static class Particle
	{
		public float posX;
		public float posY;
		public float dX;
		public float dY;
		public int life = 0;
		public int lifeMax = 0;
		public float angle = 0.0f;
		public float rot = 0.0f;
	}
	
	private void setSkulls(Graphics2D g) {
		AffineTransform af = new AffineTransform();
		
		for(Particle p : parts)
		{
			if(p.life <=0) continue;
			
			af.setToIdentity();
			af.translate(p.posX, p.posY);
			af.rotate(p.angle);
			af.translate(-16.0, -16.0);
			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)p.life / (float)p.lifeMax));
			
			g.drawImage(CommonRasters.getParticleImg(), af, null);
		}
		g.setTransform(old);
	}
	
	private void updateSkulls() {
		for(Particle p : parts)
		{
			if(p.life <= 0) continue;
			
			p.life--;
			p.posX += p.dX;
			p.posY += p.dY;
			p.dX *= 0.99f;
			p.dY = p.dY * 0.99f + 0.1f;
			p.angle += p.rot;
			p.rot *= 0.99f;
		}
	}
	private void genSkulls(int cX, int cY, float radius, int life, int count)
	{
		for(Particle p : parts)
		{
			if(p.life <= 0)
			{
				p.life = p.lifeMax = (int)(Math.random() * life * 0.5) + life / 6;
				p.posX = cX;
				p.posY = cY;
				double angle = Math.random() * Math.PI * 2.0;
				double speed = Math.random() * radius;
				p.dX = (float)(Math.cos(angle) * speed);
				p.dY = (float)(Math.sin(angle) * speed);
				p.angle = (float)(Math.random() * Math.PI * 2.0);
				p.rot = (float)(Math.random() - 0.5) * 0.1f;
				
				
				count--;
				if(count <= 0) return;
			}
		}
	}
}
