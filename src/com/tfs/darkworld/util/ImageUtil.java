package com.tfs.darkworld.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(ImageUtil.class.getResource(path));
		} catch (IOException e) {
			System.out.println("Slika nije ucitana, ouch!\n");
			e.printStackTrace();
		}
		return image;
	}
}
