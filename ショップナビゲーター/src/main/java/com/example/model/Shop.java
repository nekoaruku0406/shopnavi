package com.example.model;

import java.io.Serializable;

public class Shop implements Serializable {
	@Override
	public String toString() {
		return "Shop [id=" + id + ", password=" + password + ", name=" + name + ", genre=" + genre + ", address="
				+ address + ", budget=" + budget + ", openHours=" + openHours + ", imageUrl=" + imageUrl + "]";
	}

	private static final long serialVersionUID = 1L;

	private int id; // 店舗No
	private String password; // 店舗用ログインパスワード
	private String name; // 店舗名
	private String genre; // 取扱商品ジャンル
	private String address; // 住所
	private int budget; // 予算
	private String openHours; // 営業時間
	private String imageUrl; // 画像URL・リンク

	// コンストラクタ（引数なし）
	public Shop() {
	}

	// コンストラクタ（全フィールド初期化用）
	public Shop(int id, String password, String name, String genre, String address, int budget, String openHours,
			String imageUrl) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.genre = genre;
		this.address = address;
		this.budget = budget;
		this.openHours = openHours;
		this.imageUrl = imageUrl;
	}

	public Shop(String id, String name, String genre, String address) {
		this.id = Integer.parseInt(id);
		this.name = name;
		this.genre = genre;
		this.address = address;
	}

	// ゲッター・セッター
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getOpenHours() {
		return openHours;
	}

	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean checkCurrentOpen() {
		return false;
	}
}