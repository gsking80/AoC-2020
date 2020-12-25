package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.tuple.Triple;

public class Day24 {
  final List<String> instructions;
  static final List<Triple<Integer,Integer,Integer>> neighbors = new ArrayList<>(List.of(Triple.of(1,1,0),
                                                                                         Triple.of(1,0,-1),
                                                                                         Triple.of(0,-1,-1),
                                                                                         Triple.of(-1,-1,0),
                                                                                         Triple.of(-1,0,1),
                                                                                         Triple.of(0,1,1)));

  public Day24(final FileReader fileReader) {
    instructions = new ArrayList<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        instructions.add(lineJustFetched);
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  private Set<Triple<Integer,Integer,Integer>> initializeFloor() {
    final Set<Triple<Integer,Integer,Integer>> blackTiles = new HashSet<>();
    for (final String instruction : instructions) {
      final Triple<Integer,Integer,Integer> tile = parseTile(instruction);
      if (blackTiles.contains(tile)) {
        blackTiles.remove(tile);
      } else {
        blackTiles.add(tile);
      }
    }
    return blackTiles;
  }

  private static Triple<Integer,Integer,Integer> parseTile(final String instruction) {
    int x = 0;
    int y = 0;
    int z = 0;
    for (int i = 0; i < instruction.length(); i++) {
      if (instruction.charAt(i) == 'e') {
        if (i > 0 && instruction.charAt(i - 1) == 'n') {
          y++;
          z++;
        } else if (i > 0 && instruction.charAt(i - 1) == 's') {
          x++;
          z--;
        } else {
          x++;
          y++;
        }
      } else if (instruction.charAt(i) == 'w') {
        if (i > 0 && instruction.charAt(i - 1) == 'n') {
          x--;
          z++;
        } else if (i > 0 && instruction.charAt(i - 1) == 's') {
          y--;
          z--;
        } else {
          x--;
          y--;
        }
      }
    }
    return Triple.of(x, y, z);
  }

  public int blackTiles() {
    return initializeFloor().size();
  }

  public int blackTilesLife(final int days) {
    Set<Triple<Integer,Integer,Integer>> blackTiles = initializeFloor();
    for(int i = 0; i < days; i++) {
      final Set<Triple<Integer,Integer,Integer>> newBlackTiles = new HashSet<>();
      final Map<Triple<Integer,Integer,Integer>, Integer> whiteTiles = new HashMap<>();
      for(final Triple<Integer,Integer,Integer> blackTile: blackTiles) {
        int blackNeighbors = 0;
        for(final Triple<Integer,Integer,Integer> neighbor: neighbors) {
          final Triple<Integer,Integer,Integer> neighborTile = Triple.of(blackTile.getLeft() + neighbor.getLeft(), blackTile.getMiddle() + neighbor.getMiddle(), blackTile.getRight() + neighbor.getRight());
          if (blackTiles.contains(neighborTile)) {
            blackNeighbors++;
          } else {
            Integer neighborCount = whiteTiles.get(neighborTile);
            if (neighborCount == null) {
              neighborCount = 0;
            }
            neighborCount++;
            whiteTiles.put(neighborTile,neighborCount);
          }
        }
        if (blackNeighbors > 0 && blackNeighbors < 3) {
          newBlackTiles.add(blackTile);
        }
      }
      for(final Entry<Triple<Integer,Integer,Integer>,Integer> entry : whiteTiles.entrySet()) {
        if (entry.getValue() == 2) {
          newBlackTiles.add(entry.getKey());
        }
      }
      blackTiles = newBlackTiles;
    }


    return blackTiles.size();
  }
}
