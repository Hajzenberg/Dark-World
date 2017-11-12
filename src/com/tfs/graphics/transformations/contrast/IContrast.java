package com.tfs.graphics.transformations.contrast;

import java.awt.image.WritableRaster;

/**
 * Interface of contrast algorithm
 * 
 * @author Corax
 *
 */
public interface IContrast {
	/**
	 * @param source 
	 * @param intensity if value 0.0 there are no changes, if value below 0.0, contrast will be lower, if value above 0.0f contrast will be higher
	 * @return transformed copy of raster
	 */
	WritableRaster contrast(WritableRaster source, float intensity);
}
