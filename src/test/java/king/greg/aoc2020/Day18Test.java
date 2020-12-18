package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day18Test {

  @Test
  public void testEquation1() {
    final Day18 day18 = new Day18();
    Assertions.assertThat(day18.solveEquation("2 * 3 + (4 * 5)")).isEqualTo(26);
  }

  @Test
  public void testEquation2() {
    final Day18 day18 = new Day18();
    Assertions.assertThat(day18.solveEquation("5 + (8 * 3 + 9 + 3 * 4 * 3)")).isEqualTo(437);
  }

  @Test
  public void testEquation3() {
    final Day18 day18 = new Day18();
    Assertions.assertThat(day18.solveEquation("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).isEqualTo(12240);
  }

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/input.txt").getPath());
    final Day18 day18 = new Day18(fileReader);
    Assertions.assertThat(day18.sumAllEquations()).isEqualTo(1451467526514L);
  }

  @Test
  public void testEquation4() {
    final Day18 day18 = new Day18(true);
    Assertions.assertThat(day18.solveEquation("2 * 3 + (4 * 5)")).isEqualTo(46);
  }

  @Test
  public void testEquation5() {
    final Day18 day18 = new Day18(true);
    Assertions.assertThat(day18.solveEquation("5 + (8 * 3 + 9 + 3 * 4 * 3)")).isEqualTo(1445);
  }

  @Test
  public void testEquation6() {
    final Day18 day18 = new Day18(true);
    Assertions.assertThat(day18.solveEquation("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).isEqualTo(669060);
  }

  @Test
  public void testEquation7() {
    final Day18 day18 = new Day18(true);
    Assertions.assertThat(day18.solveEquation("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")).isEqualTo(23340);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/input.txt").getPath());
    final Day18 day18 = new Day18(fileReader, true);
    Assertions.assertThat(day18.sumAllEquations()).isEqualTo(224973686321527L);
  }

}