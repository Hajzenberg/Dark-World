package com.tfs.graphics.transformations.radialblur;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class RadialBlurInvoker implements IProcessingInvoker {
	public static final IRadialBlur DEFAULT_RADIAL_BLUR = new RadialBlur();
	
	private IRadialBlur radialBlur;
	private float strenght;
	
	public RadialBlurInvoker(IRadialBlur radialBlur, float strenght) {
		super();
		this.radialBlur = radialBlur;
		this.strenght = strenght;
	}
	
	public RadialBlurInvoker(float strenght) {
		this(DEFAULT_RADIAL_BLUR, strenght);
	}


	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return radialBlur.radialBlur(source, strenght);
	}

}
