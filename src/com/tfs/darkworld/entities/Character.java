package com.tfs.darkworld.entities;

import java.awt.geom.Rectangle2D;

public abstract class Character extends GameEntity{

	protected Rectangle2D mCharacterRect;
	
	protected boolean mIsAttacking;
	protected boolean mIsGoingLeft;
	protected boolean mIsGoingRight;
	protected boolean mIsJumping;
	protected boolean mIsFalling;

	protected double mMovingSpeed;
	protected double mJumpingForce;
	
	public Character(double x, double y, double width, double height, double speed, double mass) {
		super(x, y, width, height, speed, mass);
	}
	
	public Rectangle2D getCharacterRect() {
		return mCharacterRect;
	}
}
