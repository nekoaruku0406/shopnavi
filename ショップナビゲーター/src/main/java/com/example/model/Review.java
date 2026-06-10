package com.example.model;

import java.io.Serializable;

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;             // 口コミID
    private int shopId;         // 対象の店舗No（紐付け用外部キー）
    private String rating;      // 評価（A, B, C, D）
    private String comment;     // コメント（テキスト）
    private String mediaUrl;    // 写真、イラスト、地図の画像URL

    // コンストラクタ（引数なし）
    public Review() {}

    // コンストラクタ（全フィールド初期化用）
    public Review(int id, int shopId, String rating, String comment, String mediaUrl) {
        this.id = id;
        this.shopId = shopId;
        this.rating = rating;
        this.comment = comment;
        this.mediaUrl = mediaUrl;
    }

    // ゲッター・セッター
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getShopId() { return shopId; }
    public void setShopId(int shopId) { this.shopId = shopId; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getMediaUrl() { return mediaUrl; }
    public void setMediaUrl(String mediaUrl) { this.mediaUrl = mediaUrl; }
}