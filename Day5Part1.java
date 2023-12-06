

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5Part1
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
            List<String> allLines = Files.readAllLines(Paths.get("day5.txt"));//day5.txt testday5part1.txt

            Map<Long,Long> seedToSoilMap = new HashMap<>();
            Map<Long,Long> soilToFertilizerMap = new HashMap<>();
            Map<Long,Long> fertilizerToWaterMap = new HashMap<>();
            Map<Long,Long> waterToLightMap = new HashMap<>();
            Map<Long,Long> lightToTemperatureMap = new HashMap<>();
            Map<Long,Long> temperatureToHumidityMap = new HashMap<>();
            Map<Long,Long> humidityToLocationMap = new HashMap<>();
            List<Long> interestedSeeds = new ArrayList<Long>();

            List<Trio> seedSoilMapping = new ArrayList<>();
            List<Trio> soilFertilizerMapping = new ArrayList<>();
            List<Trio> fertilizerWaterMapping = new ArrayList<>();
            List<Trio> waterLightMapping = new ArrayList<>();
            List<Trio> lightTemperatureMapping = new ArrayList<>();
            List<Trio> temperatureHumidityMapping = new ArrayList<>();
            List<Trio> humidityLocationMapping = new ArrayList<>();

            for (int i = 0; i < allLines.size() ;i++) {
                String line = allLines.get(i);
                if(line.isBlank()){
                    continue;
                }
                if(line.contains("seeds:")){
                    int index1 = line.indexOf(":");
                    String seedsStr = line.substring(index1+1);
                    System.out.println("seeds input" + seedsStr);
                    String[] seeds = seedsStr.split(" ");
                    for (String seed : seeds) {
                        if(seed.trim().length() > 0){
                            interestedSeeds.add(Long.parseLong(seed.trim()));
                        }
                    }
                }

                if(line.contains("seed-to-soil")){
                    System.out.println("entered map 1");
                    i++;
                    line = allLines.get(i);
                    while(!line.isBlank()){
                        String[] fields = line.split(" ");
                        Long soilStart = Long.parseLong(fields[0]);
                        Long seedStart = Long.parseLong(fields[1]);
                        Long seedSoilRange = Long.parseLong(fields[2]);
                        Trio trio = new Trio(seedStart,soilStart,seedSoilRange);
                        seedSoilMapping.add(trio);
                        line = allLines.get(++i);
                    }
                }


                if(line.contains("soil-to-fertilizer")){
                    System.out.println("entered map 2");
                    i++;
                    line = allLines.get(i);
                    while(!line.isBlank()){
                        String[] fields = line.split(" ");
                        Long fertilizerStart = Long.parseLong(fields[0]);
                        Long soilStart = Long.parseLong(fields[1]);
                        Long rangeLength = Long.parseLong(fields[2]);
                        Trio trio = new Trio(soilStart,fertilizerStart,rangeLength);
                        soilFertilizerMapping.add(trio);
                        line = allLines.get(++i);
                    }
                }

                if(line.contains("fertilizer-to-water")){
                    System.out.println("entered map 3");
                    i++;
                    line = allLines.get(i);
                    while(!line.isBlank()){
                        String[] fields = line.split(" ");
                        Long waterStart = Long.parseLong(fields[0]);
                        Long fertilizerStart = Long.parseLong(fields[1]);
                        Long rangeLength = Long.parseLong(fields[2]);
                        Trio trio = new Trio(fertilizerStart,waterStart,rangeLength);
                        fertilizerWaterMapping.add(trio);
                        line = allLines.get(++i);
                    }
                }


                if(line.contains("water-to-light")){
                    System.out.println("entered map 4");
                    i++;
                    line = allLines.get(i);
                    while(!line.isBlank()){
                        String[] fields = line.split(" ");
                        Long lightStart = Long.parseLong(fields[0]);
                        Long waterStart = Long.parseLong(fields[1]);
                        Long rangeLength = Long.parseLong(fields[2]);
                        Trio trio = new Trio(waterStart,lightStart,rangeLength);
                        waterLightMapping.add(trio);
                        line = allLines.get(++i);
                    }
                }

                if(line.contains("light-to-temperature")){
                    System.out.println("entered map 5");
                    i++;
                    line = allLines.get(i);
                    while(!line.isBlank()){
                        String[] fields = line.split(" ");
                        Long temperatureStart = Long.parseLong(fields[0]);
                        Long lightStart = Long.parseLong(fields[1]);
                        Long rangeLength = Long.parseLong(fields[2]);
                        Trio trio = new Trio(lightStart,temperatureStart,rangeLength);
                        lightTemperatureMapping.add(trio);
                        line = allLines.get(++i);
                    }
                }


                if(line.contains("temperature-to-humidity")){
                    System.out.println("entered map 6");
                    i++;
                    line = allLines.get(i);
                    while(!line.isBlank()){
                        String[] fields = line.split(" ");
                        Long humidityStart = Long.parseLong(fields[0]);
                        Long temperatureStart = Long.parseLong(fields[1]);
                        Long rangeLength = Long.parseLong(fields[2]);
                        Trio trio = new Trio(temperatureStart,humidityStart,rangeLength);
                        temperatureHumidityMapping.add(trio);
                        line = allLines.get(++i);
                    }
                }

                if(line.contains("humidity-to-location")){
                    System.out.println("entered map 7");
                    i++;
                    line = allLines.get(i);
                    while(!line.isBlank()){
                        String[] fields = line.split(" ");
                        Long locationStart = Long.parseLong(fields[0]);
                        Long humidityStart = Long.parseLong(fields[1]);
                        Long rangeLength = Long.parseLong(fields[2]);
                        Trio trio = new Trio(humidityStart,locationStart,rangeLength);
                        humidityLocationMapping.add(trio);
                        if(i >= allLines.size() - 1)
                            break;
                        line = allLines.get(++i);
                    }
                }
            }
            System.out.println("seedSoilMapping "+seedSoilMapping);
            System.out.println("soilFertilizerMapping "+soilFertilizerMapping);
            System.out.println("fertilizerWaterMapping "+fertilizerWaterMapping);
            System.out.println("waterLightMapping "+waterLightMapping);
            System.out.println("lightTemperatureMapping "+lightTemperatureMapping);
            System.out.println("temperatureHumidityMapping "+temperatureHumidityMapping);
            System.out.println("humidityLocationMapping "+humidityLocationMapping);

            Long lowest = Long.MAX_VALUE;
            for(Long seedNum:interestedSeeds){
                Long soil = -1L;
                for(Trio trio:seedSoilMapping){
                    if(seedNum >= trio.srcStart && seedNum <= (trio.srcStart+trio.range-1)){
                        soil = seedNum-trio.srcStart+trio.destStart;
                        break;
                    }
                }
                if(soil < 0){
                    soil = seedNum;
                }


                Long fertilizer = -1L;
                for(Trio trio:soilFertilizerMapping){
                    if(soil >= trio.srcStart && soil <= (trio.srcStart+trio.range-1)){
                        fertilizer = soil-trio.srcStart+trio.destStart;
                        break;
                    }
                }
                if(fertilizer < 0){
                    fertilizer = soil;
                }

                Long water = -1L;
                for(Trio trio:fertilizerWaterMapping){
                    if(fertilizer >= trio.srcStart && fertilizer <= (trio.srcStart+trio.range-1)){
                        water = fertilizer-trio.srcStart+trio.destStart;
                        break;
                    }
                }
                if(water < 0){
                    water = fertilizer;
                }

                Long light = -1L;
                for(Trio trio:waterLightMapping){
                    if(water >= trio.srcStart && water <= (trio.srcStart+trio.range-1)){
                        light = water-trio.srcStart+trio.destStart;
                        break;
                    }
                }
                if(light < 0){
                    light = water;
                }



                Long temperature = -1L;
                for(Trio trio:lightTemperatureMapping){
                    if(light >= trio.srcStart && light <= (trio.srcStart+trio.range-1)){
                        temperature = light-trio.srcStart+trio.destStart;
                        break;
                    }
                }
                if(temperature < 0){
                    temperature = light;
                }

                Long humidity = -1L;
                for(Trio trio:temperatureHumidityMapping){
                    if(temperature >= trio.srcStart && temperature <= (trio.srcStart+trio.range-1)){
                        humidity = temperature-trio.srcStart+trio.destStart;
                        break;
                    }
                }
                if(humidity < 0){
                    humidity = temperature;
                }


                Long location = -1L;
                for(Trio trio:humidityLocationMapping){
                    if(humidity >= trio.srcStart && humidity <= (trio.srcStart+trio.range-1)){
                        location = humidity-trio.srcStart+trio.destStart;
                        break;
                    }
                }
                if(location < 0){
                    location = humidity;
                }
                System.out.println("location "+location);
                lowest = Math.min(lowest,location);
            }

            System.out.println("lowest location Num "+ lowest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Trio{
    Long srcStart;
    Long destStart;
    Long range;

    Trio(Long srcStart,Long destStart,Long range){
        this.srcStart = srcStart;
        this.destStart = destStart;
        this.range = range;
    }
}
