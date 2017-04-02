package co.edu.eafit.dis.analisisnumerico;

/**
 * Created by sergi on 1/04/2017.
 */

//import android.widget.Toast;

public class FunctionUtil {

    public static String Bisection(double xi, double xs, int iter, double tol, String f){

        double yi = ExpressionEvalUtil.Function(f,xi);

        double ys = ExpressionEvalUtil.Function(f,xs);

        if(yi == 0){

//            Toast.makeText(getApplicationContext(), "Found Solution, " + xi + " is a root",
//                    Toast.LENGTH_SHORT).show();
            return Double.toString(xi);

        }else if(ys == 0){

//            Toast.makeText(getApplicationContext(), "Found Solution, " + xs + " is a root",
//                    Toast.LENGTH_SHORT).show();
            return Double.toString(xs);

        }else if ((yi * ys) > 0){

//            Toast.makeText(getApplicationContext(), "Error, there is no root in the interval",
//                    Toast.LENGTH_SHORT).show();
//            throw new ArithmeticException("No root");
            return "Error";

        }else{

            double xm = (xi + xs)/2;

            double ym = ExpressionEvalUtil.Function(f,xm);

            double E = tol + 1;

            int count = 1;

            while((ym != 0) && (E > tol) && (count < iter)){

                if((ym * yi) < 0){

                    xs = xm;

                    ys = ym;

                }else{

                    xi = xm;

                    yi = ym;

                }

                double xaux = xm;

                xm = (xi + xs)/2;

                ym = ExpressionEvalUtil.Function(f,xm);

                E = Math.abs(xm - xaux);

            }

            if(ym == 0){

//                Toast.makeText(getApplicationContext(), "Found Solution, " + xm + " is a root",
//                        Toast.LENGTH_SHORT).show();
                return Double.toString(xm);

            }else if(E < tol){

//                Toast.makeText(getApplicationContext(), "Found Solution, " + xm + " is an " +
//                                "approximate root for E < tolerance",
//                        Toast.LENGTH_SHORT).show();
                return Double.toString(xm) + " (aprox)";

            }else{

//                Toast.makeText(getApplicationContext(), "Error, maximum number of iterations reached",
//                        Toast.LENGTH_SHORT).show();
//                throw new ArithmeticException("No root");
                return "Error";

            }
        }
    }
}
