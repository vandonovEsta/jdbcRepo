package utils;

import java.util.List;
import java.util.Random;

public class Randomizer {
    public static <T> T returnRandomFromList(List<T> list) {
        Random rand = new Random();
        T randomElement = list.get(rand.nextInt(list.size()));
        return randomElement;
    }
}
