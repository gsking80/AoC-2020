package king.greg.aoc2020;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day25Test {

  @Test
  public void testSample1(){
    Assertions.assertThat(Day25.encryptionKey(5764801, 17807724)).isEqualTo(14897079L);
  }

  @Test
  public void testSolution1(){
  Assertions.assertThat(Day25.encryptionKey(11349501, 5107328)).isEqualTo(7936032L);
  }

}