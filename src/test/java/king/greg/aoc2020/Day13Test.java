package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day13Test {

  @Test
  public void testSample1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/sampleA.txt").getPath());
    final Day13 day13 = new Day13(fileReader);
    Assertions.assertThat(day13.busToTake()).isEqualTo(295);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/input.txt").getPath());
    final Day13 day13 = new Day13(fileReader);
    Assertions.assertThat(day13.busToTake()).isEqualTo(370);
  }

  @Test
  public void testSample2A() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/sampleA.txt").getPath());
    final Day13 day13 = new Day13(fileReader);
    Assertions.assertThat(day13.busContestTime()).isEqualTo(1068781);
  }

  @Test
  public void testSample2B() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/sampleB.txt").getPath());
    final Day13 day13 = new Day13(fileReader);
    Assertions.assertThat(day13.busContestTime()).isEqualTo(3417);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/input.txt").getPath());
    final Day13 day13 = new Day13(fileReader);
    Assertions.assertThat(day13.busContestTime()).isEqualTo(894954360381385L);
  }

}