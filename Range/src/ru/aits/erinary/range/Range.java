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

    public Range(Range rangeA) {
        this.from = rangeA.getFrom();
        this.to = rangeA.getTo();
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

    public boolean hasIntersection(Range rangeA) {
        return (this.isInside(rangeA.getFrom()) || this.isInside(rangeA.getTo()) || rangeA.isInside(this.getFrom()) ||
                rangeA.isInside(this.getTo()));
    }

    public boolean isCommonBorder(Range rangeA) {
        return (this.getFrom() == rangeA.getTo() || rangeA.getFrom() == this.getTo());
    }

    public Range getIntersection(Range rangeA) {
        Range[] orderedRanges = Range.orderRanges(this, rangeA);
        Range rangeLeft = orderedRanges[0];
        Range rangeRight = orderedRanges[1];
        if (!rangeLeft.hasIntersection(rangeRight)) {
            return null;
        } else if (rangeLeft.isCommonBorder(rangeRight)) {
            return null;
        } else if (rangeRight.getTo() <= rangeLeft.getTo()) {
            return new Range(rangeRight);
        } else {
            return new Range(rangeLeft.getTo(), rangeRight.getFrom());
        }
    }

    public Range[] uniteRanges(Range rangeA) {
        if (!this.hasIntersection(rangeA)) {
            return Range.orderRanges(new Range(this), new Range(rangeA));
        } else {
            Range toReturn = new Range(Math.min(this.getFrom(), rangeA.getFrom()), Math.max(this.getTo(), rangeA.getTo()));
            return new Range[]{toReturn};
        }

    }

    public Range[] subtractRanges(Range rangeA) {
        if (!this.hasIntersection(rangeA)) {
            return new Range[]{new Range(this)};
        }
//      Когда границы интервалов равны
        if (this.getFrom() == rangeA.getFrom() && this.getTo() == rangeA.getTo()) {
            return new Range[]{};
//      Вычитаемый интервал находится правее
        } else if (this.isInside(rangeA.getFrom()) && !this.isInside(rangeA.getTo())) {
            return new Range[]{new Range(this.getFrom(), rangeA.getFrom())};
//      Вычитаемый интервал находится левее
        } else if (!this.isInside(rangeA.getFrom()) && this.isInside(rangeA.getTo())) {
            return new Range[]{new Range(rangeA.getTo(), this.getTo())};
//      Уменьшаемый интервал лежит внутри вычитаемого
        } else if (!this.isInside(rangeA.getFrom()) && !this.isInside(rangeA.getTo())) {
            return new Range[]{};
//      Вычитаемый интервал внутри уменьшаемого
        } else {
            return new Range[]{new Range(this.getFrom(), rangeA.getFrom()), new Range(rangeA.getTo(), this.getTo())};
        }
    }

    @Override
    public String toString() {
        return String.format("(%.2f; %.2f)", this.from, this.to);
    }
}
