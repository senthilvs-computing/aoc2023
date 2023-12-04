

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4Part1
{

    public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("day4.txt"));//testday4part1.txt
            int sum = 0;
            for (String line : allLines) {
                int index1 = line.indexOf(":");
                String gameStr = line.substring(0, index1);
                System.out.println("game num "+gameStr);
                //int gameId = getNumberFromString(gameStr);
                String valuesStr = line.substring(index1+1);
                System.out.println("game input" + valuesStr);
                String[] gameSets = valuesStr.split("\\|");
                String winnersStr = gameSets[0];
                String outcomesStr = gameSets[1];
                System.out.println("winners" + winnersStr);
                System.out.println("outcomes" + outcomesStr);
                String[] winners = winnersStr.split(" ");
                Set<String> winnerSet = new HashSet<String>();
                for (String winner : winners) {
                    String winNum = winner.trim();
                    if(winNum.length() > 0)
                        winnerSet.add(winNum);
                }
                System.out.println("winnerSet "+winnerSet);
                String[] outcomes = outcomesStr.split(" ");
                List<String> matched = new ArrayList();
                int points = 0;
                for (String outcome : outcomes) {
                    String outcomeNum = outcome.trim();

                    if (outcomeNum.length() > 0 && winnerSet.contains(outcomeNum)) {
                        System.out.println("outcomeNum "+outcomeNum);
                        if (points == 0) {
                            points = 1;
                        } else {
                            points *= 2;
                        }
                    }
                }
                System.out.println("points in this game "+points);
                System.out.println("------------------------");
                sum += points;
            }
            System.out.println("sum of  game points is "+sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
