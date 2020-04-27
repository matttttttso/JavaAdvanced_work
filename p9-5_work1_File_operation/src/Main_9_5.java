import static java.nio.file.StandardOpenOption.*;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
		int level = 0,
			money = 0,
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
				SP_SWORD = "スペシャルソード",
				YES = "y",
				NO = "n";

		// 出力用のArrayList
		List<String> outputLines = new ArrayList<String>();
		List<String> inputLines = new ArrayList<String>();
		try {
			inputLines = Files.readAllLines(inputPath);
		} catch (NoSuchFileException e) {
			System.out.println("CSVファイルが見つかりません。");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : inputLines) {
			String[] splitLineArray = line.split(",", NEGATIVE);
			List<String> tmpList = new ArrayList<String>(Arrays.asList(splitLineArray));

			try {
				level = Integer.parseInt(tmpList.get(ROW_LEVEL));
				money = Integer.parseInt(tmpList.get(ROW_MONEY));
			} catch (NumberFormatException e) {
				System.out.println("レベルの値に異常値が含まれています。");
			}

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
		try {
			Files.write(outputPath, outputLines, CREATE_NEW);
			System.out.println("作業が終了しました。");
		} catch (FileAlreadyExistsException e) {
			System.out.println("ファイルを上書きしますか? [y/n]");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
			if(input.equals(YES)) {
				try {
					Files.write(outputPath, outputLines, TRUNCATE_EXISTING);
					System.out.println("ファイルを上書きしました。");
				} catch (IOException error) {
					error.printStackTrace();
				}
			} else if(input.equals(NO)){
				System.out.println("作業を中断しました。");
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}