package com.tfs.graphics.transformations.binary;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import com.tfs.graphics.transformations.grayscale.GrayscaleType;

public interface IBinary {
	/**
	 * Makes copy of raster, transforms it binary and return copy.
	 * @param raster 
	 * @param grayscaleType color evaluation method
	 * @param binaryPercent is parameter from 0.0f to 1.0f. if closer to zero, picture contains more darker surfaces, and vice versa
	 * @param darkColor dark color in rgb
	 * @param lightColor light color in rgb
	 * @return transformed copy of original raster
	 */
	WritableRaster binary(Raster raster, GrayscaleType grayscaleType, float binaryPercent, int dr, int dg, int db, int lr, int lg, int lb );
}
