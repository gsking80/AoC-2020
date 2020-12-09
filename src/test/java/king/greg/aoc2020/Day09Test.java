package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day09Test {

  @Test
  public void testSample1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day09/sample.txt").getPath());
    final Day09 day09 = new Day09(fileReader);
    Assertions.assertThat(day09.findFirstNoSum(5)).isEqualTo(127);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day09/input.txt").getPath());
    final Day09 day09 = new Day09(fileReader);
    Assertions.assertThat(day09.findFirstNoSum(25)).isEqualTo(31161678);
  }

  @Test
  public void testSample2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day09/sample.txt").getPath());
    final Day09 day09 = new Day09(fileReader);
    Assertions.assertThat(day09.findFirstInvalidContiguousRangeSum(127)).isEqualTo(62);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day09/input.txt").getPath());
    final Day09 day09 = new Day09(fileReader);
    Assertions.assertThat(day09.findFirstInvalidContiguousRangeSum(31161678)).isEqualTo(5453868);
  }

}