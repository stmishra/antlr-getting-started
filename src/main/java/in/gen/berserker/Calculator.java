package in.gen.berserker;

import in.gen.berserker.generated.CalcLexer;
import in.gen.berserker.generated.CalcParser;
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
            System.out.println("   ");
            System.out.print(">>> ");
            String line = scanner.nextLine().trim();
            if (!line.equals("") && null != line) {
                ANTLRInputStream input = new ANTLRInputStream(line + "\n");
                CalcLexer lexer = new CalcLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                CalcParser parser = new CalcParser(tokens);
                ParseTree tree = parser.prog();
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(new CalculatorListenerImplementation(), tree);
            }
        }

    }

}
