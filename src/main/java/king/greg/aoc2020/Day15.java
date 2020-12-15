package king.greg.aoc2020;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Day15 {

  final Map<Long, Long> lastTurnSpoken;
  long lastNumber;

  public Day15(final String init) {
    lastTurnSpoken = new HashMap<>();
    final String[] numbers = init.split(",");
    long turn = 0;
    for (final String number : numbers) {
      turn++;
      lastTurnSpoken.put(Long.parseLong(number), turn);
      lastNumber = Long.parseLong(number);
    }
  }

  public long numberSpoken(long turn) {
    long nextNumber = 0L;
    for (long currentTurn = Collections.max(lastTurnSpoken.values()) + 1; currentTurn < turn; currentTurn++) {
      nextNumber = takeTurn(nextNumber, currentTurn);
    }
    return nextNumber;
  }

  long takeTurn (long number, long currentTurn) {
    Long nextNumber = lastTurnSpoken.get(number);
    if (nextNumber == null) {
      nextNumber = 0L;
    } else {
      nextNumber = currentTurn - nextNumber;
    }
    lastTurnSpoken.put(number, currentTurn);
    return nextNumber;
  }
}
