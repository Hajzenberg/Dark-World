package com.tfs.graphics.transformations.brightness;

import java.awt.image.WritableRaster;

import com.tfs.graphics.util.TransformationUtils;

class Brightness implements IBrightness {
	
	@Override
	public WritableRaster brightness(WritableRaster raster, float intensity) {
		if (raster == null) {
			throw new IllegalArgumentException();
		}
		
		
		
		WritableRaster target = raster.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				raster.getPixel(x, y, rgb);
				
				rgb[0] = TransformationUtils.saturate((int)(rgb[0] * intensity)+rgb[0]);
				rgb[1] = TransformationUtils.saturate((int)(rgb[1] * intensity)+rgb[1]);
				rgb[2] = TransformationUtils.saturate((int)(rgb[2] * intensity)+rgb[2]);
				
				target.setPixel(x, y, rgb);
			}
			
		}
		return target;
	}

}
