package sample1;

//販売システムクラス
public class payment_system{
	//合計金額
	static int amount = 0;

	//商品購入
	public static void Buy() {
		while(true) {
			//商品選択（在庫数は仮を参照）
			sample1.new_line(50);
			System.out.println("\n購入したい商品を選択してください\nカートを確認したいときはaキーを、"
					+ "支払いを確定したいときはsキーを押してください");
			for(int i=0;i<item.item_name.size();i++) {
				if(i%2==1 || i!=0) System.out.println();
				if(item_management.sub_stock.get(i) == 0) continue;
				else System.out.printf("%2d:%6s %6d円     残り%2d個　", i, item.item_name.get(i),item.item_price.get(i),
						item_management.sub_stock.get(i));
			}
			System.out.printf("\n%s:%s　%s:%s　%s:%s\n", "a", "カートを確認する", "s", "支払い画面に行く" , "d", "カード番号入力画面に戻る");

			System.out.print("選択したキー：");
			String x = sample1.sc.next();
			if(x.equals("a")) {
				//カート確認
				cart_check();
				break;
			}
			else if(x.equals("s")) {
				//決済
				payment_system.pay();
				break;
			}
			else if(x.equals("d")) {
				sample1.new_line(50);
				sample1.MainClass();
				break;
			}
			else {
				int y = Integer.valueOf(x);
				System.out.printf("買いたい個数を入力してください：");
				int num = sample1.sc.nextInt();
				if(y>=0 && y<item.item_id.size()) {
					//カートに追加
					if(payment_system.add_amount(y, num)){
						System.out.printf("%sを%d個カートにいれました\n", item.item_name.get(y), num);
						try {
							Thread.sleep(1000); // 1秒(1000ミリ秒)間だけ処理を止める
						} catch (InterruptedException e) {
						}
					}else {
						System.out.print("処理できませんでした\n");
						try {
							Thread.sleep(1000); // 1秒(1000ミリ秒)間だけ処理を止める
							} catch (InterruptedException e) {
						}
					}
				}else if(y>=item.item_id.size()){
					System.out.print("有効なキーを入力してください\n");
				}else {
					System.out.print("有効なキーを入力してください\n");
				}
			}
		}
	}

	//合計金額の取得
	public static int total_fee() {
		amount = 0;
		for(int i=0;i<cart.cart.size();i++) {
			amount += cart.cart.get(i) * item.item_price.get(i);
		}
		return amount;
	}

	//選択した商品をカートに追加
	public static boolean add_amount(int y, int num){
		return cart.cart_add_amount(y, num);
	}

	//選択した商品をカートから削除
	public static void delete_amount(String x){
		int num = cart.cart_delete(x);
		if(num==-1) {
			//amount -= item.item_price.get(index) * num;
			System.out.println("個数が多すぎます");
		}else {
			System.out.printf("%sを%2d個削除しました\n", item.item_name.get(Integer.valueOf(x)), num);
		}
	}

	//カートを確認
	public static void cart_check() {
		sample1.new_line(50);
		System.out.print("\nカートの中身：\n");
		for(int i=0;i<item.item_name.size();i++){
			if(cart.cart.get(i) > 0) System.out.printf("%d：%6s %6d円, 個数：%2d個\n",i,
					item.item_name.get(i),item.item_price.get(i), cart.cart.get(i));
		}
		System.out.printf("合計：%6d円\n",payment_system.total_fee());
		while(true){
			System.out.print("商品に対応したキーで削除、数字以外のキーで購入画面に戻ります：");
			String x = sample1.sc.next();
			if(x.matches(".*[^0-9].*")) {
				payment_system.Buy();
				break;
			}else if(cart.cart.get(Integer.valueOf(x)) <= 0) {
				System.out.print("有効な数字を入力してください\n");
			}
			else {
				//カートから商品削除
				payment_system.delete_amount(x);
				try {
					Thread.sleep(3000); // 3秒(3千ミリ秒)間だけ処理を止める
				} catch (InterruptedException e) {
				}
				cart_check();
				break;
			}
		}
	}

	//支払い確定した処理
	public static void pay(){
		//購入内容を表示
		sample1.new_line(50);
		System.out.print("内訳：\n");
		for(int i=0;i<item_management.stock.size();i++){
			if(cart.cart.get(i) > 0) System.out.printf("%d：%6s %6d円, 個数：%2d個\n",i, item.item_name.get(i),item.item_price.get(i), cart.cart.get(i));
		}
		System.out.printf("合計金額：%d円\n", total_fee());

		System.out.print("これでよろしいですか？ 1キーで確定 0キーで戻る：");

		while(true) {
			int x = sample1.sc.nextInt();
			if(x==1) {
				//チャージ額が足りない場合、カート画面に戻る
				if(amount>sample1.balance) {
					System.out.print("チャージ残額が足りません カートから商品を減らしてください\n");
					try {
						Thread.sleep(3000); // 3秒(3千ミリ秒)間だけ処理を止める
					} catch (InterruptedException e) {
					}
					sample1.new_line(20);
					cart_check();
				}//何も購入していない場合、購入画面に戻る
				else if(cart.cart.stream().mapToInt(Integer::intValue).sum() == 0) {
					System.out.print("何か購入してください\n");
					try {
						Thread.sleep(3000); // 3秒(3千ミリ秒)間だけ処理を止める
					} catch (InterruptedException e) {
					}
					Buy();
				}else {

					//使用したカードのチャージ額を更新
					int after_balance = sample1.balance - amount;
					int index = card_management_system.id_list.indexOf(sample1.card_id);
					card_management_system.balance_list.set(index, after_balance);

					//売れた分の在庫を減らす
					item_management.stock_reduce();

					//賞味期限の繰り上げ・廃棄
					int y = 0;
					for(int i=0;i<item_management.stock.size();i++){
						if(Integer.valueOf(item.item_id.get(i))/1000 == 0) y = 1;
						if(Integer.valueOf(item.item_id.get(i))/1000 == 1) y = 2;
						int date = item.expiry_date.get(i);
						item.expiry_date.set(i, date+y);
					}

					//賞味期限切れの商品を廃棄
					item_management.scrap_stock();

					//排出される商品を出力
					for(int i=0;i<cart.cart.size();i++){
						if(cart.cart.get(i) > 0)System.out.printf("%sを%2d個購入しました\n", item.item_name.get(i), cart.cart.get(i));
					}

					//カートを空にする
					cart.empty_cart();

					//スロットを回す
					slot.Slot(amount);
				}
			}else if(x==0) {
				//購入画面に戻る
				Buy();
				break;
			}else {
				System.out.print("有効なキーを入力してください：");
			}
		}
	}

	public static void Win() {
		//商品選択
		for(int i=0;i<item.item_name.size();i++) {
			if(i%2==1 || i!=0) System.out.println();
			if(item_management.sub_stock.get(i) == 0) continue;
			else System.out.printf("%2d:%6s %6d円　", i, item.item_name.get(i),item.item_price.get(i));
		}
		System.out.print("\n商品を1つ無料で差し上げます！ 商品を選んでください：");
		int num = sample1.sc.nextInt();
		//商品排出
		System.out.println(item.item_name.get(num) + "が排出されました");

		//在庫調整
		int x = item_management.stock.get(num);
		//item_management.sub_stock.set(num, x-1);
		item_management.stock.set(num, x-1);

		try {
			Thread.sleep(1000); // 1秒(1千ミリ秒)間だけ処理を止める
		} catch (InterruptedException e) {
		}
		System.out.println("購入ありがとうございました");
		try {
			Thread.sleep(5000); // 5秒(5千ミリ秒)間だけ処理を止める
		} catch (InterruptedException e) {
		}finally {
			sample1.new_line(50);
		}
		sample1.MainClass();
	}
}