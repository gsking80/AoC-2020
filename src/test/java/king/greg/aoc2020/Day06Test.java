package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day06Test {
  @Test
  public void testSample() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day06/sample.txt").getPath());
    final Day06 day06 = new Day06(fileReader, false);
    Assertions.assertThat(day06.totalAnswers()).isEqualTo(11);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day06/input.txt").getPath());
    final Day06 day06 = new Day06(fileReader, false);
    Assertions.assertThat(day06.totalAnswers()).isEqualTo(6310);
  }

  @Test
  public void testSample2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day06/sample.txt").getPath());
    final Day06 day06 = new Day06(fileReader, true);
    Assertions.assertThat(day06.totalAnswers()).isEqualTo(6);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day06/input.txt").getPath());
    final Day06 day06 = new Day06(fileReader, true);
    Assertions.assertThat(day06.totalAnswers()).isEqualTo(3193);
  }
}