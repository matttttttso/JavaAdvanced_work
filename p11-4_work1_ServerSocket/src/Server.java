import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static final int SERVER_PORT = 8888;

	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT);) {
			InetAddress server = InetAddress.getLocalHost();
			System.out.printf("%s: ポート番号[%d]でServerが起動しました。\r\n\r\n",server.getHostAddress(),SERVER_PORT);

			Socket socket = serverSocket.accept();	//クライアントからの接続を待つ
//			InetAddress client = socket.getInetAddress();	//接続してきたクライアントのInetAddressを取得
			try(
					//クライアントとの通信用ストリームを取得
					InputStream is = socket.getInputStream();
					OutputStream os = socket.getOutputStream();
					BufferedReader in = new BufferedReader(new InputStreamReader(is));
					PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
					){
				//クライアントに最初のメッセージを送信
				out.printf("２乗したい数字を入力してください。\r\n");
				out.flush();

				//クライアントからのメッセージを受信
				int inputNum = in.read();
				System.out.printf("Clientからデータを受信しました。\n受信した数字： %d\r\n", inputNum);
				//受信した数字を2乗する
				int pow = (int) Math.pow(inputNum, 2);

				//クライアントに以下の加工したメッセージを送信
				out.printf("受け取った値は%dで、2乗した値は%dです。", inputNum, pow);
				out.flush();
				System.out.println("Serverからデータを送信しました。");

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
