package com.tfs.darkworld.loading.transformations;

import com.corax.graphics.transformations.manager.Transformation;
import com.corax.graphics.transformations.sampling.SamplingInvoker;
import com.corax.graphics.transformations.sampling.SamplingType;
import com.tfs.darkworld.res.GameConstants;

public class ScalingTransformation extends Transformation {
	public ScalingTransformation() {
		addTransformation(new SamplingInvoker(SamplingType.BILINEAR, 2000, GameConstants.FRAME_HEIGTH));
	}
}
