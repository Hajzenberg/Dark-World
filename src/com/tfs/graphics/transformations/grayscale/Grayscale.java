package com.tfs.graphics.transformations.grayscale;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

class Grayscale implements IGrayscale {

	@Override
	public WritableRaster grayscale(Raster raster,GrayscaleType grayscaleType) {
		
		if (raster == null || grayscaleType == null) {
			throw new IllegalArgumentException();
		}
		
		
		int grayColor = 0;
		WritableRaster target = raster.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				raster.getPixel(x, y, rgb);
				
				grayColor = grayscaleType.calculateGray(rgb[0],rgb[1], rgb[2]);
				
				rgb[0] = grayColor;
				rgb[1] = grayColor;
				rgb[2] = grayColor;
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}
	
}
