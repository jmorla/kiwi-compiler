package org.github.jmorla.kiwicompiler;

import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class KiwiCompiler {

    public KiwiCompiler(Reader source) {
    }

    public void compile(Writer out) {
    }

    public String compile() {
        StringWriter out = new StringWriter();
        compile(out);
        return out.toString();
    }

}
