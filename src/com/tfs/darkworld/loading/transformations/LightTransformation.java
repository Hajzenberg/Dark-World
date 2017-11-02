package com.tfs.darkworld.loading.transformations;

import com.corax.graphics.transformations.manager.Transformation;
import com.corax.graphics.transformations.contrast.ContrastInvoker;
import com.corax.graphics.transformations.vignette.VignetteInvoker;

public class LightTransformation extends Transformation {
	
	public LightTransformation() {
		addTransformation(new ContrastInvoker(-0.5f));
		addTransformation(new VignetteInvoker());
	}
	
}
