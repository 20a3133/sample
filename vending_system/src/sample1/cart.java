package sample1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//カートクラス
public class cart{
	//カート作成
	static List <Integer> cart = new ArrayList<Integer>(Arrays.asList());


	//カートに追加
	public static boolean cart_add_amount(int i, int num) {
		//在庫数がカートより多いときtrue、それ以外はfalse
		int x = cart.get(i) + num;
		if(item_management.sub_stock.get(i) >= x) {
			cart.set(i, x);
			int flag = 1;
			//仮在庫数更新
			item_management.substock_reduce(i, flag, num);
			return true;
		}else return false;
	}

	//カートから商品削除
	public static int cart_delete(String x){
		int y = Integer.valueOf(x);
		System.out.print("減らす個数を入力して下さい：");
		int num = sample1.sc.nextInt();
		if(cart.get(y) >= num) {
			cart.set(y, cart.get(y) - num);
			int flag = -1;
			//仮在庫数更新
			item_management.substock_reduce(y, flag, num);
			return num;
		}else {
			return -1;
		}
	}

	//カートを空にする
	public static void empty_cart(){
		for(int i=0;i<cart.size();i++){
			cart.set(i,0);
		}
	}
}
