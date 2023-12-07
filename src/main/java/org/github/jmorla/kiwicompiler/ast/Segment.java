package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.Visitor;
import org.github.jmorla.kiwicompiler.visitor.VoidVisitor;

import java.util.Objects;

public sealed abstract class Segment implements Visitable
        permits Segment.TextSegment, Segment.ImportDirective, Segment.RenderDirective {

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void accept(VoidVisitor visitor) {
        visitor.visit(this);
    }

    public static final class TextSegment extends Segment {

        private final String text;

        public TextSegment(String text) {
            this.text = text;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visit(this);
        }

        @Override
        public void accept(VoidVisitor visitor) {
            visitor.visit(this);
        }

        public String text() {
            return text;
        }
    }

    public static final class ImportDirective extends Segment {

        private final String arg0;
        private final String comma;
        private final String arg1;

        public ImportDirective(String arg0) {
            this(arg0, null, null);
        }

        public ImportDirective(String arg0, String comma, String arg1) {
            this.arg0 = arg0;
            this.arg1 = arg1;
            this.comma = comma;
        }

        public boolean hasSingleArg() {
            return Objects.isNull(comma);
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visit(this);
        }

        @Override
        public void accept(VoidVisitor visitor) {
            visitor.visit(this);
        }

        public String arg0() {
            return arg0;
        }

        public String comma() {
            return comma;
        }

        public String arg1() {
            return arg1;
        }

    }

    public static final class RenderDirective extends Segment {
        private final KiwiElement element;

        public RenderDirective(KiwiElement element) {
            this.element = element;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visit(this);
        }

        @Override
        public void accept(VoidVisitor visitor) {
            visitor.visit(this);
        }

        public KiwiElement element() {
            return element;
        }
    }
}
