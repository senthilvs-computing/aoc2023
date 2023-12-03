
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part2 {



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
            List<String> allLines = Files.readAllLines(Paths.get("day3.txt"));//testday3part1.txt //day3
            long sum = 0;
            int rows = allLines.size();
            int cols = allLines.get(0).length();


            Map<Integer,List<Pair>> numberInfoMap = new HashMap<>();
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

                numberInfoMap.put(i,numbers);

                for(Pair numberPair:numbers){
                    System.out.println(" pair start "+numberPair.start);
                    System.out.println(" pair end "+numberPair.end);

                    String prev = i == 0 ? null : allLines.get(i-1);
                    String next = i == rows -1 ? null : allLines.get(i+1);
                    if(isNumber(numberPair,prev,next,cols, allLines.get(i))){
                        System.out.println("number is "+allLines.get(i).substring(numberPair.start,numberPair.end+1));
                        //sum += Integer.parseInt(allLines.get(i).substring(numberPair.start,numberPair.end+1));
                    }
                }

                //matrix[i] = allLines.get(i).toCharArray();
            }
            System.out.println("============gear computation below=======");
            System.out.println("initial sum "+sum);
            int gears = 0;
            for(int i = 0; i < rows ; i++){
                String currentRow = allLines.get(i);
                String loopVariant = allLines.get(i);
                int loopIndex = loopVariant.indexOf('*');
                System.out.println("######################################################");
                System.out.println("* found in row num  "+i);
                System.out.println("gears found at this row "+gears);
                while(loopIndex >= 0){
                    //System.out.println("loopVariant  "+loopVariant);

                    int index = loopIndex ; //loopVariant.indexOf('*');
                    System.out.println("star index at  "+index);

                    //check in same row
                    List<Pair> sameRowPairs = numberInfoMap.get(i);
                    int prevInd = index - 1;
                    int nextInd = index + 1;
                    int number1 = -1;
                    int number2 = -1;
                    for(Pair pair:sameRowPairs){
                        if(pair.end == prevInd){
                            number1 = Integer.parseInt(allLines.get(i).substring(pair.start,pair.end+1));
                        }
                        if(pair.start == nextInd){
                            number2 = Integer.parseInt(allLines.get(i).substring(pair.start,pair.end+1));
                        }
                    }
                    int gearRatio = -1;
                    if(number1 > 0 && number2 > 0) {
                        gearRatio = number1 * number2;

                        System.out.println("number1 "+number1);
                        System.out.println("number2 "+number2);
                        System.out.println("gear ratio same-same is "+gearRatio);
                        sum += gearRatio;
                        gears++;
                        System.out.println("gears found "+gears);
                        System.out.println("interim sum  "+sum);
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
                        //System.out.println("interim sum  "+sum);
                    }
                    if(gearRatio != -1){
                        //sum += gearRatio;
                    }

                    //check with same row and top
                    number1 = -1;
                    for(Pair pair:sameRowPairs){
                        if(pair.end == prevInd){
                            number1 = Integer.parseInt(allLines.get(i).substring(pair.start,pair.end+1));
                        }
                        if(pair.start == nextInd){
                            number1 = Integer.parseInt(allLines.get(i).substring(pair.start,pair.end+1));
                        }
                    }
                    number2 = -1;
                    List<Pair> topRowPairs = numberInfoMap.get(i-1);
                    for(Pair pair:topRowPairs) {
                        if (pair.end == prevInd || pair.end == index) {
                            number2 = Integer.parseInt(allLines.get(i-1).substring(pair.start,pair.end+1));
                        }
                        if (pair.start == nextInd || pair.start == index) {
                            number2 = Integer.parseInt(allLines.get(i-1).substring(pair.start,pair.end+1));
                        }
                        if( index >= pair.start && index <= pair.end){
                            number2 = Integer.parseInt(allLines.get(i-1).substring(pair.start,pair.end+1));
                        }
                    }
                    gearRatio = -1;
                    if(number1 > 0 && number2 > 0) {
                        gearRatio = number1 * number2;

                        System.out.println("number1 "+number1);
                        System.out.println("number2 "+number2);
                        System.out.println("gear ratio same-top is "+gearRatio);
                        sum += gearRatio;
                        gears++;
                        System.out.println("gears found "+gears);
                        System.out.println("interim sum  "+sum);
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
                        //System.out.println("interim sum  "+sum);
                    }
                    if(gearRatio != -1){
                        //sum += gearRatio;
                    }

                    //check with same row and bottom
                    number1 = -1;
                    for(Pair pair:sameRowPairs){
                        if(pair.end == prevInd){
                            number1 = Integer.parseInt(allLines.get(i).substring(pair.start,pair.end+1));
                        }
                        if(pair.start == nextInd){
                            number1 = Integer.parseInt(allLines.get(i).substring(pair.start,pair.end+1));
                        }
                    }
                    number2 = -1;
                    List<Pair> bottonRowPairs = numberInfoMap.get(i+1);
                    for(Pair pair:bottonRowPairs) {
                        if (pair.end == prevInd || pair.end == index) {
                            number2 = Integer.parseInt(allLines.get(i+1).substring(pair.start,pair.end+1));
                        }
                        if (pair.start == nextInd || pair.start == index) {
                            number2 = Integer.parseInt(allLines.get(i+1).substring(pair.start,pair.end+1));
                        }
                        if( index >= pair.start && index <= pair.end){
                            number2 = Integer.parseInt(allLines.get(i+1).substring(pair.start,pair.end+1));
                        }
                    }
                    gearRatio = -1;
                    if(number1 > 0 && number2 > 0) {
                        gearRatio = number1 * number2;

                        System.out.println("number1 "+number1);
                        System.out.println("number2 "+number2);
                        System.out.println("gear ratio same-bottom is "+gearRatio);
                        sum += gearRatio;
                        gears++;
                        System.out.println("gears found "+gears);
                        System.out.println("interim sum  "+sum);
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
                        //System.out.println("interim sum  "+sum);
                    }
                    if(gearRatio != -1){
                        //sum += gearRatio;
                    }

                    //check top and bottom
                    number1 = -1;
                    for(Pair pair:topRowPairs) {
                        if (pair.end == prevInd || pair.end == index) {
                            number1 = Integer.parseInt(allLines.get(i-1).substring(pair.start,pair.end+1));
                        }
                        if (pair.start == nextInd || pair.start == index) {
                            number1 = Integer.parseInt(allLines.get(i-1).substring(pair.start,pair.end+1));
                        }
                        if( index >= pair.start && index <= pair.end){
                            number1 = Integer.parseInt(allLines.get(i-1).substring(pair.start,pair.end+1));
                        }
                    }
                    number2 = -1;
                    for(Pair pair:bottonRowPairs) {
                        if (pair.end == prevInd || pair.end == index) {
                            number2 = Integer.parseInt(allLines.get(i+1).substring(pair.start,pair.end+1));
                        }
                        if (pair.start == nextInd || pair.start == index) {
                            number2 = Integer.parseInt(allLines.get(i+1).substring(pair.start,pair.end+1));
                        }
                        if( index >= pair.start && index <= pair.end){
                            number2 = Integer.parseInt(allLines.get(i+1).substring(pair.start,pair.end+1));
                        }
                    }
                    gearRatio = -1;
                    if(number1 > 0 && number2 > 0) {
                        gearRatio = number1 * number2;

                        System.out.println("number1 "+number1);
                        System.out.println("number2 "+number2);
                        System.out.println("gear ratio top-bottom is "+gearRatio);
                        sum += gearRatio;
                        gears++;
                        System.out.println("gears found "+gears);
                        System.out.println("interim sum  "+sum);
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
                        //
                    }
                    if(gearRatio != -1){
                        //sum += gearRatio;
                    }
                    //loopVariant = loopVariant.substring(index+1);
                    loopIndex = loopVariant.indexOf('*',loopIndex+1);
                }
            }

            System.out.println("long sum of gear ratios is "+sum);
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
