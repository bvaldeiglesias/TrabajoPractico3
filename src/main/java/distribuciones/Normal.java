package distribuciones;


public class Normal
{

    private double v[];
    private double vector_probabilidades[];
    private double vector_frecuencias_esperadas[];
    private float marca_clase[];
    private double media_aritmetica;
    private double desv_est;
    private int intervalo;

    public Normal(double v[], double media_aritmetica)
    {
        this.media_aritmetica = media_aritmetica;
        this.v = v;
    }

    public Normal(double v[], double media_aritmetica, double desv_est, int intervalo)
    {
        this.intervalo = intervalo;
        this.desv_est = desv_est;
        this.media_aritmetica = media_aritmetica;
        this.v = v;
    }

    public int size()
    {
        return v.length;
    }

    public float desviacion_estandar()
    {
        float acu = 0;
        for (int i = 0; i < v.length; i++)
        {
            acu += (float) Math.pow(v[i] - media_aritmetica, 2);
        }
        float cociente = (float) 1 / (v.length - 1);
        float desvi_est = (float) Math.sqrt(acu * cociente);
        return desvi_est;
    }

    public double[] prob_ocurrencia()
    {
        marca_clase_default();
        vector_probabilidades = new double[marca_clase.length];
        double elevacion;
        float desv_estandar = desviacion_estandar();
        double cociente = 1 / (desv_estandar * Math.sqrt(2 * Math.PI));
        double resultado;
        for (int i = 0; i < vector_probabilidades.length; i++)
        {
            elevacion = Math.exp(-0.5 * (Math.pow((marca_clase[i] - media_aritmetica) / desv_estandar, 2)));
            resultado = elevacion * cociente;
            vector_probabilidades[i] = resultado;
        }
        return vector_probabilidades;
    }

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

    public double[] prob_ocurrencia_teclado()
    {
        marca_clase(intervalo);
        vector_probabilidades = new double[marca_clase.length];
        double elevacion;
        float desv_estandar = (float) desv_est;
        double cociente = 1 / (desv_estandar * Math.sqrt(2 * Math.PI));
        double resultado;
        for (int i = 0; i < vector_probabilidades.length; i++)
        {
            elevacion = Math.exp(-0.5 * (Math.pow((marca_clase[i] - media_aritmetica) / desv_estandar, 2)));
            resultado = elevacion * cociente;
            vector_probabilidades[i] = resultado;
        }
        return vector_probabilidades;
    }

    public double[] frec_esperada_x_intervalo_teclado()
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

    public void marca_clase(int intervalo)
    {
        marca_clase = new float[intervalo];
        double anterior = 0;
        double posterior;
        float marca;
        for (int i = 0; i < marca_clase.length; i++)
        {
            posterior = anterior + 1;
            marca = (float) ((anterior + posterior) / 2);
            marca_clase[i] = marca;
            anterior = posterior;
        }
    }

    public void marca_clase_default()
    {
        marca_clase = new float[10];
        double anterior = 0;
        double posterior;
        float marca;
        for (int i = 0; i < marca_clase.length; i++)
        {
            posterior = anterior + 1;
            marca = (float) ((anterior + posterior) / 2);
            marca_clase[i] = marca;
            anterior = posterior;
        }
    }

}

//------------------------MAIN PARA HACER PRUEBAS------------------------------

//public static void (String[] args]
//{
//Scanner sc = new Scanner(System.in);
//
//        System.out.print("Ingrese la media aritmética: ");
//        float media = sc.nextFloat();
//
//        System.out.print("Ingrese la desviación estandar: ");
//        float desv_est = sc.nextFloat();
//
//        System.out.print("Ingrese el intervalo de números (5,10,15): ");
//        int intervalo = sc.nextInt();
//        while (intervalo != 5 && intervalo != 10 && intervalo != 15)
//        {
//            System.out.print("Ingrese el intervalo de números (5,10,15): ");
//            intervalo = sc.nextInt();
//        }
//
//        double v[] = new double[30];
//        v[0] = 1.56;
//        v[1] = 2.21;
//        v[2] = 3.15;
//        v[3] = 4.61;
//        v[4] = 4.18;
//        v[5] = 5.20;
//        v[6] = 6.94;
//        v[7] = 7.71;
//        v[8] = 5.15;
//        v[9] = 6.76;
//        v[10] = 7.28;
//        v[11] = 4.23;
//        v[12] = 3.21;
//        v[13] = 2.75;
//        v[14] = 4.69;
//        v[15] = 5.86;
//        v[16] = 6.25;
//        v[17] = 4.27;
//        v[18] = 4.91;
//        v[19] = 4.78;
//        v[20] = 2.46;
//        v[21] = 3.97;
//        v[22] = 5.71;
//        v[23] = 6.19;
//        v[24] = 4.20;
//        v[25] = 3.48;
//        v[26] = 5.83;
//        v[27] = 6.36;
//        v[28] = 5.90;
//        v[29] = 5.43;
//        double acu = 0;
//        for (int i = 0; i < v.length; i++)
//        {
//            acu += v[i];
//        }
//
//        double media_aritmetica = acu / v.length;
//
//        Normal normal = new Normal(v, media_aritmetica);
//
//        Normal por_teclado = new Normal(v, media, desv_est,intervalo);
//
//        System.out.println("Probabilidades de ocurrencia");
//        double v2[] = normal.prob_ocurrencia();
//        System.out.println(Arrays.toString(v2));
//
//        double v3[] = normal.frec_esperada_x_intervalo();
//        System.out.println("\nFrecuencias esperadas");
//        System.out.println(Arrays.toString(v3));
//
//        System.out.println("\nProbabilidades de ocurrencia (por teclado)");
//        double v4[] = por_teclado.prob_ocurrencia_teclado();
//        System.out.println(Arrays.toString(v4));
//
//        System.out.println("\nFrecuencias esperadas (por teclado)");
//        double v5[] = por_teclado.frec_esperada_x_intervalo_teclado();
//        System.out.println(Arrays.toString(v5));
//}
