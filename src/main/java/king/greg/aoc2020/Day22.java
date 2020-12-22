package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;

public class Day22 {
  final LinkedList<Integer> player1;
  final LinkedList<Integer> player2;
  Pattern playerPattern = Pattern.compile("Player (\\d*):");

  public Day22(final FileReader fileReader) {

    player1 = new LinkedList<>();
    player2 = new LinkedList<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      int playerID = 0;
      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        Matcher playerMatcher = playerPattern.matcher(lineJustFetched);
        if (playerMatcher.matches()) {
          playerID = Integer.parseInt(playerMatcher.group(1));
        } else if (!lineJustFetched.isEmpty()) {
          if (playerID == 1) {
            player1.addLast(Integer.parseInt(lineJustFetched));
          } else {
            player2.addLast(Integer.parseInt(lineJustFetched));
          }
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public long playGame() {

    while (!player1.isEmpty() && !player2.isEmpty()) {
      final int card1 = player1.removeFirst();
      final int card2 = player2.removeFirst();
      if (card1 > card2) {
        player1.addLast(card1);
        player1.addLast(card2);
      } else {
        player2.addLast(card2);
        player2.addLast(card1);
      }
    }

    return player1.isEmpty() ? score(player2) : score(player1);
  }

  public long playRecursiveGame() {
    return subGame(player1,player2).getRight();
  }

  private Pair<Integer, Long> subGame(final LinkedList<Integer> deck1, final LinkedList<Integer> deck2) {
    final Set<Pair<String, String>> states = new HashSet<>();
    while (!deck1.isEmpty() && !deck2.isEmpty()) {
      // Check previous states
      final Pair<String,String> currentState = Pair.of(state(deck1), state(deck2));
      if (states.contains(currentState)) {
        return Pair.of(1, score(deck1));
      }
      states.add(currentState);

      final int card1 = deck1.removeFirst();
      final int card2 = deck2.removeFirst();
      if(card1 <= deck1.size() && card2 <= deck2.size()) {
        final LinkedList<Integer> subDeck1 = new LinkedList<>();
        for (int i = 0; i < card1; i++) {
          subDeck1.addLast(deck1.get(i));
        }
        final LinkedList<Integer> subDeck2 = new LinkedList<>();
        for (int i = 0; i < card2; i++) {
          subDeck2.addLast(deck2.get(i));
        }
        if (subGame(subDeck1, subDeck2).getLeft().equals(1)) {
          deck1.addLast(card1);
          deck1.addLast(card2);
        } else {
          deck2.addLast(card2);
          deck2.addLast(card1);
        }
      } else {
        if (card1 > card2) {
          deck1.addLast(card1);
          deck1.addLast(card2);
        } else {
          deck2.addLast(card2);
          deck2.addLast(card1);
        }
      }
    }

    return deck1.isEmpty() ? Pair.of(2, score(deck2)) : Pair.of(1, score(deck1));
  }

  private String state(final LinkedList<Integer> deck) {
    final StringBuilder state = new StringBuilder();
    for (final Integer card : deck) {
      state.append(card).append(",");
    }
    return state.toString();
  }

  private long score(final LinkedList<Integer> deck) {
    long score = 0L;
    while (!deck.isEmpty()) {
      score += (long) deck.size() * deck.removeFirst();
    }
    return score;
  }

}
