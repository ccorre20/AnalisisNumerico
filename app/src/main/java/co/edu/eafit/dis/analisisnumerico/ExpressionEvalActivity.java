package co.edu.eafit.dis.analisisnumerico;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class ExpressionEvalActivity {

    public double ExpressionEval(String arg, double num) {
        final String expression = arg;
        final DoubleEvaluator eval = new DoubleEvaluator();
        final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
        double x = num;
        variables.set("x", x);
        Double result = eval.evaluate(expression, variables);
        return result;
        }
}



