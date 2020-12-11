package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day11Test {

  @Test
  public void testSample1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day11/sampleA.txt").getPath());
    final Day11 day11 = new Day11(fileReader);
    Assertions.assertThat(day11.playGame()).isEqualTo(37);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day11/input.txt").getPath());
    final Day11 day11 = new Day11(fileReader);
    Assertions.assertThat(day11.playGame()).isEqualTo(2204);
  }

  @Test
  public void testSample2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day11/sampleA.txt").getPath());
    final Day11 day11 = new Day11(fileReader);
    Assertions.assertThat(day11.playGame2()).isEqualTo(26);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day11/input.txt").getPath());
    final Day11 day11 = new Day11(fileReader);
    Assertions.assertThat(day11.playGame2()).isEqualTo(1986);
  }

}