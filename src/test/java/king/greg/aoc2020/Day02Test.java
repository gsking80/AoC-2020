package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day02Test {

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day02/input.txt").getPath());
    Assertions.assertThat(Day02.validPasswords1(fileReader)).isEqualTo(506);
  }


  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day02/input.txt").getPath());
    Assertions.assertThat(Day02.validPasswords2(fileReader)).isEqualTo(443);
  }

}