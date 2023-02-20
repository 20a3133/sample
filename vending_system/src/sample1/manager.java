package sample1;

//管理者クラス
public class manager{
	//管理者画面
	public static void Host() {
		food food = new food();
		drink drink = new drink();

		sample1.new_line(20);
		System.out.println("管理者画面");
		//在庫一覧
		System.out.printf("    品名%10s金額%5s在庫数%5s番号\n", " "," "," ");
		for(int i=0;i<item.item_name.size();i++){
			System.out.printf("%2d：", i);
			System.out.printf("%-10s",item.item_name.get(i));
			System.out.printf("%4d円 ", item.item_price.get(i));
			System.out.printf("%6d個", item_management.stock.get(i));
			System.out.printf("%10s\n", item.item_id.get(i));
		}

		//種別番号と操作を入力
		System.out.printf("操作一覧\n1:商品削除　2:商品情報変更　3:在庫追加　4:商品追加　5:初期画面\n");
		System.out.printf("操作入力:");
		int index = sample1.sc.nextInt();
		if(index == 5) {
			sample1.new_line(20);
			sample1.MainClass();
		}
		if(index == 4) {
			//商品追加（0:飲み物、1:食べ物）
			System.out.print("商品の種別を入力（0:飲み物、1:食べ物）：");
			int num = sample1.sc.nextInt();
			if(num==1) food.add_item();
			else if(num==0) drink.add_item();
		}
		else {
			while(true) {
				System.out.printf("商品番号入力:");
				String id = sample1.sc.next();
				int x = item.item_id.indexOf(id);
				if(x == -1) {
					System.out.printf("商品番号がありません\n");
					try {
						Thread.sleep(3000); // 3秒(1万ミリ秒)間だけ処理を止める
					} catch (InterruptedException e) {
					}
				}else{
					int int_id = Integer.valueOf(id);
					//商品削除
					if(index == 1){
						if(int_id/1000==0) {
							food.item_delete(id);
							break;
						}
						else if(int_id/1000==1) {
							drink.item_delete(id);
							break;
						}
					}
					//商品情報変更
					else if(index == 2){
						if(int_id/1000==0) {
							food.item_reregist(id);
							break;
						}
						else if(int_id/1000==1) {
							drink.item_reregist(id);
							break;
						}
					}
					//在庫管理
					else if(index==4){
						item_management.set_stock(id);
						break;
					}
				}
			}
		}
	}
}