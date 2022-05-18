class RangeCriterion {
    private long maxValue = Long.MAX_VALUE;
    private long minValue = Long.MIN_VALUE;

    void addCriterion(Tag tag) {
        if (tag.getRelation() == Tag.Relation.LARGER) {
            minValue = Math.max(minValue, Long.parseLong(tag.getValue()));
        }
        if (tag.getRelation() == Tag.Relation.SMALLER) {
            maxValue = Math.min(maxValue, Long.parseLong(tag.getValue()));
        }
        // if the relation is larger,return the max
        // if the relation is smaller,return the min
    }

    boolean isInRange(long value) {
        if (value < maxValue && value > minValue)
            return true;
        return false;
    }
    //check whether the value in is Range
}
