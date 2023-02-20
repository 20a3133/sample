package sample1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//商品クラス
public class item{

	String name;
	int price;
	String id;

	//商品名、価格、賞味期限、商品番号を保存するリスト作成
	static List<String> item_name = new ArrayList<String>(Arrays.asList());
	static List<Integer> item_price = new ArrayList<Integer>(Arrays.asList());
	static List<Integer> expiry_date = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0));
	static List<String> item_id = new ArrayList<String>(Arrays.asList());

	//コンストラクタ
	item(){

	}

	//商品名、価格、賞味期限を登録
	protected void register(String name, int price, String id) {
		this.name = name;
		this.price = price;
		this.id = id;

		item_name.add(this.name);
		item_price.add(this.price);
		item_management.stock.add(10);
		item_management.sub_stock.add(10);
		expiry_date.add(0);
		item_id.add(this.id);

		System.out.println("登録しました\n");
		try {
			Thread.sleep(3000); // 3秒(3000ミリ秒)間だけ処理を止める
		} catch (InterruptedException e) {
		}
		manager.Host();
	}

	//商品削除
	protected void item_delete(String id){
		//商品番号からindex番号を求める
		this.id = id;
		int index = item_id.indexOf(this.id);

		item_name.remove(index);
		item_price.remove(index);
		expiry_date.remove(index);
		item_management.stock.remove(index);
		item_management.sub_stock.remove(index);
		item_id.remove(index);
		manager.Host();
	}
	//商品名変更
	protected void item_name_reregist(String id, String name) {
		this.id = id;
		int index = item_id.indexOf(this.id);

		item_name.set(index, name);
		manager.Host();
	}

	//値段変更
	protected void item_price_reregist(String id, int price) {
		this.id = id;
		int index = item_id.indexOf(this.id);

		item_price.set(index, price);
		manager.Host();
	}

}