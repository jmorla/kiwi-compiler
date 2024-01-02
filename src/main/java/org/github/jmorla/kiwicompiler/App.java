package org.github.jmorla.kiwicompiler;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var source = """
                @import('Greeting', './components/Greeting')
                @render(<Greeting message="Hello {{name}}!" value:num="10" />)
                @render(<Greeting message="Hello Strange" clickable:bool="true" />)
                """;

        var compiler = KiwiGenerator.withDefault();
        String output = compiler.generateJs(source);
        String htmlOutput = compiler.generateHtml(source);

        System.out.println(htmlOutput);
        System.out.println(output);

    }
}
