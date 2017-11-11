package com.tfs.graphics.transformations.negative;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Interface for negative transformation
 * 
 * @author Corax
 *
 */
public interface INegative {
	/**
	 * Transforms raster into negative one
	 * 
	 * @param raster
	 * @return new object of negative raster
	 */
	WritableRaster negative(Raster raster);
}
