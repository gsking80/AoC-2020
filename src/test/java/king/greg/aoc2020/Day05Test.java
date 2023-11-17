package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day05Test {

  @Test
  public void testA() {
    Assertions.assertThat(Day05.seatID("FBFBBFFRLR")).isEqualTo(357);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day05/input.txt").getPath());
    final Day05 day05 = new Day05(fileReader);
    Assertions.assertThat(day05.highestSeatId()).isEqualTo(801);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day05/input.txt").getPath());
    final Day05 day05 = new Day05(fileReader);
    Assertions.assertThat(day05.mySeatId()).isEqualTo(597);
  }

}