package in.gen.berserker;

import in.gen.berserker.generated.CalcLexer;
import in.gen.berserker.generated.CalcParser;
import in.gen.berserker.generated.CalcVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by stm on 16/12/16.
 * A very basic test to check if we are computing correctly
 * takes input from std in
 * Ctrl -d to exit. Acts like a REPL of a calculator.
 */
public class Calculator {

    public static void main(String... args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">>> "); //Prompt
            String line = scanner.nextLine();
            if (null != line && !line.trim().equals("")) {
                if (!line.endsWith("\n")) {
                    line += "\n";   //This takes care of annoying antlr log for missing NEWLINE at 'EOF'.. I could
                                    //Probably fix the grammar, but this should do for now.
                }
                ANTLRInputStream input = new ANTLRInputStream(line);
                CalcLexer lexer = new CalcLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                CalcParser parser = new CalcParser(tokens);
                //runListener(parser);
                runVisitor(parser);
            }
        }

    }

    private static void runListener(CalcParser parser) {
        ParseTree tree = parser.prog();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new CalculatorListenerImplementation(), tree);
    }

    private static void runVisitor(CalcParser parser) {
        CalculatorVisitorImplementation calcImpl = new CalculatorVisitorImplementation();
        calcImpl.visit(parser.prog());
    }
}
