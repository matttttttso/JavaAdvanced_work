import static java.nio.file.StandardOpenOption.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main_9_5 {
	private static final String INPUT_FILE_PATH = "C:\\Pleiades\\woekspace2\\JavaAppliedCourse_work"
			+ "\\p9-5_work1_File_operation\\src\\game_player.csv";
	private static final String OUTPUT_FILE_PATH = "C:\\Pleiades\\woekspace2\\JavaAppliedCourse_work"
			+ "\\p9-5_work1_File_operation\\src\\output.csv";

	public static void main(String[] args) {
		Path inputPath = Paths.get(INPUT_FILE_PATH);
		Path outputPath = Paths.get(OUTPUT_FILE_PATH);
		int level,
			money,
			row_blank,
			kougyoku;
		final int NEGATIVE = (-1),
				INITIAL = 1,
				BIGINNER_LAYER = 10,
				ROW_LEVEL = 3,
				ROW_MONEY = 4,
				KAWARI = 100,
				SHITAKUKIN = 100;
		final String BLANK = "",
				OUENKI = "応援旗",
				KOUGYOKU = "鋼玉",
				SP_SWORD = "スペシャルソード";

		// 出力用のArrayList
		List<String> outputLines = new ArrayList<String>();
		try {
			List<String> inputLines = Files.readAllLines(inputPath);
			for (String line : inputLines) {
				String[] splitLineArray = line.split(",", NEGATIVE);
				List<String> tmpList = new ArrayList<String>(Arrays.asList(splitLineArray));
				level = Integer.parseInt(tmpList.get(ROW_LEVEL));
				money = Integer.parseInt(tmpList.get(ROW_MONEY));
				if (level == INITIAL) {
					row_blank = tmpList.indexOf(BLANK);
					if (row_blank == NEGATIVE) {
						tmpList.set(ROW_MONEY, String.valueOf(money += KAWARI));
					} else if (row_blank != NEGATIVE) {
						tmpList.set(row_blank, OUENKI);
					}
				}
				if (level <= BIGINNER_LAYER) {
					tmpList.set(ROW_MONEY, String.valueOf(money += SHITAKUKIN));
				}
				kougyoku = tmpList.indexOf(KOUGYOKU);
				if (kougyoku != NEGATIVE) {
					tmpList.set(kougyoku, SP_SWORD);
				}
				outputLines.add(String.join(",", tmpList));
			}
			Files.write(outputPath, outputLines, CREATE_NEW);
			System.out.println("処理終了");
		} catch (FileNotFoundException e) {
			System.out.println("CSVファイルが見つかりません。");
		} catch (FileAlreadyExistsException e) {
			System.out.println("ファイルを上書きしますか? [y/n]");
			Scanner scanner = new Scanner(System.in);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("レベルの値に異常値が含まれています。");
		}
	}
}