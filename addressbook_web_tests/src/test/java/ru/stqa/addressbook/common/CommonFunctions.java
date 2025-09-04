package ru.stqa.addressbook.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.io.ByteStreams.limit;

public class CommonFunctions {

    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(integer -> 'a' + integer)
                .map(Character::toString)
                .collect(Collectors.joining());
            return result;
        }

        public static String randomFile(String dir) {
        var fileNames = new File(dir).list();
            var rnd = new Random();
            var index = rnd.nextInt(fileNames.length);
            return Paths.get(dir, fileNames[index]).toString();
        }
}
