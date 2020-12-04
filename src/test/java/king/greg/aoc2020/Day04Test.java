package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day04Test {
  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day04/input.txt").getPath());
    final Day04 day04 = new Day04(fileReader);
    Assertions.assertThat(day04.validPassports()).isEqualTo(228);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day04/input.txt").getPath());
    final Day04 day04 = new Day04(fileReader);
    Assertions.assertThat(day04.validPassports2()).isEqualTo(175);
  }
}