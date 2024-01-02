package org.github.jmorla.kiwicompiler;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var compiler = new KiwiCompiler();
        String output = compiler.compileJs("""
                    <!doctype html>
                    <html lang="en">
                    <head>
                        <title>Kiwi | Sample</title>
                        @import('UserDetailsForm', './components/UserDetailsForm')
                    </head>
                    <body>
                        {{#user}}
                        @render(<UserDetailsForm\s
                                  id:num="{{id}}"\s
                                  title="User {{name}}"\s
                                  validated:bool="true" />)
                        {{/user}}
                    </body>
                    </html>
                """);

        System.out.println(output);

    }
}
