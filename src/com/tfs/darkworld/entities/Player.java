package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.tfs.darkworld.res.GameConstants;
import com.tfs.darkworld.util.ImageUtil;

import rafgfxlib.Util;

public class Player extends Character {

	private static final int ACTION_IDLE = 0;
	// private static final int ACTION_ATTACK = 1;
	private static final int ACTION_WALK = 4;
	private static final int ACTION_JUMP = 5;
	private static final int ACTION_FALL = 2;
	//private static final int ACTION_RUN = 3;

	private ArrayList<BufferedImage[]> mSprites;

	private int[] mNumOfFrames = { 12, 10, 8, 8, 8, 6 };
	private int[] mFrameWidths = { 128, 128, 128, 128, 128, 128 };
	private int[] mFrameLengths = { 128, 128, 128, 128, 128, 128 };
	private int[] mFrameIntervals = { 3, 3, 14, 3, 3, 3 };
	
	/* private static final int ACTION_IDLE = 0;
	// private static final int ACTION_ATTACK = 1;
	private static final int ACTION_WALK = 4;
	private static final int ACTION_JUMP = 5;
	private static final int ACTION_FALL = 2;
	//private static final int ACTION_RUN = 3;

	private ArrayList<BufferedImage[]> mSprites;

	private int[] mNumOfFrames = { 12, 10, 8, 8, 8, 6 };
	private int[] mFrameWidths = { 128, 128, 128, 128, 128, 128 };
	private int[] mFrameLengths = { 128, 128, 128, 128, 128, 128 };
	private int[] mFrameIntervals = { 3, 3, 14, 3, 3, 3 }; */

	protected Animation mCurrentAnimation;
	protected int mCurrentAction;

	public Player(int sw, int sh) {

		super(30, 30, 1.3, 30);

		mCurrentAnimation = new Animation();
		mJumpingForce = 6;

		try {

			BufferedImage spritesheet = Util.loadImage("design/character_sheet.png");

			int count = 0;
			mSprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < mNumOfFrames.length; i++) {
				BufferedImage[] bi = new BufferedImage[mNumOfFrames[i]];

				for (int j = 0; j < mNumOfFrames[i]; j++) {
					bi[j] = spritesheet.getSubimage(j * mFrameWidths[i], count, mFrameWidths[i], mFrameLengths[i]);
//					System.out.println(" frame " + (j * mFrameWidths[i]) + " " + (count) + " " + " " + mFrameWidths[i]
//							+ " " + mFrameLengths[i]);
				}
				mSprites.add(bi);
				count += mFrameLengths[i];

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAnimation(ACTION_WALK);
		// System.out.println("posle "+width + " " + height);
		mCharacterRect = new Rectangle2D.Double(50, 100, mWidth, mHeight);
	}

	@Override
	public void update() {

		if (mDY == 0) {
			mIsJumping = false;
		}

		getNextPosition();

		if (mX + mWidth > GameConstants.FRAME_WIDTH - 100) {
			mX = GameConstants.FRAME_WIDTH - 100 - mWidth;
		} else if (mX < 50) {
			mX = 50;
		}

		mCurrentAnimation.update();

		mCharacterRect.setRect(mX, mY, mWidth, mHeight);
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		// System.out.println(mX+" "+mY);
		// if (facingRight) {
		g.drawImage(mCurrentAnimation.getImage(), (int) mX, (int) mY, null);

		// } else {
		// g.drawImage(mCurrentAnimation.getImage(), (int) (mX + mWidth), (int) (mY),
		// (int) -mWidth, (int) mHeight, null);
		// }
		// g.drawRect((int)mX,(int)mY,(int) mWidth,(int) mHeight);
//		g.draw(mCharacterRect);
	}

	private double boundGreater(double x, double boundary) {
		if (x > boundary) {
			return x;
		}
		return boundary;
	}

	private double boundSmaller(double x, double boundary) {
		if (x < boundary) {
			return x;
		}
		return boundary;
	}

	private void getNextPosition() {
		mX += mDX;
		mY += mDY;
	}

	public void stop() {
		mDX = 0;
	}

	public void jump() {
		if (mDY == 0) {
			mIsJumping = true;
			mDY = -mJumpingForce;
		}
	}

	public void left() {
		mIsGoingLeft = true;
		// facingRight = false;
		mDX = -mSpeed;
	}

	public void right() {
		mIsGoingRight = true;
		// facingRight = true;
		mDX = +mSpeed;
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
		System.err.println("Just died!");
	}

	public Rectangle2D getCharacterRect() {
		return mCharacterRect;
	}

	@Override
	public void intersect(GameEntity ge) {
		IntersectType intersectType = isIntersecting(ge);

		switch (intersectType) {
		case UpperLine:
		case UpperLeftCorner:
		case UpperRightCorner:
			if (ge instanceof Lava) {
				die();
			}
			mDY = 0;
			mY = (ge.getPY1() - mHeight);
			break;
		default:
			break;
		}

	}
}
