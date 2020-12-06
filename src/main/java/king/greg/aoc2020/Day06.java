package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day06 {

  final List<Set<Character>> answers;

  public Day06(FileReader fileReader, final boolean part2) {
    answers = new ArrayList<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      Set<Character> groupAnswers = new HashSet<>();
      boolean firstline = true;
      while(true) {
        final String lineJustFetched = buf.readLine();
        if(null == lineJustFetched) {
          answers.add(groupAnswers);
          break;
        } else {
          if (lineJustFetched.isEmpty()){
            answers.add(groupAnswers);
            groupAnswers = new HashSet<>();
            firstline = true;
          } else {
            Set<Character> personAnswers = lineJustFetched.chars().mapToObj(c -> (char) c)
                .collect(Collectors.toCollection(HashSet::new));
            if (part2 && !firstline) {
              groupAnswers.retainAll(personAnswers);
            } else {
              groupAnswers.addAll(personAnswers);
              firstline = false;
            }
          }
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int totalAnswers() {
    int totalAnswers = 0;
    for (final Set<Character> groupAnswers: answers) {
      totalAnswers += groupAnswers.size();
    }
    return totalAnswers;
  }
}
