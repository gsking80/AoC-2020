package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day08Test {

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day08/input.txt").getPath());
    final Day08 day08 = new Day08(fileReader);
    Assertions.assertThat(day08.run()).isEqualTo(1087);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day08/input.txt").getPath());
    final Day08 day08 = new Day08(fileReader);
    Assertions.assertThat(day08.runFix()).isEqualTo(780);
  }

}