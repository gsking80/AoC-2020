package king.greg.aoc2020;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class Day23Test {

  @Test
  public void testSample1() {
    final SoftAssertions softly = new SoftAssertions();
    softly.assertThat(Day23.cups("389125467", 10)).isEqualTo("92658374");
    softly.assertThat(Day23.cups("389125467", 100)).isEqualTo("67384529");
    softly.assertAll();
  }

  @Test
  public void testSolution1() {
    Assertions.assertThat(Day23.cups("624397158", 100)).isEqualTo("74698532");
  }

  @Test
  public void testSample2() {
    Assertions.assertThat(Day23.cups2("389125467", 10000000)).isEqualTo(149245887792L);
  }

  @Test
  public void testSolution2() {
    Assertions.assertThat(Day23.cups2("624397158", 10000000)).isEqualTo(286194102744L);
  }

}