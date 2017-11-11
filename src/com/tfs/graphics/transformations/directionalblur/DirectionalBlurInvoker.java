package com.tfs.graphics.transformations.directionalblur;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class DirectionalBlurInvoker implements IProcessingInvoker {
	public static final IDirectionalBlur DEFAULT_DIRECTINALBLUR = new DirectionalBlur();
	
	private IDirectionalBlur directionalBlur;
	private float vecX;
	private float vecY;
	
	public DirectionalBlurInvoker(IDirectionalBlur directionalBlur, float vecX, float vecY) {
		super();
		this.directionalBlur = directionalBlur;
		this.vecX = vecX;
		this.vecY = vecY;
	}

	public DirectionalBlurInvoker(float vecX, float vecY) {
		this(DEFAULT_DIRECTINALBLUR, vecX, vecY);
	}

	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return directionalBlur.blur(source, vecX, vecY);
	}

}
