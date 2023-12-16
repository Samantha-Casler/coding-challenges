package AdventOfCode.Year2023.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Beam {
    int x;
    int y;
    String direction;
}
