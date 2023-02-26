package sample1;

import java.util.Scanner;

//Mainクラス
public class vending_machine {
	static Scanner sc = new Scanner(System.in);
	//カード番号・残額
	public static String card_id;
	public static int balance;

	//画面送り
	public static void new_line(int max) {
		int i=0;
		while(true) {
			System.out.println("");
			i++;
			if(i==max)break;
		}
	}

	//初期～購入・チャージ・管理画面
	public static void MainClass() {
		//仮在庫数を更新
		for(int i=0;i<item_management.stock.size();i++) {
			item_management.sub_stock.set(i, item_management.stock.get(i));
		}
		//初期画面
		System.out.print("4ケタのカード番号を入力してください：");
		while(true) {
			card_id = null;
			card_id = sc.next();
			//管理画面に遷移
			if(card_id.equals("manage")) {
				manager.Host();
			}
			//エラー処理
			else if(card_id == null || !card_id.matches("^[0-9]*$") || card_id.length() != 4 ) {
				System.out.print("有効な数字を入力してください：");
			}else break;
		}
		//カード番号から残金を取得・カードの新規作成
		balance = card_management_system.id_managemant(card_id);
		//選択画面
		System.out.print("\nカード番号：" + card_id + "　チャージ残額：" + balance + "円\n");
		System.out.print("商品購入なら０、チャージなら１を押してください：");
		int x;
		//キー入力で購入・チャージに移る
		while(true) {
			x = sc.nextInt();
			if(x==1) {
				card_management_system.Charge(card_id, balance);
				break;
			}if(x==0){
				payment_system.Buy();
				break;
			}else {
				System.out.println("有効なキーを押してください：");
			}
		}

	}

	public static void main(String[] args) {
		// 最初の商品登録
		food.primary_add_food();
		drink.primary_add_drink();
		for(int i=0;i<item.item_id.size();i++) {
			cart.cart.add(0);
		}
		//在庫数を仮在庫数に格納
		item_management.sub_stock.addAll(item_management.stock);

		// システム起動
		MainClass();
	}
}
