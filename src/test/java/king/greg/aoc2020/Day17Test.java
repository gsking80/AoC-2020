package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day17Test {

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day17/input.txt").getPath());
    final Day17 day17 = new Day17(fileReader);
    day17.cycle(6);
    Assertions.assertThat(day17.activeCubes()).isEqualTo(306);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day17/input.txt").getPath());
    final Day17 day17 = new Day17(fileReader);
    day17.cycle4D(6);
    Assertions.assertThat(day17.activeCubes()).isEqualTo(2572);
  }

}