package king.greg.aoc2020;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 {

  List<String> floorPlan;
  final int maxX;
  final int maxY;

  public Day11(final FileReader fileReader) {
    floorPlan = new ArrayList<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      while(true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
       floorPlan.add(lineJustFetched);
      }
      maxX = floorPlan.get(0).length() - 1;
      maxY = floorPlan.size() - 1;
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int playGame() {
    boolean changed = true;
    while (changed) {
//      printPlan();
      List<String> nextPlan = new ArrayList<>(floorPlan.size());
      changed = false;
      for (int y = 0; y <= maxY; y++) {
        String row = floorPlan.get(y);
        StringBuilder newRow = new StringBuilder();
        for (int x = 0; x <= maxX; x++) {
          char seat = row.charAt(x);
          switch(seat) {
            case 'L':
              if(occupiedBlock(x,y) == 0){
                newRow.append('#');
                changed = true;
              } else {
                newRow.append(seat);
              }
              break;
            case '#':
              if(occupiedBlock(x,y) > 4){
                newRow.append('L');
                changed = true;
              } else {
                newRow.append(seat);
              }
              break;
            default:
              newRow.append(seat);
              break;
          }
        }
        nextPlan.add(newRow.toString());
      }
      floorPlan = nextPlan;
    }
    return floorPlan.stream().mapToInt(st -> (int) st.chars().filter(ch -> ch == '#').count()).sum();
  }

  public int playGame2() {
    boolean changed = true;
    while (changed) {
//      printPlan();
      List<String> nextPlan = new ArrayList<>(floorPlan.size());
      changed = false;
      for (int y = 0; y <= maxY; y++) {
        String row = floorPlan.get(y);
        StringBuilder newRow = new StringBuilder();
        for (int x = 0; x <= maxX; x++) {
          char seat = row.charAt(x);
          switch(seat) {
            case 'L':
              if(occupiedVisible(x,y) == 0){
                newRow.append('#');
                changed = true;
              } else {
                newRow.append(seat);
              }
              break;
            case '#':
              if(occupiedVisible(x,y) > 4){
                newRow.append('L');
                changed = true;
              } else {
                newRow.append(seat);
              }
              break;
            default:
              newRow.append(seat);
              break;
          }
        }
        nextPlan.add(newRow.toString());
      }
      floorPlan = nextPlan;
    }
    return floorPlan.stream().mapToInt(st -> (int) st.chars().filter(ch -> ch == '#').count()).sum();
  }

  long occupiedVisible(final int x, final int y) {
    List<Character> seats = Arrays.asList('.','.','.','.','.','.','.','.');
    List<Point> multipliers = Arrays.asList(
        new Point(-1,-1),
        new Point(0,-1),
        new Point(1,-1),
        new Point(1,0),
        new Point(1,1),
        new Point(0,1),
        new Point(-1,1),
        new Point(-1,0));
    int multiply = 1;
    while (seats.contains('.')) {
      for (int i = 0; i < 8; i++) {
        if (seats.get(i).equals('.')) {
          seats.set(i,seat(x + (multiply * multipliers.get(i).x), y + (multiply * multipliers.get(i).y)));
        }
      }
      multiply++;
    }
    return seats.stream().filter(ch -> ch == '#').count();
  }

  boolean outOfBounds(final int x, final int y) {
    return x < 0 || x > maxX || y < 0 || y > maxY;
  }

  char seat(final int x, final int y) {
    return outOfBounds(x,y) ? 'L' : floorPlan.get(y).charAt(x);
  }

  int occupiedBlock(final int x, final int y) {
    int occupied = 0;
    int xMin = x > 0 ? x - 1 : 0;
    int xMax = x < maxX ? x + 1 : maxX;
    int yMin = y > 0 ? y - 1 : 0;
    int yMax = y < maxY ? y + 1 : maxY;
    for (int row = yMin; row <= yMax; row++) {
      String seats = floorPlan.get(row);
      occupied += seats.substring(xMin,xMax + 1).chars().filter(ch -> ch == '#').count();
    }
    return occupied;
  }

  void printPlan() {
    floorPlan.forEach(st -> System.out.println(st));
    System.out.println();
  }
}
