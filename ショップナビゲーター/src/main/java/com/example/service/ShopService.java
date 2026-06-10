package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.ShopDao;
import com.example.model.Shop;

public class ShopService {
	
    // 全店舗のデータが格納されているリスト（本来はDBから取得する）
    private List<Shop> allShops = new ShopDao().findAll(); 
    
    public List<Shop> findAll(){
    	return allShops;
    }

    /**
     * 条件にマッチする店舗を検索するロジック
     */
    public List<Shop> searchShops(String genre, String address, int maxBudget, boolean isOpenNow) {
        List<Shop> result = new ArrayList<>();

        for (Shop shop : allShops) {
            // 1. ジャンルが一致するか（未選択ならスルー）
            if (genre != null && !shop.getGenre().equals(genre)) continue;
            
            // 2. 住所が含まれているか（部分一致）
            if (address != null && !shop.getAddress().contains(address)) continue;
            
            // 3. 予算が上限以下か
            if (shop.getBudget() > maxBudget) continue;
            
            // 4. 今営業中か（本来は現在時刻とshopの営業時間を比較する）
            if (isOpenNow && !shop.checkCurrentOpen()) continue;

            // すべての条件をクリアした店舗だけを結果に追加
            result.add(shop);
        }
        return result;
    }

	public Shop findById(String id) {
		ShopDao dao = new ShopDao();
		return dao.findById(id);
	}
	
	public Shop findById(Integer id) {
		ShopDao dao = new ShopDao();
		return dao.findById(id);
	}
}