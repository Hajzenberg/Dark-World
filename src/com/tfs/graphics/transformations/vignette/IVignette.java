package com.tfs.graphics.transformations.vignette;

import java.awt.image.WritableRaster;

public interface IVignette {
	/**
	 * Transform raster to vignette filter 
	 * @param source 
	 * @param vignette intensity of vignette from 0.0 to 1.0
	 * @param distMultiplier multiplier of radius. Smaller multiplier, greater darkness
	 * @return
	 */
	WritableRaster vignette(WritableRaster source, float vignette,float distMultiplier);
}
