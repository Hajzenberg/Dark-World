package com.tfs.graphics.transformations.posterize;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Posterization algorithm
 * 
 * 
 * @author Corax
 *
 */
class Posterize implements IPosterize {

	@Override
	public WritableRaster posterize(Raster raster, float posterizeIntesity) {
		if (raster == null) {
			throw new IllegalArgumentException();
		}
		if (posterizeIntesity < 0.0f || posterizeIntesity > 1.0f) {
			throw new IllegalArgumentException("Intensitiy must be between 0.0 and 1.0");
		}
		

		int scale = (int) (255 * posterizeIntesity);

		WritableRaster target = raster.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				raster.getPixel(x, y, rgb);

				rgb[0] = (rgb[0] / scale) * scale;
				rgb[1] = (rgb[1] / scale) * scale;
				rgb[2] = (rgb[2] / scale) * scale;
				
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}

}
