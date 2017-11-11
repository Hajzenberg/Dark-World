package com.tfs.graphics.transformations.grayscale;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Grayscale makes raster gray
 * 
 * @author Corax
 *
 */
public interface IGrayscale {
	/**
	 * Makes copy of raster, transform it and return transformed raster with gray colors
	 * 
	 * @param raster raster to transform
	 * @param grayscaleType type of grayscale
	 * @return returns transformed copy of raster
	 */
	WritableRaster grayscale(Raster raster, GrayscaleType grayscaleType);
}
