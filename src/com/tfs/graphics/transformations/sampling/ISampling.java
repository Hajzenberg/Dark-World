package com.tfs.graphics.transformations.sampling;

import java.awt.image.WritableRaster;

public interface ISampling {
	/**
	 * Skalira sliku na druge dimenzije
	 * 
	 * 
	 * @param raster
	 * @param samplingType
	 * @param width
	 * @param height
	 * @return
	 */
	WritableRaster scale(WritableRaster raster, SamplingType samplingType, int width, int height);
}
