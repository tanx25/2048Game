class Tag {
    public enum Relation {
        SMALLER, LARGER, EQUAL
    }
    private Relation relation;
    private String name;
    private String value;

    Tag(String[] tokens) {
        name = tokens[0];

        switch (tokens[1].charAt(0)) { /* let the relation be smaller or larger or equal according to
                                          the second of tokens of the first of the input line   */
            case '<':
                relation = Relation.SMALLER;
                break;
            case '>':
                relation = Relation.LARGER;
                break;
            case '=':
                relation = Relation.EQUAL;
                break;
            default:
                throw new BadCommandException("Invalid tag: ill-defined bad relation.");
                // if the relation is neither '<''>''=',report an error
        }
        value = tokens[2];
        // value is equal to the third element of tokens
    }

    public Relation getRelation() {
        return relation;

    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
        // the three methods above return the relation ,name and value
    }
}