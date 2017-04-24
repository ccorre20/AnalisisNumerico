package co.edu.eafit.dis.analisisnumerico;


public class MultipleRootsUtil {

    public void MultipleRoots(double x0,double tol,int iter, String f, String fp, String fpp){
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
            System.out.println("x0 is a root");
        }else if (dy ==0) {
            System.out.println("First derivative equal to 0");
        }else if (ddy==0) {
            System.out.println("Second derivative equal to 0");
        }else if (E < tol){
            System.out.println("x1 is an approximate root for error < tolerance");
        }else{
            System.out.println("Failure, has exceeded the maximum number of iterations");
        }
    }


}
