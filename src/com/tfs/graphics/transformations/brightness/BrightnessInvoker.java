package com.tfs.graphics.transformations.brightness;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class BrightnessInvoker implements IProcessingInvoker {
	public static final IBrightness DEFAULT_BRIGHTNESS = new Brightness();
	
	
	private IBrightness brightness;
	private float intensity;
	
	public BrightnessInvoker(IBrightness brightness, float intensity) {
		super();
		if (brightness == null) {
			throw new IllegalArgumentException();
		}
		this.brightness = brightness;
		this.intensity = intensity;
	}

	public BrightnessInvoker(float intensity) {
		this(DEFAULT_BRIGHTNESS, intensity);
	}




	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {throw new IllegalArgumentException(); }
		return brightness.brightness(source, intensity);
	}

}
