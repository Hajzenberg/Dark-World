package com.tfs.graphics.transformations.posterize;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Interface for posterization algorithm
 * 
 * @author Corax
 *
 */
public interface IPosterize {
	/**
	 * this method do posterization of raster using posterize intensity as a paramater.
	 * 
	 * @param raster
	 * @param posterizeIntesity values can be from 0.0f to 1.0f. Closer to 1.0f posterization become stronger
	 * @return
	 */
	WritableRaster posterize(Raster raster, float posterizeIntesity);
}
