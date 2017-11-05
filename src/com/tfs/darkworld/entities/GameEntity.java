package com.tfs.darkworld.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import com.tfs.darkworld.entities.interfaces.IRenderable;
import com.tfs.darkworld.entities.interfaces.IUpdatable;



public abstract class GameEntity implements IUpdatable, IRenderable {
	private static final boolean DEBUG_BODY = false;
	private static final boolean DEBUG_INTERSECT= true;
	
	
	
	protected double mX;
	protected double mY;
	protected double mWidth;
	protected double mHeight;
	protected double mDX;
	protected double mDY;
	protected double mSpeed;
	
	protected double mass;
	
	
	protected IntersectionRectBody intersectionBody;
	
	public double getHeight() {
		return mHeight;
	}
	
	public double getWidth() {
		return mWidth;
	}
	
	
	public double getMass() {
		return mass;
	}
	
	
	
	/*
	 *  p1 ----- p2
	 *  |        |
	 *  |        |
	 * 	p3 ----- p4
	 * 
	 */
	
//	protected double getPX1() {
//		return intersectionBody.getX1();
//	}
//	protected double getPY1() {
//		return intersectionBody.getY1();
//	}
//	protected double getPX2() {
//		return intersectionBody.getX2();
//	}
//	protected double getPY2() {
//		return intersectionBody.getY2();
//	}
//	protected double getPX3() {
//		return intersectionBody.getX3();
//	}
//	protected double getPY3() {
//		return intersectionBody.getY3();
//	}
//	protected double getPX4() {
//		return intersectionBody.getX4();
//	}
//	protected double getPY4() {
//		return intersectionBody.getY4();
//	}
	
	
	public GameEntity(double width, double height, double mass) {
		this(width, height, mass, 0);
	}
	
	public GameEntity(double width, double height, double mass, double speed) {
		mX = 0;
		mY = 0;
		mWidth = width;
		mHeight = height;
		mSpeed = speed;
		this.mass = mass;
		intersectionBody = new IntersectionRectBody();
	}
	
	@Override
	public void update() {
		mX += mDX;
		mY += mDY;
		intersectionBody.updateIntersectionBody(mX, mY);
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		if (DEBUG_INTERSECT) {
			intersectionBody.render(g,sw,sh);
		}
		if (DEBUG_BODY) {
			g.setColor(Color.BLUE);
			g.drawRect((int)mX-1,(int) mY-1,(int) mWidth+2, (int) mHeight+2);
		}
	}
	
	public abstract void intersect(GameEntity ge);
	
	
	public double getX() {
		return mX;
	}

	public double getY() {
		return mY;
	}

	public double getSpeed() {
		return mSpeed;
	}

	public void setSpeed(double speed) {
		mSpeed = speed;
	}

	public void setPosition(double x, double y) {
		mX = x;
		mY = y;
	}

	public double getDX() {
		return mDX;
	}

	public void setDX(double mDX) {
		this.mDX = mDX;
	}

	public double getDY() {
		return mDY;
	}

	public void setDY(double mDY) {
		this.mDY = mDY;
	}
}
