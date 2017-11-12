package com.tfs.graphics.transformations.directionalblur;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

class DirectionalBlur implements IDirectionalBlur  {
	private static final int SAMPLE_COUNT = 32;
	@Override
	public WritableRaster blur(WritableRaster source, float vecX, float vecY) {
		
		if (source == null) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		
		int[] accum = new int[target.getNumBands()];
		
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				accum[0] = 0; accum[1] = 0; accum[2] = 0;
				
				for(int i = 0; i < SAMPLE_COUNT; i++)
				{
					float magnitude = (float)Math.random() - 0.5f;
					
					float srcX = x + magnitude * vecX;
					float srcY = y + magnitude * vecY;
					
					Util.bilSample(source, srcX, srcY, rgb);
					
					accum[0] += rgb[0];
					accum[1] += rgb[1];
					accum[2] += rgb[2];
				}
				
				accum[0] /= SAMPLE_COUNT;
				accum[1] /= SAMPLE_COUNT;
				accum[2] /= SAMPLE_COUNT;
				
				target.setPixel(x, y, accum);
			}
		}
		return target;
	}
	
}
