package com.tfs.darkworld.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class IntersectionRectBody implements IIntersectionBody {
	private double x1;
	private double y1;
	
	private double x2;
	private double y2;
	
	private double x3;
	private double y3;
	
	private double x4;
	private double y4;
	
	private double leftOffset;
	private double upperOffset;
	
	private double iBodyWidth;
	private double iBodyHeight;
	
	public void setLeftOffset(double leftOffset) {
		this.leftOffset = leftOffset;
	}
	public void setUpperOffset(double upperOffset) {
		this.upperOffset = upperOffset;
	}
	public void setHeight(double height) {
		this.iBodyHeight = height;
	}
	public void setWidth(double width) {
		this.iBodyWidth = width;
	}
	
	public double getX1() {
		return x1;
	}
	public double getY1() {
		return y1;
	}
	public double getX2() {
		return x2;
	}
	public double getY2() {
		return y2;
	}
	public double getX3() {
		return x3;
	}
	public double getY3() {
		return y3;
	}
	public double getX4() {
		return x4;
	}
	public double getY4() {
		return y4;
	}
	
	
	/**
	 * @param mX
	 * @param mY
	 */
	public void updateIntersectionBody(double mX, double mY) {
		// moglo je i da se direktno u getterima racuna, ali kao optimizacija neka
		
		x1 = mX + leftOffset;
		y1 = mY + upperOffset;
		
		x2 = x1 + iBodyWidth;
		y2 = y1;
		
		x3 = x1;
		y3 = y1 + iBodyHeight;
		
		x4 = x2;
		y4 = y3;
	}
	
	@Override
	public void render(Graphics2D g, int sw, int sh) {
//		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.drawRect((int)x1, (int)y1,(int) iBodyWidth,(int) iBodyHeight);
//		g.setColor(c);
	}
	@Override
	public IntersectType isIntersecting(IIntersectionBody intersectionBody) {
		
		if (intersectionBody instanceof IntersectionRectBody) {
			
			IntersectionRectBody ge = (IntersectionRectBody)intersectionBody;
			
			boolean pInside1 = isInsideSquare(getX1(), getY1(),ge.getX1(), ge.getY1(), ge.getX4(), ge.getY4());
			boolean pInside2 = isInsideSquare(getX2(), getY2(),ge.getX1(), ge.getY1(), ge.getX4(), ge.getY4());
			boolean pInside3 = isInsideSquare(getX3(), getY3(),ge.getX1(), ge.getY1(), ge.getX4(), ge.getY4());
			boolean pInside4 = isInsideSquare(getX4(), getY4(),ge.getX1(), ge.getY1(), ge.getX4(), ge.getY4());
			
			if (pInside1 && pInside2 && pInside3 && pInside4) {
				return IntersectType.Inside;
			}
			else if (pInside1 && pInside2) {
				return IntersectType.BottomLine;
			}
			else if (pInside2 && pInside3) {
				return IntersectType.leftLine;
			}
			else if (pInside3 && pInside4) {
				return IntersectType.UpperLine;
			}
			else if (pInside4 && pInside1) {
				return IntersectType.RightLine;
			}
			else if (pInside1) {
				return IntersectType.BottomRightCorner;
			}
			else if (pInside2) {
				return IntersectType.BottomLeftCorner;
			}
			else if (pInside3) {
				return IntersectType.UpperLeftCorner;
			}
			else if (pInside4) {
				return IntersectType.UpperRightCorner;
			}
			return IntersectType.None;
		}
		return null;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param px1 gornji levi
	 * @param py1
	 * @param px2 dosni denji
	 * @param py2
	 * @return
	 */
	public boolean isInsideSquare(double x, double y, double px1,double py1, double px2, double py2) {
		if (x >= px1 && x <= px2 && y >= py1 && y <= py2) {
			return true;
		}
		return false;
	}
	
}
