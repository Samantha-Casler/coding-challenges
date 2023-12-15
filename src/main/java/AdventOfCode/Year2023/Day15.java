package AdventOfCode.Year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Day15 {
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("Day15Input.txt"));

        long startTime = System.nanoTime();

        String[] instructions = lines.getFirst().split(",");

        List<String>  instructionsList = Arrays.stream(instructions).toList();
        int totalPart1 = 0;
        for (String s : instructionsList) {
            int currentValue = 0;
            for (int j = 0; j < s.length(); j++) {
                char characterToHash = s.charAt(j);
                currentValue += characterToHash;
                currentValue *= 17;
                currentValue %= 256;
            }
            totalPart1 += currentValue;
        }

        System.out.println("Result Part 1: " + totalPart1 + " in " + TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-startTime))+"ms");

    }
}
