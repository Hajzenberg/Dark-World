package com.tfs.graphics.transformations.negative;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Implementation of negative raster transformation
 * 
 * @author Corax
 *
 */
class Negative implements INegative {

	@Override
	public WritableRaster negative(Raster raster) {
		
		if (raster == null) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster target = raster.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				raster.getPixel(x, y, rgb);
				rgb[0] = 255 - rgb[0];
				rgb[1] = 255 - rgb[1];
				rgb[2] = 255 - rgb[2];
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}

}
