package com.example.service;

import java.util.List;

import com.example.dao.ReviewDAO;
import com.example.model.Review;

public class ReviewService {
    
    // 処理の実行役であるDAOを準備する
    private ReviewDAO reviewDAO = new ReviewDAO();

    /**
     * 1. 口コミを登録する
     */
 // 【修正後】
    public int addReview(Review review) {
        // DAOにインサートさせつつ、返ってきた本物のIDをそのままServletへパスする
        return reviewDAO.insertReview(review);
    }

    /**
     * 2. 特定の店舗に紐づくすべての口コミを取得する
     * ★ここでお前が悩んでいた「店舗ごとの照合」をDAOのSQL（WHERE句）で実現する！
     */
    public List<Review> getReviewsByShopId(int shopId) {
        return reviewDAO.findByShopId(shopId);
    }

 // ❌ 古い findReviewById(int reviewId) や removeReview(Review review) は完全に消せ！

    /**
     * 【新・削除メソッド】IDを直接受け取ってDBから抹消する（パフォーマンス重視設計）
     */
    public void deleteReviewById(int reviewId) {
        reviewDAO.deleteReview(reviewId);
    }

    /**
     * 4. 口コミをデータベースから削除する
     */
    public void removeReview(Review review) {
        // 引数で渡された口コミオブジェクトのIDを元に、DBから抹消する
        reviewDAO.deleteReview(review.getId());
    }
}