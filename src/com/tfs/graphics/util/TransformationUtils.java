package com.tfs.graphics.util;

import rafgfxlib.Util;

public final class TransformationUtils {

	public TransformationUtils() {}

	public static int saturate(int value) {
		return Util.clamp(value, 0, 255);
	}
	
	
}
