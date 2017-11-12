package com.tfs.graphics.generators.gradientperlin;

import java.awt.image.WritableRaster;

public interface IGradientPerlinGenerator {
	/**
	 * Generates Perlin noise in gradient. For gradient, RasterUtil method called imageToGradient can be used.
	 * 
	 * @param octave number of octaves
	 * @param persistece value between 0.0 and 1.0, represents strenght of perlin noise
	 * @param gradient matrix dimensions N x 3.
	 * @return
	 */
	WritableRaster generateGradientPerlin(int octave, float persistece, int[][] gradient);
}
