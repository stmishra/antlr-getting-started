package in.gen.berserker;

import in.gen.berserker.generated.CalcBaseVisitor;
import in.gen.berserker.generated.CalcParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhinavn on 25/12/16.
 */

public class CalculatorVisitorImplementation extends CalcBaseVisitor{
    private Integer result;
    private Map<String, Integer> doneCalculations = new HashMap<>();

    @Override
    public Integer visitMultOrDiv(CalcParser.MultOrDivContext ctx) {
        super.visitMultOrDiv(ctx);
        String string1 = ctx.expr().get(0).getText();
        String string2 = ctx.expr().get(1).getText();
        String symbol = ctx.op.getText();
        result = performCalculation(string1, string2, symbol);
        return result;
    }

    @Override
    public Integer visitPlusOrMinus(CalcParser.PlusOrMinusContext ctx) {
        super.visitPlusOrMinus(ctx);
        String string1 = ctx.expr().get(0).getText();
        String string2 = ctx.expr().get(1).getText();
        String symbol = ctx.op.getText();
        result = performCalculation(string1, string2, symbol);
        return result;
    }

    @Override
    public Integer visitProg(CalcParser.ProgContext ctx) {
        super.visitProg(ctx);
        System.out.println(result);
        return result;
    }

    private Integer performCalculation(String string1, String string2, String symbol) {
        StringBuilder sb = new StringBuilder(string1).append(symbol).append(string2);

        Integer num1, num2;
        if (doneCalculations.containsKey(string1))
            num1 = doneCalculations.get(string1);
        else
            num1 = Integer.parseInt(string1);

        if (doneCalculations.containsKey(string2))
            num2 = doneCalculations.get(string2);
        else
            num2 = Integer.parseInt(string2);

        switch (symbol) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }

        doneCalculations.put(sb.toString(), result);
        return result;
    }
}
