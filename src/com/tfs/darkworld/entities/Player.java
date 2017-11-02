package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.tfs.darkworld.util.ImageUtil;

public class Player extends Character {

	private static final int ACTION_IDLE = 0;
	// private static final int ACTION_ATTACK = 1;
	private static final int ACTION_WALK = 4;
	private static final int ACTION_JUMP = 3;
	private static final int ACTION_FALL = 1;

	private ArrayList<BufferedImage[]> mSprites;

	private int[] mNumOfFrames = { 1, 10, 10, 10, 10 };
	private int[] mFrameWidths = { 60, 60, 60, 60, 60 };
	private int[] mFrameLengths = { 64, 64, 64, 64, 64 };
	private int[] mFrameIntervals = { 3, 3, 3, 3, 3 };

	protected Animation mCurrentAnimation;
	protected int mCurrentAction;
	protected boolean facingRight;

	public Player(int sw, int sh) {
		super(200, 200, 30, 30, 2); // y = sh -200

		mCurrentAnimation = new Animation();
		facingRight = true;
		mMaxSpeed = 1.6;
		mStoppingSpeed = 1.6;
		mFallingSpeed = 0.15;
		mMaxFallSpeed = 4.0;
		mStartingJumpSpeed = -4.8;
		mStoppingJumpSpeed = 0.3;

		try {

			BufferedImage spritesheet = ImageUtil.loadImage("/images/character_updated.png");

			int count = 0;
			mSprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < mNumOfFrames.length; i++) {
				BufferedImage[] bi = new BufferedImage[mNumOfFrames[i]];

				System.out.println("RED " + i);

				for (int j = 0; j < mNumOfFrames[i]; j++) {
					bi[j] = spritesheet.getSubimage(j * mFrameWidths[i], count, mFrameWidths[i], mFrameLengths[i]);
					System.out.println(" frame " + (j * mFrameWidths[i]) + " " + (count) + " " + " " + mFrameWidths[i]
							+ " " + mFrameLengths[i]);
				}
				mSprites.add(bi);
				count += mFrameLengths[i];

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println("pre " + width + " " + height);

		setAnimation(ACTION_IDLE);

		// System.out.println("posle "+width + " " + height);

		mCharacterRect = new Rectangle2D.Double(50, 100, mWidth, mHeight);
	}

	@Override
	public void update() {

		getNextPosition();
		
		if (mDX == 0) {
			mX = (int) mX;
		}

		if (mDY < 0) {
			if (mCurrentAction != ACTION_JUMP) {
				setAnimation(ACTION_JUMP);
			}
		} else if (mDY > 0) {
			if (mCurrentAction != ACTION_FALL) {
				setAnimation(ACTION_FALL);
			}
		} else if (mIsGoingLeft || mIsGoingRight) {
			if (mCurrentAction != ACTION_WALK) {
				setAnimation(ACTION_WALK);
			}
		} else if (mCurrentAction != ACTION_IDLE) {
			setAnimation(ACTION_IDLE);
		}

		mCurrentAnimation.update();

		if (!mIsAttacking) {
			if (mIsGoingRight)
				facingRight = true;
			if (mIsGoingLeft)
				facingRight = false;
		}

		mCharacterRect.setRect(mX, mY, mWidth, mHeight);
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {

		// System.out.println(x + " " + y + " mDX " + mDX + "mDY " + mDY);

		mX += mDX;
		mY += mDY;

		if (facingRight) {
			// System.out.println("RIGHT");
			g.drawImage(mCurrentAnimation.getImage(), (int) mX, (int) mY, null);
		} else {
			// System.out.println("LEFT");
			g.drawImage(mCurrentAnimation.getImage(), (int) (mX + mWidth), (int) (mY), (int) -mWidth, (int) mHeight,
					null);
		}

		g.draw(mCharacterRect);
	}

	private void getNextPosition() {

		if (mIsGoingLeft) {
			mDX -= mSpeed;
			if (mDX < -mMaxSpeed) {
				mDX = -mMaxSpeed;
			}
		} else if (mIsGoingRight) {
			mDX += mSpeed;
			if (mDX > mMaxSpeed) {
				mDX = mMaxSpeed;
			}
		} else {
			if (mDX > 0) {
				mDX -= mStoppingSpeed;
				if (mDX < 0) {
					mDX = 0;
				}
			} else if (mDX < 0) {
				mDX += mStoppingSpeed;
				if (mDX > 0) {
					mDX = 0;
				}
			}
		}

		if ((mIsAttacking) && !(mIsJumping || mIsFalling)) {
			mDX = 0;
		}

		if (mIsJumping && !mIsFalling) {
			mDY = mStartingJumpSpeed;
			mIsFalling = true;
		}

		if (mIsFalling) {
			mDY += mFallingSpeed;
			if (mDY < 0 && !mIsJumping)
				mDY += mStoppingJumpSpeed;
			if (mDY > mMaxFallSpeed)
				mDY = mMaxFallSpeed;
		}
	}

	private void setAnimation(int i) {
		mCurrentAction = i;
		mCurrentAnimation.setFrames(mSprites.get(mCurrentAction));
		mCurrentAnimation.setFrameInterval(mFrameIntervals[mCurrentAction]);
		mWidth = mFrameWidths[mCurrentAction];
		mHeight = mFrameLengths[mCurrentAction];

		System.out.println("SET ANIMATION " + mWidth + " " + mHeight);
	}

	public void setPosition(double x, double y) {
		mX = x;
		mY = y;
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

	public Rectangle2D getCharacterRect() {
		return mCharacterRect;
	}
}
