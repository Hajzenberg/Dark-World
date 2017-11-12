package com.tfs.darkworld.entities;

import java.awt.geom.Rectangle2D;

public abstract class Character extends GameEntity{

	protected Rectangle2D mCharacterRect;
	
	protected boolean mIsAttacking;
	protected boolean mIsGoingRight;
	protected boolean mIsGoingLeft;
	protected boolean mIsJumping;
	protected boolean mIsFalling;
	protected boolean mIsAlive;

	protected double mJumpingForce;
	
	public Character(int width, int height, double speed, double mass) {
		super(width, height, mass, speed);
	}
	
	public boolean isIsAlive() {
		return mIsAlive;
	}
	
	public Rectangle2D getCharacterRect() {
		return mCharacterRect;
	}
}
