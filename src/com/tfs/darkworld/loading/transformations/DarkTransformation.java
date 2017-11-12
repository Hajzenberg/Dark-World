package com.tfs.darkworld.loading.transformations;

import com.tfs.graphics.transformations.brightness.BrightnessInvoker;
import com.tfs.graphics.transformations.grayscale.GrayscaleInvoker;
import com.tfs.graphics.transformations.grayscale.GrayscaleType;
import com.tfs.graphics.transformations.manager.Transformation;
import com.tfs.graphics.transformations.vignette.VignetteInvoker;

public class DarkTransformation extends Transformation {
	public DarkTransformation() {
		addTransformation(new GrayscaleInvoker(GrayscaleType.LUMINOSITY));
		addTransformation(new BrightnessInvoker(-0.5f));
		addTransformation(new VignetteInvoker());
	}
}
