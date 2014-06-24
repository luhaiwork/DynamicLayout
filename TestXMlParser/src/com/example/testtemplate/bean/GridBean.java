package com.example.testtemplate.bean;

public class GridBean implements Cloneable {
	public enum ViewType {
		VIEW_TYPE_0, VIEW_TYPE_01;
		public int getViewType() {
			int viewType = -1;
			switch (this) {
			case VIEW_TYPE_0:
				viewType = 0;
				break;
			case VIEW_TYPE_01:
				viewType = 1;
				break;
			default:
				break;
			}
			return viewType;
		}

		public static int getTotType() {
			return ViewType.values().length;
		}
	}

	private int style;
	private int image;
	private String title;

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public GridBean clone() {
		try {
			return (GridBean) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
