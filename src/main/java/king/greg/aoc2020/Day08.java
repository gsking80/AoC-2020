package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 {

  final List<String> program;

  public Day08(FileReader fileReader) {

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

  public int run() {
    final Set<Integer> runLines = new HashSet<>();
    int accumulator = 0;
    int currentLine = 0;

    while(!runLines.contains(currentLine)) {
      runLines.add(currentLine);
      final String[] instruction = program.get(currentLine).split(" ");
      switch(instruction[0]) {
        case "acc":
          accumulator += Integer.parseInt(instruction[1]);
          currentLine++;
          break;
        case "jmp":
          currentLine += Integer.parseInt(instruction[1]);
          break;
        case "nop":
          currentLine++;
          break;
      }
    }
    return accumulator;
  }

  public int runFix() {
    for (int i = 0; i < program.size(); i++) {
      String opToChange = program.get(i);
      String command = opToChange.substring(0,3);
      switch (command) {
        case "nop":
          opToChange = opToChange.replace("nop", "jmp");
          break;
        case "jmp":
          opToChange = opToChange.replace("jmp","nop");
          break;
        default:
          continue;
      }
      final Set<Integer> runLines = new HashSet<>();
      int accumulator = 0;
      int currentLine = 0;

      while (!runLines.contains(currentLine)) {
        runLines.add(currentLine);

        if (currentLine == program.size()) {
          return accumulator;
        } else if (currentLine > program.size()) {
          break;
        }
        final String[] instruction = currentLine == i ? opToChange.split(" ") : program.get(currentLine).split(" ");
        switch (instruction[0]) {
          case "acc":
            accumulator += Integer.parseInt(instruction[1]);
            currentLine++;
            break;
          case "jmp":
            currentLine += Integer.parseInt(instruction[1]);
            break;
          case "nop":
            currentLine++;
            break;
        }
      }
    }

    throw new RuntimeException();
  }

}
