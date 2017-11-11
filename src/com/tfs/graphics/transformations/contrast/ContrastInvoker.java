package com.tfs.graphics.transformations.contrast;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

/**
 * Invoker for contrast transformation
 * 
 * @author Corax
 *
 */
public class ContrastInvoker implements IProcessingInvoker {
	public static final IContrast DEFAULT_CONTRAST = new Contrast();

	private IContrast contrast;
	private float intensity;

	public ContrastInvoker(IContrast contrast, float intensity) {
		super();
		if (contrast == null) {
			throw new IllegalArgumentException();
		}
		this.intensity = intensity;
		this.contrast = contrast;
	}
	

	public ContrastInvoker(float intensity) {
		this(DEFAULT_CONTRAST, intensity);
	}

	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return contrast.contrast(source, intensity);
	}

}
