package com.tfs.graphics.transformations.waves;

import java.awt.image.WritableRaster;

public interface IWaves {
	WritableRaster wave(WritableRaster source, float power, float size);
}
