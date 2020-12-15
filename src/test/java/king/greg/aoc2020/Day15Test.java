package king.greg.aoc2020;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day15Test {

  @Test
  public void testSolution1() {
    final Day15 day15 = new Day15("8,0,17,4,1,12");
    Assertions.assertThat(day15.numberSpoken(2020)).isEqualTo(981L);
  }

  @Test
  public void testSolution2() {
    final Day15 day15 = new Day15("8,0,17,4,1,12");
    Assertions.assertThat(day15.numberSpoken(30000000)).isEqualTo(164878L);
  }

}