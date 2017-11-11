package com.tfs.graphics.generators.perlinnoise;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

public class PerlinNoiseGenerator implements IPerlinNoiseGenerator {


	@Override
	public WritableRaster generatePerlinNoise(int octaves, float persistence, int[] firstColor, int[] secondColor) {
		if (octaves < 2 || persistence < 0.0f || persistence > 1.0f || firstColor.length < 3 || secondColor.length < 3) {
			throw new IllegalArgumentException();
		}
		
		int octaveSize = 2;

		int width = (int)Math.pow(octaveSize, octaves);
		int height = width;

		WritableRaster target = Util.createRaster(width, height,false);

		float[][] tempMap = new float[width][height];

		float[][] finalMap = new float[width][height];

		float multiplier = 1.0f;

		for(int o = 0; o < octaves; ++o)
		{
			float[][] octaveMap = new float[octaveSize][octaveSize];

			for(int x = 0; x < octaveSize; ++x)
			{
				for(int y = 0; y < octaveSize; ++y)
				{
					octaveMap[x][y] = ((float)Math.random() - 0.5f) * 2.0f;
				}
			}

			Util.floatMapRescaleCos(octaveMap, tempMap);

			Util.floatMapMAD(tempMap, finalMap, multiplier);

			octaveSize *= 2;

			multiplier *= persistence;
		}

		Util.mapFloatMapToRaster(finalMap, -1.0f, 1.0f, firstColor, secondColor, target);
		return target;
	}

}
