package distribuciones;
public class Normal
{

    private double v[];
    private double vector_probabilidades[];
    private double vector_frecuencias_esperadas[];
    private double marca_clase[];
    private double media_aritmetica;
    private double desv_est;
    private int intervalo;
    double temp[];
    private double diferencias[];
    
    //CONSTRUCTOR A BORRAR
    public Normal(double v[], int intervalo)
    {
        this.v = v;
        this.intervalo = intervalo;
    }

//CONSTRUCTOR QUE FUNCIONA
//    public Normal(double v[], int intervalo, double desv, double media)
//    {
//        this.intervalo = intervalo;
//        this.v = v;
//        this.desv_est = desv;
//        this.media_aritmetica = media;
//    }

    public int size()
    {
        return v.length;
    }

    public double desviacion_estandar()
    {
        double acu = 0;
        for (int i = 0; i < v.length; i++)
        {
            acu += (double) Math.pow(v[i] - media_aritmetica, 2);
        }
        double cociente = (double) 1 / (v.length - 1);
        double desvi_est = (double) Math.sqrt(acu * cociente);
        return desvi_est;
    }

    public void fmc()
    {
        marca_clase(intervalo);
        vector_probabilidades = new double[marca_clase.length];
        double elevacion;
        double desv_estandar = (float) desv_est;
        double cociente = 1 / (desv_estandar * Math.sqrt(2 * Math.PI));
        double resultado;
        for (int i = 0; i < vector_probabilidades.length; i++)
        {
            elevacion = Math.exp((-0.5) * (Math.pow((marca_clase[i] - media_aritmetica) / desv_estandar, 2)));
            resultado = elevacion * cociente;
            vector_probabilidades[i] = resultado;
        }
    }

    //ESTE VECTOR ES EL QUE TE DEVUELVE LAS FRECUENCIAS ESPERADAS POR INTERVALO
    public double[] frec_esperada_x_intervalo()
    {
        fmc();
        vector_frecuencias_esperadas = new double[marca_clase.length];
        double resultado, po;
        for (int i = 0; i < marca_clase.length; i++)
        {
            po = vector_probabilidades[i] * diferencias[i];
            resultado = po * v.length;
            vector_frecuencias_esperadas[i] = resultado;
        }
        return vector_frecuencias_esperadas;
    }

    public void marca_clase(int intervalo)
    {
        temp = v;
        quickSort();
        double primer_valor = temp[0];
        double ultimo_valor = temp[temp.length - 1];
        double recorrido = ultimo_valor - primer_valor;
        double cant_x_int = recorrido / intervalo;
        marca_clase = new double[intervalo];
        diferencias = new double[intervalo];
        double diferencia;
        double anterior = primer_valor;
        double posterior;
        float marca;
        for (int i = 0; i < marca_clase.length; i++)
        {
            posterior = anterior + cant_x_int;
            diferencia = posterior - anterior;
            diferencias[i] = diferencia;
            marca = (float) ((anterior + posterior) / 2);
            marca_clase[i] = marca;
            anterior = posterior;
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

    public double media_aritmetica()
    {
        double acu = 0;
        for (int i = 0; i < v.length; i++)
        {
            acu += v[i];
        }
        return (acu / v.length);
    }

}