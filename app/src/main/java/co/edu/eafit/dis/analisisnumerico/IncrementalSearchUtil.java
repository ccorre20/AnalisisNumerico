package co.edu.eafit.dis.analisisnumerico;


public class IncrementalSearchUtil {

    public static String IncrementalSearch(double x0, double delta, int iter, String f){
        double y0 = ExpressionEvalUtil.Function(f,x0);
        if (y0 == 0){
            return Double.toString(x0)+" is a root";
        }else{
            double x1 = x0 + delta;
            double y1 = ExpressionEvalUtil.Function(f,x1);
            int count = 1;
            while ((y0 * y1 < 0) && (count > iter)){
                x0 = x1;
                y0 = y1;
                x1 = x0 + delta;
                y1 = ExpressionEvalUtil.Function(f,x1);
                count = count + 1;
            }
            if (y1 == 0){
                return Double.toString(x1)+" is a root";
            }else if ((y1 * y0) < 0){
                return Double.toString(x0)+", "+Double.toString(x1)+" is an interval where a root exists";
            }else{
                return "Failure, has exceeded the maximum number of iterations";
            }
        }
    }
}
