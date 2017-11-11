package com.tfs.graphics.transformations.noise;

import java.util.Random;

import com.tfs.graphics.util.TransformationUtils;

public enum NoiseType {
	MONOCHROME {
		@Override
		public void calculateRGB(int noise, int[] rgb) {
			double n = noise / 255.0;
			double factor = 1.0 + rnd.nextDouble() * n - n / 2.0; 
		
			rgb[0] = TransformationUtils.saturate((int)(rgb[0] * factor));
			rgb[1] = TransformationUtils.saturate((int)(rgb[1] * factor));
			rgb[2] = TransformationUtils.saturate((int)(rgb[2] * factor));
			
		}
	},
	COLORED {
		@Override
		public void calculateRGB(int noise, int[] rgb) {
			
			rgb[0] = TransformationUtils.saturate(rgb[0] + rnd.nextInt(noise) - noise / 2);
			rgb[1] = TransformationUtils.saturate(rgb[1] + rnd.nextInt(noise) - noise / 2);
			rgb[2] = TransformationUtils.saturate(rgb[2] + rnd.nextInt(noise) - noise / 2);
			
		}
	};
	private NoiseType() {}
	private static Random rnd = new Random();
	public abstract void calculateRGB(int noise, int[] rgb);
	
	
}
