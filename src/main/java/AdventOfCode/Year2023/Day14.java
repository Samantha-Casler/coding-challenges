package AdventOfCode.Year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Day14 {
    static int width;
    static int height;

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("Day14Input.txt"));
        width = lines.getFirst().length();
        height = lines.size();

        // Part 1
        long startTime = System.nanoTime();
        char[][] rockMap = createMap(lines);
        long totalLoad = calculateLoad(rollNorth(rockMap));

        System.out.println("Result part 1 : " + totalLoad + " in " + TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-startTime))+"ms");


        // Part 2
        startTime = System.nanoTime();
        rockMap = createMap(lines);

        Map<String, Long> occuranceMap = new HashMap<>();

        for (long i=0;i<1000000000;i++) {
            rockMap = spinCycle(rockMap);
            String rockMapString = rockMapToString(rockMap);
            // To make this run faster, we can skip ahead if we find a duplicate
            if (occuranceMap.containsKey(rockMapString)) {
                long delta = i - occuranceMap.get(rockMapString);
                i += delta * ((1000000000-i) / delta);
            }
            occuranceMap.put(rockMapString, i);
        }
        totalLoad = calculateLoad(rockMap);

        System.out.println("Result part 2 : " + totalLoad + " in " + TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-startTime))+"ms");
    }


    private static char[][] createMap(List<String> lines) {
        char[][] map = new char[width][height];
        for (int y=0;y<lines.size();y++) {
            String line = lines.get(y);
            for (int x=0;x<line.length();x++) {
                map[x][y] = line.charAt(x);
            }
        }
        return map;
    }

    private static char[][] rollNorth(char[][] rockMap) {
        for (int x = 0; x < width; x++) {
            boolean move = true;
            while (move) {
                move = false;
                for (int y = 1; y < height; y++) {
                    if (rockMap[x][y] == 'O' && rockMap[x][y-1] == '.') {
                        rockMap[x][y] = '.';
                        rockMap[x][y-1] = 'O';
                        move = true;
                    }
                }
            }
        }
        return rockMap;
    }

    private static char[][] rollSouth(char[][] rockMap) {
        for (int x = 0; x < width; x++) {
            boolean move = true;
            while (move) {
                move = false;
                for (int y = height - 1; y > 0; y--) {
                    if (rockMap[x][y] == '.' && rockMap[x][y-1] == 'O') {
                        rockMap[x][y] = 'O';
                        rockMap[x][y-1] = '.';
                        move = true;
                    }
                }
            }
        }
        return rockMap;
    }

    private static char[][] rollEast(char[][] rockMap) {
        for (int y = 0; y < height; y++) {
            boolean move = true;
            while (move) {
                move = false;
                for (int x = width - 1; x > 0; x--) {
                    if (rockMap[x][y] == '.' && rockMap[x-1][y] == 'O') {
                        rockMap[x][y] = 'O';
                        rockMap[x-1][y] = '.';
                        move = true;
                    }
                }
            }
        }
        return rockMap;
    }

    private static char[][] rollWest(char[][] rockMap) {
        for (int y = 0; y < height; y++) {
            boolean move = true;
            while (move) {
                move = false;
                for (int x = 1; x < width; x++) {
                    if (rockMap[x][y] == 'O' && rockMap[x-1][y] == '.') {
                        rockMap[x][y] = '.';
                        rockMap[x-1][y] = 'O';
                        move = true;
                    }
                }
            }
        }
        return rockMap;
    }

    private static long calculateLoad(char[][] map) {
        long total = 0;
        for (int x=0;x<width;x++) {
            for (int y=0;y<height;y++) {
                if (map[x][y] == 'O') {
                    total += height-y;
                }
            }
        }
        return total;
    }

    public static String rockMapToString(char[][] rockMap) {
        StringBuilder rockMapString = new StringBuilder();
        for (char[] line : rockMap) {
            rockMapString.append(new String(line));
        }
        return rockMapString.toString();
    }

    private static char[][] spinCycle(char[][] rockMap) {
        char[][] rockMapNorth = rollNorth(rockMap);
        char[][] rockMapWest = rollWest(rockMapNorth);
        char[][] rockMapSouth = rollSouth(rockMapWest);
        return rollEast(rockMapSouth);
    }
}
