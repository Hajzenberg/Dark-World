package com.tfs.graphics.transformations.grayscale;

/**
 * Enum of possible grayscale types 
 * 
 * @author Corax
 *
 */
public enum GrayscaleType {
	/**
	 * The luminosity method is a more sophisticated version of the average method.
	 * It also averages the values, but it forms a weighted average to account for human perception.
	 * We’re more sensitive to green than other colors, so green is weighted most heavily. 
	 * The formula for luminosity is 0.21 R + 0.72 G + 0.07 B.
	 * 
	 * @author Corax
	 *
	 */
	LUMINOSITY{
		@Override
		public int calculateGray(int r, int g, int b) {
			return (int)( r * 0.21f + g * 0.72f + b*0.07f);
		}
	},
	/**
	 * 
	 * The average method simply averages the values: (R + G + B) / 3.
	 * @author Corax
	 *
	 */
	AVERAGE{
		@Override
		public int calculateGray(int r, int g, int b) {
			return (r+g+b)/3;
		}
	},
	/**
	 * The lightness method averages the most prominent and least prominent colors: (max(R, G, B) + min(R, G, B)) / 2.
	 * 
	 * @author Corax
	 *
	 */
	LIGHTNESS {
		@Override
		public int calculateGray(int r, int g, int b) {
			return (Math.max(r, Math.max(g, b)) + Math.min(r, Math.min(g, b)))/2;
		}
	};
	
	private GrayscaleType() {}
	
	public abstract int calculateGray(int r, int g, int b);
}
