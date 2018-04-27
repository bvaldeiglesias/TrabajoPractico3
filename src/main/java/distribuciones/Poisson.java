package distribuciones;

public class Poisson
{

    private int lambda;
    private int intervalo;
    private double frecuencia_esperada[];
    private double temp[];
    private double v[];

    public Poisson(double v[], int lambda, int intervalo)
    {
        this.lambda = lambda;
        this.intervalo = intervalo;
        frecuencia_esperada = new double[intervalo];
        this.v = v;
    }

    //Este método te devuelve el vector con todas las frecuencias esperadas de todos los intervalos
//    public double[] frecuencia_esperada_intervalo()
//    {
//        temp = v;
//        quickSort();
//        double primer_valor = temp[0];
//        double ultimo_valor = temp[temp.length - 1];
//        double recorrido = ultimo_valor - primer_valor;
//        double cant_x_int = recorrido / intervalo;
//        double anterior = primer_valor;
//        double posterior;
//        for (int i = 0; i < intervalo; i++)
//        {
//            posterior = anterior + cant_x_int;
//            double valor1 = probabilidad_ocurrencia_parcial(anterior);
//            double valor2 = probabilidad_ocurrencia_parcial(posterior);
//            double prob_ocurrencia = valor1 + valor2;
//            double frec_esperada = prob_ocurrencia * v.length;
//            frecuencia_esperada[i] = frec_esperada;
//            anterior = posterior;
//        }
//        return frecuencia_esperada;
//    }
    
        public double[] frecuencia_esperada_intervalo()
    {
        frecuencia_esperada = new double[intervalo];
        temp = v;
        quickSort();
        int primer_valor = (int) temp[0];
        int ultimo_valor = (int) temp[temp.length - 1];
        int recorrido = ultimo_valor - primer_valor;
        int cant_x_int = (int) Math.floor(recorrido / intervalo);
        int anterior = primer_valor - cant_x_int;
        int posterior;
        for (int i = 0; i < intervalo; i++)
        {
            posterior = anterior + cant_x_int;
            double prob_ocurrencia = probabilidad_ocurrencia_parcial(anterior) + probabilidad_ocurrencia_parcial(posterior);
            double frec_esperada = Math.round(prob_ocurrencia * v.length);
            frecuencia_esperada[i] = frec_esperada;
            anterior = posterior + cant_x_int;
        }
        return frecuencia_esperada;
    }

    public double probabilidad_ocurrencia_parcial(int x)
    {
        double resultado = ((Math.pow(lambda, x)) * (Math.exp(-lambda))) / (factorial(x));
        return resultado;
    }

    public double factorial(int n)
    {
        if (n == 0)
        {
            return 1;
        } else
        {
            return (n * factorial(n - 1));
        }
    }

    public void quickSort()
    {
        quick(0, temp.length - 1);
    }

    private void quick(int izq, int der)
    {
        int i = izq, j = der;
        double y;
        double x = temp[(izq + der) / 2];
        do
        {
            while (temp[i] < x && i < der)
            {
                i++;
            }
            while (x < temp[j] && j > izq)
            {
                j--;
            }
            if (i <= j)
            {
                y = temp[i];
                temp[i] = temp[j];
                temp[j] = y;
                i++;
                j--;
            }
        } while (i <= j);
        if (izq < j)
        {
            quick(izq, j);
        }
        if (i < der)
        {
            quick(i, der);
        }
    }

}
