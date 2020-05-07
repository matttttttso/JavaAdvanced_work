package p13_5_work_Thread;

public class ShowThread extends Thread {
	int times = 100; // 繰り返す回数

	@Override
	public void run() {
		System.out.print("\n");
		for (int i = 1; i < times; i++) {
			System.out.print(i + " ");
			if(i % 10 == 0) {
				System.out.print("\n");
			}
	        try {
	            Thread.sleep(500);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	}
}
