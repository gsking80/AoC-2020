package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day07Test {

  @Test
  public void testSSample1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day07/sample.txt").getPath());
    final Day07 day07 = new Day07(fileReader);
    Assertions.assertThat(day07.placesFor("shiny gold")).isEqualTo(4);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day07/input.txt").getPath());
    final Day07 day07 = new Day07(fileReader);
    Assertions.assertThat(day07.placesFor("shiny gold")).isEqualTo(192);
  }

  @Test
  public void testSSample2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day07/sample.txt").getPath());
    final Day07 day07 = new Day07(fileReader);
    Assertions.assertThat(day07.bagsInside("shiny gold")).isEqualTo(32);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day07/input.txt").getPath());
    final Day07 day07 = new Day07(fileReader);
    Assertions.assertThat(day07.bagsInside("shiny gold")).isEqualTo(12128);
  }

}