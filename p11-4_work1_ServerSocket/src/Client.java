import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private static final String SERVER_HOST = "localhost";

	public static void main(String[] args) {
		try (
				Socket socket = new Socket(SERVER_HOST, Server.SERVER_PORT); //Socketインスタンスを取得
				//Socketから入出力用のストリームを取得
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(is));) {
			//Clientからデータを受信
			String line = in.readLine();
			System.out.println("ClientがServerに接続しました。");
			System.out.println(line);

			//Clientにデータを送信
			Scanner scanner = new Scanner(System.in);
			int inputNum = scanner.nextInt();
			os.write(inputNum);
			os.flush();
			System.out.println("Serverにデータを送信しました。");
			scanner.close();

			//Clientからデータを受信
			while(true) {
				line = in.readLine();
				if(line == null) {
					break;
				}
				System.out.println(line);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
