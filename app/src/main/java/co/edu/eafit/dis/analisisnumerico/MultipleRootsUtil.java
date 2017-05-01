package co.edu.eafit.dis.analisisnumerico;


public class MultipleRootsUtil {

    public static String MultipleRoots(String f, String fp, String fpp, int iter, double tol, double x0){
        double y = ExpressionEvalUtil.Function(f,x0);
        double dy = ExpressionEvalUtil.Function(fp,x0);
        double ddy = ExpressionEvalUtil.Function(fpp,x0);
        int count = 0;
        double E = tol + 1;
        while ((y != 0) && (dy != 0) && (ddy != 0) && (E > tol) && (count < iter)){
            double x1 = x0-((y*dy)/((dy*dy)-(y*ddy)));
            E = Math.abs(x1-x0);
            x0 = x1;
            y = ExpressionEvalUtil.Function(f,x0);
            dy = ExpressionEvalUtil.Function(fp,x0);
            ddy = ExpressionEvalUtil.Function(fpp,x0);
            count ++;
        }
        if (y == 0) {
            return Double.toString(x0)+" is a root";
        }else if (dy ==0) {
            return "First derivative equal to 0";
        }else if (ddy==0) {
            return "Second derivative equal to 0";
        }else if (E < tol){
            return Double.toString(x0)+" is an approximate root, error < tolerance";
        }else{
            return "Failure, has exceeded the maximum number of iterations";
        }
    }


}
