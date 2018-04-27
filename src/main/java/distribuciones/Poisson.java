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
        this.v = v;
    }

    //Este método te devuelve el vector con todas las frecuencias esperadas de todos los intervalos
    public double[] frecuencia_esperada_intervalo()
    {
        temp = v;
        quickSort();
        double primer_valor = temp[0];
        double ultimo_valor = temp[temp.length - 1];
        double recorrido = ultimo_valor - primer_valor;
        double cant_x_int = recorrido / intervalo;
        double anterior = primer_valor;
        double posterior;
        for (int i = 0; i < intervalo; i++)
        {
            posterior = anterior + cant_x_int;
            double valor1 = probabilidad_ocurrencia_parcial(anterior);
            double valor2 = probabilidad_ocurrencia_parcial(posterior);
            double prob_ocurrencia = valor1 + valor2;
            double frec_esperada = prob_ocurrencia * v.length;
            frecuencia_esperada[i] = frec_esperada;
            anterior = posterior;
        }
        return frecuencia_esperada;
    }

    public double probabilidad_ocurrencia_parcial(double x)
    {
        double numerador = Math.pow(lambda, x) * Math.exp(-lambda);
        int valor_para_factorial = (int) Math.round(x);
        double denominador = factorial(valor_para_factorial);
        return (numerador/denominador);
    }

    public double factorial(int number)
    {
        long result = 1;

        for (int factor = 2; factor <= number; factor++)
        {
            result *= factor;
        }

        return result;
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
