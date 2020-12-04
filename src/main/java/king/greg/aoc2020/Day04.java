package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {

  final List<Map<String,String>> passports;
  final Set<String> requiredFields = new HashSet<>(Arrays.asList("byr","iyr","eyr","hgt","hcl","ecl","pid"));
  final Pattern patternHgt = Pattern.compile("^(\\d+)(\\D+)$");
  final Pattern patternHcl = Pattern.compile("^[#][0-9|a-f]{6}$");
  final Pattern patternEcl = Pattern.compile("^amb|blu|brn|gry|grn|hzl|oth$");
  final Pattern patternPid = Pattern.compile("^\\d{9}$");

  public Day04(FileReader fileReader) {

    passports = new ArrayList<>();

    try {
      final BufferedReader buf = new BufferedReader(fileReader);

      Map<String,String> passport = new HashMap<>();
      while(true) {
        final String lineJustFetched = buf.readLine();
        if(null == lineJustFetched) {
          passports.add(passport);
          break;
        } else {
          if (lineJustFetched.isEmpty()) {
            passports.add(passport);
            passport = new HashMap<>();
          } else {
            final String[] fields = lineJustFetched.split(" ");
            for (final String field : fields) {
              final String[] values = field.split(":");
              passport.put(values[0], values[1]);
            }
          }
        }
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int validPassports() {
    int valid = 0;

    for(final Map<String,String> passport : passports) {
      if (passport.keySet().containsAll(requiredFields)) {
        valid++;
      }
    }

    return valid;
  }

  public int validPassports2() {
    int valid = 0;

    for(final Map<String,String> passport : passports) {
      if (!passport.keySet().containsAll(requiredFields)) {
        continue;
      }

      //byr
      try {
        int year = Integer.parseInt(passport.get("byr"));
        if (year < 1920 || year > 2002) {
          continue;
        }
      } catch (NumberFormatException e) {
        continue;
      }

      //iyr
      try {
        int year = Integer.parseInt(passport.get("iyr"));
        if (year < 2010 || year > 2020) {
          continue;
        }
      } catch (NumberFormatException e) {
        continue;
      }

      //eyr
      try {
        int year = Integer.parseInt(passport.get("eyr"));
        if (year < 2020 || year > 2030) {
          continue;
        }
      } catch (NumberFormatException e) {
        continue;
      }

      //hgt
      final Matcher matcherHgt = patternHgt.matcher(passport.get("hgt"));
      if(matcherHgt.find()) {
        int heightValue = Integer.valueOf(matcherHgt.group(1));
        switch(matcherHgt.group(2)){
          case "cm":
            if(heightValue < 150 || heightValue > 193) {
              continue;
            }
            break;
          case "in":
            if (heightValue < 59 || heightValue > 76) {
              continue;
            }
            break;
          default:
            continue;
        }
      } else {
        continue;
      }

      //hcl
      if(!patternHcl.matcher(passport.get("hcl")).find()) {
        continue;
      }

      //ecl
      if(!patternEcl.matcher(passport.get("ecl")).find()) {
        continue;
      }

      //pid
      if(!patternPid.matcher(passport.get("pid")).find()) {
        continue;
      }

      valid++;
    }

    return valid;
  }

}
