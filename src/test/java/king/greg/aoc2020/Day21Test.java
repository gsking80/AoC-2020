package king.greg.aoc2020;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day21Test {

  @Test
  public void testSolution1() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day21/input.txt").getPath());
    final Day21 day21 = new Day21(fileReader);
    Assertions.assertThat(day21.ingredientsWithoutAllergens()).isEqualTo(2614);
  }

  @Test
  public void testSolution2() throws FileNotFoundException {
    final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day21/input.txt").getPath());
    final Day21 day21 = new Day21(fileReader);
    Assertions.assertThat(day21.canonicalDangerousIngredients()).isEqualTo("qhvz,kbcpn,fzsl,mjzrj,bmj,mksmf,gptv,kgkrhg");
  }

}