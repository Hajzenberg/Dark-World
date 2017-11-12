package com.tfs.graphics.transformations.sharpen;

import java.awt.image.WritableRaster;

import com.tfs.graphics.util.TransformationUtils;

class MatrixSharpen implements ISharpen {

	@Override
	public WritableRaster sharp(WritableRaster source, float intensity) {
		
		if (source == null || intensity < 0.0f) {
			throw new IllegalArgumentException();
		}
		
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		int[] pixel = new int[target.getNumBands()];
		
		
		float matrix[][] = 
		{
			{ -intensity, -intensity, -intensity },
			{ -intensity,  9*intensity, -intensity },
			{ -intensity, -intensity, -intensity }
		};
		
		
		for(int y = 1; y < source.getHeight() - 1; y++)
		{
			for(int x = 1; x < source.getWidth() - 1; x++)
			{
				pixel[0] = 0;
				pixel[1] = 0;
				pixel[2] = 0;
				
				for(int dy = 0; dy < 3; dy++)
				{
					for(int dx = 0; dx < 3; dx++)
					{
						source.getPixel(x + dx - 1, y + dy - 1, rgb);
						
						pixel[0] += rgb[0] * matrix[dx][dy];
						pixel[1] += rgb[1] * matrix[dx][dy];
						pixel[2] += rgb[2] * matrix[dx][dy];
					}
				}
				
				pixel[0] = TransformationUtils.saturate(pixel[0]);
				pixel[1] = TransformationUtils.saturate(pixel[1]);
				pixel[2] = TransformationUtils.saturate(pixel[2]);
				
				target.setPixel(x, y, pixel);
			}
		}
		return target;
	}

}
