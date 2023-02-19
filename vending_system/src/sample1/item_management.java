package sample1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//商品管理クラス
public class item_management{
 	//在庫数のリスト作成
	static List<Integer> stock = new ArrayList<Integer>(Arrays.asList());
	//仮在庫数のリスト
	static List<Integer> sub_stock = new ArrayList<Integer>(Arrays.asList());

	//在庫数の更新
	public static void stock_reduce() {
		for(int i=0;i<item_management.stock.size();i++){
			int stock = item_management.stock.get(i);
			stock -= cart.cart.get(i);
			item_management.stock.set(i, stock);
		}
	}

	//仮在庫数の更新
	public static void substock_reduce(int i, int flag, int num) {
		if(flag == 1) {
			item_management.sub_stock.set(i, sub_stock.get(i) - cart.cart.get(i)*flag);
		}
		if(flag == -1) {
			item_management.sub_stock.set(i, sub_stock.get(i) - num*flag);
		}
	}

	//賞味期限切れの商品廃棄
	public static void scrap_stock() {
		for(int i=0;i<item.expiry_date.size();i++){
			if(item.expiry_date.get(i) == 10) {
				//item_management.sub_stock.set(i, 0);
				item_management.stock.set(i, 0);
			}
		}
	}

	//管理クラスから在庫数の調整
	static void set_stock(String id){
		//商品番号からindex番号を求める
		int index = item.item_id.indexOf(id);

		System.out.printf("\n商品名：%s 在庫数：%d個\n", item.item_name.get(index), stock.get(index));
		System.out.print("補充する数を入力：");
		int amount = sample1.sc.nextInt();

		//在庫を補充（）
		int x = stock.get(index);
		stock.set(index, x + amount);

		//賞味期限を更新
		item.expiry_date.set(index, 0);
		System.out.println();
		manager.Host();
	}
}