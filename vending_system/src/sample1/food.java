package sample1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class food extends item{
	//初期の在庫の種類・金額・在庫数
	String name;
	int price;
	String id;

	public static void primary_add_food() {
		item_name.addAll(Arrays.asList("コーラ", "水", "緑茶", "コーヒー"));
		item_price.addAll(Arrays.asList(150, 100, 120, 120));
		item_id.addAll(Arrays.asList("0001","0002","0003","0004"));
		item_management.stock.addAll(Arrays.asList(10, 10, 10, 10));
	}

	public void item_delete(String id) {
		super.item_delete(id);
	}

	public void item_reregist(String id) {
		System.out.print("変更したい情報は：");
		String column = sample1.sc.next();
		if(column.equals("品名")) {
			System.out.print("変更内容：");
			String new_name = sample1.sc.next();
			super.item_name_reregist(id, new_name);
		}else if(column.equals("金額")) {
			System.out.print("変更内容：");
			int new_price = sample1.sc.nextInt();
			super.item_price_reregist(id, new_price);
		}else manager.Host();
	}

	public void add_item() {
		System.out.print("\n商品の新規登録\n");
		//商品名登録
		System.out.print("商品名：");
		this.name = sample1.sc.next();
		//値段登録
		System.out.print("値段：");
		this.price = sample1.sc.nextInt();
		//商品番号から飲み物のみ抽出しリスト化、一番大きい番号の次に振り当て
		List<String> list = item_id.stream().filter(str -> Integer.valueOf(str)/1000==1).collect(Collectors.toList());
		int num = Integer.valueOf(list.get(list.size()-1)) + 1;
		this.id = String.valueOf(num);
		super.register(name, price, id);
	}
}