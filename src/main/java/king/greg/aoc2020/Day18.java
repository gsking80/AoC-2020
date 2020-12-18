package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class Day18 {

  final List<String> homework;

  public Day18(final FileReader fileReader) {
    homework = new ArrayList<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        homework.add(lineJustFetched);
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public long sumAllEquations() {
    return sumAllEquations(false);
  }
  public long sumAllEquations(final boolean part2) {
    return homework.stream().mapToLong(equation -> solveEquation(equation, part2)).sum();
  }

  public static Long solveEquation(final String equation) {
    return solveEquation(equation, false);
  }

  public static Long solveEquation(final String equation, final boolean part2) {
    return solveEquation(equation, 0, part2).getLeft();
  }

  static Pair<Long, Integer> solveEquation(final String equation, final int startIndex, final boolean part2) {
    Long answer = 0L;
    Long currentNumber = null;
    int index = startIndex;
    Character currentOperand = null;
    while (index < equation.length()) {
      Character current = equation.charAt(index);
      index++;
      if (current >= '0' && current <= '9') {
        if (null == currentNumber) {
          currentNumber = 0L;
        }
        currentNumber *= 10;
        currentNumber += current - '0';
      } else if (current == '(') {
        Pair<Long, Integer> result = solveEquation(equation, index, part2);
        currentNumber = result.getLeft();
        index = result.getRight();
      } else if (current == '+') {
        currentOperand = current;
      } else if (current == '*') {
        currentOperand = current;
        if(part2) {
          Pair<Long, Integer> result = solveEquation(equation, index + 1, true);
          currentNumber = result.getLeft();
          index = result.getRight() - 1;
        }
      } else if (current.equals(' '))  {
        if (null == currentOperand) {
          answer = currentNumber;
          currentNumber = null;
        } else if (currentNumber != null) {
          answer = operation(currentOperand, answer, currentNumber);
          currentNumber = null;
          currentOperand = null;
        }
      }
      else if (current.equals(')')) {
        return Pair.of(operation(currentOperand, answer, currentNumber), index);
      }
    }
    return Pair.of(operation(currentOperand, answer, currentNumber), index + 1);
  }

  static Long operation(final Character operation, final Long left, final Long right) {
    if (null == operation) {
      return right;
    }
    if (operation.equals('+')) {
      return left + right;
    }
    return left * right;
  }

}
