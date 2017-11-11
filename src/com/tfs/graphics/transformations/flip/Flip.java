package com.tfs.graphics.transformations.flip;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Algorithm for Flip transformation
 * 
 * @author Corax
 *
 */
class Flip implements IFlip {
	
	public Flip() {}
	
	@Override
	public WritableRaster flip(Raster raster, FlipDirection flipDirection) {
		if (raster == null || flipDirection == null) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster target = raster.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				raster.getPixel(x, y, rgb);
				flipDirection.setPixel(target, x, y, rgb);
			}
		}		
		return target;
	}
}
