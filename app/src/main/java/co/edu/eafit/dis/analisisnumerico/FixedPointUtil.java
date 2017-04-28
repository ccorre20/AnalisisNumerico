package co.edu.eafit.dis.analisisnumerico;

public class FixedPointUtil {

    public static String FixedPoint(double x0,double tol,int iter, String f, String g){
        double y = ExpressionEvalUtil.Function(f,x0);
        int count = 0;
        double E = tol + 1;
        while ((y != 0) && (E > tol) && (count < iter)){
            double x1 = ExpressionEvalUtil.Function(g,x0);
            E = Math.abs(x1-x0);
            x0 = x1;
            y = ExpressionEvalUtil.Function(f,x0);
            count ++;
        }
        if (y == 0) {
            return Double.toString(x0)+" is a root";
        }else if (E < tol){
            return Double.toString(x0)+" is an approximate root, error < tolerance";
        }else{
            return "Failure, has exceeded the maximum number of iterations";
        }
    }

}
