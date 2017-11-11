package com.tfs.graphics.transformations.noise;

import java.awt.image.WritableRaster;

public interface INoise {
	/**
	 * Creates noise on raster
	 * 
	 * @param source
	 * @param noiseType
	 * @param noiseIntensity
	 * @return
	 */
	WritableRaster noise(WritableRaster source, NoiseType noiseType, float noiseIntensity);
}
