package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day19Test {

  @Test
  public void testSample1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day19/sampleA.txt").getPath());
    final Day19 day19 = new Day19(fileReader);
    Assertions.assertThat(day19.matches(0)).isEqualTo(2);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day19/input.txt").getPath());
    final Day19 day19 = new Day19(fileReader);
    Assertions.assertThat(day19.matches(0)).isEqualTo(165);
  }

  @Test
  public void testSample2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day19/sampleB.txt").getPath());
    final Day19 day19 = new Day19(fileReader);
    Assertions.assertThat(day19.matches(0, true)).isEqualTo(12);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day19/input.txt").getPath());
    final Day19 day19 = new Day19(fileReader);
    Assertions.assertThat(day19.matches(0, true)).isEqualTo(274);
  }

}