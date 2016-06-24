package com.akira.advenceui;

/**
 * Created by akira on 2016/6/7.
 */

// 實作 getter setter 定義資料內容
public class Hotel {

	private String Title;
	private String Address;
	private String Tel;

	// 經緯度座標 longitude & latitude
	private String Y;
	private String X;

	public Hotel(String Title, String address, String Tel, String Y, String X) {
		setAddress(address);
		setTel(Tel);
		setTitle(Title);

		setY(Y);
		setX(X);

	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String Tel) {
		this.Tel = Tel;
	}

	public String getY() {
		return Y;
	}

	public void setY(String Y) {
		this.Y = Y;
	}

	public String getX() {
		return X;
	}

	public void setX(String X) {
		this.X = X;
	}
}
