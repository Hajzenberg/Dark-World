package com.tfs.graphics.transformations.flip;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Interface of flip transformation
 * @author Corax
 *
 */
public interface IFlip {
	/**
	 * Flips raster in chosen direction
	 * @param raster
	 * @param flipDirection
	 * @return
	 */
	WritableRaster flip(Raster raster,FlipDirection flipDirection);
}
