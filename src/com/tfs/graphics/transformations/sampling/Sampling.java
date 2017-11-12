package com.tfs.graphics.transformations.sampling;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

class Sampling implements ISampling {

	@Override
	public WritableRaster scale(WritableRaster raster, SamplingType samplingType, int scaleW, int scaleH) {
		
		if (raster == null || scaleW <= 0 || scaleH <= 0) {
			throw new IllegalArgumentException();
		}
		
		int sourceW = raster.getWidth();
		int sourceH = raster.getHeight();
				
		WritableRaster target = Util.createRaster(scaleW, scaleH,false);		
		int[] rgb = new int[target.getNumBands()];
		
		for(int y = 0; y < scaleH; y++)
		{
			float fy = (float)y / scaleH;
			
			for(int x = 0; x < scaleW; x++)
			{
				float fx = (float)x / scaleW;
				
				float srcX = fx * sourceW;
				float srcY = fy * sourceH;
				
				samplingType.calculateRGB(raster, srcX, srcY, rgb);
				
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}
}
