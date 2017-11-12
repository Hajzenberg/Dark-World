package com.tfs.darkworld.loading.transformations;

import com.tfs.darkworld.res.GameConstants;
import com.tfs.graphics.transformations.manager.Transformation;
import com.tfs.graphics.transformations.sampling.SamplingInvoker;
import com.tfs.graphics.transformations.sampling.SamplingType;

public class ScalingTransformation extends Transformation {
	public ScalingTransformation() {
		addTransformation(new SamplingInvoker(SamplingType.BILINEAR, 2000, GameConstants.FRAME_HEIGTH));
	}
}
