package com.example.testtemplate.bean;

public class Type2GridBean implements Cloneable {
	public enum ViewType {
		VIEW_TYPE_0, VIEW_TYPE_01, VIEW_TYPE_02, VIEW_TYPE_03;
		public int getViewType() {
			int viewType = -1;
			switch (this) {
			case VIEW_TYPE_0:
				viewType = 0;
				break;
			case VIEW_TYPE_01:
				viewType = 1;
				break;
			case VIEW_TYPE_02:
				viewType = 2;
				break;
			case VIEW_TYPE_03:
				viewType = 3;
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

	private String imageUrl;
	private String text;
	private int viewType;
	private int backgroundResouce;
	/** 文本背景 */
	private int txtbackgroundResouce;
	/**图片宽度*/
	private int imageWidth;
	/**图片高度*/
	private int imageHeight;
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getBackgroundResouce() {
		return backgroundResouce;
	}

	public void setBackgroundResouce(int backgroundResouce) {
		this.backgroundResouce = backgroundResouce;
	}

	public Type2GridBean clone() {
		try {
			return (Type2GridBean) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getTxtbackgroundResouce() {
		return txtbackgroundResouce;
	}

	public void setTxtbackgroundResouce(int txtbackgroundResouce) {
		this.txtbackgroundResouce = txtbackgroundResouce;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	

}
