package com.tfs.darkworld.loading.tasks;

import java.awt.image.BufferedImage;

import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.res.CommonRasters;

import rafgfxlib.Util;

public class LoadingResurcesTask implements ITask {
	
	/* Menu state */
	private String menuBackgroundImgPath = "design/GatesOfHell.png";
	private String skullImagePath = "design/skull.png";
	
	/* Gameplay state */
	private String tileGround = "design/ground1.jpg";
	private String lavaGround = "design/lava.jpg";

	private String playerSpriteSheetPath = "design/character_sheet.png";
	private String mountainImagePath = "design/mountain.png";

	private String spikesGround = "design/siljci2.png";


	@Override
	public void doTask(IProgressListener progressListener, ITaskListener taskListener) {
		
		// zbir progresa mora da bude 100
		
		taskListener.updateTaskName("Loading images...");
		progressListener.updateProgress(0);
		
		CommonRasters.setParticleImg(Util.loadImage(skullImagePath));
		CommonRasters.setGroundTile(Util.loadImage(tileGround));
		CommonRasters.setLavaTile(Util.loadImage(lavaGround));

		progressListener.updateProgress(50);
		
		BufferedImage img = Util.loadImage(playerSpriteSheetPath);
		
		if (img == null){
			System.out.println("NULL");
		} else {
			System.out.println(img.getWidth()+" "+img.getHeight());
		}
		
		CommonRasters.setMenuBackgroundImg(Util.loadImage(menuBackgroundImgPath));
		CommonRasters.setPlayerSptitesheet(img);
		CommonRasters.setMountainBackground(Util.loadImage(mountainImagePath));
		CommonRasters.setSpikes(Util.loadImage(spikesGround));
		
		progressListener.updateProgress(50);
	}
}
