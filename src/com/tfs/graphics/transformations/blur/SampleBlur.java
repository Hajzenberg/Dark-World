package com.tfs.graphics.transformations.blur;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

class SampleBlur implements IBlur {
	private static final int SAMPLE_CNT = 32;
	@Override
	public WritableRaster blur(WritableRaster source, int blurPower) {
		if (source == null || blurPower < 0) {
			throw new IllegalArgumentException();
		}
		WritableRaster target = source.createCompatibleWritableRaster();

		int rgb[] = new int[target.getNumBands()];
		int accum[] = new int[target.getNumBands()];

		int radius = blurPower;

		int width = source.getWidth();
		int height = source.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				accum[0] = 0;
				accum[1] = 0;
				accum[2] = 0;

				for (int i = 0; i < SAMPLE_CNT; i++) {
					float X = (float) (Math.random() - 0.5) * radius * 2.0f;
					float Y = (float) (Math.random() - 0.5) * radius * 2.0f;

					Util.bilSample(source, x + X, y + Y, rgb);

					accum[0] += rgb[0];
					accum[1] += rgb[1];
					accum[2] += rgb[2];
				}

				rgb[0] = accum[0] / SAMPLE_CNT;
				rgb[1] = accum[1] / SAMPLE_CNT;
				rgb[2] = accum[2] / SAMPLE_CNT;

				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}
}
