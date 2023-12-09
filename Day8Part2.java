import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8Part2 {

    public static void main(String[] args) {

            try {
                List<String> allLines = Files.readAllLines(Paths.get("day8.txt")); //testday8part2.txt
                List<String> endingWithAList =  new ArrayList<>();

                String directions = allLines.get(0);
                Map<String,List<String>> input = new HashMap<>();
                for(int i = 1; i < allLines.size() ;i++){
                    if(!allLines.get(i).isBlank()){
                        String inputLine = allLines.get(i);
                        String[] fields = inputLine.split("=");
                        String nodeName = fields[0].trim();
                        //System.out.println("nodeName "+nodeName);
                        if(nodeName.charAt(nodeName.length()-1) == 'A'){
                            endingWithAList.add(nodeName);
                        }

                        List<String> pair = new ArrayList<>();
                        String left = fields[1].substring(fields[1].indexOf("(")+1,fields[1].indexOf(","));
                        String right = fields[1].substring(fields[1].indexOf(",")+2,fields[1].indexOf(")"));
                        //System.out.println("left "+left);
                        //System.out.println("right "+right);
                        pair.add(left);
                        pair.add(right);
                        input.put(nodeName,pair);
                    }
                }
                //System.out.println("~~~~~~~~~~~~~~");
                int steps = 0;
                char[] directionsArr = directions.toCharArray();
                boolean repeat =false;
                do {

                    for (int i = 0; i < directionsArr.length; i++) {
                        boolean met = true;
                        Character currDirection = directionsArr[i];
                        List<String> newList = new ArrayList<>();
                        //System.out.println("endingWithAList " + endingWithAList);
                        for (String current : endingWithAList) {
                            String prev = current;
                            //System.out.println("currDirection " + currDirection);
                            if ('L' == currDirection) {
                                current = input.get(current).get(0);
                                //System.out.println("current " + current);
                            } else {
                                current = input.get(current).get(1);
                                //System.out.println("current " + current);
                            }
                            //System.out.println("met b4 " + met);
                            boolean endsWithZ = current.charAt(current.length() - 1) == 'Z';
                            //System.out.println("endsWithZ " + endsWithZ);
                            met = met && endsWithZ;
                            //System.out.println("met af " + met);
                            newList.add(current);
                            //System.out.println("newList " + newList);
                            //endingWithAList.remove(prev);
                            //endingWithAList.add(current);
                            //System.out.println("--------");
                        }
                        steps++;
                        //System.out.println("met after one round of called out directions " + met);
                        System.out.println("steps " + steps);
                        //System.out.println("newList "+newList);
                        if (met) {
                            System.out.println("step at which all guys ended to z  " + steps);
                            return;
                        }
                        else{
                            repeat = true;
                        }

                        endingWithAList = new ArrayList<>();
                        endingWithAList.addAll(newList);
                        //System.out.println("=====================");
                    }
                } while(repeat);

            }
            catch(Exception e){

            }
    }
}
