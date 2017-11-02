package com.tfs.darkworld.loading.tasks;

import java.awt.image.BufferedImage;

import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.res.CommonRasters;

import rafgfxlib.Util;

public class LoadingResurcesTask implements ITask {
	
	private String playerSpriteSheet = "design/Undead_Knight_F_noBG.png";
	
	private String menuBackgroundImg = "design/GatesOfHell.png";
	
	@Override
	public void doTask(IProgressListener progressListener, ITaskListener taskListener) {
		
		// zbir progresa mora da bude 100
		
		taskListener.updateTaskName("Loading images...");
		progressListener.updateProgress(0);
		BufferedImage playerSpritesheet = Util.loadImage(playerSpriteSheet);
		BufferedImage menuBackgroundImage = Util.loadImage(menuBackgroundImg);
		progressListener.updateProgress(50);
		CommonRasters.setMenuBackgroundImg(menuBackgroundImage);
		CommonRasters.setPlayerSptitesheet(playerSpritesheet);
		progressListener.updateProgress(50);
		
	}
}
