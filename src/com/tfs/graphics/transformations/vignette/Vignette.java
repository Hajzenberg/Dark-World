package com.tfs.graphics.transformations.vignette;

import java.awt.image.WritableRaster;

class Vignette implements IVignette {

	@Override
	public WritableRaster vignette(WritableRaster source, float vignette,float distMultiplier) {
		if (source == null || vignette < 0.0f || vignette > 1.0f || distMultiplier <= 0f) {
			throw new IllegalArgumentException();
		}
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int rgb[] = new int[target.getNumBands()];
		
		final double radius = Math.sqrt(2) / 2.0;
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				source.getPixel(x, y, rgb);
		
				double fX = x / (double)source.getWidth();
				double fY = y / (double)source.getHeight();
				
				double dist = Math.sqrt((fX - 0.5) * (fX - 0.5) + (fY - 0.5) * (fY - 0.5)) / radius;
				
				dist = Math.pow(dist, distMultiplier);
				
				rgb[0] = (int)(rgb[0] * (1.0 - dist * vignette));
				rgb[1] = (int)(rgb[1] * (1.0 - dist * vignette));
				rgb[2] = (int)(rgb[2] * (1.0 - dist * vignette));
				
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}

}
