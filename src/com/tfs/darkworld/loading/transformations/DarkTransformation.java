package com.tfs.darkworld.loading.transformations;

import com.corax.graphics.transformations.brightness.BrightnessInvoker;
import com.corax.graphics.transformations.grayscale.GrayscaleInvoker;
import com.corax.graphics.transformations.grayscale.GrayscaleType;
import com.corax.graphics.transformations.manager.Transformation;
import com.corax.graphics.transformations.vignette.VignetteInvoker;

public class DarkTransformation extends Transformation {
	public DarkTransformation() {
		addTransformation(new GrayscaleInvoker(GrayscaleType.LUMINOSITY));
		addTransformation(new BrightnessInvoker(-0.5f));
		addTransformation(new VignetteInvoker());
	}
}
