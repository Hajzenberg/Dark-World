package com.tfs.darkworld.entities;

import java.awt.image.BufferedImage;

public class Animation {
	
	private IFinishingAnimationListener finishingAnimationListeners;
	private BufferedImage[] mFrames;
	private int mCurrentFrameIndex;
	private int mNumOfFrames;
	
	private int mIntervalCounter;
	private int mFrameInterval;
	private int mTimesPlayed;
	
	public void setFrames(BufferedImage[] frames) {
		mFrames = frames;
		mCurrentFrameIndex = 0;
		mIntervalCounter = 0;
		mTimesPlayed = 0;
		mFrameInterval = 2;
		mNumOfFrames = frames.length;
	}
	
	
	public void setFinishingAnimationListeners(IFinishingAnimationListener finishingAnimationListeners) {
		this.finishingAnimationListeners = finishingAnimationListeners;
	}
	public void setFrameInterval(int i) { mFrameInterval = i; }
	public void setFrame(int i) { mCurrentFrameIndex = i; }
	public void setNumFrames(int i) { mNumOfFrames = i; }
	
	public void update() {
		
		if(mFrameInterval == -1) return;
		
		mIntervalCounter++;
		
		if(mIntervalCounter == mFrameInterval) {
			mCurrentFrameIndex++;
			mIntervalCounter = 0;

		}
		if(mCurrentFrameIndex == mNumOfFrames) {
			mCurrentFrameIndex = 0;
			mTimesPlayed++;
		}
		if (mCurrentFrameIndex == mNumOfFrames -1) {
			if (finishingAnimationListeners != null) {
				finishingAnimationListeners.animationFinished();
			}
		}
		
	}
	
	public int getFrame() { return mCurrentFrameIndex; }
	public int getCount() { return mIntervalCounter; }
	public BufferedImage getImage() { return mFrames[mCurrentFrameIndex]; }
	public boolean hasPlayedOnce() { return mTimesPlayed > 0; }
	public boolean hasPlayed(int i) { return mTimesPlayed == i; }
	
}