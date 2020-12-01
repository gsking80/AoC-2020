package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day01Test {

  @Test
  public void testSolution1() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day01/input.txt").getPath());
    final Day01 day01 = new Day01(fileReader);
    Assertions.assertThat(day01.getTwoProduct()).isEqualTo(996996);

  }

  @Test
  public void testSolution2() throws FileNotFoundException {

    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day01/input.txt").getPath());
    final Day01 day01 = new Day01(fileReader);
    Assertions.assertThat(day01.getThreeProduct()).isEqualTo(996996);

  }

}