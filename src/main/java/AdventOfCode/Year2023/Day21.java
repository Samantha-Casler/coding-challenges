package AdventOfCode.Year2023;

import AdventOfCode.Year2023.models.StepLocation;
import AdventOfCode.util.MapUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Day21 {

    private static final List<StepLocation> stepLocations = new ArrayList<>();
    private static final List<StepLocation> stepsToAdd = new ArrayList<>();

    private static int stepsTaken = 0;

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("Day21Input.txt"));

        // Part 1
        long startTime = System.nanoTime();

        char[][] gardenMap = MapUtils.createCharMap(lines, lines.getFirst().length(), lines.size());

        int startx = 0;
        int starty = 0;

        for (int y=0;y<gardenMap.length;y++) {
            for (int x=0;x<gardenMap[y].length;x++) {
                if (gardenMap[x][y] == 'S') {
                    startx = x;
                    starty = y;
                }
            }
        }

        stepLocations.add(new StepLocation(startx, starty));
        stepsTaken++;
        checkEast(gardenMap, startx, starty);
        checkWest(gardenMap, startx, starty);
        checkNorth(gardenMap, startx, starty);
        checkSouth(gardenMap, startx, starty);

        updateMap(gardenMap);
        stepLocations.removeFirst();
        stepLocations.addAll(stepsToAdd);
        stepsToAdd.clear();

        while (stepsTaken < 64) {
            stepsTaken++;
            for (StepLocation stepLocation : stepLocations) {
                checkDirections(gardenMap, stepLocation.getX(), stepLocation.getY());
            }
            updateMap(gardenMap);
            stepLocations.clear();
            stepLocations.addAll(stepsToAdd);
            stepsToAdd.clear();
        }



        int countPart1 = 0;
        for (int y=0;y<gardenMap.length;y++) {
            for (int x=0;x<gardenMap[y].length;x++) {
                if (gardenMap[x][y] == 'X') {
                    countPart1++;
                }
            }
        }



        long PartEendTime = TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-startTime));
        System.out.println("Result part 1 : " + countPart1 + " in " + PartEendTime +"ms");


        // Part 2
        long start2Time = System.nanoTime();
        long endTime = TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-start2Time));
        System.out.println("Result part 2 : " + " in " + endTime +"ms");

    }

    private static void updateMap(char[][] gardenMap) {
        for (StepLocation stepLocation : stepLocations) {
            gardenMap[stepLocation.getX()][stepLocation.getY()] = '.';
        }
        for (StepLocation stepLocation : stepsToAdd) {
            gardenMap[stepLocation.getX()][stepLocation.getY()] = 'X';
        }
    }

    private static void checkDirections (char[][] gardenMap, int x, int y) {
        checkEast(gardenMap, x, y);
        checkWest(gardenMap, x, y);
        checkNorth(gardenMap, x, y);
        checkSouth(gardenMap, x, y);
    }

    private static void checkNorth(char[][] gardenMap, int x, int y) {
        if (y - 1 >= 0) {
            if (gardenMap[x][y - 1] == '.') {
                if (stepsToAdd.isEmpty()) {
                    stepsToAdd.add(new StepLocation(x, y - 1));
                } else {
                    boolean alreadyAdded = false;
                    for (StepLocation stepLocation : stepsToAdd) {
                        if (stepLocation.getX() == x && stepLocation.getY() == y - 1) {
                            alreadyAdded = true;
                            break;
                        }
                    }
                    if (!alreadyAdded) {
                        stepsToAdd.add(new StepLocation(x, y - 1));
                    }
                }
            }
        }
    }

    private static void checkSouth(char[][] gardenMap, int x, int y) {
        if (y + 1 < gardenMap.length) {
            if (gardenMap[x][y + 1] == '.') {
                if (stepsToAdd.isEmpty()) {
                    stepsToAdd.add(new StepLocation(x, y + 1));
                } else {
                    boolean alreadyAdded = false;
                    for (StepLocation stepLocation : stepsToAdd) {
                        if (stepLocation.getX() == x && stepLocation.getY() == y + 1) {
                            alreadyAdded = true;
                            break;
                        }
                    }
                    if (!alreadyAdded) {
                        stepsToAdd.add(new StepLocation(x, y + 1));
                    }
                }
            }
        }
    }

    private static void checkEast(char[][] gardenMap, int x, int y) {
        if (x + 1 < gardenMap[y].length) {
            if (gardenMap[x + 1][y] == '.') {
                if (stepsToAdd.isEmpty()) {
                    stepsToAdd.add(new StepLocation(x + 1, y));
                } else {
                    boolean alreadyAdded = false;
                    for (StepLocation stepLocation : stepsToAdd) {
                        if (stepLocation.getX() == x + 1 && stepLocation.getY() == y) {
                            alreadyAdded = true;
                            break;
                        }
                    }
                    if (!alreadyAdded) {
                        stepsToAdd.add(new StepLocation(x + 1, y));
                    }
                }
            }
        }
    }

    private static void checkWest(char[][] gardenMap, int x, int y) {
        if (x - 1 >= 0) {
            if (gardenMap[x - 1][y] == '.') {
                if (stepsToAdd.isEmpty()) {
                    stepsToAdd.add(new StepLocation(x - 1, y));
                } else {
                    boolean alreadyAdded = false;
                    for (StepLocation stepLocation : stepsToAdd) {
                        if (stepLocation.getX() == x - 1 && stepLocation.getY() == y) {
                            alreadyAdded = true;
                            break;
                        }
                    }
                    if (!alreadyAdded) {
                        stepsToAdd.add(new StepLocation(x - 1, y));
                    }
                }
            }
        }
    }
}
