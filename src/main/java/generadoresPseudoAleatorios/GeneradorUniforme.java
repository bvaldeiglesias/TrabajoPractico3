package generadoresPseudoAleatorios;




public class GeneradorUniforme
{

    private int a;
    private int b;
    private Congruencial gnr;

    public GeneradorUniforme()
    {
        a = 2;
        b = 5;
        gnr = new Congruencial();
    }

    public GeneradorUniforme(int a, int b)
    {
        this.a = a;
        this.b = b;
        gnr = new Congruencial();
    }

    public double rnd()
    {
        double x = (double) ((double) a + gnr.RND() * (b - a));
        return x;
    }

}

//    public static void main(String[] args)
//    {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Ingresar a: ");
//        float a = sc.nextFloat();
//        System.out.print("Ingresar b: ");
//        float b = sc.nextFloat();
//
//        System.out.print("\nIngresar cantidad de números a generar: ");
//        int cant = sc.nextInt();
//        while (cant <= 0)
//        {
//            System.out.print("Ingresar cantidad de números a generar: ");
//            cant = sc.nextInt();
//        }
//        System.out.println("\n");
//        GeneradorUniforme u = new GeneradorUniforme(a, b);
//        
//        for (int i = 0; i < cant; i++)
//        {
//            System.out.println("Número " + (i+1) + ": " + u.generar());
//        }
//
//    }
