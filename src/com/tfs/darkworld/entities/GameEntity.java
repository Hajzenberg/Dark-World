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
	
	public GameEntity(double x, double y, double width, double height, double dx, double dy, double speed) {
		mX = x;
		mY = y;
		mWidth = width;
		mHeight = height;
		mDX = dx;
		mDY = dy;
		mSpeed = speed;
	}

	public abstract void update();

	public abstract void render(Graphics2D g, int sw, int sh);

	public double getX() {
		return mX;
	}

	public double getY() {
		return mY;
	}

	public double getSpeed() {
		return mSpeed;
	}

	public void setSpeed(int speed) {
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
