package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day20 {
  final Map<Integer, Tile> tileMap;
  Pattern tilePattern = Pattern.compile("Tile (\\d*):");

  public Day20(final FileReader fileReader) {
    tileMap = new HashMap<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        Matcher matcher = tilePattern.matcher(lineJustFetched);
        if (matcher.matches()) {
          final Integer tileId = Integer.parseInt(matcher.group(1));
          final List<String> pixels = new ArrayList<>();
          for (int i = 0; i < 10; i++) {
            pixels.add(buf.readLine());
          }
          tileMap.put(tileId, new Tile(tileId, pixels));
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public long findCorners() {
    populateNeighbors();
    long cornerScore = 1L;
    for (final Tile tile : tileMap.values()) {
      if (tile.neighbors.size() == 2) {
        cornerScore *= tile.id;
      }
    }
    return cornerScore;
  }

  public int roughWaters() {
    final Image image = buildImage();
    final int seaMonsters = image.findSeaMonsters();
    return image.blips() - (seaMonsters * 15);
  }

  private void populateNeighbors() {
    for (final Tile tile: tileMap.values()) {
      final List<String> unmatchedEdges = new ArrayList<>(4);
      unmatchedEdges.addAll(tile.getEdges());
      for (final Tile neighbor: tileMap.values()) {
        if (tile.equals(neighbor) || tile.neighbors.contains(neighbor.id)) {
          continue;
        }
        for (final String neighborEdge: neighbor.getEdges()) {
          if (unmatchedEdges.contains(neighborEdge) || unmatchedEdges.contains(new StringBuffer(neighborEdge).reverse().toString())) {
            tile.neighbors.add(neighbor.id);
            neighbor.neighbors.add(tile.id);
          }
        }
      }
    }
  }

  private Image buildImage() {
    populateNeighbors();
    final int edgeLength = (int) Math.sqrt(tileMap.size());
    int[][] imageBlocks = new int[edgeLength][edgeLength];
    final Set<Integer> placedImages = new HashSet<>();
    //Find a corner
    Tile upperLeftCorner = null;
    for (final Tile tile: tileMap.values()) {
      if(tile.neighbors.size() == 2) {
        upperLeftCorner = tile;
        break;
      }
    }
    outerloop:
    for (int i = 0; i < 8; i++) {
      upperLeftCorner.orientation = i;
      for (int neighborID : upperLeftCorner.neighbors) {
        Tile neighbor = tileMap.get(neighborID);
        for (int j = 0; j < 8; j++) {
          neighbor.orientation = j;
          if (upperLeftCorner.rightEdge().equals(neighbor.leftEdge())) {
            imageBlocks[0][0] = upperLeftCorner.id;
            placedImages.add(upperLeftCorner.id);
            imageBlocks[0][1] = neighbor.id;
            placedImages.add(neighbor.id);
            break outerloop;
          }
        }
      }
    }
    for (int row = 0; row < edgeLength; row++) {
      for (int column = row == 0 ? 2 : 0; column < edgeLength; column++) {
        final Set<Integer> candidateIDs = new HashSet<>();
        if (column == 0) {
          candidateIDs.addAll(tileMap.get(imageBlocks[row-1][column]).neighbors);
          candidateIDs.removeAll(placedImages);
        } else if (row == 0) {
          candidateIDs.addAll(tileMap.get(imageBlocks[row][column-1]).neighbors);
          candidateIDs.removeAll(placedImages);
        } else {
          candidateIDs.addAll(tileMap.get(imageBlocks[row-1][column]).neighbors);
          candidateIDs.retainAll(tileMap.get(imageBlocks[row][column-1]).neighbors);
          candidateIDs.removeAll(placedImages);
        }
        candidateLoop:
        for(final Integer tileID: candidateIDs) {
          final Tile tile = tileMap.get(tileID);
          for (int orientation = 0; orientation < 8; orientation++) {
            tile.orientation = orientation;
            if ((column == 0 || tileMap.get(imageBlocks[row][column-1]).rightEdge().equals(tile.leftEdge())) && (row == 0 || tileMap.get(imageBlocks[row-1][column]).bottomEdge().equals(tile.topEdge()))) {
              // LOCK IT IN
              imageBlocks[row][column] = tileID;
              placedImages.add(tileID);
              break candidateLoop;
            }
            if (column == 0 && row == 1 && tileMap.get(imageBlocks[0][0]).topEdge().equals(tile.topEdge())) {
              // Special case - flip the top row
              for (int i = 0; i < edgeLength; i++) {
                tileMap.get(imageBlocks[0][i]).flipVertical();
              }
              // LOCK IT IN
              imageBlocks[row][column] = tileID;
              placedImages.add(tileID);
              break candidateLoop;
            }
          }
        }
      }
    }
    Image image = new Image(edgeLength * 8);
    for (int tileRow = 0; tileRow < edgeLength; tileRow++) {
      for (int tileColumn = 0; tileColumn <edgeLength; tileColumn++) {
        final Tile tile = tileMap.get(imageBlocks[tileRow][tileColumn]);
        for (int pixelRow = 1; pixelRow < 9; pixelRow++) {
          for (int pixelColumn = 1; pixelColumn < 9; pixelColumn++) {
            image.pixels[(tileRow * 8) + (pixelRow - 1)][(tileColumn * 8) + (pixelColumn - 1)] = tile.getOrientedPixel(pixelRow,pixelColumn);
          }
        }
      }
    }
    return image;
  }

  class Image {
    char[][] pixels;
    int orientation;

    Image(final int size) {
      pixels = new char[size][size];
      orientation = 0;
    }

    public int findSeaMonsters() {
      for(int orientationOption = 0; orientationOption < 8; orientationOption++) {
        int seaMonsters = 0;
        orientation = orientationOption;
        for (int row = 0; row < pixels.length - 2; row++) {
          for (int column = 0; column < pixels[row].length - 19; column++) {
            if(getOrientedPixel(row+1, column) != '#') {
              continue;
            }
            if(getOrientedPixel(row+2, column+1) != '#') {
              continue;
            }
            if(getOrientedPixel(row+2,column+4) != '#') {
              continue;
            }
            if(getOrientedPixel(row+1, column+5) != '#') {
              continue;
            }
            if(getOrientedPixel(row+1, column+6) != '#') {
              continue;
            }
            if(getOrientedPixel(row+2, column+7) != '#') {
              continue;
            }
            if(getOrientedPixel(row+2,column+10) != '#') {
              continue;
            }
            if(getOrientedPixel(row+1, column+11) != '#') {
              continue;
            }
            if(getOrientedPixel(row+1, column+12) != '#') {
              continue;
            }
            if(getOrientedPixel(row+2,column+13) != '#') {
              continue;
            }
            if(getOrientedPixel(row+2, column+16) != '#') {
              continue;
            }
            if(getOrientedPixel(row+1, column+17) != '#') {
              continue;
            }
            if(getOrientedPixel(row, column+18) != '#') {
              continue;
            }
            if(getOrientedPixel(row+1, column+18) != '#') {
              continue;
            }
            if(getOrientedPixel(row+1, column+19) != '#') {
              continue;
            }
//            System.out.println("Sea monster found!");
            seaMonsters++;
          }
        }
        if (seaMonsters > 0) {
          return seaMonsters;
        }
      }
      return 0;
    }

    public int print() {
      int pounds = 0;
      for (int row = 0; row < pixels.length; row++) {
        for (int column = 0; column < pixels[row].length; column++) {
          System.out.print(getOrientedPixel(row,column));
          if(getOrientedPixel(row,column) == '#') {
            pounds++;
          }
        }
        System.out.println();
      }
      return pounds;
    }

    public int blips() {
      int blips = 0;
      for (int row = 0; row < pixels.length; row++) {
        for (int column = 0; column < pixels[row].length; column++) {
          if(pixels[row][column] == '#') {
            blips++;
          }
        }
      }
      return blips;
    }

    public char getOrientedPixel(int row, int column) {
      char pixel;
      int inverse = pixels.length - 1;
      switch(this.orientation) {
        case 0:
          pixel = pixels[row][column];
          break;
        case 1:
          pixel = pixels[column][row];
          break;
        case 2:
          pixel = pixels[column][inverse-row];
          break;
        case 3:
          pixel = pixels[row][inverse-column];
          break;
        case 4:
          pixel = pixels[inverse-row][inverse-column];
          break;
        case 5:
          pixel = pixels[inverse-column][inverse-row];
          break;
        case 6:
          pixel = pixels[inverse-column][row];
          break;
        case 7:
          pixel = pixels[inverse-row][column];
          break;
        default:
          throw new RuntimeException();
      }
      return pixel;
    }

  }

  class Tile {
    final int id;
    char[][] pixels = new char[10][10];
    final HashSet<Integer> neighbors;
    int orientation;

    Tile(final int id, final List<String> input) {
      this.id = id;
      orientation = 0;
      neighbors = new HashSet<>();
      for (int i = 0; i < 10; i++) {
        final String currentLine = input.get(i);
        for (int j = 0; j < 10; j++) {
          pixels[i][j] = currentLine.charAt(j);
        }
      }
    }

    public char getOrientedPixel(int row, int column) {
      char pixel;
      switch(this.orientation) {
        case 0:
          pixel = pixels[row][column];
          break;
        case 1:
          pixel = pixels[column][row];
          break;
        case 2:
          pixel = pixels[column][9-row];
          break;
        case 3:
          pixel = pixels[row][9-column];
          break;
        case 4:
          pixel = pixels[9-row][9-column];
          break;
        case 5:
          pixel = pixels[9-column][9-row];
          break;
        case 6:
          pixel = pixels[9-column][row];
          break;
        case 7:
          pixel = pixels[9-row][column];
          break;
        default:
          throw new RuntimeException();
      }
      return pixel;
    }

    public void flipVertical() {
      switch(this.orientation) {
        case 0:
          orientation = 7;
          break;
        case 1:
          orientation = 2;
          break;
        case 2:
          orientation = 1;
          break;
        case 3:
          orientation = 4;
          break;
        case 4:
          orientation = 3;
          break;
        case 5:
          orientation = 6;
          break;
        case 6:
          orientation = 5;
          break;
        case 7:
          orientation = 0;
          break;
        default:
          throw new RuntimeException();
      }
    }

    public String rightEdge() {
      final StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 10; i++) {
        sb.append(getOrientedPixel(i,9));
      }
      return sb.toString();
    }

    public String leftEdge() {
      final StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 10; i++) {
        sb.append(getOrientedPixel(i,0));
      }
      return sb.toString();
    }

    public String topEdge() {
      final StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 10; i++) {
        sb.append(getOrientedPixel(0,i));
      }
      return sb.toString();
    }

    public String bottomEdge() {
      final StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 10; i++) {
        sb.append(getOrientedPixel(9,i));
      }
      return sb.toString();
    }

    public List<String> getEdges() {
      final List<String> edges = new ArrayList<>();
      final StringBuilder a = new StringBuilder();
      final StringBuilder b = new StringBuilder();
      final StringBuilder c = new StringBuilder();
      final StringBuilder d = new StringBuilder();
      for (int i = 0; i < 10; i++) {
        a.append(pixels[0][i]);
        b.append(pixels[i][0]);
        c.append(pixels[9][i]);
        d.append(pixels[i][9]);
      }
      edges.add(a.toString());
      edges.add(b.toString());
      edges.add(c.toString());
      edges.add(d.toString());
      return edges;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Tile tile = (Tile) o;
      return id == tile.id;
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }
  }
}
