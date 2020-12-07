package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {

  final Map<String, Map<String,Integer>> bagMap;
  final Map<String, Set<String>> goesInto;


  public Day07(FileReader fileReader) {

    Pattern pattern1 = Pattern.compile("^(\\w* \\w*) bags contain(.*)$");
    Pattern pattern2 = Pattern.compile("( (\\d*) (\\w* \\w*)|no other) (bag|bags)[,.]");

    bagMap = new HashMap<>();
    goesInto = new HashMap<>();

    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      while(true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        final Map<String,Integer> bagCount = new HashMap<>();
        Matcher matcher1 = pattern1.matcher(lineJustFetched);
        if(!matcher1.find()) {
          break;
        }
        final String outColor = matcher1.group(1);
        Matcher matcher2 = pattern2.matcher(matcher1.group(2));

        while(matcher2.find()) {
          if (matcher2.group(1).equals("no other")) {
            //empty
          } else {
            bagCount.put(matcher2.group(3),Integer.valueOf(matcher2.group(2)));
            Set<String> bags = goesInto.computeIfAbsent(matcher2.group(3), k -> new HashSet<>());
            bags.add(matcher1.group(1));
          }
        }
        bagMap.put(outColor,bagCount);

      }

    } catch (IOException ioe) {
      throw new RuntimeException();
    }

  }

  public int placesFor(final String bagColor) {
    return goesInto(bagColor).size();
  }

  public Set<String> goesInto(final String bagColor) {
    final Set<String> bags = new HashSet<>();
    final Set<String> colors = goesInto.get(bagColor);
    if (null==colors) {
      return Collections.emptySet();
    }
    for (final String color : colors)
    {
      bags.add(color);
      bags.addAll(goesInto(color));
    }
    return bags;
  }

  public int bagsInside(final String bagColor) {
    int bags = 0;
    for(final Entry<String,Integer> bagCounts : bagMap.get(bagColor).entrySet()) {
      bags += bagCounts.getValue() * (1+bagsInside(bagCounts.getKey()));
    }
    return bags;
  }
}
