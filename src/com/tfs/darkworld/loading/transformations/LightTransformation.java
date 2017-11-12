package com.tfs.darkworld.loading.transformations;

import com.tfs.graphics.transformations.contrast.ContrastInvoker;
import com.tfs.graphics.transformations.manager.Transformation;
import com.tfs.graphics.transformations.vignette.VignetteInvoker;

public class LightTransformation extends Transformation {
	
	public LightTransformation() {
		addTransformation(new ContrastInvoker(-0.5f));
		addTransformation(new VignetteInvoker());
	}
	
}
