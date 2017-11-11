package com.tfs.darkworld.loading.tasks;

import com.tfs.darkworld.effects.EffectsUtil;
import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.res.CommonRasters;

import rafgfxlib.Util;

public class LoadingResurcesTask implements ITask {
	
	/* Menu state */
	private String menuBackgroundImgPath = "design/GatesOfHell.png";
	private String skullImagePath = "design/skull.png";
	private String handImagePath = "design/hand.png";
	
	/* Gameplay state */
	private String tileGround = "design/ground1.jpg";
	private String lavaGround = "design/lava.jpg";
	private String spikesGround = "design/siljci2.png";
	private String playerSpriteSheetPath = "design/character_sheet.png";
	private String mountainImagePath = "design/mountain.png";
	private String forestImagePath = "design/forest.png";
	private String coinImagePath = "design/coin.png";
	private String explosionImagePath = "design/explosion.png";
	private String rocketImagePath = "design/rocket.png";

	@Override
	public void doTask(IProgressListener progressListener, ITaskListener taskListener) {
		
		// zbir progresa mora da bude 100
		
		taskListener.updateTaskName("Loading images...");
		progressListener.updateProgress(0);
		
		CommonRasters.setParticleImg(Util.loadImage(skullImagePath));
		CommonRasters.setMenuBackgroundImg(Util.loadImage(menuBackgroundImgPath));
		CommonRasters.setHandImage(Util.loadImage(handImagePath));

		progressListener.updateProgress(20);
		
		CommonRasters.setGroundTile(Util.loadImage(tileGround));
		CommonRasters.setLavaTile(Util.loadImage(lavaGround));
		CommonRasters.setSpikes(Util.loadImage(spikesGround));
		
		progressListener.updateProgress(20);
		
		CommonRasters.setCoinSheet(Util.loadImage(coinImagePath));
		CommonRasters.setExplosionSheet(Util.loadImage(explosionImagePath));
		CommonRasters.setRocketSheet(Util.loadImage(rocketImagePath));
		
		progressListener.updateProgress(10);
		
		CommonRasters.setPlayerSptitesheet(Util.loadImage(playerSpriteSheetPath));
		
		CommonRasters.setMountainBackground(Util.loadImage(mountainImagePath));
		CommonRasters.setMountainBackgroundNoise(EffectsUtil.applyVignette(CommonRasters.getMountainBackground(), 1.2));
		CommonRasters.setMountainBackgroundVignette(EffectsUtil.applyVignette(CommonRasters.getMountainBackground(), 0.8));
		
		CommonRasters.setForestBackground(Util.loadImage(forestImagePath));
		CommonRasters.setForestBackgroundNoise(EffectsUtil.applyVignette(CommonRasters.getForestBackground(), 1.2));
		CommonRasters.setForestBackgroundVignette(EffectsUtil.applyVignette(CommonRasters.getForestBackground(), 0.8));
		
		progressListener.updateProgress(50);
	}
}
