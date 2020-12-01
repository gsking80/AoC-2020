package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day01 {

  final List<Integer> numbers;

  public Day01(FileReader fileReader) {
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      numbers = new ArrayList<>();

      while(true) {
        final String lineJustFetched = buf.readLine();
        if(null == lineJustFetched) {
          break;
        } else {
          numbers.add(Integer.valueOf(lineJustFetched));
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int getTwoProduct() {
    for (int i = 0; i < numbers.size(); i++) {
      for (int j = i + 1; j < numbers.size(); j++) {
        if (numbers.get(i) + numbers.get(j) == 2020) {
          return numbers.get(i) * numbers.get(j);
        }
      }
    }
    return 0;
  }

  public int getThreeProduct() {
    for (int i = 0; i < numbers.size(); i++) {
      for (int j = i + 1; j < numbers.size(); j++) {
        for (int k = j+1; k < numbers.size(); k++) {
          if (numbers.get(i) + numbers.get(j) + numbers.get(k) == 2020) {
            return numbers.get(i) * numbers.get(j) * numbers.get(k);
          }
        }
      }
    }
    return 0;
  }
}
