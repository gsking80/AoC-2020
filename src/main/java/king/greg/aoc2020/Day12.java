package king.greg.aoc2020;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day12 {

  final List<String> instructions;
  Point currentLocation = new Point(0,0);
  Point waypoint = new Point(10,-1);
  int currentFacing = 0;

  public Day12(FileReader fileReader) {

    instructions = new ArrayList<>();

    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      while(true) {
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

  public int followInstructions() {

    for(final String instruction: instructions) {
      char action = instruction.charAt(0);
      int distance = Integer.parseInt(instruction.substring(1));
      switch(action) {
        case 'N':
          movePoint(1, distance, currentLocation);
          break;
        case 'S':
          movePoint(3, distance, currentLocation);
          break;
        case 'E':
          movePoint(0, distance, currentLocation);
          break;
        case 'W':
          movePoint(2, distance, currentLocation);
          break;
        case 'F':
          movePoint(currentFacing, distance, currentLocation);
          break;
        case 'L':
        case 'R':
          turn(action, distance);
          break;
        default:
          throw new RuntimeException();
      }

    }
    return (int) Math.abs(currentLocation.getX()) + (int) Math.abs(currentLocation.getY());
  }

  public int followInstructions2() {

    for(final String instruction: instructions) {
      char action = instruction.charAt(0);
      int distance = Integer.parseInt(instruction.substring(1));
      switch(action) {
        case 'N':
          movePoint(1, distance, waypoint);
          break;
        case 'S':
          movePoint(3, distance, waypoint);
          break;
        case 'E':
          movePoint(0, distance, waypoint);
          break;
        case 'W':
          movePoint(2, distance, waypoint);
          break;
        case 'F':
          moveToWaypoint(distance);
          break;
        case 'L':
        case 'R':
          rotateWaypoint(action, distance);
          break;
        default:
          throw new RuntimeException();
      }

    }
    return (int) Math.abs(currentLocation.getX()) + (int) Math.abs(currentLocation.getY());
  }

  private void movePoint(int direction, int distance, Point point) {
    switch(direction) {
      case 1:
        point.setLocation(point.getX(), point.getY() - distance);
        break;
      case 3:
        point.setLocation(point.getX(), point.getY() + distance);
        break;
      case 0:
        point.setLocation(point.getX() + distance, point.getY());
        break;
      case 2:
        point.setLocation(point.getX() - distance, point.getY());
        break;
      default:
        throw new RuntimeException();
    }
  }

  void moveToWaypoint(final int distance) {
    currentLocation.setLocation(currentLocation.getX() + waypoint.getX() * distance, currentLocation.getY() + waypoint.getY() * distance);
  }

  void turn(final char direction, final int distance) {
    currentFacing = ((currentFacing + ((distance / 90) * (direction == 'L' ? 1 : -1))) + 4) % 4;
  }

  void rotateWaypoint(final char direction, final int distance) {
    int distanceTurned = 0;
    while (distanceTurned < distance) {
      waypoint.setLocation(waypoint.getY() * (direction == 'L' ? 1 : -1), waypoint.getX() * (direction == 'R' ? 1 : -1));
      distanceTurned += 90;
    }
  }

}
