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

public class Day19 {
  final Map<Integer, String> rules;
  final Map<Integer, String> regexes;
  final List<String> messages;
  final Pattern rulePattern = Pattern.compile("(\\d*): (.*)");

  public Day19(final FileReader fileReader) {
    rules = new HashMap<>();
    regexes = new HashMap<>();
    messages = new ArrayList<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      boolean messagePart = false;
      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        if (lineJustFetched.isEmpty()) {
          messagePart = true;
        } else if (messagePart) {
          messages.add(lineJustFetched);
        } else {
          Matcher matcher = rulePattern.matcher(lineJustFetched);
          if(matcher.matches()) {
            rules.put(Integer.parseInt(matcher.group(1)), matcher.group(2));
          }
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int matches(final int rule) {
    return matches(rule, false);
  }

  public int matches(final int rule, final boolean part2) {
    if (part2) {
      prepPart2();
    }
    Pattern ruleZeroPattern = Pattern.compile(parseRule(rule));
    int matches = 0;
    for(final String message: messages) {
      final Matcher matcher = ruleZeroPattern.matcher(message);
      if(matcher.matches()) {
        matches++;
      }
    }
    return matches;
  }

  String parseRule(final int ruleNumber) {
      if(regexes.containsKey(ruleNumber)) {
        return regexes.get(ruleNumber);
      }
      final String rule = rules.get(ruleNumber);
      if(rule.contains("\"")) {
        final String regex = rule.replaceAll("\"", "");
        regexes.put(ruleNumber, regex);
        return regex;
      }
      final StringBuilder regex = new StringBuilder("(");
      final String[] parts = rule.split(" ");
      for (final String part : parts) {
        if(part.equals("|")) {
          regex.append("|");
        } else {
          regex.append(parseRule(Integer.parseInt(part)));
        }
      }
      regex.append(")");
      regexes.put(ruleNumber, regex.toString());
      return regex.toString();
  }

  private void prepPart2() {
    // Rule 8
    regexes.put(8,"(" + parseRule(42) + "+)");

    // Rule 11
    String reg42 = parseRule(42);
    String reg31 = parseRule(31);
    // Java regex doesn't support recursion?  10 loops seems enough to "handle the rules you have"
    // ...But I still hate it
    StringBuilder hackRule = new StringBuilder("(");
    for (int i = 1; i < 10; i++) {
      if (i > 1) {
        hackRule.append("|");
      }
      hackRule.append("(");
      for (int j = 0; j < i; j++) {
        hackRule.append(reg42);
      }
      for (int j = 0; j < i; j++) {
        hackRule.append(reg31);
      }
      hackRule.append(")");
    }
    hackRule.append(")");
    regexes.put(11,hackRule.toString());
  }

}
