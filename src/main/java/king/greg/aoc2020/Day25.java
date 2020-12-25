package king.greg.aoc2020;

public class Day25 {

  private Day25() {}

  public static long encryptionKey(final int cardPublicKey, int doorPublicKey) {
    long encryptionKey = 1;
    long cardValue = 1;
    while (cardValue != cardPublicKey) {
      cardValue = (cardValue * 7) % 20201227;
      encryptionKey = (encryptionKey * doorPublicKey) % 20201227;
    }
    return encryptionKey;
  }

}
