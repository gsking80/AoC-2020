package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {

  final List<Integer> adapters;
  final Map<Integer,Long> choicesMap = new HashMap<>();

  public Day10(final FileReader fileReader) {
    adapters = new ArrayList<>();
    adapters.add(0);
    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      while(true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        adapters.add(Integer.parseInt(lineJustFetched));
      }
      Collections.sort(adapters);
      adapters.add(adapters.get(adapters.size() - 1) + 3);
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int joltageDifferences() {
    int ones = 0;
    int threes = 0;
    for (int i = 1; i < adapters.size(); i++) {
      int difference = adapters.get(i) - adapters.get(i-1);
      if (difference == 1) {
        ones++;
      }
      if (difference == 3) {
        threes++;
      }
    }

    return ones * threes;
  }

  long choicesFrom(final int adapter) {
    if(adapter == adapters.size() -1) {
      return 1;
    }
    long choices = 0;
    int adapterValue = adapters.get(adapter);
    for (int i = adapter + 1; i <= adapter + 3 && i < adapters.size(); i++) {
      if (adapters.get(i) <= adapterValue + 3) {
        Long currentChoices = choicesMap.get(i);
        if (currentChoices == null) {
          currentChoices = choicesFrom(i);
          choicesMap.put(i, currentChoices);
        }
        choices += currentChoices;
      }
    }
    return choices;
  }

}
