package com.tfs.darkworld.loading.transformations;

import com.corax.graphics.transformations.grayscale.GrayscaleInvoker;
import com.corax.graphics.transformations.grayscale.GrayscaleType;
import com.corax.graphics.transformations.manager.Transformation;
import com.corax.graphics.transformations.vignette.VignetteInvoker;

public class GrayTransformation extends Transformation {
	public GrayTransformation() {
		addTransformation(new GrayscaleInvoker(GrayscaleType.LUMINOSITY));
		addTransformation(new VignetteInvoker());
	}
}
