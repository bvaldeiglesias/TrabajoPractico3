package generadoresPseudoAleatorios;

public class GeneradorExponencial
{

    private float lambda;
    private Congruencial gnr;

    public GeneradorExponencial()
    {
        lambda = 1 / 3;
    }

    public GeneradorExponencial(float lambda)
    {
        this.lambda = lambda;
        gnr = new Congruencial();
    }

    public double generarExponencial()
    {
        
        double x = (-1 / lambda) * Math.log(1 - gnr.RND());
        return x;
    }

}
