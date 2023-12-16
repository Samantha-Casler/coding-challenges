package AdventOfCode.Year2023;

import AdventOfCode.Year2023.models.Beam;
import AdventOfCode.Year2023.models.Tile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static AdventOfCode.util.MapUtils.createMap;

public class Day16 {

    static int width;
    static int height;

    static List<Beam> movingBeams = new java.util.ArrayList<>();
    static List<Beam> newBeams = new java.util.ArrayList<>();
    static List<Beam> removeBeams = new java.util.ArrayList<>();
    static List<Beam> beamsToTry = new java.util.ArrayList<>();

    static Tile[][] tileMap;
    static char[][] mirrorMap;

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("Day16Input.txt"));
        width = lines.getFirst().length();
        height = lines.size();

        // Part 1
        long startTime = System.nanoTime();
        mirrorMap = createMap(lines, width, height);
        tileMap = createTileMap(width, height);
        Beam beam = new Beam(0, 0, "right");
        movingBeams.add(beam);
        while (!movingBeams.isEmpty()) {
            removeBeams.clear();
            newBeams.clear();
            for (Beam movingBeam : movingBeams) {
                determineBeamDirection(movingBeam);
            }
            movingBeams.removeAll(removeBeams);
            movingBeams.addAll(newBeams);
        }

        int totalEnergized = 0;
        for(int y=0;y<height;y++) {
            for (int x=0;x<width;x++) {
                if (!tileMap[x][y].getDirections().isEmpty()) {
                    totalEnergized++;
                }
            }
        }

        System.out.println("Result part 1 : " + totalEnergized + " in " + java.util.concurrent.TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-startTime))+"ms");


        // Part 2
        startTime = System.nanoTime();
        mirrorMap = createMap(lines, width, height);
        tileMap = createTileMap(width, height);
        int totalEnergizedPart2 = 0;
        resetBeamsToTry();

        for (int i = 0; i < beamsToTry.size(); i++) {
            tileMap = createTileMap(width, height);
            movingBeams.add(beamsToTry.get(i));
            while (!movingBeams.isEmpty()) {
                removeBeams.clear();
                newBeams.clear();
                for (Beam movingBeam : movingBeams) {
                    determineBeamDirection(movingBeam);
                }
                movingBeams.removeAll(removeBeams);
                movingBeams.addAll(newBeams);
            }

            totalEnergized = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (!tileMap[x][y].getDirections().isEmpty()) {
                        totalEnergized++;
                    }
                }
            }

            if (totalEnergizedPart2 < totalEnergized) {
                totalEnergizedPart2 = totalEnergized;
            }
            resetBeamsToTry();
        }
        System.out.println("Result part 1 : " + totalEnergizedPart2 + " in " + java.util.concurrent.TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-startTime))+"ms");


    }

    private static void determineBeamDirection(Beam beam) {
        do {
            if (beam.getX() < 0 || beam.getX() >= width || beam.getY() < 0 || beam.getY() >= height) {
                removeBeams.add(beam);
                break;
            }
            if (mirrorMap[beam.getX()][beam.getY()] == '/' && Objects.equals(beam.getDirection(), "right")) {
                beam.setDirection("up");
            } else if (mirrorMap[beam.getX()][beam.getY()] == '/' && Objects.equals(beam.getDirection(), "left")) {
                beam.setDirection("down");
            } else if (mirrorMap[beam.getX()][beam.getY()] == '/' && Objects.equals(beam.getDirection(), "up")) {
                beam.setDirection("right");
            } else if (mirrorMap[beam.getX()][beam.getY()] == '/' && Objects.equals(beam.getDirection(), "down")) {
                beam.setDirection("left");
            } else if (mirrorMap[beam.getX()][beam.getY()] == '\\' && Objects.equals(beam.getDirection(), "right")) {
                beam.setDirection("down");
            } else if (mirrorMap[beam.getX()][beam.getY()] == '\\' && Objects.equals(beam.getDirection(), "left")) {
                beam.setDirection("up");
            } else if (mirrorMap[beam.getX()][beam.getY()] == '\\' && Objects.equals(beam.getDirection(), "up")) {
                beam.setDirection("left");
            } else if (mirrorMap[beam.getX()][beam.getY()] == '\\' && Objects.equals(beam.getDirection(), "down")) {
                beam.setDirection("right");
            } else if (mirrorMap[beam.getX()][beam.getY()] == '|' && Objects.equals(beam.getDirection(), "right")) {
                beam.setDirection("down");
                if (beam.getY() - 1 >= 0) {
                    Beam newBeam = new Beam(beam.getX(), beam.getY() - 1, "up");
                    if (!(tileMap[newBeam.getX()][newBeam.getY()].getDirections().contains(newBeam.getDirection()))) {
                        newBeams.add(newBeam);
                    }
                }
            } else if (mirrorMap[beam.getX()][beam.getY()] == '|' && Objects.equals(beam.getDirection(), "left")) {
                beam.setDirection("up");
                if (beam.getY() + 1 < height) {
                    Beam newBeam = new Beam(beam.getX(), beam.getY() + 1, "down");
                    if (!(tileMap[newBeam.getX()][newBeam.getY()].getDirections().contains(newBeam.getDirection()))) {
                        newBeams.add(newBeam);
                    }
                }
            } else if (mirrorMap[beam.getX()][beam.getY()] == '-' && Objects.equals(beam.getDirection(), "up")) {
                beam.setDirection("left");
                if (beam.getX() + 1 < width) {
                    Beam newBeam = new Beam(beam.getX() + 1, beam.getY(), "right");
                    if (!(tileMap[newBeam.getX()][newBeam.getY()].getDirections().contains(newBeam.getDirection()))) {
                        newBeams.add(newBeam);
                    }
                }
            } else if (mirrorMap[beam.getX()][beam.getY()] == '-' && Objects.equals(beam.getDirection(), "down")) {
                beam.setDirection("right");
                if (beam.getX() - 1 >= 0 ) {
                    Beam newBeam = new Beam(beam.getX() - 1, beam.getY(), "left");
                    if (!(tileMap[newBeam.getX()][newBeam.getY()].getDirections().contains(newBeam.getDirection()))) {
                        newBeams.add(newBeam);
                    }
                }
            }

            if (!(tileMap[beam.getX()][beam.getY()].getDirections().contains(beam.getDirection()))) {
                List<String> directions = tileMap[beam.getX()][beam.getY()].getDirections();
                directions.add(beam.getDirection());
                tileMap[beam.getX()][beam.getY()].setDirections(directions);
            } else {
                removeBeams.add(beam);
                break;
            }
        }while(moveBeam(beam));
    }

    private static boolean moveBeam(Beam beam) {
        boolean moveBeam = false;
        if (Objects.equals(beam.getDirection(), "right")) {
            if(beam.getX() + 1 == width) {
                removeBeams.add(beam);
            } else {
                moveBeam = true;
                beam.setX(beam.getX() + 1);
            }
        } else if (Objects.equals(beam.getDirection(), "left")) {
            if(beam.getX() - 1 < 0) {
                removeBeams.add(beam);
            } else {
                moveBeam = true;
                beam.setX(beam.getX() - 1);
            }
        } else if (Objects.equals(beam.getDirection(), "up")) {
            if(beam.getY() - 1 < 0) {
                removeBeams.add(beam);
            } else {
                moveBeam = true;
                beam.setY(beam.getY() - 1);
            }
        } else if (Objects.equals(beam.getDirection(), "down")) {
            if(beam.getY() + 1 == height) {
                removeBeams.add(beam);
            } else {
                moveBeam = true;
                beam.setY(beam.getY() + 1);
            }
        }
        return moveBeam;
    }

    public static Tile[][] createTileMap(int width, int height) {
        Tile[][] map = new Tile[width][height];
        for (int y=0;y<height;y++) {
            for (int x=0;x<width;x++) {
                map[x][y] = new Tile(x, y, new ArrayList<>());
            }
        }
        return map;
    }

    // Still not sure how but it was resetting the beamToTry value to the last one before counting the totalEnergized... ??? Doesnt make sense
    // A bit hacky but it works lol
    private static void resetBeamsToTry() {
        beamsToTry.clear();
        for (int y = 0; y < height; y++) {
            Beam beam1 = new Beam(0, y, "right");
            beamsToTry.add(beam1);
            Beam beam2 = new Beam(width - 1, y, "left");
            beamsToTry.add(beam2);
        }

        for (int x = 0; x < width; x++) {
            Beam beam1 = new Beam(x, 0, "down");
            beamsToTry.add(beam1);
            Beam beam2 = new Beam(x, height - 1, "up");
            beamsToTry.add(beam2);
        }
    }

}
