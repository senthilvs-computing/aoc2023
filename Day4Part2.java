

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4Part2
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
            List<String> allLines = Files.readAllLines(Paths.get("day4.txt"));//day4.txt
            int sum = 0;
            int[] cards = new int[allLines.size()+1];
            Arrays.fill(cards,1);
            Map<Integer,Integer> gameWonCardsMap = new HashMap<>();
            for (String line : allLines) {
                int index1 = line.indexOf(":");
                String gameStr = line.substring(0, index1);
                System.out.println("game num "+gameStr);
                int gameId = getNumberFromString(gameStr);
                System.out.println("gameId "+gameId);
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
                int cardsWon = 0;
                for (String outcome : outcomes) {
                    String outcomeNum = outcome.trim();
                    if (outcomeNum.length() > 0 && winnerSet.contains(outcomeNum)) {
                        cardsWon++;
                    }
                }
                System.out.println("cards won in this game "+cardsWon);
                gameWonCardsMap.put(gameId,cardsWon);
                System.out.println("------------------------");
                //sum += cardsWon;
            }
            System.out.println("gameWonCardsMap "+gameWonCardsMap);
            int pre = 0;
            for(int i = 1; i < cards.length;i++){
                pre = cards[i];
                System.out.println("pre "+pre);
                int cardsWonInThisGame = gameWonCardsMap.get(i);
                for(int j = 1; j <= cardsWonInThisGame ; j++) {
                    if(j+i < cards.length)
                        cards[j + i] += pre;
                }
            }
            for(int i = 1; i < cards.length;i++){
                sum += cards[i];
            }
            System.out.println("sum of total cards won is "+sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
