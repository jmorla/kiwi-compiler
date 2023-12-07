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
                    @import('./components/UserDetailsForm', 'UserDetailsForm')
                    @import('./components/UserGallery', 'UserGallery')
                <head>
                <body>
                   {{#user}}
                   @render(<UserDetailsForm userId="{{id}}" />)
                   {{/user}}
                </body>
                </html>
                """);
        SyntaxTree tree = parser.parse();
        var mapper = new JsonTreeMapper();
        tree.accept(mapper);
        System.out.println(mapper.getJsonString());
    }
}
