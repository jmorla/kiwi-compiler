package org.github.jmorla.kiwicompiler.ast;

public record Attribute(String identifier, AttributeType type, String value) {

    public enum AttributeType {
        NUMERIC, STRING, BOOLEAN;

        public static AttributeType of(String o) {
            return switch (o) {
                case "num" -> NUMERIC;
                case "string" -> STRING;
                case "bool" -> BOOLEAN;
                default -> throw new IllegalArgumentException("invalid type");
            };
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (!identifier.equals(attribute.identifier)) return false;
        return type == attribute.type;
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}

