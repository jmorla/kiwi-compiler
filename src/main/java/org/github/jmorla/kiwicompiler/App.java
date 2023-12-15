package org.github.jmorla.kiwicompiler;

import org.github.jmorla.kiwicompiler.visitor.TestVisitor;

import java.io.StringReader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var reader = new StringReader("""
                <html>
                <head>
                    @import('UserDetailsForm')
                    @import('EmptyState', './components/EmptyState')
                <head>
                <body>
                   {{#user}}
                   @render(<UserDetailsForm id:num="{{id}}" validated:bool="true" title="User details" />)
                   {{/user}}
                   {{^user}}
                   @render(<EmptyState message="{{message}}" size:num="5" showIcon:bool="true" />)
                   {{/user}}
                </body>
                </html>
                """);

        var parser = new KiwiParser(reader);
        var tree = parser.parse();
        tree.accept(new TestVisitor());
    }
}
