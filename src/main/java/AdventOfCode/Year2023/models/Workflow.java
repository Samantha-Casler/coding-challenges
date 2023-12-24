package AdventOfCode.Year2023.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
public class Workflow {
    List<Rule> rules;
    String isFalseWorkflow;
}
