package com.tfs.darkworld.entities;

import java.awt.geom.Rectangle2D;

public abstract class Character extends GameEntity{

	protected Rectangle2D mCharacterRect;
	
	protected boolean mIsAttacking;
	protected boolean mIsGoingLeft;
	protected boolean mIsGoingRight;
	protected boolean mIsJumping;
	protected boolean mIsFalling;

	protected double mMaxSpeed;
	protected double mMaxFallSpeed;
	protected double mStoppingSpeed;
	protected double mFallingSpeed;
	protected double mStartingJumpSpeed;
	protected double mStoppingJumpSpeed;
	
	protected double mMaxdY;
	protected double mGravity;
	protected boolean mCanJump;
	protected boolean mFalling;
	
	public Character(double x, double y, double width, double height, double speed) {
		super(x, y, width, height, speed);
	}
	
	public Rectangle2D getCharacterRect() {
		return mCharacterRect;
	}
}
