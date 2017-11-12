package com.tfs.graphics.transformations.waves;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

public class Waves implements IWaves {

	@Override
	public WritableRaster wave(WritableRaster source, float power, float size) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		
		for(int y = 0; y < source.getHeight(); y++)
		{			
			for(int x = 0; x < source.getWidth(); x++)
			{
				
				float srcX = (float)(x + Math.sin(y * size) * power);
				float srcY = (float)(y + Math.cos(x * size) * power);
				
				
				// Koristimo deformisane koordinate za citanje bilinearnog uzorka
				Util.bilSample(source, srcX, srcY, rgb);
				target.setPixel(x, y, rgb);
			}
		}
		
		return target;
	}

}
