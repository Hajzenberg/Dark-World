package com.tfs.graphics.transformations.blur;

import java.awt.image.WritableRaster;

public interface IBlur {
	/**
	 * @param writableRaster
	 * @param blurPower goes from 0 to INT_MAX
	 * @return
	 */
	WritableRaster blur(WritableRaster writableRaster, int blurPower);
}
