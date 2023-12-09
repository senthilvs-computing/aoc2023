import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day8Part1 {

    public static void main(String[] args) {
            /*
            String directions = "RL";
            Map<String,List<String>> input = new HashMap<>();
            String[] node1 = {"BBB","CCC"};
            input.put("AAA", Arrays.asList(node1));
            String[] node2 = {"DDD","EEE"};
            input.put("BBB", Arrays.asList(node2));
            String[] node3 = {"ZZZ","GGG"};
            input.put("CCC", Arrays.asList(node3));
            String[] node4 = {"DDD","DDD"};
            input.put("DDD", Arrays.asList(node4));
            String[] node5 = {"EEE","EEE"};
            input.put("EEE", Arrays.asList(node5));
            String[] node6 = {"GGG","GGG"};
            input.put("GGG", Arrays.asList(node6));
            String[] node7 = {"ZZZ","ZZZ"};
            input.put("ZZZ", Arrays.asList(node7));
            */


            /*
            String directions = "LLR";
            Map<String,List<String>> input = new HashMap<>();
            String[] node1 = {"BBB","BBB"};
            input.put("AAA", Arrays.asList(node1));
            String[] node2 = {"AAA","ZZZ"};
            input.put("BBB", Arrays.asList(node2));
            String[] node3 = {"ZZZ","ZZZ"};
            input.put("CCC", Arrays.asList(node3));
            */

            try {
                List<String> allLines = Files.readAllLines(Paths.get("day8.txt"));

                String directions = allLines.get(0);
                Map<String,List<String>> input = new HashMap<>();
                for(int i = 1; i < allLines.size() ;i++){
                    if(!allLines.get(i).isBlank()){
                        String inputLine = allLines.get(i);
                        String[] fields = inputLine.split("=");
                        String nodeName = fields[0].trim();
                        System.out.println("nodeName "+nodeName);
                        List<String> pair = new ArrayList<>();
                        String left = fields[1].substring(fields[1].indexOf("(")+1,fields[1].indexOf(","));
                        String right = fields[1].substring(fields[1].indexOf(",")+2,fields[1].indexOf(")"));
                        System.out.println("left "+left);
                        System.out.println("right "+right);
                        pair.add(left);
                        pair.add(right);
                        input.put(nodeName,pair);
                    }
                }

                int steps = 0;
                boolean repeat = false;
                String current = "AAA";
                do {
                    for (char direction : directions.toCharArray()) {
                        if ('L' == direction) {
                            current = input.get(current).get(0);
                            System.out.println("current " + current);
                        } else {
                            current = input.get(current).get(1);
                            System.out.println("current " + current);
                        }
                        steps++;
                        System.out.println("-------");
                    }

                    System.out.println("after one follow current " + current);

                    if (current.equals("ZZZ")) {
                        repeat = false;
                        //steps++;
                        break;
                    } else {
                        repeat = true;
                    }
                    System.out.println("repeat " + repeat);
                }
                while (repeat);
                System.out.println("steps " + steps);
            }
            catch(Exception e){

            }
    }
}
