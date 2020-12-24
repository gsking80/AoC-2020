package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day22Test {

  @Test
  public void testSampleA1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day22/sampleA.txt").getPath());
    final Day22 day22 = new Day22(fileReader);
    Assertions.assertThat(day22.playGame()).isEqualTo(306L);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day22/input.txt").getPath());
    final Day22 day22 = new Day22(fileReader);
    Assertions.assertThat(day22.playGame()).isEqualTo(33393L);
  }

  @Test
  public void testSampleA2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day22/sampleA.txt").getPath());
    final Day22 day22 = new Day22(fileReader);
    Assertions.assertThat(day22.playRecursiveGame()).isEqualTo(291L);
  }

  @Test
  public void testSampleB2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day22/sampleB.txt").getPath());
    final Day22 day22 = new Day22(fileReader);
    Assertions.assertThat(day22.playRecursiveGame()).isEqualTo(105L);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day22/input.txt").getPath());
    final Day22 day22 = new Day22(fileReader);
    Assertions.assertThat(day22.playRecursiveGame()).isEqualTo(31963L);
  }

}