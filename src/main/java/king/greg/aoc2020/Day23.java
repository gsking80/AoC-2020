package king.greg.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 {

  private  Day23() { }

  public static String cups(final String startingCups, final int moves) {
    Map<Integer, Integer> cupMap = new HashMap<>();
    for (int i = 0; i < startingCups.length() - 1; i++) {
      cupMap.put(startingCups.charAt(i) - '0', startingCups.charAt(i+1) - '0');
    }
    cupMap.put(startingCups.charAt(startingCups.length()-1) - '0', startingCups.charAt(0) - '0');
    moveCups(cupMap, startingCups.charAt(0) - '0', moves);
    final StringBuilder cupsBuilder = new StringBuilder();
    int cup = cupMap.get(1);
    while (cup != 1) {
      cupsBuilder.append(cup);
      cup = cupMap.get(cup);
    }
    return cupsBuilder.toString();
  }

  public static Long cups2(final String startingCups, final int moves) {
    Map<Integer, Integer> cupMap = new HashMap<>();
    for (int i = 0; i < startingCups.length() - 1; i++) {
      cupMap.put(startingCups.charAt(i) - '0', startingCups.charAt(i+1) - '0');
    }
    int lastCup = startingCups.length() + 1;
    cupMap.put(startingCups.charAt(startingCups.length()-1) - '0', lastCup);
    while (lastCup < 1000000) {
      cupMap.put(lastCup, ++lastCup);
    }
    cupMap.put(lastCup, startingCups.charAt(0) - '0');
    moveCups(cupMap, startingCups.charAt(0) - '0', moves);
    int cup1 = cupMap.get(1);
    int cup2 = cupMap.get(cup1);
    return (long) cup1 * cup2;
  }

  public static void moveCups(final Map<Integer,Integer> cupMap, final int startingCup, final int moves) {
    int currentCup = startingCup;
    for (int i = 0; i < moves; i++) {
      final List<Integer> cupsToMove = new ArrayList<>(3);
      int previousCup = currentCup;
      for (int j = 0; j < 3; j++) {
        int nextCup = cupMap.get(previousCup);
        cupsToMove.add(nextCup);
        previousCup = nextCup;
      }
      int destination = currentCup == 1 ? cupMap.size() : currentCup - 1;
      while(cupsToMove.contains(destination)) {
        destination = destination == 1 ? cupMap.size() : destination - 1;
      }
      int cupAfterDestination = cupMap.get(destination);
      cupMap.put(currentCup, cupMap.get(cupsToMove.get(2)));
      cupMap.put(destination, cupsToMove.get(0));
      cupMap.put(cupsToMove.get(2), cupAfterDestination);
      currentCup = cupMap.get(currentCup);
    }
  }

}
