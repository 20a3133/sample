package sample1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//電子マネー管理システム
public class card_management_system{
	//入力を保存するリスト作成
	static List<String> id_list = new ArrayList<String>(Arrays.asList());
	static List<Integer> balance_list = new ArrayList<Integer>(Arrays.asList());

	//電子マネー管理
	public static int id_managemant(String card_id) {
		int balance = 0;
		if(id_list.contains(card_id)) {
			//id_listからbalance_listと対応するindex番号を取得
			int index = id_list.indexOf(card_id);
			balance = balance_list.get(index);
		}else {
			id_list.add(card_id);
			balance_list.add(500);
			int index = id_list.indexOf(card_id);
			balance = balance_list.get(index);
		}
		return balance;
	}

	//チャージ
	public static void Charge(String card_id, int balance) {
		System.out.println("\nチャージ");
		System.out.printf("現在の残高：%d円\n", balance);
		System.out.print("チャージ金額を入力してください：");
		while(true) {
			String cash = vending_machine.sc.next();
			if(cash.matches(".*[a-zA-Z].*")) {
				System.out.print("数字で入力してください：");
				try {
					Thread.sleep(1000); // 1秒(1000ミリ秒)間だけ処理を止める
				} catch (InterruptedException e) {
				}
			}else {
				int int_cash = Integer.valueOf(cash);
				realcash(int_cash, card_id, balance);
			}
		}
	}

	public static void realcash(int cash, String card_id, int balance){
		System.out.print("1キーを押して投入金額を確定させてください\n戻るなら0キーを押してください：");
		while(true){
			int x = vending_machine.sc.nextInt();
			if(x==1) {
				System.out.print("チャージが完了されました");
				try {
					Thread.sleep(3000); // 3秒(3000ミリ秒)間だけ処理を止める
				} catch (InterruptedException e) {
			}
			//チャージ後の残高、balance_listのindex番号の取得
			int new_balance= balance + cash;
			int index = card_management_system.id_list.indexOf(card_id);
			//balance_listの更新
			card_management_system.balance_list.set(index, new_balance);
			vending_machine.new_line(25);
			vending_machine.MainClass();
			}
			else if(x==0)
				Charge(card_id, balance);
			else {
				vending_machine.new_line(25);
				vending_machine.MainClass();
			}
		}
	}
}