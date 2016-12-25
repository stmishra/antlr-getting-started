package in.gen.berserker;

import org.antlr.runtime.RecognitionException;
import org.antlr.v4.Tool;
import org.antlr.v4.tool.ErrorType;

import java.io.IOException;

/**
 * Created by stm on 16/12/16.
 *
 * This class generates the antlr classes. Helps in quick prototyping when you are
 * finalizing a grammar.
 * Run as main, or as a gradle task antlr
 */
public class AntlrRunner {

    public static void main(String... args) throws IOException, RecognitionException {
        AntlrRunner runner = new AntlrRunner();
        runner.run(System.getProperty("user.dir") + "/src/main/java/in/gen/berserker/generated/Calc.g4", "-visitor");
    }

    public void run(String... args) throws IOException, RecognitionException {
        //Copied from antlr's Tool.java/main
        Tool antlr = new Tool(args);
        if (args.length == 0) {
            antlr.help();
            antlr.exit(0);
        }

        try {
            antlr.processGrammarsOnCommandLine();
        } finally {
            if (antlr.log) {
                try {
                    String logname = antlr.logMgr.save();
                    System.out.println("wrote " + logname);
                } catch (IOException ioe) {
                    antlr.errMgr.toolError(ErrorType.INTERNAL_ERROR, ioe);
                }
            }
        }
        if (antlr.errMgr.getNumErrors() > 0) {
            antlr.exit(1);
        }
    }
}
