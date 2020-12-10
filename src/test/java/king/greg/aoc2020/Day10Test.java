package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day10Test {

  @Test
  public void testSample1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/sampleA.txt").getPath());
    final Day10 day10 = new Day10(fileReader);
    Assertions.assertThat(day10.joltageDifferences()).isEqualTo(7*5);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/input.txt").getPath());
    final Day10 day10 = new Day10(fileReader);
    Assertions.assertThat(day10.joltageDifferences()).isEqualTo(2100);
  }

  @Test
  public void testSample2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/sampleA.txt").getPath());
    final Day10 day10 = new Day10(fileReader);
    Assertions.assertThat(day10.choicesFrom(0)).isEqualTo(8);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/input.txt").getPath());
    final Day10 day10 = new Day10(fileReader);
    Assertions.assertThat(day10.choicesFrom(0)).isEqualTo(16198260678656L);
  }

}