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
        this.from = rangeA.from;
        this.to = rangeA.to;
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
        if (rangeA.from < rangeB.from) {
            rangeLeft = rangeA;
            rangeRight = rangeB;
        } else {
            rangeLeft = rangeB;
            rangeRight = rangeA;
        }
        return new Range[]{rangeLeft, rangeRight};
    }

    public boolean hasIntersection(Range rangeA) {
        return (rangeA.from <= this.to && rangeA.to >= this.from);
    }

    public boolean equals(Range rangeA) {
        return (this.from == rangeA.from && this.to == rangeA.to);
    }

    public Range getIntersection(Range rangeA) {
        Range[] orderedRanges = Range.orderRanges(this, rangeA);
        Range rangeLeft = orderedRanges[0];
        Range rangeRight = orderedRanges[1];
        if (!rangeLeft.hasIntersection(rangeRight)) {
            return null;
//            пересечение по 1 концу
        } else if (rangeLeft.to == rangeRight.from) {
            return null;
        } else if (rangeRight.to <= rangeLeft.to) {
            return new Range(rangeRight);
        } else {
            return new Range(rangeLeft.to, rangeRight.from);
        }
    }

    public Range[] uniteRanges(Range rangeA) {
        if (!this.hasIntersection(rangeA)) {
            return Range.orderRanges(new Range(this), new Range(rangeA));
        } else {
            Range toReturn = new Range(Math.min(this.from, rangeA.from), Math.max(this.to, rangeA.to));
            return new Range[]{toReturn};
        }

    }

    public Range[] subtractRanges(Range rangeA) {
        if (!this.hasIntersection(rangeA)) {
            return new Range[]{new Range(this)};
        }
//      Когда границы интервалов равны
        if (this.equals(rangeA)) {
            return new Range[]{};
//      Вычитаемый интервал находится правее
        } else if (this.isInside(rangeA.from) && !this.isInside(rangeA.to)) {
            return (this.from != rangeA.from) ? new Range[]{new Range(this.from, rangeA.from)} : new Range[]{};
//      Вычитаемый интервал находится левее
        } else if (!this.isInside(rangeA.from) && this.isInside(rangeA.to)) {
            return (rangeA.to != this.to) ? new Range[]{new Range(rangeA.to, this.to)} : new Range[]{};
//      Уменьшаемый интервал лежит внутри вычитаемого
        } else if (!this.isInside(rangeA.from) && !this.isInside(rangeA.to)) {
            return new Range[]{};
//      Вычитаемый интервал внутри уменьшаемого
        } else {
            if (this.from == rangeA.from) {
                return new Range[] {new Range(rangeA.to, this.to)};
            } else if (this.to == rangeA.to) {
                return new Range[] {new Range(this.from, rangeA.from)};
            } else {
                return new Range[]{new Range(this.from, rangeA.from), new Range(rangeA.to, this.to)};
            }
        }
    }

    @Override
    public String toString() {
        return String.format("(%.2f; %.2f)", this.from, this.to);
    }
}
