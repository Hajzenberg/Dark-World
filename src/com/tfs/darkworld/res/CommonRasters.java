package com.tfs.darkworld.res;

import java.awt.image.BufferedImage;

public class CommonRasters {

	private static BufferedImage lastScreenCapture;
	private static BufferedImage lastTransformedScreenCapture;
	private static BufferedImage dyingSnapshot;
	
	/* Menu */
	private static BufferedImage menuBackgroundImg;
	private static BufferedImage partImg;

	private static BufferedImage groundTile;
	private static BufferedImage lavaTile;
	
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

	/* GamePlayState */
	private static BufferedImage lightBackground;
	private static BufferedImage darkBackground;
	private static BufferedImage mountainBackground;
	private static BufferedImage playerSptitesheet;


	public static BufferedImage getLightBackground() {
		return lightBackground;
	}

	public static void setLightBackground(BufferedImage lightBackground) {
		CommonRasters.lightBackground = lightBackground;
	}

	public static BufferedImage getDarkBackground() {
		return darkBackground;
	}

	public static void setDarkBackground(BufferedImage darkBackground) {
		CommonRasters.darkBackground = darkBackground;
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
	
}
