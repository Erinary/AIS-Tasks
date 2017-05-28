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

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return (number >= from && number <= to);
    }

    private static Range[] orderRanges(Range rangeA, Range rangeB) {
        Range rangeLeft;
        Range rangeRight;
        if (rangeA.getFrom() < rangeB.getFrom()) {
            rangeLeft = rangeA;
            rangeRight = rangeB;
        } else {
            rangeLeft = rangeB;
            rangeRight = rangeA;
        }
        return new Range[]{rangeLeft, rangeRight};
    }

    public Range getIntersection(Range rangeA) {
        Range[] orderedRanges = Range.orderRanges(this, rangeA);
        Range rangeLeft = orderedRanges[0];
        Range rangeRight = orderedRanges[1];
        if (rangeRight.getFrom() > rangeLeft.getTo()) {
            return null;
        } else if (rangeRight.getTo() <= rangeLeft.getTo()) {
            return new Range(rangeRight.getFrom(), rangeRight.getTo());
        } else {
            return new Range(rangeLeft.getTo(), rangeRight.getFrom());
        }
    }

    public Range[] uniteRanges(Range rangeA) {
        Range intersection = this.getIntersection(rangeA);
        if (intersection == null) {
            return Range.orderRanges(this, rangeA);
        } else {
            Range toReturn = new Range(Math.min(this.getFrom(), rangeA.getFrom()), Math.max(this.getTo(), rangeA.getTo()));
            return new Range[]{toReturn};
        }

    }

    public Range[] subtractRanges(Range rangeA) {
        Range intersection = this.getIntersection(rangeA);
        if (intersection == null) {
            return new Range[]{this};
        }
        if (this.isInside(rangeA.getFrom()) && !this.isInside(rangeA.getTo())) {
            return new Range[]{new Range(this.getFrom(), rangeA.getFrom())};
        } else if (!this.isInside(rangeA.getFrom()) && this.isInside(rangeA.getTo())) {
            return new Range[]{new Range(rangeA.getTo(), this.getTo())};
        } else if (!this.isInside(rangeA.getFrom()) && !this.isInside(rangeA.getTo())) {
            return new Range[]{};
        } else {
            return new Range[]{new Range(this.getFrom(), rangeA.getFrom()), new Range(rangeA.getTo(), this.getTo())};
        }
    }

    @Override
    public String toString() {
        return String.format("(%.2f; %.2f)", this.from, this.to);
    }
}
