package com.tfs.graphics.transformations.directionalblur;

import java.awt.image.WritableRaster;

public interface IDirectionalBlur {
	WritableRaster blur(WritableRaster source, float directionX, float directionY);
}
