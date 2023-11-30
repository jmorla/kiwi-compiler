package org.github.jmorla.kiwicompiler;

public class ScannerException extends RuntimeException {

    public ScannerException(String message) {
        super(message);
    }

    public ScannerException(String message, CharSequence sequence, int line) {
        super(message + " " + sequence + " at line: " + line);
    }

    public ScannerException(Throwable ex, int line) {
        super("at line" + line, ex);
    }
}
