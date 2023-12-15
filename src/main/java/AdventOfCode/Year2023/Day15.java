package AdventOfCode.Year2023;

import AdventOfCode.Year2023.models.Lens;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Day15 {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("Day15Input.txt"));

        // Part 1
        long startTime = System.nanoTime();

        String[] instructions = lines.getFirst().split(",");

        List<String>  instructionsList = Arrays.stream(instructions).toList();
        int totalPart1 = 0;
        for (String s : instructionsList) {
            totalPart1 += hash(s);
        }

        System.out.println("Result Part 1: " + totalPart1 + " in " + TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-startTime))+"ms");

        // Part 2
        startTime = System.nanoTime();

        int totalPart2 = 0;
        ArrayList<List<Lens>> boxes = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            boxes.add(new LinkedList<>());
        }

        for (String s : instructionsList) {

            if (s.contains("-")) {
                String[] label = s.split("-");
                int boxNumber = hash(label[0]);
                boxes.get(boxNumber).removeIf(lens -> lens.label().equals(label[0]));
            } else {
                String[] split = s.split("=");
                String label = split[0];
                int boxNumber = hash(label);
                boolean found = false;
                for(int i = 0; i < boxes.get(boxNumber).size(); i++) {
                    if (boxes.get(boxNumber).get(i).label().equals(label)) {
                        boxes.get(boxNumber).get(i).setFocalLength(Integer.parseInt(split[1]));
                        found = true;
                    }
                }
                if (!found) {
                    boxes.get(boxNumber).add(new Lens(label, Integer.parseInt(split[1])));
                }
            }
        }

        for(int i = 0; i < boxes.size(); i++) {
            if (!boxes.get(i).isEmpty()) {
                for(int j = 0; j < boxes.get(i).size(); j++) {
                    int focusPower = (i + 1) * (j + 1) * boxes.get(i).get(j).focalLength();
                    totalPart2 += focusPower;
                }
            }
        }

        System.out.println("Result Part 2: " + totalPart2 + " in " + TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-startTime))+"ms");

    }

    private static int hash(String s) {
        int currentValue = 0;
        for (int j = 0; j < s.length(); j++) {
            char characterToHash = s.charAt(j);
            currentValue += characterToHash;
            currentValue *= 17;
            currentValue %= 256;
        }
        return currentValue;
    }
}
