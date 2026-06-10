package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Review;
import com.example.model.Shop;

public class ShopDao {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * 2. 【登録】口コミをデータベースにインサートする
	 */
	// 【修正後】void ではなく int を返すように変更
	public int insertReview(Review review) {
		String sql = "INSERT INTO reviews (shop_id, rating, comment, media_url) VALUES (?, ?, ?, ?)";
		int generatedId = -1;

		// ★RETURN_GENERATED_KEYS を指定して、DBが作ったIDを受け取る準備をする
		try (Connection conn = ConnectionManager.getConnection();
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
	public List<Shop> findByName(String name) {
		List<Shop> shops = new ArrayList<>();
		// WHERE句で「今見ている店のID」を指定して冷徹にフィルタリングする
		String sql = "SELECT * FROM shops WHERE name = ? ORDER BY id DESC";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + name + "%"); // 1番目の「?」に画面から受け取った店舗IDをハメ込む

			try (ResultSet rs = pstmt.executeQuery()) {
				// DBから返ってきた行を1行ずつループで回して、JavaのModelオブジェクトに変換する
				while (rs.next()) {
					Shop shop = new Shop(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("genre"),
							rs.getString("address"));

					shops.add(shop); // リストに詰め込む
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shops; // その店専用の口コミリストを返す
	}

	public List<Shop> findAll() {
		List<Shop> shops = new ArrayList<>();
		String sql = "SELECT * from shops ORDER BY id DESC";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery()) {
				// DBから返ってきた行を1行ずつループで回して、JavaのModelオブジェクトに変換する
				while (rs.next()) {
					Shop shop = new Shop(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("genre"),
							rs.getString("address"));

					shops.add(shop); // リストに詰め込む
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shops; // その店専用の口コミリストを返す
	}
	//    /**
	//     * 4. 【削除】指定された口コミIDのデータをDBから抹消する
	//     */
	//    public void deleteReview(int reviewId) {
	//        String sql = "DELETE FROM reviews WHERE id = ?";
	//
	//        try (Connection conn = getConnection();
	//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	//
	//            pstmt.setInt(1, reviewId);
	//            int affectedRows = pstmt.executeUpdate();
	//            
	//            if (affectedRows > 0) {
	//                System.out.println("DBから口コミID: " + reviewId + " を完全に抹消した。");
	//            } else {
	//                System.out.println("対象の口コミIDが見つからなかった。");
	//            }
	//
	//        } catch (SQLException e) {
	//            e.printStackTrace();
	//        }
	//    }

	public Shop findById(String id) {
		// WHERE句で「今見ている店のID」を指定して冷徹にフィルタリングする
		String sql = "SELECT * FROM shops WHERE id = ? ORDER BY id DESC";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, Integer.parseInt(id)); // 1番目の「?」に画面から受け取った店舗IDをハメ込む

			try (ResultSet rs = pstmt.executeQuery()) {
				// DBから返ってきた行を1行ずつループで回して、JavaのModelオブジェクトに変換する
				if (rs.next()) {
					Shop shop = new Shop(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("genre"),
							rs.getString("address"));
					return shop;
				}return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Shop findById(Integer id) {
		// WHERE句で「今見ている店のID」を指定して冷徹にフィルタリングする
		String sql = "SELECT * FROM shops WHERE id = ? ORDER BY id DESC";

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id); // 1番目の「?」に画面から受け取った店舗IDをハメ込む

			try (ResultSet rs = pstmt.executeQuery()) {
				// DBから返ってきた行を1行ずつループで回して、JavaのModelオブジェクトに変換する
				if (rs.next()) {
					Shop shop = new Shop(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("genre"),
							rs.getString("address"));
					return shop;
				}return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
