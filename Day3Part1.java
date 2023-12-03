
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part1 {

    public static boolean isNumber(Pair pair, String prev, String next, int cols, String curr) {
        boolean prevSymbolPresent = false;
        if (prev != null) {
            char[] prevRow = prev.toCharArray();
            int newStart = pair.start - 1;
            if (pair.start == 0) {
                newStart = 0;
            }
            int newEnd = pair.end + 1;
            if (pair.end == cols - 1) {
                newEnd = cols - 1;
            }

            for (int i = newStart; i <= newEnd; i++) {
                if (prevRow[i] != '.') {
                    prevSymbolPresent = true;
                }
            }
        }


        boolean nextSymbolPresent = false;
        if(next !=null){
            char[] nextRow = next.toCharArray();
            int newStart = pair.start - 1;
            if (pair.start == 0) {
                newStart = 0;
            }
            int newEnd = pair.end + 1;
            if (pair.end == cols - 1) {
                newEnd = cols - 1;
            }

            for (int i = newStart; i <= newEnd; i++) {
                if (nextRow[i] != '.') {
                    nextSymbolPresent = true;
                }
            }
        }

        //boolean adjacentSymbolPresent = false;
        boolean leftSymbolPresent  = false;
        if(pair.start != 0){
            leftSymbolPresent = curr.charAt(pair.start -1) != '.';
        }
        System.out.println(" leftSymbolPresent is "+leftSymbolPresent);

        boolean rightSymbolPresent  = false;
        if(pair.end < cols - 1){
            rightSymbolPresent = curr.charAt(pair.end +1) != '.';
        }
        System.out.println(" rightSymbolPresent is "+rightSymbolPresent);

        return prevSymbolPresent || nextSymbolPresent || leftSymbolPresent || rightSymbolPresent;
    }


    public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("day3.txt"));//testday3part1.txt
            int sum = 0;
            int rows = allLines.size();
            int cols = allLines.get(0).length();

            for(int i = 0; i < rows ; i++){
                char[] currRow = allLines.get(i).toCharArray();
                List<Pair> numbers = new ArrayList<>();
                int start = -1;
                for(int j = 0 ; j < cols ; j++){
                    if(Character.isDigit(currRow[j])){
                        if(start < 0){
                            start = j;
                        }
                        if(start >= 0 && j == cols-1){
                            Pair lastNum = new Pair(start,j);
                            numbers.add(lastNum);
                            start = -1;
                        }
                    }
                    else {
                        if(start != -1){
                            Pair newPair = new Pair(start,j-1);
                            numbers.add(newPair);
                            start = -1;
                        }
                    }
                }

                for(Pair numberPair:numbers){
                    System.out.println(" pair start "+numberPair.start);
                    System.out.println(" pair end "+numberPair.end);

                    String prev = i == 0 ? null : allLines.get(i-1);
                    String next = i == rows -1 ? null : allLines.get(i+1);
                    if(isNumber(numberPair,prev,next,cols, allLines.get(i))){
                        System.out.println("number is "+allLines.get(i).substring(numberPair.start,numberPair.end+1));
                        sum += Integer.parseInt(allLines.get(i).substring(numberPair.start,numberPair.end+1));
                    }
                }

                //matrix[i] = allLines.get(i).toCharArray();
            }

            System.out.println("sum of part numbers is "+sum);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Pair{
        int start;
        int end;


        Pair(int start,int end){
            this.start = start;
            this.end = end;
        }
}
