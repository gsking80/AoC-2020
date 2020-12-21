package king.greg.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Day21 {
  final Set<Food> foods;

  public Day21(final FileReader fileReader) {
    foods = new HashSet<>();
    try {
      final BufferedReader buf = new BufferedReader(fileReader);
      while (true) {
        final String lineJustFetched = buf.readLine();
        if (null == lineJustFetched) {
          break;
        }
        final String[] parts = lineJustFetched.split(" \\(contains ");
        final String[] ingredients = parts[0].split(" ");
        final String[] allergens = parts[1].substring(0,parts[1].length()-1).split(", ");
        foods.add(new Food(ingredients, allergens));
      }
    } catch (IOException ioe) {
      throw new RuntimeException();
    }
  }

  public int ingredientsWithoutAllergens() {
    final Map<String, Set<String>> potentialIngredientsForAllergens = potentialIngredientsForAllergens();
    final Set<String> allergenIngredients = new HashSet<>();
    for (final Set<String> ingredients : potentialIngredientsForAllergens.values())
    {
      allergenIngredients.addAll(ingredients);
    }
    int allergenFreeIngredientCount = 0;
    for (final Food food : foods) {
      for (final String ingredient : food.ingredients) {
        if(!allergenIngredients.contains(ingredient)) {
          allergenFreeIngredientCount++;
        }
      }
    }
    return allergenFreeIngredientCount;
  }

  public String canonicalDangerousIngredients() {
    final Map<String, Set<String>> potentialIngredientsForAllergens = potentialIngredientsForAllergens();
    final Map<String, String> allergenIngredients = reduce(potentialIngredientsForAllergens);

    final List<String> dangerousIngredients = allergenIngredients.entrySet().stream().sorted(
        Entry.comparingByKey()).map(Map.Entry::getValue).collect(Collectors.toList());
    final StringBuilder sb = new StringBuilder(dangerousIngredients.get(0));
    for(int i = 1; i < dangerousIngredients.size(); i++) {
      sb.append(',').append(dangerousIngredients.get(i));
    }
    return sb.toString();
  }

  private static Map<String, String> reduce(Map<String, Set<String>> potentialIngredientsForAllergens) {
    final Map<String, String> allergenIngredients = new HashMap<>();
    while(allergenIngredients.size() != potentialIngredientsForAllergens.size()) {
      for (final Entry<String, Set<String>> potentialIngredientsForAllergen : potentialIngredientsForAllergens
          .entrySet()) {
        if (potentialIngredientsForAllergen.getValue().size() == 1) {
          allergenIngredients
              .put(potentialIngredientsForAllergen.getKey(),
                  potentialIngredientsForAllergen.getValue().iterator().next());
          for (final Entry<String, Set<String>> reduceEntries : potentialIngredientsForAllergens
              .entrySet()) {
            if (!reduceEntries.getKey().equals(potentialIngredientsForAllergen.getKey())) {
              reduceEntries.getValue().removeAll(potentialIngredientsForAllergen.getValue());
            }
          }
        }
      }
    }
    return allergenIngredients;
  }

  private Map<String, Set<String>> potentialIngredientsForAllergens() {
    final Map<String, Set<String>> potentialIngredientsForAllergens = new HashMap<>();
    for(final Food food : foods) {
      for (final String allergen : food.allergens) {
        Set<String> ingredientCandidates = potentialIngredientsForAllergens.get(allergen);
        if (ingredientCandidates == null) {
          ingredientCandidates = new HashSet<>(food.ingredients);
        } else {
          ingredientCandidates.retainAll(food.ingredients);
        }
        potentialIngredientsForAllergens.put(allergen, ingredientCandidates);
      }
    }
    return potentialIngredientsForAllergens;
  }

  static class Food {
    final Set<String> ingredients;
    final Set<String> allergens;

    public Food(final String[] ingredients, final String[] allergens) {
      this.ingredients = new HashSet<>(Arrays.asList(ingredients));
      this.allergens = new HashSet<>(Arrays.asList(allergens));
    }
  }


}
