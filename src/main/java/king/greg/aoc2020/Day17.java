package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day17 {

  Set<Point4d> activeCubes;

  public Day17(final FileReader fileReader) {
    activeCubes = new HashSet<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      int y = -1;
      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        y++;
        for (int x = 0; x < lineJustFetched.length(); x++) {
          if (lineJustFetched.charAt(x) == '#') {
            activeCubes.add(new Point4d(x, y, 0));
          }
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public void cycle() {
    Set<Point4d> activeCubesNext = new HashSet<>();
    final int minX = activeCubes.stream().min(Comparator.comparing(a -> a.x)).get().x - 1;
    final int maxX = activeCubes.stream().max(Comparator.comparing(a -> a.x)).get().x + 1;
    final int minY = activeCubes.stream().min(Comparator.comparing(a -> a.y)).get().y - 1;
    final int maxY = activeCubes.stream().max(Comparator.comparing(a -> a.y)).get().y + 1;
    final int minZ = activeCubes.stream().min(Comparator.comparing(a -> a.z)).get().z - 1;
    final int maxZ = activeCubes.stream().max(Comparator.comparing(a -> a.z)).get().z + 1;
    for (int x = minX; x <= maxX; x++) {
      for (int y = minY; y <= maxY; y++) {
        for (int z = minZ; z <= maxZ; z++) {
          final Point4d point = new Point4d(x,y,z);
          if (point.activeNext()) {
            activeCubesNext.add(point);
          }
        }
      }
    }
    activeCubes = activeCubesNext;
  }

  public void cycle4D() {
    Set<Point4d> activeCubesNext = new HashSet<>();
    final int minX = activeCubes.stream().min(Comparator.comparing(a -> a.x)).get().x - 1;
    final int maxX = activeCubes.stream().max(Comparator.comparing(a -> a.x)).get().x + 1;
    final int minY = activeCubes.stream().min(Comparator.comparing(a -> a.y)).get().y - 1;
    final int maxY = activeCubes.stream().max(Comparator.comparing(a -> a.y)).get().y + 1;
    final int minZ = activeCubes.stream().min(Comparator.comparing(a -> a.z)).get().z - 1;
    final int maxZ = activeCubes.stream().max(Comparator.comparing(a -> a.z)).get().z + 1;
    final int minW = activeCubes.stream().min(Comparator.comparing(a -> a.w)).get().w - 1;
    final int maxW = activeCubes.stream().max(Comparator.comparing(a -> a.w)).get().w + 1;
    for (int x = minX; x <= maxX; x++) {
      for (int y = minY; y <= maxY; y++) {
        for (int z = minZ; z <= maxZ; z++) {
          for (int w = minW; w <= maxW; w++) {
            final Point4d point = new Point4d(x, y, z, w);
            if (point.activeNext()) {
              activeCubesNext.add(point);
            }
          }
        }
      }
    }
    activeCubes = activeCubesNext;
  }

  public void cycle(final int times) {
    for (int i = 0; i < times; i++) {
      cycle();
    }
  }

  public void cycle4D(final int times) {
    for (int i = 0; i < times; i++) {
      cycle4D();
    }
  }

  public int activeCubes() {
    return activeCubes.size();
  }

  class Point4d {

    final int x;
    final int y;
    final int z;
    final int w;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Point4d point = (Point4d) o;
      return x == point.x &&
          y == point.y &&
          z == point.z &&
          w == point.w;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y, z, w);
    }

    Point4d(final int x, final int y, final int z) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.w = 0;
    }

    Point4d(final int x, final int y, final int z, final int w) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.w = w;
    }

    public boolean activeNext() {
      boolean active = activeCubes.contains(this);
      int activeNeighbors = 0;
      for (int x1 = x - 1; x1 <= x + 1; x1++) {
        for (int y1 = y - 1; y1 <= y + 1; y1++) {
          for (int z1 = z - 1; z1 <= z + 1; z1++) {
            for (int w1 = w - 1; w1 <= w + 1; w1++) {
              Point4d neighbor = new Point4d(x1, y1, z1, w1);
              if (!neighbor.equals(this) && activeCubes.contains(neighbor)) {
                activeNeighbors++;
              }
            }
          }
        }
      }
      return (active && (activeNeighbors == 2 )) || activeNeighbors == 3;
    }

  }
}

