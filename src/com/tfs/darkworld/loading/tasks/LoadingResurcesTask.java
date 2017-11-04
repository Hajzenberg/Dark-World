package com.tfs.darkworld.loading.tasks;

import java.awt.image.BufferedImage;

import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.res.CommonRasters;

import rafgfxlib.Util;

public class LoadingResurcesTask implements ITask {
	
	private String playerSpriteSheetPath = "design/Undead_Knight_F_noBG.png";
	private String mountainImagePath = "design/mountain.png";
	private String menuBackgroundImgPath = "design/GatesOfHell.png";
	private String skullImagePath = "design/skull.png";
	
	@Override
	public void doTask(IProgressListener progressListener, ITaskListener taskListener) {
		
		// zbir progresa mora da bude 100
		
		taskListener.updateTaskName("Loading images...");
		progressListener.updateProgress(0);
		BufferedImage playerSpritesheet = Util.loadImage(playerSpriteSheetPath);
		BufferedImage menuBackgroundImage = Util.loadImage(menuBackgroundImgPath);
		BufferedImage rotatingSkullImage = Util.loadImage(skullImagePath);
		BufferedImage mountainImage = Util.loadImage(mountainImagePath);
		progressListener.updateProgress(50);
		CommonRasters.setMenuBackgroundImg(menuBackgroundImage);
		CommonRasters.setPlayerSptitesheet(playerSpritesheet);
		CommonRasters.setParticleImg(rotatingSkullImage);
		CommonRasters.setMountainBackground(mountainImage);
		progressListener.updateProgress(50);
	}
}
