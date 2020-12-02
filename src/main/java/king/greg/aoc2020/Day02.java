package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day02 {



  public static int validPasswords1(FileReader fileReader) {
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      int validPasswords = 0;

      while(true) {
        final String lineJustFetched = buf.readLine();
        if(null == lineJustFetched) {
          break;
        } else {
          final String[] parts = lineJustFetched.split("-|: | ");
          //System.out.println(parts[0] + "~" +parts[1] + "~" +parts[2] + "~" +parts[3]);
          final long count = parts[3].chars().filter(ch -> ch == parts[2].charAt(0)).count();
          if (count >= Integer.parseInt(parts[0]) & count <= Integer.parseInt(parts[1])) {
            validPasswords++;
          }
        }
      }
      return validPasswords;
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public static int validPasswords2(FileReader fileReader) {
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      int validPasswords = 0;

      while(true) {
        final String lineJustFetched = buf.readLine();
        if(null == lineJustFetched) {
          break;
        } else {
          final String[] parts = lineJustFetched.split("-|: | ");
          if ((parts[3].charAt(Integer.parseInt(parts[0])-1) == parts[2].charAt(0))
              ^ (parts[3].charAt(Integer.parseInt(parts[1])-1) == parts[2].charAt(0))) {
            validPasswords++;
          }
        }
      }
      return validPasswords;
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

}
