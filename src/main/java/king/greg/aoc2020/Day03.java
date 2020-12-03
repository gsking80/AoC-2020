package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day03 {

  final List<String> map;

  public Day03(final FileReader fileReader) {

    map = new ArrayList<>();

    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      while(true) {
        final String lineJustFetched = buf.readLine();
        if(null == lineJustFetched) {
          break;
        } else {
          map.add(lineJustFetched);
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int treesEncountered(final int slopeX, final int slopeY){
    int trees = 0;
    int x = 0;
    int y = slopeY;

    while (y < map.size()) {
      final String currentRow = map.get(y);
      x = (x + slopeX)  % currentRow.length();
      if (currentRow.charAt(x) == '#') {
        trees++;
      }
      y += slopeY;
    }
    return trees;
  }

}
