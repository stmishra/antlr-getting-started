package in.gen.berserker;

import in.gen.berserker.generated.CalcBaseVisitor;
import in.gen.berserker.generated.CalcParser;

import java.util.Stack;

/**
 * Created by abhinavn on 25/12/16.
 */

public class CalculatorVisitorImplementation extends CalcBaseVisitor{
    private Integer result;
    private Stack<Integer> stack = new Stack<>();

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
        try {
            num1 = Integer.parseInt(string1);
        } catch (NumberFormatException e) {
            num1 = stack.pop();
        }

        try {
            num2 = Integer.parseInt(string2);
        } catch (NumberFormatException e) {
            num2 = stack.pop();
        }

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

        stack.push(result);
        return result;
    }
}
