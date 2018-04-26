package generadoresPseudoAleatorios;


public class GeneradorBoxMuller
{

    private double phi = (double) Math.PI;
    private double desv_est;
    private double media;
    private Congruencial gnr;
    private double n1;
    private double n2;

    public GeneradorBoxMuller()
    {
        desv_est = 2;
        media = 15;
        gnr = new Congruencial();
    }

    public GeneradorBoxMuller(double desv, double med)
    {
        this.desv_est = desv;
        this.media = med;
        gnr = new Congruencial();
    }

    public void generarBoxMuller()
    {
        double rnd1 = (double) gnr.RND();
        double rnd2 = (double) gnr.RND();
        double adentro_raiz = (double) ((double) -2*Math.log(rnd1));
        double argumento = (double) 2*phi*rnd2;
        n1 = (double) (desv_est*Math.sqrt(adentro_raiz)*Math.cos(argumento))+media;
        n2 = (double) (desv_est*Math.sqrt(adentro_raiz)*Math.sin(argumento))+media;
    }
    
    public double getN1()
    {
        return n1;
    }
    
    public double getN2()
    {
        return n2;
    }
}

//    public static void main(String[] args)
//    {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Ingresar la media: ");
//        float media = sc.nextFloat();
//
//        System.out.print("Ingresar la desviación estándar: ");
//        float desv = sc.nextFloat();
//
//        System.out.print("\nIngresar la cantidad de números a generar: ");
//        int cant = sc.nextInt();
//        
//        
//        if (cant % 2 == 0)
//        {
//            cant = cant/2;
//        } else
//        {
//            cant = (cant+1)/2;
//        }
//        
//        GeneradorBoxMuller g = new GeneradorBoxMuller(desv, media);
//
//        int x = 0;
//        for (int i = 0; i < cant; i++)
//        {
//            g.generarBoxMuller();
//            System.out.println("Número " + (x + 1) + ": " + g.getN1());
//            x++;
//            System.out.println("Número " + (x + 1) + ": " + g.getN2());
//            x++;
//        }
//
//    }
