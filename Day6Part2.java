

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6Part2
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
            List<String> allLines = Files.readAllLines(Paths.get("testday6part1.txt"));//day6.txt testday6part1.txt

            /*
            long raceDuration = 71530;
            long recordDistance = 940200;
            */


            long raceDuration = 47847467L;
            long recordDistance = 207139412091014L;

            System.out.println("recordDistance for this race is  "+ recordDistance);
            long wons = 0L;
            long wait =  1L;
            while(wait < raceDuration){
                long speed = wait;
                long remaining = raceDuration - wait;
                long distance = speed * remaining;
                if(distance > recordDistance){
                    System.out.println("distance crossed record is  "+ distance);
                    wons++;
                }
                wait++;
            }
            System.out.println("wons  "+ wons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
