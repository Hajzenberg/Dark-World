package com.tfs.graphics.transformations.radialblur;

import java.awt.image.WritableRaster;

public interface IRadialBlur {
	/**
	 * Blurs picture
	 * 
	 * @param source
	 * @param strenght of radial blur
	 * @return
	 */
	WritableRaster radialBlur(WritableRaster source, float strenght);
}
