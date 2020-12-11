package king.greg.aoc2020;

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
    int i = 1;
    while (seats.contains('.')) {
      if (seats.get(0) == '.') { //UL
        seats.set(0,seat(x-i,y-i));
      }
      if (seats.get(1) == '.') { //U
        seats.set(1,seat(x,y-i));
      }
      if (seats.get(2) == '.') { //UR
        seats.set(2,seat(x+i,y-i));
      }
      if (seats.get(3) == '.') { //R
        seats.set(3,seat(x+i,y));
      }
      if (seats.get(4) == '.') { //LR
        seats.set(4,seat(x+i,y+i));
      }
      if (seats.get(5) == '.') { //Lower
        seats.set(5,seat(x,y+i));
      }
      if (seats.get(6) == '.') { //LL
        seats.set(6,seat(x-i,y+i));
      }
      if (seats.get(7) == '.') { //Left
        seats.set(7,seat(x-i,y));
      }
      i++;
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
