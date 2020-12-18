package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day18 {

  final List<String> homework;
  int index = 0;

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
    long total = 0;
    for (final String equation : homework) {
      index = 0;
      total += solveEquation(equation);
    }
    return total;
  }

  public long sumAllEquations2() {
    long total = 0;
    for (final String equation : homework) {
      index = 0;
      total += solveEquation2(equation);
    }
    return total;
  }

  public long solveEquation(final String equation) {
    long answer = 0;
    Long currentNumber = null;
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
        currentNumber = solveEquation(equation);
      } else if (current == '+' || current == '*') {
        currentOperand = current;
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
        return operation(currentOperand, answer, currentNumber);
      }
    }

    return operation(currentOperand, answer, currentNumber);
  }

  public long solveEquation2(final String equation) {
    long answer = 0;
    Long currentNumber = null;
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
        currentNumber = solveEquation2(equation);
      } else if (current == '+') {
        currentOperand = current;
      } else if (current == '*') {
        currentOperand = current;
        index++;
        currentNumber = solveEquation2(equation);
        index--;
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
        return operation(currentOperand, answer, currentNumber);
      }
    }
    index++;
    return operation(currentOperand, answer, currentNumber);
  }

  Long operation(final Character operation, final Long left, final Long right) {
    if (null == operation) {
      return right;
    }
    if (operation.equals('+')) {
      return left + right;
    }
    return left * right;
  }

}