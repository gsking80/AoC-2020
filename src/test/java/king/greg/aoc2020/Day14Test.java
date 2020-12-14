package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day14Test {

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day14/input.txt").getPath());
    final Day14 day14 = new Day14(fileReader);
    Assertions.assertThat(day14.initialize(false)).isEqualTo(12135523360904L);
  }

  @Test
  public void testSample2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day14/sampleA.txt").getPath());
    final Day14 day14 = new Day14(fileReader);
    Assertions.assertThat(day14.initialize(true)).isEqualTo(208L); // Not 2761644903968L
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day14/input.txt").getPath());
    final Day14 day14 = new Day14(fileReader);
    Assertions.assertThat(day14.initialize(true)).isEqualTo(2741969047858L); // Not 2761644903968L
  }

}