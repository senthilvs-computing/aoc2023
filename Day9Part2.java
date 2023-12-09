import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day9Part2 {

    public static void predicted(List<Integer> originalSeq){
        int prevDiff = 0;
        boolean repeat = false;
        List<List<Integer>> allRows = new ArrayList<>();
        allRows.add(originalSeq);
        int rows = 0;
        List<Integer> originalSeqCopy = new ArrayList<>(originalSeq);
        do {
            int prev = originalSeqCopy.get(0);
            List<Integer> diffs = new ArrayList<>();
            for (int i = 1; i < originalSeqCopy.size(); i++) {
                int diff = originalSeqCopy.get(i) - prev;
                diffs.add(diff);
                prev = originalSeqCopy.get(i);
            }
            System.out.println(" diffs "+diffs);
            allRows.add(diffs);
            rows++;
            if(diffs.stream().distinct().count() == 1){
                repeat = false;
                int j = 0;

                do{
                    List<Integer> interestedRow = allRows.get(allRows.size()-1-j);
                    prevDiff = interestedRow.get(0);//diffs.get(0);
                    System.out.println(" prevDiff "+prevDiff);
                    rows--;
                    int newFirstElem = (-1 * prevDiff) + allRows.get(rows).get(0);
                    System.out.println(" newFirstElem "+newFirstElem);
                    List<Integer> currRow = allRows.get(rows);
                    Collections.reverse(currRow);
                    currRow.add(newFirstElem);
                    Collections.reverse(currRow);
                    System.out.println(" currRow "+currRow);
                    //allRows.get(rows).add(newlastElem);
                    j++;

                }
                while(rows > 0);
            }
            else{
                repeat = true;
                originalSeqCopy.clear();
                originalSeqCopy.addAll(diffs);
            }
        }
        while(repeat);
        //return allRows.get(0).get(allRows.size()-1);
    }

    public static void main(String[] args) {
            try {
                List<String> allLines = Files.readAllLines(Paths.get("day9.txt")); //testday9part1 day9
                int sum = 0;
                for(int i = 0 ; i < allLines.size() ; i++){
                    String currLine = allLines.get(i);
                    System.out.println(" currLine "+currLine);
                    String[] numberStrs = currLine.split(" ");
                    List<Integer> nums = new ArrayList<>();
                    System.out.println(" numberStrs "+ Arrays.toString(numberStrs));
                    for(String number: numberStrs){
                        int num = Integer.parseInt(number);
                        nums.add(num);
                    }
                    System.out.println("nums  " + nums);
                    //int next = predicted(nums);
                    predicted(nums);
                    System.out.println("orig seq  " + nums);
                    int next = nums.get(0);
                    System.out.println("predicted new start elem " + next);
                    sum += next;
                    System.out.println("------");
                }
                System.out.println("sum " + sum);
            }
            catch(Exception e){

            }
    }
}
