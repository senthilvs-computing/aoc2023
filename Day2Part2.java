

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2Part2
{

    public static int getNumberFromString(String input){
        System.out.println(input);
        String regex = "\\d+";
        //Creating a pattern object
        Pattern pattern = Pattern.compile(regex);
        //Creating a Matcher object
        Matcher matcher = pattern.matcher(input);
        //System.out.println("Digits in the given string are: ");
        StringBuilder number = new StringBuilder();
        while(matcher.find()) {
            number.append(matcher.group());
        }
        System.out.println(Integer.parseInt(number.toString()));
        return Integer.parseInt(number.toString());
    }


    public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("testday2part1.txt"));
            int sum = 0;
            int maxRed = 12;
            int maxGreen = 13;
            int maxBlue = 14;
            for (String line : allLines) {
                int index1 = line.indexOf(":");
                String gameStr = line.substring(0,index1);
                System.out.println(gameStr);
                int gameId = getNumberFromString(gameStr);
                String valuesStr = line.substring(index1);
                String[] gameSets = valuesStr.split(";");
                int power = 1;
                int powerRed = 0;
                int powerGreen = 0;
                int powerBlue = 0;
                for(String gameSet : gameSets){
                    String[] cubes = gameSet.split(",");
                    for(String cube:cubes){
                        int num = getNumberFromString(cube);
                        if(cube.contains("red")){
                            powerRed = Math.max(powerRed,num);
                        }
                        else if(cube.contains("green")){
                            powerGreen = Math.max(powerGreen,num);
                        }
                        else if(cube.contains("blue")){
                            powerBlue = Math.max(powerBlue,num);
                        }
                        else{
                            //do nothing
                        }
                    }
                }
                System.out.println("powerRed "+powerRed);
                System.out.println("powerGreen "+powerGreen);
                System.out.println("powerBlue "+powerBlue);
                if(powerRed > 0)
                    power *= powerRed;
                if(powerGreen > 0)
                    power *= powerGreen;
                if(powerBlue > 0)
                    power *= powerBlue;
                System.out.println("power "+power);
                sum += power;


            }
            System.out.println("sum of possible game ids is "+sum);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
