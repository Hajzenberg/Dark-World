package com.tfs.graphics.transformations.sharpen;

import java.awt.image.WritableRaster;

public interface ISharpen {
	WritableRaster sharp(WritableRaster source, float intensity);
}
