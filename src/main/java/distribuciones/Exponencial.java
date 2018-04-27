/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuciones;

/**
 *
 * @author 62684
 */
public class Exponencial {
    
    private double v[];
    private int lambda;
    private double vector_probabilidades[];
    private double vector_frecuencias_esperadas[];
    private double marca_clase[];
    private double media_aritmetica;
    private int intervalo;
    double temp[];
    
    public Exponencial (int lambda, double v[], int intervalo)
    {
        this.lambda = lambda;
        this.intervalo = intervalo;
        this.v = v;
    }
    
    
        public int size()
    {
        return v.length;}
    

    public double[] prob_ocurrencia_teclado()
        {   
        marca_clase(intervalo);
        double izq = 0;
        double der = marca_clase[0]*2;
        vector_probabilidades = new double[marca_clase.length];
        double incremento = der;
        double resultado;
        for (int i = 0; i < vector_probabilidades.length; i++)
        {
            resultado = acumulacion(izq) - acumulacion(der);
            vector_probabilidades[i] = resultado;
            izq=der;
            der = der + incremento;
        }
        return vector_probabilidades;
    }
        
        public double acumulacion (double limite)
        {
        double a = - lambda * limite; 
        double exp= 1 - Math.exp(a);   
        return exp;  
        }

        public void marca_clase(int intervalo)
    {
        temp = v;
        quickSort();
        double primer_valor = temp[0];
        double ultimo_valor = temp[temp.length-1];
        double recorrido = ultimo_valor - primer_valor;
        double cant_x_int = recorrido/intervalo;
        marca_clase = new double[intervalo];
        double anterior = primer_valor;
        double posterior;
        float marca;
        for (int i = 0; i < marca_clase.length; i++)
        {
            posterior = anterior + cant_x_int;
            marca = (float) ((anterior + posterior) / 2);
            marca_clase[i] = marca;
            anterior = posterior;
        }
    }
        
    //El vector de frecuencias esperadas por intervalo.
    public double[] frec_esperada_x_intervalo()
    {
        vector_frecuencias_esperadas = new double[marca_clase.length];
        double resultado;
        for (int i = 0; i < marca_clase.length; i++)
        {
            resultado = vector_probabilidades[i] * v.length;
            vector_frecuencias_esperadas[i] = resultado;
        }
        return vector_frecuencias_esperadas;
        
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
