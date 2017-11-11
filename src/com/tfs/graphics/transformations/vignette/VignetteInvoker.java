package com.tfs.graphics.transformations.vignette;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

/**
 * 
 * Invoker for vignette filter
 * 
 * @author Corax
 *
 */
public class VignetteInvoker implements IProcessingInvoker {
	public static final IVignette DEFAULT_VIGNETTE = new Vignette();
	
	private IVignette vignetteAlgorithm;
	private float vignetteIntensity;
	private float distMultiplier;
	
	public VignetteInvoker(IVignette vignetteAlgorithm, float vignetteIntensity,float distMultiplier) {
		super();
		if (vignetteAlgorithm == null || (vignetteIntensity < 0.0f || vignetteIntensity > 1.0f) || distMultiplier < 0f) {
			throw new IllegalArgumentException();
		}
		this.vignetteAlgorithm = vignetteAlgorithm;
		this.vignetteIntensity = vignetteIntensity;
		this.distMultiplier = distMultiplier;
	}

	
	public VignetteInvoker(float vignetteIntensity,float distMultilplier) {
		this(DEFAULT_VIGNETTE, vignetteIntensity,distMultilplier);
	}


	public VignetteInvoker() {
		this(0.8f,2.8f);
	}


	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return vignetteAlgorithm.vignette(source, vignetteIntensity,distMultiplier);
	}

}
