package ru.aits.erinary.range;

@SuppressWarnings("WeakerAccess")
public class Range {
    private double from;
    private double to;

    public Range(double sideA, double sideB) {
        if (sideA > sideB) {
            this.from = sideB;
            this.to = sideA;
        } else {
            this.from = sideA;
            this.to = sideB;
        }
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double calculateLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return (number >= from && number <= to);
    }

    public Range getIntersection(Range rangeA, Range rangeB) {
        Range rangeLeft;
        Range rangeRight;
        if (rangeA.getFrom() < rangeB.getFrom()) {
            rangeLeft = rangeA;
            rangeRight = rangeB;
        } else {
            rangeLeft = rangeB;
            rangeRight = rangeA;
        }
        if (rangeRight.getFrom() > rangeLeft.getTo()) {
            return null;
        } else if (rangeRight.getTo() <= rangeLeft.getTo()) {
            return new Range(rangeRight.getFrom(), rangeRight.getTo());
        } else {
            return new Range(rangeLeft.getTo(), rangeRight.getFrom());
        }
    }
}
