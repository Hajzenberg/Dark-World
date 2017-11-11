package com.tfs.darkworld.effects;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;

import rafgfxlib.Util;

public class EffectsUtil {
	
	public static BufferedImage applyNoiseEffect(BufferedImage image){
		//BufferedImage image = Util.loadImage("design/mountain.png");

		if (image == null) {
			System.out.println("Nema slike!");
			return null;
		}

		WritableRaster source = image.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), true);

		int rgb[] = new int[4];

		int noise = 50;
		Random rnd = new Random();

		for (int y = 0; y < source.getHeight(); y++) {
			for (int x = 0; x < source.getWidth(); x++) {
				source.getPixel(x, y, rgb);
				
				rgb[0] = saturate(rgb[0] + rnd.nextInt(noise) - noise / 2);
				rgb[1] = saturate(rgb[1] + rnd.nextInt(noise) - noise / 2);
				rgb[2] = saturate(rgb[2] + rnd.nextInt(noise) - noise / 2);

				target.setPixel(x, y, rgb);
			}
		}
		
		return Util.rasterToImage(target);

//		BufferedImage i = Util.rasterToImage(target);
//
//		Util.saveImage(i, "design/mountain_finish.png");
	}
	
	public static BufferedImage applyVignette(BufferedImage image, double impact){
		//BufferedImage image = Util.loadImage("design/mountain.png");
		
		if(image == null) { System.out.println("Nema slike!"); return null; }
		
		WritableRaster source = image.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), true);
		
		int rgb[] = new int[4];
		
		// Jacina efekta
		double vignette = impact;
		
		// Poluprecnik kruga opisanog oko jedinicnog kvadrata
		final double radius = Math.sqrt(2) / 2.0;
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				source.getPixel(x, y, rgb);
		
				// Pronalazimo normalizovane (X,Y) koordinate u 0-1 opsegu
				double fX = x / (double)source.getWidth();
				double fY = y / (double)source.getHeight();
				
				// Daljina normalizovanih koordinata od centra kruga (0.5, 0.5)
				double dist = Math.sqrt((fX - 0.5) * (fX - 0.5) + (fY - 0.5) * (fY - 0.5)) / radius;
				
				// Daljinu stepenujemo da bismo dobili nelinearnu progresiju,
				// iz estetskih razloga (zapamtimo da smo u 0-1 opsegu, 0 ce
				// i dalje biti 0, 1 ce biti 1, samo se mijenja oblik krive
				// izmedju tih krajeva).
				dist = Math.pow(dist, 1.8);
				
				// Trenutnu boju potamnjujemo zavisno od daljine i zeljene
				// jacine vignette efekta.
				rgb[0] = (int)(rgb[0] * (1.0 - dist * vignette));
				rgb[1] = (int)(rgb[1] * (1.0 - dist * vignette));
				rgb[2] = (int)(rgb[2] * (1.0 - dist * vignette));
				
				target.setPixel(x, y, rgb);
			}
		}
	
		//ImageViewer.showImageWindow(Util.rasterToImage(target), image, "RAF Racunarska Grafika");
		
//		BufferedImage i = Util.rasterToImage(target);
//		
//		Util.saveImage(i, "design/mountain_finish.png");
		
		return Util.rasterToImage(target);
	}
	
	public static int clamp(int value, int min, int max) {
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;
	}

	public static int saturate(int value) {
		return clamp(value, 0, 255);
	}
	
}
