package com.tfs.graphics.transformations.autoadjust;

import java.awt.image.WritableRaster;

import com.tfs.graphics.util.TransformationUtils;

public class RGBAutoAdjust implements IAutoAdjust {

	@Override
	public WritableRaster autoAdjust(WritableRaster source, float percent) {
		if (source == null || percent < 0.0f) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int rgb[] = new int[3];
		int histoR[] = new int[256];
		int histoG[] = new int[256];
		int histoB[] = new int[256];
		
		double cutOff = percent/100.0f;
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				source.getPixel(x, y, rgb);
				histoR[rgb[0]]++;
				histoG[rgb[1]]++;
				histoB[rgb[2]]++;
			}
		}
		
		int cap = (int)((source.getWidth() * source.getHeight()) * cutOff);
		int leftBoundR = 0, rightBoundR = 255;
		int leftBoundG = 0, rightBoundG = 255;
		int leftBoundB = 0, rightBoundB = 255;
		
		int sum = histoR[0];
		
		for(int i = 1; i < 255; i++)
		{
			sum += histoR[i];
			if(sum >= cap)
			{
				leftBoundR = i - 1;
				break;
			}
		}
		
		sum = histoR[255];
		
		for(int i = 254; i >= 0; i--)
		{
			sum += histoR[i];
			if(sum >= cap)
			{
				rightBoundR = i + 1;
				break;
			}
		}
		
		sum = histoG[0];
		
		for(int i = 1; i < 255; i++)
		{
			sum += histoG[i];
			if(sum >= cap)
			{
				leftBoundG = i - 1;
				break;
			}
		}
		
		sum = histoG[255];
		
		for(int i = 254; i >= 0; i--)
		{
			sum += histoG[i];
			if(sum >= cap)
			{
				rightBoundG = i + 1;
				break;
			}
		}
		
		sum = histoB[0];
		
		for(int i = 1; i < 255; i++)
		{
			sum += histoB[i];
			if(sum >= cap)
			{
				leftBoundB = i - 1;
				break;
			}
		}
		
		sum = histoB[255];
		
		for(int i = 254; i >= 0; i--)
		{
			sum += histoB[i];
			if(sum >= cap)
			{
				rightBoundB = i + 1;
				break;
			}
		}
		
		double scaleR = 255.0 / (rightBoundR - leftBoundR);
		double scaleG = 255.0 / (rightBoundG - leftBoundG);
		double scaleB = 255.0 / (rightBoundB - leftBoundB);
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				source.getPixel(x, y, rgb);
				rgb[0] = TransformationUtils.saturate((int)((rgb[0] - leftBoundR) * scaleR));
				rgb[1] = TransformationUtils.saturate((int)((rgb[1] - leftBoundG) * scaleG));	
				rgb[2] = TransformationUtils.saturate((int)((rgb[2] - leftBoundB) * scaleB));	
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}

}
