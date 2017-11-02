package com.tfs.darkworld.loading.transformations;

import com.corax.graphics.transformations.blur.BlurInvoker;
import com.corax.graphics.transformations.manager.Transformation;
import com.corax.graphics.transformations.negative.NegativeInvoker;
import com.corax.graphics.transformations.vignette.VignetteInvoker;

public class PauseTransformation extends Transformation {
	public PauseTransformation() {
		addTransformation(new BlurInvoker(BlurInvoker.DEFAULT_GAUSSIAN_BLUR_ALGORITHM, 20));
//		addTransformation(new GrayscaleInvoker(GrayscaleType.LUMINOSITY));
		addTransformation(new NegativeInvoker());
		addTransformation(new VignetteInvoker(0.6f,10));
	}
}
