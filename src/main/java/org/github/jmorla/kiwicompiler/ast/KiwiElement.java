package org.github.jmorla.kiwicompiler.ast;

import java.util.List;
import java.util.Objects;

public class KiwiElement {

    private final String identifier;

    private final List<Attribute> attributes;

    public KiwiElement(String identifier) {
        this(identifier, null);
    }

    public KiwiElement(String identifier, List<Attribute> attributes) {
        this.identifier = identifier;
        this.attributes = attributes;
    }

    public String identifier() {
        return identifier;
    }

    public List<Attribute> attributes() {
        return attributes;
    }

    public boolean hasAttributes () {
        return attributes != null && attributes.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KiwiElement element = (KiwiElement) o;

        if (!identifier.equals(element.identifier)) return false;
        return Objects.equals(attributes, element.attributes);
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }
}
