package com.tfs.darkworld.entities;

public abstract class Character extends GameEntity{

	protected double mMaxdY;
	protected double mGravity;
	protected boolean mCanJump;
	protected boolean mFalling;
	
	public Character(double x, double y, double width, double height, double dx, double dy, double speed) {
		super(x, y, width, height, dx, dy, speed);
	}
}
