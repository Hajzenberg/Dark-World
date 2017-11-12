package com.tfs.graphics.transformations.zoomblur;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

public class ZoomBlur implements IZoomBlur {
	private static final int SAMPLE_CNT = 100;
	@Override
	public WritableRaster zoomBlur(WritableRaster source, float strength) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		int[] accum = new int[target.getNumBands()];
				
		// Centar zumiranja moze biti na proizvoljnoj poziciji, ovdje ga
		// postavljamo na centar slike
		int centerX = source.getWidth() / 2;
		int centerY = source.getHeight() / 2;
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				accum[0] = 0; accum[1] = 0; accum[2] = 0;
				
				for(int i = 0; i < SAMPLE_CNT; i++)
				{
					float magnitude = 1.0f + ((float)Math.random() - 0.5f) * strength;
					
					float srcX = centerX + (x - centerX) * magnitude;
					float srcY = centerY + (y - centerY) * magnitude;
					
					Util.bilSample(source, srcX, srcY, rgb);
					
					accum[0] += rgb[0];
					accum[1] += rgb[1];
					accum[2] += rgb[2];
				}
				
				accum[0] /= SAMPLE_CNT;
				accum[1] /= SAMPLE_CNT;
				accum[2] /= SAMPLE_CNT;
				
				target.setPixel(x, y, accum);
			}
		}
		return target;
		
	}

}
