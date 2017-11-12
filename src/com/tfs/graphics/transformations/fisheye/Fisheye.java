package com.tfs.graphics.transformations.fisheye;

import java.awt.image.WritableRaster;

import rafgfxlib.Util;

class Fisheye implements IFisheye{

	@Override
	public WritableRaster fisheye(WritableRaster source, float power) {
		if (source == null){
			throw new IllegalArgumentException();
		}
		
		power = - power;
		power += 1.0f;
		
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		
		int centerX = source.getWidth() / 2;
		int centerY = source.getHeight() / 2;
		
		float radius = Math.min(centerX, centerY);
		
		for(int y = 0; y < source.getHeight(); y++)
		{			
			for(int x = 0; x < source.getWidth(); x++)
			{
				double angle = Math.atan2(y - centerY, x - centerX);
				double distance = Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY));
				
				distance = Math.pow(distance / radius, power) * radius;
				
				float srcX = centerX + (float)(Math.cos(angle) * distance);
				float srcY = centerY + (float)(Math.sin(angle) * distance);
				
				Util.bilSample(source, srcX, srcY, rgb);
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}

}
