package co.edu.eafit.dis.analisisnumerico;


public class FalseRuleUtil {

    public void FalseRule(double xi, double xs, int iter, double tol, String f){
        double yi = ExpressionEvalUtil.Function(f,xi);
        double ys = ExpressionEvalUtil.Function(f,xs);
        if (yi == 0){
            System.out.println("xi is a root");
        }else if (ys == 0){
            System.out.println("xs is root");
        }else if ((yi * ys)  > 0) {
            System.out.println("There is no root in the interval");
        }else{
            double xm = xi - ((yi*(xs-xi))/(ys- yi));
            double ym = ExpressionEvalUtil.Function(f,xm);
            double E = tol + 1;
            int count = 1;
            while ((ym != 0) && (E>tol) && (count < iter)){
                if ((ym * yi) < 0 ){
                    xs = xm;
                    ys = ym;
                }else{
                    xi = xm;
                    yi = ym;
                }
                double xaux = xm;
                xm = xi - ((yi*(xs-xi))/(ys- yi));
                ym = ExpressionEvalUtil.Function(f,xm);
                E = Math.abs(xm - xaux);
            }
            if (ym == 0){
                System.out.println("xm is a root");
            }else if (E < tol){
                System.out.println("xm is an approximate root for E < tolerance");
            }else{
                System.out.println("Failure");
            }
        }
    }
}
