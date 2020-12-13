package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {

  final int earliestDeparture;
  final List<String> schedule;

  public Day13(final FileReader fileReader) {
    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      earliestDeparture = Integer.parseInt(buf.readLine());
      schedule = Arrays.asList(buf.readLine().split(","));

    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  final int busToTake() {
    int busToTake = -1;
    int waitTime = 100000;
    for(final String bus: schedule) {
      if (bus.equals("x")) {
        continue;
      }
      int busId = Integer.parseInt(bus);
      int wait = busId - (earliestDeparture % busId);
      if (wait == busId) {
        wait = 0;
      }
      if (wait < waitTime) {
        waitTime = wait;
        busToTake = busId;
      }
    }

    return busToTake * waitTime;
  }

  final long busContestTime () {
    // Chinese Remainder Theorem
    List<Long> numbers = new ArrayList<>();
    List<Long> remainders = new ArrayList<>();
    for(int i = 0; i < schedule.size(); i++) {
      if (schedule.get(i).equals("x")) {
        continue;
      }
      long number = Long.parseLong(schedule.get(i));
      long remainder = (number - i);
      while (remainder < 0) {
        remainder += number;
      }
      remainder = remainder % number;
      numbers.add(number);
      remainders.add(remainder);
    }
    return findMinX(numbers,remainders);
  }

  private Long findMinX(final List<Long> numbers, final List<Long> remainders){
    long product = 1;
    for (final Long number: numbers) {
      product *= number;
    }
    long result = 0;

    for (int i = 0; i < numbers.size(); i++) {
      long partialProduct = product / numbers.get(i);
      result += remainders.get(i) * modularMultiplicativeInverse(partialProduct, numbers.get(i)) * partialProduct;
    }

    return result % product;
  }

  private static long modularMultiplicativeInverse(long a, long m)
  {
    long m0 = m;
    long t;
    long q;
    long x0 = 0;
    long x1 = 1;

    if (m == 1) {
      return 0;
    }

    // Apply extended Euclid Algorithm
    while (a > 1)
    {
      // q is quotient
      q = a / m;
      t = m;
      // m is remainder now, process same as euclid's algorithm
      m = a % m;
      a = t;
      t = x0;
      x0 = x1 - q * x0;
      x1 = t;
    }

    // Make x1 positive
    if (x1 < 0) {
      x1 += m0;
    }

    return x1;
  }
}
