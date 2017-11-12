package com.tfs.darkworld.loading.tasks;

import java.awt.image.BufferedImage;

import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.graphics.transformations.noise.NoiseInvoker;
import com.tfs.graphics.transformations.vignette.VignetteInvoker;

import rafgfxlib.Util;

public class LoadingResurcesTask implements ITask {

	/* Menu state */
	private String menuBackgroundImgPath = "design/GatesOfHell.png";
	// private String skullImagePath = "design/skull.png";
	private String handImagePath = "design/hand.png";
	private String skullImagePath = "design/maks_ferhagen.png";

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

	// About state
	private String maxoImg = "design/maxoImg.png";
	private String drazaImg = "design/djoleImg.png";
	private String djoleImg = "design/drazaImg.png";

	@Override
	public void doTask(IProgressListener progressListener, ITaskListener taskListener) {

		// zbir progresa mora da bude 100

		taskListener.updateTaskName("Loading images...");
		progressListener.updateProgress(0);

		CommonRasters.setParticleImg(Util.loadImage(skullImagePath));
		CommonRasters.setMenuBackgroundImg(Util.loadImage(menuBackgroundImgPath));
		CommonRasters.setHandImage(Util.loadImage(handImagePath));

		progressListener.updateProgress(10);

		CommonRasters.setGroundTile(Util.loadImage(tileGround));
		CommonRasters.setLavaTile(Util.loadImage(lavaGround));
		CommonRasters.setSpikes(Util.loadImage(spikesGround));

		progressListener.updateProgress(20);

		CommonRasters.setCoinSheet(Util.loadImage(coinImagePath));
		CommonRasters.setExplosionSheet(Util.loadImage(explosionImagePath));
		CommonRasters.setRocketSheet(Util.loadImage(rocketImagePath));

		progressListener.updateProgress(10);

		CommonRasters.setPlayerSptitesheet(Util.loadImage(playerSpriteSheetPath));

		BufferedImage image = Util.loadImage(mountainImagePath);
		CommonRasters
				.setMountainBackground(Util.rasterToImage(new VignetteInvoker(1f, 1.4f).process(image.getRaster())));
		CommonRasters.setMountainBackgroundNoise(
				Util.rasterToImage(new VignetteInvoker(1f, 1.4f).process(image.getRaster())));
		CommonRasters.setMountainBackgroundVignette(
				Util.rasterToImage(new VignetteInvoker(0.8f, 2f).process(image.getRaster())));
		progressListener.updateProgress(30);

		image = Util.loadImage(forestImagePath);
		CommonRasters.setForestBackground(Util.rasterToImage(new VignetteInvoker(1f, 1.4f).process(image.getRaster())));
		CommonRasters
				.setForestBackgroundNoise(Util.rasterToImage(new VignetteInvoker(1f, 1.4f).process(image.getRaster())));
		CommonRasters.setForestBackgroundVignette(
				Util.rasterToImage(new VignetteInvoker(0.8f, 2f).process(image.getRaster())));

		CommonRasters.setMaxoImg(Util.loadImage(maxoImg));
		CommonRasters.setDjoleImg(Util.loadImage(djoleImg));
		CommonRasters.setDrazaImg(Util.loadImage(drazaImg));
		progressListener.updateProgress(30);
		
	}
}
