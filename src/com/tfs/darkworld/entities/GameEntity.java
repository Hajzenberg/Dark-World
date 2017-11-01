package com.tfs.darkworld.entities;

import java.awt.Graphics2D;

public abstract class GameEntity {

	protected int mX;
	protected int mY;
	protected int mSpeed;

	public GameEntity(int x, int y, int speed) {
		mX = x;
		mY = y;
		mSpeed = speed;
	}

	public abstract void update();

	public abstract void render(Graphics2D g, int sw, int sh);

	public int getX() {
		return mX;
	}

	public int getY() {
		return mY;
	}

	public int getSpeed() {
		return mSpeed;
	}

	public void setSpeed(int speed) {
		mSpeed = speed;
	}

	public void setPosition(int x, int y) {
		mX = x;
		mY = y;
	}

	public void move(int dX, int dY) {
		mX += dX;
		mY += dY;
	}
}
