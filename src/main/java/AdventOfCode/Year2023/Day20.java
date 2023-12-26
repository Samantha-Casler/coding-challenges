package AdventOfCode.Year2023;

import java.util.*;
import java.util.concurrent.TimeUnit;

import AdventOfCode.Year2023.models.HandleModule;
import AdventOfCode.Year2023.models.Module;

public class Day20 {

    static public HashMap<String, Module> modules = new HashMap<>();
    static public HashMap<String, Module> modules2 = new HashMap<>();
    static public List<HandleModule> handleModules = new ArrayList<>();
    static int countHigh = 0;
    static int countLow = 0;
    static long minOnPress = 0;
    static boolean isOn = false;

    public static void main(String[] args) {

        // Part 1
        long startTime = System.nanoTime();
        modules.put("qx", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("gz")),"qx"));
        modules.put("tr", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("rm")), "tr"));
        modules.put("qr", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("kx", "jm")), "qr"));
        modules.put("gj", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("tx", "rj")), "gj"));
        modules.put("lc", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("hr")), "lc"));
        HashMap<String, Boolean> conjunctionMemoryKx = new HashMap<>();
        conjunctionMemoryKx.put("qr", false);
        conjunctionMemoryKx.put("jz", false);
        conjunctionMemoryKx.put("sk", false);
        conjunctionMemoryKx.put("zs", false);
        conjunctionMemoryKx.put("pt", false);
        conjunctionMemoryKx.put("jm", false);
        conjunctionMemoryKx.put("vp", false);
        conjunctionMemoryKx.put("lx", false);
        conjunctionMemoryKx.put("cb", false);
        modules.put("kx", new Module(Module.ModuleType.CONJUNCTION, conjunctionMemoryKx, false, new ArrayList<>(List.of("zs", "br", "jd", "bj", "vg")), "kx"));
        HashMap<String, Boolean> conjunctionMemoryKd = new HashMap<>();
        conjunctionMemoryKd.put("tq", false);
        modules.put("kd", new Module(Module.ModuleType.CONJUNCTION, conjunctionMemoryKd, false, new ArrayList<>(List.of("rg")), "kd"));
        modules.put("rm", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("pf", "ml")), "rm"));
        modules.put("tg", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("tq", "cp")), "tg"));
        modules.put("cp", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("tp", "tq")), "cp"));
        modules.put("sx", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("qc", "pf")), "sx"));
        HashMap<String, Boolean> conjunctionMemoryZf = new HashMap<>();
        conjunctionMemoryZf.put("pf", false);
        modules.put("zf", new Module(Module.ModuleType.CONJUNCTION, conjunctionMemoryZf, false, new ArrayList<>(List.of("rg")), "zf"));
        modules.put("jz", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("kx", "pt")), "jz"));
        modules.put("dt", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("tg", "tq")), "dt"));
        modules.put("xv", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("rj")), "xv"));
        modules.put("vz", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("rj", "xv")), "vz"));
        modules.put("vn", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("vv", "tq")), "vn"));
        modules.put("hl", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("xt")), "hl"));
        modules.put("qc", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("pf")), "qc"));
        modules.put("br", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("jz")), "br"));
        modules.put("broadcaster", new Module(Module.ModuleType.BRODCAST, new HashMap<>(), false, new ArrayList<>(List.of("sr", "cg", "dt", "zs")), "broadcaster"));
        modules.put("sk", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("kx", "qr")), "sk"));
        modules.put("xq", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("dj")), "xq"));
        HashMap<String, Boolean> conjunctionMemoryVg = new HashMap<>();
        conjunctionMemoryVg.put("kx", false);
        modules.put("vg", new Module(Module.ModuleType.CONJUNCTION, conjunctionMemoryVg, false, new ArrayList<>(List.of("rg")), "vg"));
        modules.put("zd", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("pf", "lc")), "zd"));
        modules.put("hr", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("pm")), "hr"));
        modules.put("cg", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("qx", "rj")), "cg"));
        modules.put("tx", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("vz", "rj")), "tx"));
        modules.put("qf", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("sb")), "qf"));
        HashMap<String, Boolean> conjunctionMemoryRj = new HashMap<>();
        conjunctionMemoryRj.put("gj", false);
        conjunctionMemoryRj.put("xv", false);
        conjunctionMemoryRj.put("vz", false);
        conjunctionMemoryRj.put("cg", false);
        conjunctionMemoryRj.put("tx", false);
        conjunctionMemoryRj.put("xt", false);
        conjunctionMemoryRj.put("ns", false);
        modules.put("rj", new Module(Module.ModuleType.CONJUNCTION, conjunctionMemoryRj, false, new ArrayList<>(List.of("gs", "sb", "qx", "qf", "gz", "hl", "cg")), "rj"));
        modules.put("rb", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("lz")), "rb"));
        modules.put("ml", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("pf", "xq")), "ml"));
        modules.put("bj", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("jd")), "bj"));
        HashMap<String, Boolean> conjunctionMemoryGs = new HashMap<>();
        conjunctionMemoryGs.put("rj", false);
        modules.put("gs", new Module(Module.ModuleType.CONJUNCTION, conjunctionMemoryGs, false, new ArrayList<>(List.of("rg")), "gs"));
        modules.put("sr", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("pf", "zd")), "sr"));
        modules.put("sb", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("gj")), "sb"));
        HashMap<String, Boolean> conjunctionMemoryTq = new HashMap<>();
        conjunctionMemoryTq.put("tg", false);
        conjunctionMemoryTq.put("cp", false);
        conjunctionMemoryTq.put("dt", false);
        conjunctionMemoryTq.put("vn", false);
        conjunctionMemoryTq.put("vv", false);
        conjunctionMemoryTq.put("lz", false);
        conjunctionMemoryTq.put("qn", false);
        conjunctionMemoryTq.put("gp", false);
        conjunctionMemoryTq.put("dm", false);
        modules.put("tq", new Module(Module.ModuleType.CONJUNCTION, conjunctionMemoryTq, false, new ArrayList<>(List.of("tp", "rb", "dt", "kd", "zt")), "tq"));
        modules.put("tp", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("dm")), "tp"));
        modules.put("vv", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("tq")), "vv"));
        modules.put("pm", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("tr")), "pm"));
        modules.put("dj", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("pf", "sx")), "dj"));
        modules.put("lz", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("vn", "tq")), "lz"));
        modules.put("jd", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("lx")), "jd"));
        modules.put("qn", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("tq", "rb")), "qn"));
        modules.put("zs", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("kx", "bj")), "zs"));
        HashMap<String, Boolean> conjunctionMemoryRg = new HashMap<>();
        conjunctionMemoryRg.put("kd", false);
        conjunctionMemoryRg.put("zf", false);
        conjunctionMemoryRg.put("vg", false);
        conjunctionMemoryRg.put("gs", false);
        modules.put("rg", new Module(Module.ModuleType.CONJUNCTION, conjunctionMemoryRg, false, new ArrayList<>(List.of("rx")), "rg"));
        modules.put("pt", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("cb", "kx")), "pt"));
        modules.put("xt", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("ns", "rj")), "xt"));
        modules.put("gz", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("hl")), "gz"));
        modules.put("zt", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("qn")), "zt"));
        modules.put("jm", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("kx")), "jm"));
        modules.put("vp", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("br", "kx")), "vp"));
        HashMap<String, Boolean> conjunctionMemoryPf = new HashMap<>();
        conjunctionMemoryPf.put("rm", false);
        conjunctionMemoryPf.put("sx", false);
        conjunctionMemoryPf.put("qc", false);
        conjunctionMemoryPf.put("zd", false);
        conjunctionMemoryPf.put("ml", false);
        conjunctionMemoryPf.put("sr", false);
        conjunctionMemoryPf.put("dj", false);
        modules.put("pf", new Module(Module.ModuleType.CONJUNCTION, conjunctionMemoryPf, false, new ArrayList<>(List.of("tr", "hr", "zf", "sr", "xq", "pm", "lc")), "pf"));
        modules.put("gp", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("tq", "zt")), "gp"));
        modules.put("dm", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("tq", "gp")), "dm"));
        modules.put("lx", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("kx", "vp")), "lx"));
        modules.put("ns", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("qf", "rj")), "ns"));
        modules.put("cb", new Module(Module.ModuleType.FLIP_FLOP, new HashMap<>(), false, new ArrayList<>(List.of("sk", "kx")), "cb"));

        modules2.putAll(modules);
        for (int i = 0; i < 1000; i++) {
            handleModules.add(new HandleModule(modules.get("broadcaster"), false, "broadcaster"));
            countLow++;
            handleButtonPush();
        }

        int total = countHigh * countLow;
        System.out.println("Result part 1 : " + total + " in " + TimeUnit.NANOSECONDS.toMillis((System.nanoTime() - startTime)) + "ms");

//        // Part 2
//        long startTime2 = System.nanoTime();
//        while (!isOn) {
//            minOnPress++;
//            handleModules.add(new HandleModule(modules2.get("broadcaster"), false, "broadcaster"));
//            handleButtonPush2();
//        }
//        System.out.println("Result part 2 : " + minOnPress + " in " + TimeUnit.NANOSECONDS.toMillis((System.nanoTime() - startTime2)) + "ms");
    }

    private static void handleButtonPush() {
        while (true) {
            if (handleModules.isEmpty()) {
                break;
            }
            Module module = handleModules.getFirst().getModule();
            if (module.getType() == Module.ModuleType.BRODCAST) {
                for (String destination : module.getDestinations()) {
                    if (modules.get(destination) != null) {
                        handleModules.add(new HandleModule(modules.get(destination), false, module.getName()));
                    }
                    countLow++;
                }
                handleModules.removeFirst();
            } else if (module.getType() == Module.ModuleType.FLIP_FLOP && !handleModules.getFirst().isPulse()) {
                module.setFlipFlopStatus(!module.isFlipFlopStatus());
                if (module.isFlipFlopStatus()) {
                    for (String destination : module.getDestinations()) {
                        if (modules.get(destination) != null) {
                            handleModules.add(new HandleModule(modules.get(destination), module.isFlipFlopStatus(), module.getName()));
                        }
                        countHigh++;
                    }
                } else {
                    for (String destination : module.getDestinations()) {
                        if (modules.get(destination) != null) {
                            handleModules.add(new HandleModule(modules.get(destination), module.isFlipFlopStatus(), module.getName()));
                        }
                        countLow++;
                    }
                }
                handleModules.removeFirst();
            } else if (module.getType() == Module.ModuleType.CONJUNCTION) {
                module.getConjunctionMemory().put(handleModules.getFirst().getSentFrom(), handleModules.getFirst().isPulse());
                boolean result = true;
                for (Map.Entry<String, Boolean> entry : module.getConjunctionMemory().entrySet()) {
                    if (!entry.getValue()) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    for (String destination : module.getDestinations()) {
                        if (modules.get(destination) != null) {
                            handleModules.add(new HandleModule(modules.get(destination), false, module.getName()));
                        }

                        countLow++;
                    }
                } else {
                    for (String destination : module.getDestinations()) {
                        if (modules.get(destination) != null) {
                            handleModules.add(new HandleModule(modules.get(destination), true, module.getName()));
                        }
                        countHigh++;
                    }
                }
                handleModules.removeFirst();
            } else {
                handleModules.removeFirst();
            }
        }
    }

//    private static void handleButtonPush2() {
//        System.out.println(minOnPress);
//        while (true) {
//            if (handleModules.isEmpty()) {
//                break;
//            }
//            Module module = handleModules.getFirst().getModule();
//            if (module.getType() == Module.ModuleType.BRODCAST) {
//                for (String destination : module.getDestinations()) {
//                    if (modules2.get(destination) != null) {
//                        handleModules.add(new HandleModule(modules2.get(destination), false, module.getName()));
//                    }
//                    countLow++;
//                }
//                handleModules.removeFirst();
//            } else if (module.getType() == Module.ModuleType.FLIP_FLOP && !handleModules.getFirst().isPulse()) {
//                module.setFlipFlopStatus(!module.isFlipFlopStatus());
//                if (module.isFlipFlopStatus()) {
//                    for (String destination : module.getDestinations()) {
//                        if (modules2.get(destination) != null) {
//                            handleModules.add(new HandleModule(modules2.get(destination), module.isFlipFlopStatus(), module.getName()));
//                        }
//                        countHigh++;
//                    }
//                } else {
//                    for (String destination : module.getDestinations()) {
//                        if (modules2.get(destination) != null) {
//                            handleModules.add(new HandleModule(modules2.get(destination), module.isFlipFlopStatus(), module.getName()));
//                        }
//                        countLow++;
//                    }
//                }
//                handleModules.removeFirst();
//            } else if (module.getType() == Module.ModuleType.CONJUNCTION) {
//                module.getConjunctionMemory().put(handleModules.getFirst().getSentFrom(), handleModules.getFirst().isPulse());
//                boolean result = true;
//                for (Map.Entry<String, Boolean> entry : module.getConjunctionMemory().entrySet()) {
//                    if (!entry.getValue()) {
//                        result = false;
//                        break;
//                    }
//                }
//                if (result) {
//                    for (String destination : module.getDestinations()) {
//                        if (modules2.get(destination) != null) {
//                            handleModules.add(new HandleModule(modules2.get(destination), false, module.getName()));
//                            System.out.println("Conjunction pulse false(low)" + " to " + destination);
//                        }
//
//                        if (destination.contains("rx")) {
//                            System.out.println("RX is on");
//                            isOn = true;
//                        }
//
//                        countLow++;
//                    }
//                } else {
//                    for (String destination : module.getDestinations()) {
//                        if (modules2.get(destination) != null) {
//                            handleModules.add(new HandleModule(modules2.get(destination), true, module.getName()));
//                        }
//                        countHigh++;
//                    }
//                }
//                handleModules.removeFirst();
//            } else {
//                handleModules.removeFirst();
//            }
//        }
//    }

}

