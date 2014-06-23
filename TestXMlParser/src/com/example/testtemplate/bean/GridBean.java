package com.example.testtemplate.bean;

import android.graphics.drawable.Drawable;

public class GridBean  {
	public static int VIEW_TYPE_01=1;
	public static int VIEW_TYPE_02=2;
	public static int VIEW_TYPE_03=3;
	private Drawable drawable;
	private String text;
	private int textColor;
	private int backgroundColor;
	private int viewType;
	public Drawable getDrawable() {
		return drawable;
	}
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getTextColor() {
		return textColor;
	}
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
	public int getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public int getViewType() {
		return viewType;
	}
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	
	
}
