package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day09 {

  final List<Long> numbers;

  public Day09(final FileReader fileReader) {

    numbers = new ArrayList<>();

    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      while(true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }

      numbers.add(Long.parseLong(lineJustFetched));

      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public long findFirstNoSum(final int preambleLength) {

    final LinkedList<Long> list = new LinkedList<>();
    for (final Long i : numbers) {
      if (list.size() < preambleLength) {
        list.addLast(i);
        continue;
      }
      boolean valid = false;
      for(int j = 0; j < preambleLength && !valid; j++) {
        for(int k = j + 1; k < preambleLength; k++) {
          if (i == list.get(j) + list.get(k)) {
            valid = true;
            break;
          }
        }
      }
      if(!valid) {
        return i;
      }
      list.removeFirst();
      list.addLast(i);
    }
    throw new RuntimeException();
  }

  public long findFirstInvalidContiguousRangeSum(final long invalidNumber) {
    long min;
    long max;
    for(int j = 0; j < numbers.size() ; j++) {
      long sum = numbers.get(j);
      min = sum;
      max = sum;
      for(int k = j + 1; k < numbers.size() && sum < invalidNumber; k++) {
        long current = numbers.get(k);
        sum += current;
        if (current < min) {
          min = current;
        }
        if (current > max) {
          max = current;
        }
        if (invalidNumber == sum) {
          return min + max;
        }
      }
    }
    throw new RuntimeException();
  }

}
