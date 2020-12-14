package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class Day14 {

  final Pattern memPattern = Pattern.compile("^mem\\[(\\d*)]$");
  final Map<Long, Long> memory;
  final List<String> program;

  public Day14(final FileReader fileReader) {
    memory = new HashMap<>();
    program = new ArrayList<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      while(true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        program.add(lineJustFetched);
      }

    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public long initialize(final boolean part2) {

    String currentMask = "";
    for(final String programLine: program) {
      final String[] pieces = programLine.split(" = ");
      if ("mask".equals(pieces[0])) {
        currentMask = pieces[1];
      } else {
        Matcher matcher = memPattern.matcher(pieces[0]);
        if(matcher.matches()) {
          if (part2) {
            setMemory2(Long.valueOf(matcher.group(1)), Long.valueOf(pieces[1]), currentMask);
          } else {
            setMemory(Long.valueOf(matcher.group(1)), Long.valueOf(pieces[1]), currentMask);
          }
        }
      }
    }

    return memory.values().stream().mapToLong(Long::longValue).sum();
  }

  void setMemory(final Long location, final Long value, final String bitMask) {
    long orMask = 0L;
    long andMask = 0L;
    for (int i = 0; i < bitMask.length(); i++) {
      orMask = orMask<<1;
      andMask = andMask<<1;
      switch(bitMask.charAt(i)){
        case '1':
          orMask++;
          break;
        case '0':
          break;
        default:
          andMask++;
          break;
      }
    }
    memory.put(location, (value & andMask) | orMask);
  }

  void setMemory2(final Long location, final Long value, final String bitMask) {
    List<Long> locationsToSet = new ArrayList<>();
    final String binaryLocation = StringUtils.leftPad(Long.toBinaryString(location), bitMask.length(), "0");
    locationsToSet.add(0L);
    for (int i = 0; i < bitMask.length(); i++) {
        switch(bitMask.charAt(i)){
        case '0':
          for(int j = 0; j < locationsToSet.size(); j++) {
            locationsToSet.set(j, (locationsToSet.get(j)<<1) + (binaryLocation.charAt(i) - '0'));
          }
          break;
        case '1':
          for(int j = 0; j < locationsToSet.size(); j++) {
            locationsToSet.set(j, (locationsToSet.get(j)<<1) + 1);
          }
          break;
        default:
          List<Long> nextLocations = new ArrayList<>(locationsToSet.size() * 2);
          for(final Long locationToSet : locationsToSet) {
            nextLocations.add(locationToSet<<1);
            nextLocations.add((locationToSet<<1) + 1);
          }
          locationsToSet = nextLocations;
          break;
      }
    }
    for(final Long locationToSet : locationsToSet) {
      memory.put(locationToSet, value);
    }
  }

}
