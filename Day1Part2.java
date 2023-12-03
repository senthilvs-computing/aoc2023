
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1Part2 {

	public static void main(String[] args) {
		try {
			List<String> allLines = Files.readAllLines(Paths.get("day1.txt"));
			int sum = 0;
			for (String line : allLines) {
				System.out.println(line);

				// duplicate end chars to correctly infer rightly the appended pairs like twone -> twoone
				String process1 = line.replaceAll("nine","ninee");
				String process2 = process1.replaceAll("five","fivee");
				String process3 = process2.replaceAll("eight","eightt");
				String process4 = process3.replaceAll("seven","sevenn");
				String process5 = process4.replaceAll("one","onee");
				String process6 = process5.replaceAll("two","twoo");
				String process7 = process6.replaceAll("three","threee");

				String processed1 = process7.replaceAll("nine","9");
				String processed2 = processed1.replaceAll("eight","8");
				String processed3 = processed2.replaceAll("seven","7");
				String processed4 = processed3.replaceAll("six","6");
				String processed5 = processed4.replaceAll("five","5");
				String processed6 = processed5.replaceAll("four","4");
				String processed7 = processed6.replaceAll("three","3");
				String processed8 = processed7.replaceAll("two","2");
				String processed9 = processed8.replaceAll("one","1");
				String num = "";
				char[] letters1 = processed9.toCharArray();
				for(char letter:letters1){
					if(Character.isDigit(letter)){
						num += letter;
						break;
					}
				}
				StringBuilder sb = new StringBuilder(processed9);
				String reversedLine = sb.reverse().toString();
				char[] letters2 = reversedLine.toCharArray();
				for(char letter:letters2){
					if(Character.isDigit(letter)){
						num += letter;
						break;
					}
				}
				System.out.println("num at each entry is "+num);
				if(num.length()>0){
					sum += Integer.parseInt(num);
				}
			}
			System.out.println("sum is "+sum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
