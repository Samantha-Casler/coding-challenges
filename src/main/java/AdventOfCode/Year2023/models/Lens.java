package AdventOfCode.Year2023.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Lens {
        String label;
        @Setter
        int focalLength;

}
