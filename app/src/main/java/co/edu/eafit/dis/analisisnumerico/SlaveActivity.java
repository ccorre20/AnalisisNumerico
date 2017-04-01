package co.edu.eafit.dis.analisisnumerico;

/**
 * Created by sergi on 1/04/2017.
 */

import android.widget.Toast;
import java.lang.ArithmeticException;

public class SlaveActivity extends InitActivity{

    protected double Bisection(double xi, double xs, int iter, double tol){

        double yi = f(xi);

        double ys = f(xs);

        if(yi == 0){

            Toast.makeText(getApplicationContext(), "Found Solution, " + xi + " is a root",
                    Toast.LENGTH_SHORT).show();
            return xi;

        }else if(ys == 0){

            Toast.makeText(getApplicationContext(), "Found Solution, " + xs + " is a root",
                    Toast.LENGTH_SHORT).show();
            return xs;

        }else if ((yi * ys) > 0){

            Toast.makeText(getApplicationContext(), "Error, there is no root in the interval",
                    Toast.LENGTH_SHORT).show();
            throw new ArithmeticException("No root");

        }else{

            double xm = (xi + xs)/2;

            double ym = f(xm);

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

                ym = f(xm);

                E = Math.abs(xm - xaux);

            }

            if(ym == 0){

                Toast.makeText(getApplicationContext(), "Found Solution, " + xm + " is a root",
                        Toast.LENGTH_SHORT).show();
                return xm;

            }else if(E < tol){

                Toast.makeText(getApplicationContext(), "Found Solution, " + xm + " is an " +
                                "approximate root for E < tolerance",
                        Toast.LENGTH_SHORT).show();
                return xm;

            }else{

                Toast.makeText(getApplicationContext(), "Error, maximum number of iterations reached",
                        Toast.LENGTH_SHORT).show();
                throw new ArithmeticException("No root");

            }
        }
    }
}
