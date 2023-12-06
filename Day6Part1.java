

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6Part1
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
            int[] raceDurations = {7,15,30};
            int[] recordDistances = {9,40,200};
            */


            int[] raceDurations = {47,84,74,67};
            int[] recordDistances = {207,1394,1209,1014};

            int result = 1;
            for(int i = 0 ; i < raceDurations.length ; i++){
                System.out.println("recordDistance for this race is  "+ recordDistances[i]);
                int wons = 0;
                int wait =  1;
                while(wait < raceDurations[i]){
                    int speed = wait;
                    int remaining = raceDurations[i] - wait;
                    int distance = speed * remaining;
                    if(distance > recordDistances[i]){
                        System.out.println("distance crossed record is  "+ distance);
                        wons++;
                    }
                    wait++;
                }
                if(wons > 0){
                    result *= wons;
                }
                System.out.println("wons  "+ wons);

            }
            System.out.println("result  "+ result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
