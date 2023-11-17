package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day12Test {

  @Test
  public void testSample1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day12/sampleA.txt").getPath());
    final Day12 day12 = new Day12(fileReader);
    Assertions.assertThat(day12.followInstructions()).isEqualTo(25);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day12/input.txt").getPath());
    final Day12 day12 = new Day12(fileReader);
    Assertions.assertThat(day12.followInstructions()).isEqualTo(1106);
  }

  @Test
  public void testSample2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day12/sampleA.txt").getPath());
    final Day12 day12 = new Day12(fileReader);
    Assertions.assertThat(day12.followInstructions2()).isEqualTo(286);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day12/input.txt").getPath());
    final Day12 day12 = new Day12(fileReader);
    Assertions.assertThat(day12.followInstructions2()).isEqualTo(107281);
  }

}