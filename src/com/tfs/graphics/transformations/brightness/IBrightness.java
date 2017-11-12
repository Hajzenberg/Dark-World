package com.tfs.graphics.transformations.brightness;

import java.awt.image.WritableRaster;

public interface IBrightness {
	WritableRaster brightness(WritableRaster raster, float intensity);
}
