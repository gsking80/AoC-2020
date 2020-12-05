package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day05 {

  final List<String> passes;

  public Day05(FileReader fileReader) {

    passes = new ArrayList<>();

    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      Map<String,String> passport = new HashMap<>();
      while(true) {
        final String lineJustFetched = buf.readLine();
        if(null == lineJustFetched) {
          break;
        } else {
          passes.add(lineJustFetched);
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public static int seatID(final String pass) {
    int front = 0;
    int back = 127;
    for (int i = 0; i < 7; i++) {
      if(pass.charAt(i) == 'F') {
        back -= Math.pow(2, 6 - i);
      } else {
        front += Math.pow(2, 6 - i);
      }
    }
    int left = 0;
    int right = 7;
    for (int i = 0; i < 3; i++) {
      if(pass.charAt(i+7) == 'L') {
        right -= Math.pow(2, 2 - i);
      } else {
        left += Math.pow(2, 2 - i);
      }
    }
    int row = pass.charAt(6) == 'F' ? front : back;
    int seat = pass.charAt(9) == 'L' ? left : right;

    return (row * 8) + seat;
  }

  public int highestSeatId() {
    int highestID = 0;
    for(final String pass : passes) {
      int id = seatID(pass);
      if (id > highestID) {
        highestID = id;
      }
    }
    return highestID;
  }

  public int mySeatId() {
    final Set<Integer> seats = new HashSet<>();
    for (final String pass : passes) {
      seats.add(seatID(pass));
    }
    for (int i = 40; i < 801; i++) {
      if (!seats.contains(i)) {
        return i;
      }
    }
    return 0;
  }
}
