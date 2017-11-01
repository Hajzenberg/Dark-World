package com.tfs.darkworld.res;

import java.awt.image.BufferedImage;

public class CommonRasters {
	
	private static BufferedImage lightBackground;
	private static BufferedImage darkBackground;
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
	
	
	
	
}
