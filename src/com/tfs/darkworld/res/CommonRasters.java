package com.tfs.darkworld.res;

import java.awt.image.BufferedImage;

public class CommonRasters {

	private static BufferedImage lastScreenCapture;
	private static BufferedImage lastTransformedScreenCapture;
	
	/* Menu */
	private static BufferedImage menuBackgroundImg;
	private static BufferedImage partImg;

	/* GamePlayState */
	private static BufferedImage lightSky;
	private static BufferedImage darkSky;
	private static BufferedImage mountainBackground;
	private static BufferedImage forestBackground;
	private static BufferedImage playerSptitesheet;
	private static BufferedImage coinSheet;
	private static BufferedImage rocketSheet;
	private static BufferedImage explosionSheet;
	private static BufferedImage groundTile;
	private static BufferedImage lavaTile;
	private static BufferedImage dyingSnapshot;
	private static BufferedImage spikes;

	public static BufferedImage getForestBackground() {
		return forestBackground;
	}

	public static void setForestBackground(BufferedImage forestBackground) {
		CommonRasters.forestBackground = forestBackground;
	}

	public static BufferedImage getLightSky() {
		return lightSky;
	}

	public static void setLightSky(BufferedImage lightBackground) {
		CommonRasters.lightSky = lightBackground;
	}

	public static BufferedImage getDarkSky() {
		return darkSky;
	}

	public static void setDarkSky(BufferedImage darkBackground) {
		CommonRasters.darkSky = darkBackground;
	}

	public static BufferedImage getPlayerSptitesheet() {
		return playerSptitesheet;
	}

	public static void setPlayerSptitesheet(BufferedImage playerSptitesheet) {
		CommonRasters.playerSptitesheet = playerSptitesheet;
	}

	public static BufferedImage getMenuBackgroundImg() {
		return menuBackgroundImg;
	}

	public static void setMenuBackgroundImg(BufferedImage menuBackgroundImg) {
		CommonRasters.menuBackgroundImg = menuBackgroundImg;
	}

	public static void setLastScreenCapture(BufferedImage lastScreenCapture) {
		CommonRasters.lastScreenCapture = lastScreenCapture;
	}

	public static BufferedImage getLastScreenCapture() {
		return lastScreenCapture;
	}

	public static BufferedImage getLastTransformedScreenCapture() {
		return lastTransformedScreenCapture;
	}

	public static void setLastTransformedScreenCapture(BufferedImage lastTransformedScreenCapture) {
		CommonRasters.lastTransformedScreenCapture = lastTransformedScreenCapture;
	}

	public static BufferedImage getParticleImg() {
		return partImg;
	}

	public static void setParticleImg(BufferedImage partImg) {
		CommonRasters.partImg = partImg;
	}

	public static BufferedImage getMountainBackground() {
		return mountainBackground;
	}

	public static void setMountainBackground(BufferedImage mountainBackground) {
		CommonRasters.mountainBackground = mountainBackground;
	}
	
	public static BufferedImage getDyingSnapshot() {
		return dyingSnapshot;
	}
	
	public static void setDyingSnapshot(BufferedImage dyingSnapshot) {
		CommonRasters.dyingSnapshot = dyingSnapshot;
	}

	public static void setSpikes(BufferedImage loadImage) {
		CommonRasters.spikes = loadImage;
	}
	
	public static BufferedImage getSpikes() {
		return spikes;
	}
	
	public static BufferedImage getLavaTile() {
		return lavaTile;
	}
	
	public static void setLavaTile(BufferedImage lavaTile) {
		CommonRasters.lavaTile = lavaTile;
	}
	
	public static BufferedImage getGroundTile() {
		return groundTile;
	}
	
	public static void setGroundTile(BufferedImage groundTile) {
		CommonRasters.groundTile = groundTile;
	}

	public static BufferedImage getCoinSheet() {
		return coinSheet;
	}

	public static void setCoinSheet(BufferedImage coinSheet) {
		CommonRasters.coinSheet = coinSheet;
	}

	public static BufferedImage getRocketSheet() {
		return rocketSheet;
	}

	public static void setRocketSheet(BufferedImage rocketSheet) {
		CommonRasters.rocketSheet = rocketSheet;
	}

	public static BufferedImage getExplosionSheet() {
		return explosionSheet;
	}

	public static void setExplosionSheet(BufferedImage explosionSheet) {
		CommonRasters.explosionSheet = explosionSheet;
	}
	
	
	
}
