package org.github.jmorla.kiwicompiler;

import org.github.jmorla.kiwicompiler.ast.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KiwiParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"this is text", "@", "@impor"})
    public void shouldParseTextSegment(String template) {
        var parser = new KiwiParser(template);
        var tree = parser.parse();


        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertEquals(1, tree.segments().size());
        var text = (Segment.TextSegment) tree.segments().get(0);
        Assertions.assertEquals(template, text.text());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@import('foo')", "@import('foo', 'bar')"})
    public void shouldParseImportSegment(String template) {
        var parser = new KiwiParser(template);
        var tree = parser.parse();

        Assertions.assertFalse(tree.isEmpty());
        Assertions.assertEquals(1, tree.segments().size());
        var directive = (Segment.ImportDirective) tree.segments().get(0);
        Assertions.assertEquals("foo", directive.arg0());
        if (!directive.hasSingleArg()) {
            Assertions.assertEquals("bar", directive.arg1());
        }
    }
}
