package com.tfs.graphics.transformations.fisheye;

import java.awt.image.WritableRaster;

public interface IFisheye {
	/**
	 * Trasnforms raster in fisheye effect
	 * 
	 * @param source
	 * @param power intensity of effect, 0.0f is neutral, values > 0.0f do fisheye effect, values < 0.0f do opposite effect. Recommended values between 0.1f and 0.5f
	 * @return
	 */
	WritableRaster fisheye(WritableRaster source, float power);
}
