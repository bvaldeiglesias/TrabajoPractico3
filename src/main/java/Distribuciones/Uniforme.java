package Distribuciones;

public class Uniforme
{

    private double media_aritmetica;
    private double a;
    private double b;
    private double v[];

    //Constructor x default que se usa cuando el valor de "a" y "b" no fueron ingresados, utilizando solo la frecuencia esperada directa
    public Uniforme()
    {
    }

    //Constructor utilizado cuando el valor de "a" y "b" no fueron ingresados, calculando las funciones solo con el vector y la media
    public Uniforme(double media_aritmetica, double v1[])
    {
        this.v = v1;
        this.media_aritmetica = media_aritmetica;
    }

    //Constructor que trae por parametros los valores de las variables "a" y "b" ingresadas por teclado, 
    //un vector con los valores generados, para luego ser utilizados en las demás operaciones
    //y a la media aritmética que al ser el mismo cálculo en todas las distribuciones, es preferible que se haga en la clase donde se genera la serie RND.
    public Uniforme(double media_aritmetica, double v1[], double a, double b)
    {
        this.v = v1;
        this.media_aritmetica = media_aritmetica;
        this.a = a;
        this.b = b;
    }

    //Método que devuelve la frecuencia esperada cuando ni "a" ni "b" fueron ingresados
    //Debido a este método, se utiliza el constructor por default
    public double fe_directa(int cant_observaciones, int cant_intervalos)
    {
        return cant_observaciones / cant_intervalos;
    }

    //Método que devuelve la función de densidad cuando ni "a" ni "b" fueron ingresados, 
    //la cual va a ser utilizada como frecuencia esperada.
    public double fe_densidad()
    {
        return 2 * (1 / (b() - a()));
    }

    //Método que devuelve la varianza del vector de números generados.
    public double varianza()
    {
        double acu = 0;
        for (int i = 0; i < v.length; i++)
        {
            acu += Math.pow(v[i] - media_aritmetica, 2);
        }
        return (acu / (v.length - 1));
    }

    //Método que devuelve el valor de la variable "a" cuando no fue ingresado por teclado
    public double a()
    {
        return media_aritmetica * 2 - b();
        //return media_aritmetica - ((Math.sqrt(12 * Math.pow(varianza(),2)))/2);
    }

    //Método que devuelve el valor de la variable "b" cuando no fue ingresado por teclado
    public double b()
    {
        return (media_aritmetica + ((Math.sqrt(12) * varianza()) / 2));
    }

    //Método que devuelve la frecuencia esperada, utilizando la función de densidad f(x) cuando el valor de "a" y "b" fueron ingresados por teclado.
    public double fe_densidad_ayb()
    {
        return 2 * (1 / (b - a));
    }

    //Agrego un método quickSort para ordenar el vector y obtener el mayor y menor valor de la serie
    //HAY QUE VALIDAR DE QUE CUANDO SE INGRESE 'a' y 'b' COINCIDAN EN VALORES POSIBLES DE LA SERIE.
    public void quickSort()
    {
        quick(0, v.length - 1);
    }

    private void quick(int izq, int der)
    {
        int i = izq, j = der;
        double y;
        double x = v[(izq + der) / 2];
        do
        {
            while (v[i] < x && i < der)
            {
                i++;
            }
            while (x < v[j] && j > izq)
            {
                j--;
            }
            if (i <= j)
            {
                y = v[i];
                v[i] = v[j];
                v[j] = y;
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
    
    public double get(int i)
    {
        return v[i];
    }
    
    public int size()
    {
        return v.length;
    }
    
    public double sumatoria()
    {
        double acu = 0;
        for (int i = 0; i < v.length; i++)
        {
            acu += v[i];
        }
        return acu;
    }

}

//-----------------------CLASE MAIN PARA TESTEAR----------------------
// public static void main(String[] args)
//    {
//        Scanner sc = new Scanner(System.in);
//
////        System.out.print("Ingresar la cantidad de elementos a generar: ");
////        int cant = sc.nextInt();
//        double v[] = new double[30];
//        v[0] = 0.15;
//        v[1] = 0.22;
//        v[2] = 0.41;
//        v[3] = 0.65;
//        v[4] = 0.84;
//        v[5] = 0.81;
//        v[6] = 0.62;
//        v[7] = 0.45;
//        v[8] = 0.32;
//        v[9] = 0.07;
//        v[10] = 0.11;
//        v[11] = 0.29;
//        v[12] = 0.58;
//        v[13] = 0.73;
//        v[14] = 0.93;
//        v[15] = 0.97;
//        v[16] = 0.79;
//        v[17] = 0.55;
//        v[18] = 0.35;
//        v[19] = 0.09;
//        v[20] = 0.99;
//        v[21] = 0.51;
//        v[22] = 0.35;
//        v[23] = 0.02;
//        v[24] = 0.19;
//        v[25] = 0.24;
//        v[26] = 0.98;
//        v[27] = 0.10;
//        v[28] = 0.31;
//        v[29] = 0.17;
//        System.out.println("\n" + Arrays.toString(v));
//        Uniforme ordenado = new Uniforme(20, v);
//        ordenado.quickSort();
//
////        System.out.print("Ingresar la cantidad de intervalos a generar: ");
////        int intervalos = sc.nextInt();
//        int intervalos = 5;
//
//        System.out.print("Ingresar el valor de a (valor menor: " + ordenado.get(0) + ", valor mayor: " + ordenado.get(ordenado.size() - 1) + "): ");
//        double a = sc.nextDouble();
//
//        System.out.print("Ingresar el valor de b (valor menor: " + a + ", valor mayor: " + ordenado.get(ordenado.size() - 1) + "): ");
//        double b = sc.nextDouble();
//
////        Random r = new Random();
////        double acu = 0;
////        for (int i = 0; i < v.length; i++)
////        {
////            v[i] = r.nextDouble() * 10;
////            acu += v[i];
////        }
//        double acu = 0.0;
//        for (int i = 0; i < v.length; i++)
//        {
//            acu += v[i];
//        }
//
//        double media_aritmetica = acu / v.length;
//        System.out.println("\nMedia aritmetica: " + media_aritmetica);
//
//        Uniforme u1 = new Uniforme();
//        System.out.println("\nLa frecuencia esperada directa (sin 'a' ni 'b') es: " + u1.fe_directa(30, intervalos));
//
//        Uniforme u2 = new Uniforme(media_aritmetica, v);
//        System.out.println("\nValores usados de a: " + u2.a() + " y b: " + u2.b());
//        System.out.println("\nLa frecuencia esperada utilizando f(x)(sin 'a' ni 'b') es: " + u2.fe_densidad());
//
//        Uniforme u3 = new Uniforme(media_aritmetica, v, a, b);
//        double frecuencia_esperada_nueva = Math.round(u3.fe_densidad_ayb());
//        System.out.println("\nLa frecuencia esperada utilizando f(x)(con 'a' y 'b')es:" + frecuencia_esperada_nueva);
//        
//        //Aca almaceno la nueva cantidad de intervalos que va a tener la tabla de frecuencias con los valores nuevos de 'a' y 'b'
//        int nuevo_cant_int = (int) Math.round(u3.size() / u3.fe_densidad_ayb());
//        System.out.println("La nueva cantidad de intervalos es: " + nuevo_cant_int);
//        
//        //Aca almaceno el nuevo incremento que va a haber en cada intervalo y lo muestro para verificar de que esté bien.
//        double incremento = u3.sumatoria() / nuevo_cant_int;
//        System.out.println("Nuevo incremento entre intervalos: " + incremento);
//        double anterior = 0;
//        double posterior;
//        for (int i = 0; i < nuevo_cant_int; i++)
//        {
//            System.out.println("\nDesde: " + anterior);
//            posterior = anterior + incremento;
//            System.out.println("Hasta: " +posterior);
//            anterior = posterior;
//            System.out.println("Frecuencia esperada: "+frecuencia_esperada_nueva);
//        }
//
//    }
