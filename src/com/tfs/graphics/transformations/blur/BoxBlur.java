package com.tfs.graphics.transformations.blur;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

class BoxBlur implements IBlur {

	@Override
	public WritableRaster blur(WritableRaster source, int blurPower) {
		
		if (source == null || blurPower < 0) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int rgb[] = new int[target.getNumBands()];
		
		int accum[] = new int[target.getNumBands()];
		
		int radius = blurPower;
		
		int pixCount = (radius * 2 + 1) * (radius * 2 + 1);
		
		int width = source.getWidth();
		int height = source.getHeight();
		
		for(int y = 0; y < height; y++)
		{	
			for(int x = 0; x < width; x++)
			{
				accum[0] = 0; accum[1] = 0; accum[2] = 0;
				
				for(int Y = -radius; Y <= radius; Y++)
				{
					for(int X = -radius; X <= radius; X++)
					{
						source.getPixel(Util.clamp(x + X, 0, width - 1), 
								Util.clamp(y + Y, 0, height - 1), rgb);
						
						accum[0] += rgb[0];
						accum[1] += rgb[1];
						accum[2] += rgb[2];
					}
				}
				
				rgb[0] = accum[0] / pixCount;
				rgb[1] = accum[1] / pixCount;
				rgb[2] = accum[2] / pixCount;
				
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}

}
