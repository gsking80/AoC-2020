package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day20Test {

  @Test
  public void testSample1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/sampleA.txt").getPath());
    final Day20 day20 = new Day20(fileReader);
    Assertions.assertThat(day20.findCorners()).isEqualTo(20899048083289L);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/input.txt").getPath());
    final Day20 day20 = new Day20(fileReader);
    Assertions.assertThat(day20.findCorners()).isEqualTo(4006801655873L);
  }

  @Test
  public void testSample2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/sampleA.txt").getPath());
    final Day20 day20 = new Day20(fileReader);
    Assertions.assertThat(day20.roughWaters()).isEqualTo(273L);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/input.txt").getPath());
    final Day20 day20 = new Day20(fileReader);
    Assertions.assertThat(day20.roughWaters()).isEqualTo(1838L);
  }

}