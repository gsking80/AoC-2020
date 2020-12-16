package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day16 {

  final Map<String, List<Integer>> fieldRules;
  final List<Integer> myTicket;
  final List<List<Integer>> otherTickets;
  final List<List<Integer>> validTickets;

  final Pattern fieldPattern = Pattern.compile("(.*): (\\d*)-(\\d*) or (\\d*)-(\\d*)");

  public Day16(final FileReader fileReader) {
    fieldRules = new HashMap<>();
    myTicket = new ArrayList<>();
    otherTickets = new ArrayList<>();
    validTickets = new ArrayList<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      boolean nearby = false;

      while(true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        if (nearby) {
          otherTickets.add(ticketValues(lineJustFetched));
        }
        final Matcher fieldMatcher = fieldPattern.matcher(lineJustFetched);
        if (fieldMatcher.matches()) {
          final List<Integer> values = new ArrayList<>(4);
          for (int i = 2; i < 6; i++) {
            values.add(Integer.parseInt(fieldMatcher.group(i)));
          }
          fieldRules.put(fieldMatcher.group(1), values);
        }
        if ("your ticket:".equals(lineJustFetched)) {
          myTicket.addAll(ticketValues(buf.readLine()));
        }
        if ("nearby tickets:".equals(lineJustFetched)) {
          nearby = true;
        }
      }

    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  List<Integer> ticketValues (final String line) {
    return Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
  }

  boolean numberValid (final Integer number) {
    for (final List<Integer> ranges: fieldRules.values()) {
      if(numberValid(number, ranges)) {
        return true;
      }
    }
    return false;
  }

  boolean numberValid (final Integer number, final List<Integer> ranges) {
    return (((number >= ranges.get(0)) && (number <= ranges.get(1))) || ((number >= ranges.get(2)) && (number <= ranges.get(3))));
  }

  public int validate() {
    int invalidSum = 0;
    for (final List<Integer> ticket : otherTickets) {
      boolean valid = true;
      for (final Integer value : ticket) {
        if(!numberValid(value)) {
          invalidSum += value;
          valid = false;
        }
      }
      if(valid) {
        validTickets.add(ticket);
      }
    }
    return invalidSum;
  }

  public long departureValue() {
    final Map<String,List<Boolean>> fields = new HashMap<>();
    validate();

    for (final Entry<String, List<Integer>> fieldRule:  fieldRules.entrySet()) {
      final Boolean[] booleans = new Boolean[myTicket.size()];
      Arrays.fill(booleans, true);
      for (final List<Integer> ticket : validTickets) {
        for( int i = 0; i < ticket.size(); i++) {
          if(booleans[i] && !numberValid(ticket.get(i), fieldRule.getValue())){
            booleans[i] = false;
          }
        }
      }
      fields.put(fieldRule.getKey(), Arrays.asList(booleans));
    }

    reduce(fields);

    long total = 1L;
    for (final Entry<String, List<Boolean>> entry : fields.entrySet()) {
      if (entry.getKey().startsWith("departure")) {
        total *= myTicket.get(entry.getValue().indexOf(true));
      }
    }

    return total;
  }

  void reduce(final Map<String, List<Boolean>> fields) {
    boolean reduce = true;
    while(reduce) {
      reduce = false;
      for (final Entry<String, List<Boolean>> field : fields.entrySet()) {
        if (Collections.frequency(field.getValue(), true) == 1) {
          final int index = field.getValue().indexOf(true);
          for (final Entry<String, List<Boolean>> reduceFields : fields.entrySet()) {
            if (!reduceFields.getKey().equals(field.getKey())) {
              reduceFields.getValue().set(index, false);
            }
          }
        } else {
          reduce = true;
        }
      }
    }
  }
}
