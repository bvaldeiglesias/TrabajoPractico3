package generadoresPseudoAleatorios;

public class GeneradorExponencial
{

    private double lambda;
    private Congruencial gnr;

    public GeneradorExponencial()
    {
        lambda = 1 / 3;
    }

    public GeneradorExponencial(double lambda)
    {
        this.lambda = lambda;
        gnr = new Congruencial();
    }

    public double rnd()
    {
        
        double x = (-1 / lambda) * Math.log(1 - gnr.RND());
        return x;
    }

}
