import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day7Part2 {

    public static void main(String[] args) {

        try {

            List<String> allLines = Files.readAllLines(Paths.get("testday7part1.txt"));//day7.txt testday7part1.txt
            Map<String,Integer> handToBidMap = new HashMap<>();
            List<String> fiveKind = new ArrayList<>();
            List<String> fourKind = new ArrayList<>();
            List<String> fullKind = new ArrayList<>();
            List<String> threeKind = new ArrayList<>();
            List<String> twoKind = new ArrayList<>();
            List<String> oneKind = new ArrayList<>();
            List<String> highKind = new ArrayList<>();
            int notadded = 0;
            for(String entry: allLines){
                String[] fields = entry.split(" ");
                String hand = fields[0];
                System.out.println(" hand pack "+hand);
                String bidStr = fields[1];
                System.out.println(" bidStr "+bidStr);
                handToBidMap.put(hand,Integer.parseInt(bidStr));

                int distincts = 0;
                Set<Character> cards = new HashSet<>();
                Map<Character,Integer> charCountMap = new HashMap<>();
                for(Character card:hand.toCharArray()){
                    if(!cards.contains(card)){
                        distincts++;
                        cards.add(card);
                    }
                    charCountMap.put(card,charCountMap.getOrDefault(card,0)+1);
                }
                System.out.println(" charCountMap "+charCountMap);
                int nonJHighest = Integer.MIN_VALUE;
                char[] chars = {'2','3','4','5','6','7','8','9','T','Q','K','A'};

                Character toBeReplaced = 'J';
                for( Map.Entry entryyy : charCountMap.entrySet() )
                {
                    if('J' != (Character) entryyy.getKey()){
                        if((Integer)entryyy.getValue() >= nonJHighest){
                            nonJHighest = (Integer)entryyy.getValue();
                            toBeReplaced = (Character) entryyy.getKey();
                        }
                    }
                }

                System.out.println(" J to be replaced with  "+toBeReplaced);
                if(toBeReplaced != 'J') {
                    String newHand = hand.replaceAll("J", toBeReplaced+"");
                    charCountMap = new HashMap<>();
                    for(Character card:newHand.toCharArray()){
                        if(!cards.contains(card)){
                            distincts++;
                            cards.add(card);
                        }
                        charCountMap.put(card,charCountMap.getOrDefault(card,0)+1);
                    }
                    System.out.println("new charCountMap "+charCountMap);
                }

                /*
                for(int i = 0 ; i < chars.length ; i++){
                    System.out.println(" hell i start "+i);
                    int value = charCountMap.get(chars[i]);
                    System.out.println("letter "+chars[i]);
                    System.out.println("value "+value);
                    nonJHighest =Math.max(value,nonJHighest);
                    System.out.println("nonJHighest "+nonJHighest);
                    System.out.println(" hell i end "+i);
                    System.out.println(" chars length "+chars.length);
                }
                */
                System.out.println("nonJHighest "+nonJHighest);

                System.out.println(" original distincts  "+distincts);
                if(charCountMap.get('J') != null){
                    distincts = distincts - charCountMap.get('J') ;
                    //distincts = 5 - (nonJHighest + charCountMap.get('J'));
                }
                System.out.println(" overridden distincts  "+distincts);

                if(distincts == 5){
                    highKind.add(hand);
                }
                else if(distincts == 4){
                    oneKind.add(hand);
                }
                else if(distincts == 3){
                    if(charCountMap.values().contains(3)){
                        threeKind.add(hand);
                    }
                    else {
                        twoKind.add(hand);
                    }
                }
                else if(distincts == 2){
                    if(charCountMap.values().contains(4)){
                        fourKind.add(hand);
                    }
                    else {
                        fullKind.add(hand);
                    }
                }
                else if(distincts == 1) {
                    fiveKind.add(hand);
                }
                else{
                    //do nothing
                    System.out.println("hand not added to bag "+hand);
                    notadded++;
                }

                System.out.println(" not added hands   "+notadded);
                System.out.println("------");
            }

            System.out.println(" total hands  "+allLines.size());
            System.out.println(" not added hands   "+notadded);
            System.out.println(" five hands  "+fiveKind.size());
            System.out.println(" four hands  "+fourKind.size());
            System.out.println(" full hands  "+fullKind.size());
            System.out.println(" three hands  "+threeKind.size());
            System.out.println(" two hands  "+twoKind.size());
            System.out.println(" one hands  "+oneKind.size());
            System.out.println(" zero hands  "+ highKind.size());

            int totalwinning = 0;
            int rankDescending = allLines.size();
            Collections.sort(fiveKind, new CamelCardStrengthComparator());
            for(String hand:fiveKind){
                System.out.println(" hand "+hand);
                System.out.println(" rankDescending "+rankDescending);
                int winning = rankDescending * handToBidMap.get(hand);
                System.out.println(" winning "+winning);
                totalwinning += winning;
                rankDescending--;
                System.out.println("------");
            }
            Collections.sort(fourKind, new CamelCardStrengthComparator());
            for(String hand:fourKind){
                System.out.println(" hand "+hand);
                System.out.println(" rankDescending "+rankDescending);
                int winning = rankDescending * handToBidMap.get(hand);
                System.out.println(" winning "+winning);
                totalwinning += winning;
                rankDescending--;
                System.out.println("------");
            }
            Collections.sort(fullKind, new CamelCardStrengthComparator());
            for(String hand:fullKind){
                System.out.println(" hand "+hand);
                System.out.println(" rankDescending "+rankDescending);
                int winning = rankDescending * handToBidMap.get(hand);
                System.out.println(" winning "+winning);
                totalwinning += winning;
                rankDescending--;
                System.out.println("------");
            }
            Collections.sort(threeKind, new CamelCardStrengthComparator());
            for(String hand:threeKind){
                System.out.println(" hand "+hand);
                System.out.println(" rankDescending "+rankDescending);
                int winning = rankDescending * handToBidMap.get(hand);
                System.out.println(" winning "+winning);
                totalwinning += winning;
                rankDescending--;
                System.out.println("------");
            }
            Collections.sort(twoKind, new CamelCardStrengthComparator());
            for(String hand:twoKind){
                System.out.println(" hand "+hand);
                System.out.println(" rankDescending "+rankDescending);
                int winning = rankDescending * handToBidMap.get(hand);
                System.out.println(" winning "+winning);
                totalwinning += winning;
                rankDescending--;
                System.out.println("------");
            }
            Collections.sort(oneKind, new CamelCardStrengthComparator());
            for(String hand:oneKind){
                System.out.println(" hand "+hand);
                System.out.println(" rankDescending "+rankDescending);
                int winning = rankDescending * handToBidMap.get(hand);
                System.out.println(" winning "+winning);
                totalwinning += winning;
                rankDescending--;
                System.out.println("------");
            }
            Collections.sort(highKind, new CamelCardStrengthComparator());
            for(String hand:highKind){
                System.out.println(" hand "+hand);
                System.out.println(" rankDescending "+rankDescending);
                int winning = rankDescending * handToBidMap.get(hand);
                System.out.println(" winning "+winning);
                totalwinning += winning;
                rankDescending--;
                System.out.println("------");
            }
            System.out.println(" total winning "+totalwinning);

        }
        catch (Exception e){

        }

    }


}

class CamelCardStrengthComparator implements Comparator<String> {
    public int compare(String obj1, String obj2) {
        //System.out.println("obj1 "+obj1);
        //System.out.println("obj2 "+obj2);
        int result = 0;
        for(int i = 0; i < 5 ; i++){
            String obj1Letter = obj1.charAt(i)+"";
            obj1Letter = obj1Letter.replaceFirst("T","10");
            obj1Letter = obj1Letter.replaceFirst("J","1");
            obj1Letter = obj1Letter.replaceFirst("Q","12");
            obj1Letter = obj1Letter.replaceFirst("K","13");
            obj1Letter = obj1Letter.replaceFirst("A","14");
            String obj2Letter = obj2.charAt(i)+"";
            obj2Letter = obj2Letter.replaceFirst("T","10");
            obj2Letter = obj2Letter.replaceFirst("J","1");
            obj2Letter = obj2Letter.replaceFirst("Q","12");
            obj2Letter = obj2Letter.replaceFirst("K","13");
            obj2Letter = obj2Letter.replaceFirst("A","14");
            //System.out.println("obj1Letter "+obj1Letter);
            //System.out.println("obj2Letter "+obj2Letter);
            if(Integer.parseInt(obj1Letter) == Integer.parseInt(obj2Letter)){
                continue;
            }
            else if (Integer.parseInt(obj1Letter) > Integer.parseInt(obj2Letter)) {
                result = -1;
                break;
            }
            else{
                result = 1;
                break;
            }
        }
        //System.out.println("result  "+result);
        return result;
    }
}
