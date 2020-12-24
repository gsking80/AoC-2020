package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day24Test {

  @Test
  public void testSampleA1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day24/sampleA.txt").getPath());
    final Day24 day24 = new Day24(fileReader);
    Assertions.assertThat(day24.blackTiles()).isEqualTo(10);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day24/input.txt").getPath());
    final Day24 day24 = new Day24(fileReader);
    Assertions.assertThat(day24.blackTiles()).isEqualTo(386);
  }

  @Test
  public void testSampleA2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day24/sampleA.txt").getPath());
    final Day24 day24 = new Day24(fileReader);
    Assertions.assertThat(day24.blackTilesLife(100)).isEqualTo(2208);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day24/input.txt").getPath());
    final Day24 day24 = new Day24(fileReader);
    Assertions.assertThat(day24.blackTilesLife(100)).isEqualTo(4214);
  }

}