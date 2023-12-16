package AdventOfCode.util;

import java.util.List;

public class MapUtils {

    public static char[][] createMap(List<String> lines, int width, int height) {
        char[][] map = new char[width][height];
        for (int y=0;y<lines.size();y++) {
            String line = lines.get(y);
            for (int x=0;x<line.length();x++) {
                map[x][y] = line.charAt(x);
            }
        }
        return map;
    }
}
