package com.tfs.graphics.transformations.blur;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

class GaussianBlur implements IBlur {

	@Override
	public WritableRaster blur(WritableRaster source, int radius) {
		if (source == null || radius < 0) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster temp = source.createCompatibleWritableRaster();
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int rgb[] = new int[target.getNumBands()];
		float accum[] = new float[target.getNumBands()];
		
		int width = source.getWidth();
		int height = source.getHeight();
		
		float[] factors = new float[radius * 2 + 1];
		
		float sum = 0.0f;
		
		
		for(int i = 0; i < radius * 2 + 1; i++){
			factors[i] = (float)Math.cos(((i - radius) / (double)(radius)) * (Math.PI / 2.0));
			sum += factors[i];
		}
		
		for(int i = 0; i < radius * 2 + 1; i++) {
			factors[i] /= sum;
		}
		
		
		for(int y = 0; y < height; y++)
		{	
			for(int x = 0; x < width; x++)
			{
				accum[0] = 0.0f; accum[1] = 0.0f; accum[2] = 0.0f;
				
				for(int innterX = -radius; innterX <= radius; innterX++){
					
					source.getPixel(Util.clamp(x + innterX, 0, width - 1), y, rgb);
					
					accum[0] += rgb[0] * factors[innterX + radius];
					accum[1] += rgb[1] * factors[innterX + radius];
					accum[2] += rgb[2] * factors[innterX + radius];
				}
				
				rgb[0] = (int)accum[0];
				rgb[1] = (int)accum[1];
				rgb[2] = (int)accum[2];
				
				temp.setPixel(x, y, rgb);
			}
		}
		
		for(int y = 0; y < height; y++)
		{	
			for(int x = 0; x < width; x++)
			{
				accum[0] = 0.0f; accum[1] = 0.0f; accum[2] = 0.0f;
				
				for(int Y = -radius; Y <= radius; Y++)
				{
					temp.getPixel(x, Util.clamp(y + Y, 0, height - 1), rgb);
					
					accum[0] += rgb[0] * factors[Y + radius];
					accum[1] += rgb[1] * factors[Y + radius];
					accum[2] += rgb[2] * factors[Y + radius];
				}
				
				rgb[0] = (int)accum[0];
				rgb[1] = (int)accum[1];
				rgb[2] = (int)accum[2];
				
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}

}
