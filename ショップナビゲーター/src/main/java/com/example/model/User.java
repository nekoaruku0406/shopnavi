package com.example.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;             // ユーザーNo
    private String email;       // ログイン用メールアドレス
    private String password;    // パスワード
    private String nickname;    // ユーザー名・ニックネーム

    // コンストラクタ（引数なし）
    public User() {}

    // コンストラクタ（全フィールド初期化用）
    public User(int id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    // ゲッター・セッター
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}