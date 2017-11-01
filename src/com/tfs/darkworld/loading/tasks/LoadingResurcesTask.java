package com.tfs.darkworld.loading.tasks;

import java.awt.image.BufferedImage;

import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.res.CommonRasters;

import rafgfxlib.Util;

public class LoadingResurcesTask implements ITask {
	
	private String playerSpriteSheet = "design/Undead_Knight_F_noBG.png";
	
	
	@Override
	public void doTask(IProgressListener progressListener, ITaskListener taskListener) {
		
		// zbir progresa mora da bude 100
		
		taskListener.updateTaskName("Loading images...");
		progressListener.updateProgress(0);
		BufferedImage playerSpritesheet = Util.loadImage(playerSpriteSheet);
		progressListener.updateProgress(50);
		CommonRasters.setPlayerSptitesheet(playerSpritesheet);
		progressListener.updateProgress(50);
	}
}
