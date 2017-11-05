package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.GameConstants;

import rafgfxlib.Util;

public class Player extends Character {

	// private static final int ACTION_ATTACK = 1;
	private static final int ACTION_WALK = 6;
	private static final int ACTION_JUMP = 5;
	private static final int ACTION_DIE = 1;
	private static final int ACTION_RUN = 4;

	private static final double NORMAL_MASS = 10;
	private static final double FALLING_MASS = 30;
	
	//Djole
//	private static final int RUNNING_FRAMES = 20;
//	private static final int WALKING_FRAMES = 30;
//	private static final int WALKING_SPEED_OFFSET =  4;
	
	//Maksa
//	private static final int RUNNING_FRAMES = 20;
//	private static final int WALKING_FRAMES = 30;
//	private static final int WALKING_SPEED_OFFSET =  4;
	
	//Draza
	private static final int RUNNING_FRAMES = 4;
	private static final int WALKING_FRAMES = 5;
	private static final int WALKING_SPEED_OFFSET =  2;

	private ArrayList<BufferedImage[]> mSprites;

	private int[] mNumOfFrames = { 12, 10, 8, 8, 8, 6, 10, 10 };
	private int[] mFrameWidths = { 128, 128, 128, 128, 128, 128, 128, 128 };
	private int[] mFrameLengths = { 128, 128, 128, 128, 128, 128, 128, 128 };
	private int[] mFrameIntervals = { 3, 3, 14, 5, RUNNING_FRAMES, 3, WALKING_FRAMES, 3 };

	private boolean officialyDead = false;
	
	private boolean accelerating = false;
	private boolean slowing = false;
	private boolean normal = false;

	/*
	 * private static final int ACTION_IDLE = 0; // private static final int
	 * ACTION_ATTACK = 1; private static final int ACTION_WALK = 4; private static
	 * final int ACTION_JUMP = 5; private static final int ACTION_FALL = 2;
	 * //private static final int ACTION_RUN = 3;
	 * 
	 * private ArrayList<BufferedImage[]> mSprites;
	 * 
	 * private int[] mNumOfFrames = { 12, 10, 8, 8, 8, 6 }; private int[]
	 * mFrameWidths = { 128, 128, 128, 128, 128, 128 }; private int[] mFrameLengths
	 * = { 128, 128, 128, 128, 128, 128 }; private int[] mFrameIntervals = { 3, 3,
	 * 14, 3, 3, 3 };
	 */

	protected Animation mCurrentAnimation;
	protected int mCurrentAction;

	public Player(int sw, int sh) {
		super(30, 30, 1.3, 10);
		
		/* Mora se pozvati pre reset da bi se popunili nizovi koje koristi animacija */
		try {
			BufferedImage spritesheet = Util.loadImage("design/character_sheet.png");

			int count = 0;
			mSprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < mNumOfFrames.length; i++) {
				BufferedImage[] bi = new BufferedImage[mNumOfFrames[i]];

				for (int j = 0; j < mNumOfFrames[i]; j++) {
					bi[j] = CommonRasters.getPlayerSptitesheet().getSubimage(j * mFrameWidths[i], count, mFrameWidths[i], mFrameLengths[i])
							.getSubimage(0, 0, mFrameWidths[i], mFrameLengths[i]);
				}
				mSprites.add(bi);
				count += mFrameLengths[i];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		/* Izdvojena logika za setovanje igraca
		 * na default poziciju pri startovanju
		 * nivoa
		 */
		reset();
		
		intersectionBody.setLeftOffset(32);
		intersectionBody.setUpperOffset(0);
		intersectionBody.setHeight(mHeight);
		intersectionBody.setWidth(64);
	}

	@Override
	public void update() {

		if (mCurrentAction == ACTION_DIE) {
			if (mCurrentAnimation.isLastFrame()) {
				officialyDead = true;
			}
		}

		if (mIsAlive && !mIsJumping) {
			if (mDX > 0) {
				if (!accelerating) {
					System.out.println("TRCANJE");
					slowing = false;
					normal = false;
					accelerating = true;
					setAnimation(ACTION_RUN);

				}
			} else if (mDX < 0) {
				if (!slowing) {
					System.out.println("SPORI HOD");
					accelerating = false;
					slowing = true;
					normal = false;
					mFrameIntervals[ACTION_WALK] = WALKING_FRAMES + WALKING_SPEED_OFFSET;
					setAnimation(ACTION_WALK);
				}
			} else {
				if (!normal) {
					System.out.println("NORMALAN HOD");
					accelerating = false;
					slowing = false;
					normal = true;
					mFrameIntervals[ACTION_WALK] = WALKING_FRAMES;
					setAnimation(ACTION_WALK);
				}
			}
		} else if (mIsAlive && mIsJumping) {
			if (mDY == 0) {
				if (mIsJumping) {
					setAnimation(ACTION_WALK);
					mIsJumping = false;
				}
			}

		}

		super.update();

		if (mX + mWidth > GameConstants.FRAME_WIDTH - 100) {
			mX = GameConstants.FRAME_WIDTH - 100 - mWidth;
		} else if (mX < 50) {
			mX = 50;
		}

		mCurrentAnimation.update();
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		super.render(g, sw, sh);

		g.drawImage(mCurrentAnimation.getImage(), (int) mX, (int) mY + 3, null);
	}

	public void stop() {
		mDX = 0;
	}

	public void jump() {
		if (mIsAlive) {
			if (mDY == 0) {
				setAnimation(ACTION_JUMP);
				mIsJumping = true;
				mIsFalling = false;
				mDY = -mJumpingForce;
			}
		}
	}

	public void left() {
		if (mIsAlive) {
			mIsGoingLeft = true;
			// facingRight = false;
			mDX = -mSpeed;
		}
	}

	public void right() {
		if (mIsAlive) {
			mIsGoingRight = true;
			// facingRight = true;
			mDX = +mSpeed;
		}
	}

	private void setAnimation(int i) {
		mCurrentAction = i;
		mCurrentAnimation.setFrames(mSprites.get(mCurrentAction));
		mCurrentAnimation.setFrameInterval(mFrameIntervals[mCurrentAction]);
		mWidth = mFrameWidths[mCurrentAction];
		mHeight = mFrameLengths[mCurrentAction];
		// System.out.println("SET ANIMATION " + mWidth + " " + mHeight);
	}

	public void setLeft(boolean b) {
		mIsGoingLeft = b;
	}

	public void setRight(boolean b) {
		mIsGoingRight = b;
	}

	public void setJumping(boolean b) {
		mIsJumping = b;
	}

	public void setFalling(boolean b) {
		mIsFalling = b;
		if (!b) {
			mDY = 0;
		}
	}

	public void die() {
		mIsAlive = false;
		setAnimation(ACTION_DIE);

		System.err.println("Just died!");
	}

	public Rectangle2D getCharacterRect() {
		return mCharacterRect;
	}

	@Override
	public void intersect(GameEntity ge) {

		IntersectType intersectType = intersectionBody.isIntersecting(ge.intersectionBody);

		switch (intersectType) {
		case UpperLine:
		case UpperLeftCorner:
		case UpperRightCorner:
			if (ge instanceof Lava || ge instanceof Spikes) {
				if (mIsAlive) {
					die();
				}
			}
			mDY = 0;
			mY = (ge.getY() - mHeight);
			break;
		default:
			break;
		}
	}
	
	public void reset(){
		System.out.println("RESET");
		mX = 100;
		mY = 100;		//375
		
		mCurrentAnimation = new Animation();
		mJumpingForce = 5;
		
		mIsAlive = true;
		officialyDead = false;
		
		mIsGoingRight = true;
		
		setAnimation(ACTION_WALK);
		mCharacterRect = new Rectangle2D.Double(50, 100, mWidth, mHeight);
	}
	
	public boolean isOfficialyDead() {
		return officialyDead;
	}
}
