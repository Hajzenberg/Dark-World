package com.tfs.darkworld.loading.transformations;

import com.tfs.graphics.transformations.grayscale.GrayscaleInvoker;
import com.tfs.graphics.transformations.grayscale.GrayscaleType;
import com.tfs.graphics.transformations.manager.Transformation;
import com.tfs.graphics.transformations.vignette.VignetteInvoker;

public class GrayTransformation extends Transformation {
	public GrayTransformation() {
		addTransformation(new GrayscaleInvoker(GrayscaleType.LUMINOSITY));
		addTransformation(new VignetteInvoker());
	}
}
