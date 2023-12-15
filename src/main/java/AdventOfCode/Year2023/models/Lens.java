package AdventOfCode.Year2023.models;

public class Lens {
        String label;
        int focalLength;

        public Lens(String label, int focalLength) {
                this.label = label;
                this.focalLength = focalLength;
        }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }

    public String label() {
        return label;
    }

    public int focalLength() {
        return focalLength;
    }


    public String toString() {
        return label + " " + focalLength;
    }
}
