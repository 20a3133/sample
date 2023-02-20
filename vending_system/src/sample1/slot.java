package sample1;

import java.util.Random;

//スロットクラス
public class slot{
	public static void Slot(int total_fee){
		//スロット処理
		Random rand = new Random();

		double b;
		double kounyu=total_fee;
		double kijun=5000;//ここの金額に近づけば近づくほどあたりの確立が高くなります
		double c = kounyu / kijun;

		int num [] = new int[4];
		if(c>1) {
			c=1;
		}
		//cの値によってnum[0]に値を近づける
		b = (1-c) * 10;
		num[0]=(int)rand.nextDouble()*10;
		for(int i=1;i<4;i++) {
			num[i] += (int)((rand.nextDouble()*b)+num[i-1]);
		}

		//numの要素が3個以上揃ったら当たり
		int count = 0;
		for(int i=0;i<num.length;i++) {
			for(int j=0;j<num.length;j++) {
				if(num[i] == num[j]) {
					count++;
				}
			}
		}
		//スロット画面

		sample1.new_line(3);
		System.out.print("スロット中...");
		try {
			Thread.sleep(3000); // 5秒(5千ミリ秒)間だけ処理を止める
		} catch (InterruptedException e) {
		}

		if(count >= 9) {
			//スロットが当たった時、アタリ画面
			System.out.println("あたり！\n");
			try {
				Thread.sleep(3000); // 3秒(3千ミリ秒)間だけ処理を止める
			} catch (InterruptedException e) {
			}finally {
				sample1.new_line(20);
			}
			payment_system.Win();
		 }else {
			 //はずれ画面
			System.out.println("はずれ...");
			try {
				Thread.sleep(3000); // 3秒(3千ミリ秒)間だけ処理を止める
			} catch (InterruptedException e) {
			}
			System.out.println("購入ありがとうございました");
			try {
				Thread.sleep(5000); // 5秒(5千ミリ秒)間だけ処理を止める
			} catch (InterruptedException e) {
			}finally {
				sample1.new_line(20);
			}
			sample1.MainClass();
		 }
	}

}