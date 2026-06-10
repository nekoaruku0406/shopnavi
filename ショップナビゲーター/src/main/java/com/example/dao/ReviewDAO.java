package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Review;

public class ReviewDAO {

    // ★お前の環境のPostgreSQLの接続情報に書き換えろよ！
    private static final String URL = "jdbc:postgresql://localhost:5432/shop_navigator_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1032"; // ← インストール時に決めたパスワードに変えろ

    /**
     * 1. データベースへの接続を確立するヘルパーメソッド
     */
    private Connection getConnection() throws SQLException {
        try {
            // PostgreSQLのドライバクラスをロード（Tomcat環境での安全策）
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver が見つかりません。libフォルダを確認しろ！", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 2. 【登録】口コミをデータベースにインサートする
     */
 // 【修正後】void ではなく int を返すように変更
    public int insertReview(Review review) {
        String sql = "INSERT INTO reviews (shop_id, rating, comment, media_url) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        // ★RETURN_GENERATED_KEYS を指定して、DBが作ったIDを受け取る準備をする
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, review.getShopId());
            pstmt.setString(2, review.getRating());
            pstmt.setString(3, review.getComment());
            pstmt.setString(4, review.getMediaUrl());

            pstmt.executeUpdate();

            // ★ここでDBから実際に採番されたID（3とか4とか）を抜き出す！
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId; // 本物のIDをServiceへ返す
    }

    /**
     * 3. 【照合・抽出】特定の店舗IDに紐づく口コミだけをDBから全件取得する
     * ★お前が「どうやって照合させるの？」と言っていた答えがこれだ！
     */
    public List<Review> findByShopId(int shopId) {
        List<Review> reviews = new ArrayList<>();
        // WHERE句で「今見ている店のID」を指定して冷徹にフィルタリングする
        String sql = "SELECT id, shop_id, rating, comment, media_url FROM reviews WHERE shop_id = ? ORDER BY id DESC";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, shopId); // 1番目の「?」に画面から受け取った店舗IDをハメ込む

            try (ResultSet rs = pstmt.executeQuery()) {
                // DBから返ってきた行を1行ずつループで回して、JavaのModelオブジェクトに変換する
                while (rs.next()) {
                    Review review = new Review();
                    review.setId(rs.getInt("id"));
                    review.setShopId(rs.getInt("shop_id"));
                    review.setRating(rs.getString("rating"));
                    review.setComment(rs.getString("comment"));
                    review.setMediaUrl(rs.getString("media_url"));
                    
                    reviews.add(review); // リストに詰め込む
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews; // その店専用の口コミリストを返す
    }

    /**
     * 4. 【削除】指定された口コミIDのデータをDBから抹消する
     */
    public void deleteReview(int reviewId) {
        String sql = "DELETE FROM reviews WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reviewId);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("DBから口コミID: " + reviewId + " を完全に抹消した。");
            } else {
                System.out.println("対象の口コミIDが見つからなかった。");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}