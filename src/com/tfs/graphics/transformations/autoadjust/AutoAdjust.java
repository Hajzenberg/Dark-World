package com.tfs.graphics.transformations.autoadjust;

import java.awt.image.WritableRaster;

import com.tfs.graphics.util.TransformationUtils;

class AutoAdjust implements IAutoAdjust {

	@Override
	public WritableRaster autoAdjust(WritableRaster source, float percent) {
		
		if (source == null || percent < 0.0f) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int rgb[] = new int[target.getNumBands()];
		int histo[] = new int[256];
		
		double cutOff = percent/100.0f;
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				source.getPixel(x, y, rgb);
				int i = (int)(rgb[0] * 0.30 + rgb[1] * 0.59 + rgb[2] * 0.11);		
				histo[i]++;
			}
		}
		
		int cap = (int)((source.getWidth() * source.getHeight()) * cutOff);
		
		int leftBound = 0, rightBound = 255;
		
		int sum = histo[0];
		
		for(int i = 1; i < 255; i++)
		{
			sum += histo[i];
			
			if(sum >= cap)
			{
				leftBound = i - 1;
				break;
			}
		}
		
		sum = histo[255];
		
		for(int i = 254; i >= 0; i--)
		{
			sum += histo[i];
			if(sum >= cap)
			{
				rightBound = i + 1;
				break;
			}
		}
		
		double scale = 255.0 / (rightBound - leftBound);
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				source.getPixel(x, y, rgb);
				
				rgb[0] = TransformationUtils.saturate((int)((rgb[0] - leftBound) * scale));
				rgb[1] = TransformationUtils.saturate((int)((rgb[1] - leftBound) * scale));	
				rgb[2] = TransformationUtils.saturate((int)((rgb[2] - leftBound) * scale));	
				target.setPixel(x, y, rgb);
			}
		}
		return target;
		
	}
	
	
	
}
