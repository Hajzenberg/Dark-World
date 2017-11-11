package com.tfs.graphics.transformations.radialblur;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

class RadialBlur implements IRadialBlur {
	private static final int SAMPLE_CNT = 32;
	
	@Override
	public WritableRaster radialBlur(WritableRaster source, float strength) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		
		
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		int[] accum = new int[target.getNumBands()];
		
		int centerX = source.getWidth() / 2;
		int centerY = source.getHeight() / 2;
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				accum[0] = 0; accum[1] = 0; accum[2] = 0;
				
				for(int i = 0; i < SAMPLE_CNT; i++)
				{
					// Pronalazimo ugao trenutnog piksela u odnosu na centar
					double angle = Math.atan2(y - centerY, x - centerX);
					// Potrebna nam je i daljina
					double distance = Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY));
					
					// Ugao nasumicno offsetujemo, u zadanom opsegu
					angle += (Math.random() - 0.5) * strength;
					
					// Izracunamo novu poziciju, sa blago drugacijim uglom
					// i istom daljinom od centra
					float srcX = (float)(Math.cos(angle) * distance) + centerX;
					float srcY = (float)(Math.sin(angle) * distance) + centerY;
					
					Util.bilSample(source, srcX, srcY, rgb);
					
					accum[0] += rgb[0];
					accum[1] += rgb[1];
					accum[2] += rgb[2];
				}
				
				accum[0] /= SAMPLE_CNT;
				accum[1] /= SAMPLE_CNT;
				accum[2] /= SAMPLE_CNT;
				
				target.setPixel(x, y, accum);
			}
		}
		return target;
	}

}
