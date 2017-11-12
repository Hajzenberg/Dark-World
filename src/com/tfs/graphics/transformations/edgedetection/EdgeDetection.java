package com.tfs.graphics.transformations.edgedetection;

import java.awt.image.WritableRaster;

import com.tfs.graphics.util.TransformationUtils;

class EdgeDetection implements IEdgeDetection {

	@Override
	public WritableRaster detectEdges(WritableRaster source, int multiplier) {
		if (multiplier < 1) {
			throw new IllegalArgumentException();
		}
		WritableRaster target = source.createCompatibleWritableRaster();
		
		int[] rgb = new int[target.getNumBands()];
		int[] center = new int[target.getNumBands()];
		
		for(int y = 1; y < source.getHeight() - 1; y++)
		{
			for(int x = 1; x < source.getWidth() - 1; x++)
			{
				int delta = 0;
				
				source.getPixel(x, y, center);
				
				for(int dy = -1; dy < 2; dy++)
				{
					for(int dx = -1; dx < 2; dx++)
					{
						source.getPixel(x + dx, y + dy, rgb);
						
						for(int c = 0; c < 3; c++)
						{
							int diff = Math.abs(rgb[c] - center[c]);
							if(diff > delta)
								delta = diff;
						}
					}
				}
				
				
				delta = TransformationUtils.saturate(delta * multiplier);
				
				rgb[0] = delta;
				rgb[1] = delta;
				rgb[2] = delta;
				
				target.setPixel(x, y, rgb);
			}
		}
		return target;
	}

}
