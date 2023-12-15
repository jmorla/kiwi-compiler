package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.VoidVisitor;

public sealed abstract class Segment implements Visitable
        permits Segment.TextSegment, Segment.ImportDirective, Segment.RenderDirective {

    public static final class TextSegment extends Segment {
        private final String text;

        public TextSegment(String text) {
            this.text = text;
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

        private boolean singleArg;
        private final String arg0;
        private final String arg1;

        public ImportDirective(String arg0) {
            this(arg0, null);
            singleArg = true;
        }

        public ImportDirective(String arg0, String arg1) {
            this.arg0 = arg0;
            this.arg1 = arg1;
            this.singleArg = false;
        }

        public boolean hasSingleArg() {
            return singleArg;
        }

        @Override
        public void accept(VoidVisitor visitor) {
            visitor.visit(this);
        }

        public String arg0() {
            return arg0;
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
        public void accept(VoidVisitor visitor) {
            visitor.visit(this);
            System.out.println("Heey");
            visitor.visit(element);
        }

        public KiwiElement element() {
            return element;
        }
    }
}
