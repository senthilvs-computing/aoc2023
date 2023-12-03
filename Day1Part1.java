
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1Part1 {

	List<String> getInputLines() throws URISyntaxException, IOException {
		return Files.readAllLines(Paths.get(getClass().getClassLoader().getResource("testpart1.txt").toURI()));
	}

	public static void main(String[] args) {
		Day1Part1 day1 = new Day1Part1();
		try {
			List<String> allLines = Files.readAllLines(Paths.get("testday1part1.txt")); //day1
			int sum = 0;
			for (String line : allLines) {
				System.out.println(line);
				String num = "";
				char[] letters1 = line.toCharArray();
				for(char letter:letters1){
					if(Character.isDigit(letter)){
						num += letter;
						break;
					}
				}
				StringBuilder sb = new StringBuilder(line);
				String reversedLine = sb.reverse().toString();
				char[] letters2 = reversedLine.toCharArray();
				for(char letter:letters2){
					if(Character.isDigit(letter)){
						num += letter;
						break;
					}
				}
				if(num.length()>0){
					sum += Integer.parseInt(num);
				}
			}
			System.out.println("sum is "+sum);
		} catch (IOException e) {
			e.printStackTrace();
		} /*catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}*/
	}

}
