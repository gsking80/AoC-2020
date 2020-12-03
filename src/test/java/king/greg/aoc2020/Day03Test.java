package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day03Test {
  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day03/input.txt").getPath());
    final Day03 day03 = new Day03(fileReader);
    Assertions.assertThat(day03.treesEncountered(3,1)).isEqualTo(184);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day03/input.txt").getPath());
    final Day03 day03 = new Day03(fileReader);
    long trees = day03.treesEncountered(1,1);
    trees *= day03.treesEncountered(3,1);
    trees *= day03.treesEncountered(5,1);
    trees *= day03.treesEncountered(7,1);
    trees *= day03.treesEncountered(1,2);
    Assertions.assertThat(trees).isEqualTo(2431272960L);
  }
}