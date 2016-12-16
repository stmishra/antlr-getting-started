package in.gen.berserker;

import in.gen.berserker.generated.CalcBaseListener;
import in.gen.berserker.generated.CalcParser;

import java.util.Stack;

/**
 * Created by stm on 16/12/16.
 */
public class CalculatorListenerImplementation extends CalcBaseListener {
    Stack<Integer> stack = new Stack<>();

    @Override
    public void exitMultOrDiv(CalcParser.MultOrDivContext ctx) {
        int num1 = -1;

        try {
            num1 = Integer.parseInt(ctx.expr().get(0).getText());
        } catch (Exception e) {
            num1 = stack.pop();
        }

        int num2 = -1;

        try {
            num2 = Integer.parseInt(ctx.expr().get(1).getText());
        } catch (NumberFormatException e) {
            num2 = stack.pop();
        }

        String symbol = ctx.op.getText();

        if (symbol.trim().equals("*")) {
            stack.push(num1 * num2);
        } else {
            stack.push(num1 / num2);
        }
    }

    @Override
    public void exitPlusOrMinus(CalcParser.PlusOrMinusContext ctx) {
        int num1 = -1;

        try {
            num1 = Integer.parseInt(ctx.expr().get(0).getText());
        } catch (NumberFormatException e) {
            num1 = stack.pop();
        }

        int num2 = -1;
        try {
            num2 = Integer.parseInt(ctx.expr().get(1).getText());
        } catch (NumberFormatException e) {
            num2 = stack.pop();
        }

        String symbol = ctx.op.getText();

        if (symbol.trim().equals("+")) {
            stack.push(num1 + num2);
        } else {
            stack.push(num1 - num2);
        }
    }

    @Override
    public void exitProg(CalcParser.ProgContext ctx) {
        //Print the final result
        System.out.println(stack.pop());
    }
}
