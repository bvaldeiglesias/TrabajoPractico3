/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuciones;

import service.nrosAleatorios.Congruencial;

/**
 *
 * @author Bruno
 */
public class Poisson {
    
    private int lambda;
    private Congruencial gnr;
    private double p;
    private int x;
    private double a;

    public Poisson(int lambda) {
        this.lambda = lambda;
        
        
        this.a = Math.pow(Math.E, -lambda);
        
        this.gnr = new Congruencial();
    }
    
    public Poisson(int lambda, double seed) {
        this.lambda = lambda;
        
        this.p = 1;
        this.x = -1;
        this.a = Math.pow(Math.E, -lambda);
        
        this.gnr = new Congruencial(seed);
    }
    
    public int rnd(){
        this.p = 1;
        this.x = -1;
        do{
            double u = gnr.RND();
            p=p*u;
            x++;
            
        }while(p >= a);
        return x;
    }
    
    
    
    
}
