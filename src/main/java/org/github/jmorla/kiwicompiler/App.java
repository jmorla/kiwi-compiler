package org.github.jmorla.kiwicompiler;

import org.github.jmorla.kiwicompiler.ast.SyntaxTree;
import org.github.jmorla.kiwicompiler.visitor.JsonTreeMapper;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var parser = new KiwiParser("""
                <html>
                <head>
                    @import('UserDetailsForm')
                    @import('./components/EmptyState', 'EmptyState')
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
        var tree = parser.parse();
        var json = toJson(tree);

        System.out.println(json);

    }

    private static String toJson(SyntaxTree tree) {
        var mapper = new JsonTreeMapper();
        tree.accept(mapper);
        return mapper.getJsonString();
    }
}
