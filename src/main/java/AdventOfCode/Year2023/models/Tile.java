package AdventOfCode.Year2023.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Tile {
    int x;
    int y;
    @Setter
    List<String> directions;
}
