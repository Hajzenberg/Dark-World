package com.tfs.graphics.transformations.autoadjust;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class AutoAdjustInvoker implements IProcessingInvoker {
	public static final IAutoAdjust DEFAULT_AUTOADJUST = new AutoAdjust();
	public static final IAutoAdjust DEFAULT_RGBAUTOADJUST = new RGBAutoAdjust();
	
	private IAutoAdjust autoAdjust;
	private float percent;
	
	
	public AutoAdjustInvoker(IAutoAdjust autoAdjust, float percent) {
		super();
		if (autoAdjust == null || percent < 0.0f) {
			throw new IllegalArgumentException();
		}
		this.autoAdjust = autoAdjust;
		this.percent = percent;
	}


	public AutoAdjustInvoker(float percent) {
		this(DEFAULT_RGBAUTOADJUST, percent);
	}




	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return autoAdjust.autoAdjust(source, percent);
	}
	
}
