package com.tfs.graphics.generators.perlinnoise;

import java.awt.image.WritableRaster;

public interface IPerlinNoiseGenerator {
	/**
	 * Generates perlin noise in two colors
	 * 
	 * @param octaves number of octaves
	 * @param persistence strenght of perlin
	 * @param firstColor
	 * @param secondColor
	 * @return
	 */
	WritableRaster generatePerlinNoise(int octaves, float persistence,int[] firstColor, int[] secondColor);
}
