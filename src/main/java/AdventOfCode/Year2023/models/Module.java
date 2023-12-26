package AdventOfCode.Year2023.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class Module {
    public enum ModuleType {
        BRODCAST,
        FLIP_FLOP,
        CONJUNCTION
    }

    private ModuleType type;
    private HashMap<String, Boolean> conjunctionMemory;
    private boolean flipFlopStatus;
    private List<String> destinations;
    private String name;
}
