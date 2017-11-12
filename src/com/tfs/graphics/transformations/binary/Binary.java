package com.tfs.graphics.transformations.binary;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import com.tfs.graphics.transformations.grayscale.GrayscaleType;

/**
 * 
 * Algorithm for binary transformation
 * 
 * @author Corax
 *
 */
class Binary implements IBinary{
	


	@Override
	public WritableRaster binary(Raster raster, GrayscaleType grayscaleType, float binaryPercent, int dr, int dg,
			int db, int lr, int lg, int lb) {
		if (raster == null || (binaryPercent < 0.0f || binaryPercent > 1.0f)) {
			throw new IllegalArgumentException();
		}
		if ((dr < 0 || dr > 255) || (dg < 0 || dg > 255) || (db < 0 || db > 255) || (lr < 0 || lr > 255) || (lg < 0 || lg > 255) || (lb < 0 || lb > 255)) {
			throw new IllegalArgumentException("Color numbers must be between 0 and 255");
		}
		
		int[] rgb = new int[3];
		
		int border = (int)(255 * binaryPercent);
		int gray;
		
		WritableRaster target = raster.createCompatibleWritableRaster();
		
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				raster.getPixel(x, y, rgb);
				
				gray = grayscaleType.calculateGray(rgb[0], rgb[1], rgb[2]);
				
				if (gray > border) {
					rgb[0] = lr;
					rgb[1] = lg;
					rgb[2] = lb;
				}
				else {
					rgb[0] = dr;
					rgb[1] = dg;
					rgb[2] = db;
				}
				target.setPixel(x, y, rgb);
			}
			
		}
		return target;
	}
}
