package com.tfs.graphics.transformations.sampling;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

public enum SamplingType {
	BILINEAR {
		@Override
		public void calculateRGB(WritableRaster src, float u, float v, int[] rgb) {
			Util.bilSample(src, u, v, rgb);
			
		}
	},
	POINT {
		@Override
		public void calculateRGB(WritableRaster src, float u, float v, int[] rgb) {
			Util.pointSample(src, u, v, rgb);
			
		}
	};
	public abstract void calculateRGB(WritableRaster source, float srcX, float srcY, int[] rgb);
}
