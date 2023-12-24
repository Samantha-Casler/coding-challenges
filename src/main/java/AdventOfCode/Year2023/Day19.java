package AdventOfCode.Year2023;

import AdventOfCode.Year2023.models.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {

    static Pattern pattern = Pattern.compile("^\\d+$");

    static HashMap<String, Workflow> workflowHashmap = new HashMap<>();
    static List<PartRating> partRatings = new ArrayList<>();
    static List<PartRating> acceptedPartRatings = new ArrayList<>();

    static int totalAccepted = 0;
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("Day19Input.txt"));

        // Part 1
        long startTime = System.nanoTime();

        for (int i = 0; i < lines.size(); i++) {
            while(!lines.get(i).startsWith("{")) {
                String[] parts = lines.get(i).split("\\{");
                if (parts.length > 1) {
                    List<Rule> rules = new ArrayList<>();
                    String[] ruleParts = parts[1].split(",");
                    for (int j = 0; j < ruleParts.length - 1; j++) {
                        String[] ruleParts2 = ruleParts[j].split(":");
                        String rule = ruleParts2[0];
                        String number = rule.substring(2);

                        if (rule.charAt(1) == '<')
                            rules.add(new Rule(rule.charAt(0), true, Integer.parseInt(number), ruleParts2[1]));
                        else
                            rules.add(new Rule(rule.charAt(0), false, Integer.parseInt(number), ruleParts2[1]));

                    }
                    String lastRule = ruleParts[ruleParts.length - 1].substring(0, ruleParts[ruleParts.length - 1].length() - 1);
                    workflowHashmap.put(parts[0], new Workflow(rules, lastRule));
                }
                i++;
            }

            String[] values = lines.get(i).split(",");
            PartRating partRating = getPartRating(values);
            partRatings.add(partRating);


        }
        for (PartRating part : partRatings) {
            evaluatePart(part);
            System.out.println("Part evaluated");
        }

        for(PartRating partRating : acceptedPartRatings) {
            System.out.println(partRating);
            int partTotal = partRating.getX() + partRating.getM() + partRating.getA() + partRating.getS();
            totalAccepted += partTotal;
        }

        long PartEendTime = TimeUnit.NANOSECONDS.toMillis((System.nanoTime()-startTime));
        System.out.println("Result part 1 : " + totalAccepted + " in " + PartEendTime +"ms");

        // Part 2
    }

    private static PartRating getPartRating(String[] values) {
        PartRating partRating = new PartRating();
        for (String value : values) {
            StringBuilder number = new StringBuilder();
            for (int k = 0; k < value.length(); k++) {
                Matcher matcher = pattern.matcher(value.charAt(k) + "");
                if (matcher.matches()) {
                    number.append(value.charAt(k));
                }
            }
            if (value.contains("x")) {
                partRating.setX(Integer.parseInt(number.toString()));
            } else if (value.contains("m")) {
                partRating.setM(Integer.parseInt(number.toString()));
            } else if (value.contains("a")) {
                partRating.setA(Integer.parseInt(number.toString()));
            } else if (value.contains("s")) {
                partRating.setS(Integer.parseInt(number.toString()));
            }
        }
        return partRating;
    }

    private static void evaluatePart(PartRating part) {
        Workflow workflow = workflowHashmap.get("in");
        System.out.println(part);
        do {
            System.out.println(workflow);
            boolean allRulesFalse = true;
            for (Rule rule : workflow.getRules()) {
                if (!allRulesFalse) {
                    break;
                }
                char partChar = rule.getCategory();

                if (partChar == 'x') {
                    if (rule.isLessThan()) {
                        if (part.getX() < rule.getCompareValue()) {
                            if (!Objects.equals(rule.getIsTrueWorkflow(), "A") && !Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                System.out.println("At x less than " + rule.getIsTrueWorkflow());
                                workflow = workflowHashmap.get(rule.getIsTrueWorkflow());
                            } else {
                                if (Objects.equals(rule.getIsTrueWorkflow(), "A")) {
                                    acceptedPartRatings.add(part);
                                    workflow = null;
                                } else if (Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                    workflow = null;
                                }
                            }
                            allRulesFalse = false;
                        }
                    } else {
                        if (part.getX() > rule.getCompareValue()) {
                            if (!Objects.equals(rule.getIsTrueWorkflow(), "A") && !Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                System.out.println("At x greater than " + rule.getIsTrueWorkflow());
                                workflow = workflowHashmap.get(rule.getIsTrueWorkflow());
                            } else {
                                if (Objects.equals(rule.getIsTrueWorkflow(), "A")) {
                                    acceptedPartRatings.add(part);
                                    workflow = null;
                                } else if (Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                    workflow = null;
                                }
                            }
                            allRulesFalse = false;
                        }
                    }
                } else if (partChar == 'm') {
                    if (rule.isLessThan()) {
                        if (part.getM() < rule.getCompareValue()) {
                            if (!Objects.equals(rule.getIsTrueWorkflow(), "A") && !Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                System.out.println("At m less than " + rule.getIsTrueWorkflow());
                                workflow = workflowHashmap.get(rule.getIsTrueWorkflow());
                            } else {
                                if (Objects.equals(rule.getIsTrueWorkflow(), "A")) {
                                    acceptedPartRatings.add(part);
                                    workflow = null;
                                } else if (Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                    workflow = null;
                                }
                            }
                            allRulesFalse = false;
                        }
                    } else {
                        if (part.getM() > rule.getCompareValue()) {
                            if (!Objects.equals(rule.getIsTrueWorkflow(), "A") && !Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                System.out.println("At m greater than " + rule.getIsTrueWorkflow());
                                workflow = workflowHashmap.get(rule.getIsTrueWorkflow());
                            } else {
                                if (Objects.equals(rule.getIsTrueWorkflow(), "A")) {
                                    acceptedPartRatings.add(part);
                                    workflow = null;
                                } else if (Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                    workflow = null;
                                }
                            }
                            allRulesFalse = false;
                        }
                    }
                } else if (partChar == 'a') {
                    if (rule.isLessThan()) {
                        if (part.getA() < rule.getCompareValue()) {
                            if (!Objects.equals(rule.getIsTrueWorkflow(), "A") && !Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                System.out.println("At a less than " + rule.getIsTrueWorkflow());
                                workflow = workflowHashmap.get(rule.getIsTrueWorkflow());
                            } else {
                                if (Objects.equals(rule.getIsTrueWorkflow(), "A")) {
                                    acceptedPartRatings.add(part);
                                    workflow = null;
                                } else if (Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                    workflow = null;
                                }
                            }
                            allRulesFalse = false;
                        }
                    } else {
                        if (part.getA() > rule.getCompareValue()) {
                            if (!Objects.equals(rule.getIsTrueWorkflow(), "A") && !Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                System.out.println("At a greater than " + rule.getIsTrueWorkflow());
                                workflow = workflowHashmap.get(rule.getIsTrueWorkflow());
                            } else {
                                if (Objects.equals(rule.getIsTrueWorkflow(), "A")) {
                                    acceptedPartRatings.add(part);
                                    workflow = null;
                                } else if (Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                    workflow = null;
                                }
                            }
                            allRulesFalse = false;
                        }
                    }
                } else if (partChar == 's') {
                    if (rule.isLessThan()) {
                        if (part.getS() < rule.getCompareValue()) {
                            if (!Objects.equals(rule.getIsTrueWorkflow(), "A") && !Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                System.out.println("At s less than " + rule.getIsTrueWorkflow());
                                workflow = workflowHashmap.get(rule.getIsTrueWorkflow());
                            } else {
                                if (Objects.equals(rule.getIsTrueWorkflow(), "A")) {
                                    acceptedPartRatings.add(part);
                                    workflow = null;
                                } else if (Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                    workflow = null;
                                }
                            }
                            allRulesFalse = false;
                        }
                    } else {
                        if (part.getS() > rule.getCompareValue()) {
                            if (!Objects.equals(rule.getIsTrueWorkflow(), "A") && !Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                System.out.println("At s greater than " + rule.getIsTrueWorkflow());
                                workflow = workflowHashmap.get(rule.getIsTrueWorkflow());
                            } else {
                                if (Objects.equals(rule.getIsTrueWorkflow(), "A")) {
                                    acceptedPartRatings.add(part);
                                    workflow = null;
                                } else if (Objects.equals(rule.getIsTrueWorkflow(), "R")) {
                                    workflow = null;
                                }
                            }
                            allRulesFalse = false;
                        }
                    }
                }
            }
            if (allRulesFalse) {
                System.out.println("At all rules false " + workflow.getIsFalseWorkflow());
                if (Objects.equals(workflow.getIsFalseWorkflow(), "A")) {
                    acceptedPartRatings.add(part);
                    workflow = null;
                } else if (Objects.equals(workflow.getIsFalseWorkflow(), "R")) {
                    workflow = null;
                } else {
                    workflow = workflowHashmap.get(workflow.getIsFalseWorkflow());
                }
            }
        } while (workflow != null);
    }

}
