package com.tfs.darkworld.entities;

import java.awt.Graphics2D;


public abstract class GameEntity {

	protected double mX;
	protected double mY;
	protected double mWidth;
	protected double mHeight;
	protected double mDX;
	protected double mDY;
	protected double mSpeed;
	
	protected double mass;
	
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
	
	protected double getPX1() {
		return mX;
	}
	protected double getPY1() {
		return mY;
	}
	protected double getPX2() {
		return mX+mWidth;
	}
	protected double getPY2() {
		return mY;
	}
	protected double getPX3() {
		return mX;
	}
	protected double getPY3() {
		return mY+mHeight;
	}
	protected double getPX4() {
		return mX+mWidth;
	}
	protected double getPY4() {
		return mY+mHeight;
	}
	
	
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
	}
	

	public abstract void update();

	public abstract void render(Graphics2D g, int sw, int sh);
	
	public abstract void intersect(GameEntity ge);
//	public abstract void intersectRoutine(GameEntity ge);
	
	public static enum IntersectType {
		UpperLine,
		BottomLine,
		leftLine,
		RightLine,
		UpperRightCorner,
		UpperLeftCorner,
		BottomRightCorner,
		BottomLeftCorner,
		Inside,
		None;	
	}
	
	/**
	 * @param ge
	 * @return
	 */
	public IntersectType isIntersecting(GameEntity ge) {
		
		boolean pInside1 = isInsideSquare(getPX1(), getPY1(),ge.getPX1(), ge.getPY1(), ge.getPX4(), ge.getPY4());
		boolean pInside2 = isInsideSquare(getPX2(), getPY2(),ge.getPX1(), ge.getPY1(), ge.getPX4(), ge.getPY4());
		boolean pInside3 = isInsideSquare(getPX3(), getPY3(),ge.getPX1(), ge.getPY1(), ge.getPX4(), ge.getPY4());
		boolean pInside4 = isInsideSquare(getPX4(), getPY4(),ge.getPX1(), ge.getPY1(), ge.getPX4(), ge.getPY4());
		
		if (pInside1 && pInside2 && pInside3 && pInside4) {
			return IntersectType.Inside;
		}
		else if (pInside1 && pInside2) {
			return IntersectType.BottomLine;
		}
		else if (pInside2 && pInside3) {
			return IntersectType.leftLine;
		}
		else if (pInside3 && pInside4) {
			return IntersectType.UpperLine;
		}
		else if (pInside4 && pInside1) {
			return IntersectType.RightLine;
		}
		else if (pInside1) {
			return IntersectType.BottomRightCorner;
		}
		else if (pInside2) {
			return IntersectType.BottomLeftCorner;
		}
		else if (pInside3) {
			return IntersectType.UpperLeftCorner;
		}
		else if (pInside4) {
			return IntersectType.UpperRightCorner;
		}
		return IntersectType.None;
		
	}
	
	/**
	 * @param x
	 * @param y
	 * @param px1 gornji levi
	 * @param py1
	 * @param px2 dosni denji
	 * @param py2
	 * @return
	 */
	public boolean isInsideSquare(double x, double y, double px1,double py1, double px2, double py2) {
		if (x >= px1 && x <= px2 && y >= py1 && y <= py2) {
			return true;
		}
		return false;
	}
	
	
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
