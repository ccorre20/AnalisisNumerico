package co.edu.eafit.dis.analisisnumerico;



public class NewtonMethodUtil {

    public void NewtonMethod(double x0,double tol,int iter, String f, String fp){
        double y = ExpressionEvalUtil.Function(f,x0);
        double dy = ExpressionEvalUtil.Function(fp,x0);
        int count = 0;
        double E = tol + 1;
        while ((y != 0) && dy!=0 && (E > tol) && (count < iter)){
            double x1 = x0 -(y/dy);
            y = ExpressionEvalUtil.Function(f,x1);
            dy = ExpressionEvalUtil.Function(fp,x1);
            E = Math.abs(x1-x0);
            x0 = x1;
            count ++;
        }
        if (y == 0) {
            System.out.println("x0 is a root");
        }else if (dy == 0){
            System.out.println("x1 is a root");
        }else if (E < tol){
            System.out.println("x1 is an approximate root by error < tolerance");
        }else{
            System.out.println("Failure, has exceeded the maximum number of iterations");
        }
    }

}
