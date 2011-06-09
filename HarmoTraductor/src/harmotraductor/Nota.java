/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package harmotraductor;

/**
 *
 * @author Joel Castro
 */

 /**
 *  Es la que representa las notas de la cancion de manera interna
 */
public class Nota
{
    /** numero de nota, en simbologia matematica */
    int numeronota;
    /** Modificador de la nota, para saber mas directo si es aspirada(-1) o soplada (1) */
    int modificador;
    /** Path de la imagen que representa la nota*/
    String imagename;

     /**
     * Constructor: Genera una nota introduciendole un entero
     * @param a es el valor de la nota, a partir de la cual, la genera
     */
    Nota(int a)
    {
        if(a>0)
        {
            numeronota=a;
            modificador=1;
            imagename = numeronota+".gif";
        }
        else
        {
            numeronota=(a*-1);
            modificador=-1;
            imagename = "("+numeronota+").gif";
        }
    }

     /**
     * Metodo que devuelve los path de las notas
     * @return String que contiene el path del fichero de imagen.
     */
    public String getpath()
    {
        return imagename;
    }

     /**
     * Metodo que devuelve el numero interno de la nota
     * @return int el valor interno de la nota
     */
    public int getnum()
    {
        return numeronota;
    }

     /**
     * Metodo toString() sobreescrito para devolver la nota
     * @return String que representa la nota en simbologia de parentesis
     */
    public String toString()
    {
        if(this.numeronota==120)
        {
            return "\n";
        }
        else
        {
            if(this.modificador==-1)
            {
                return "-"+this.numeronota;
            }
            else
            {
                return String.valueOf(this.numeronota);
            }
        }
    }

     /**
     * Metodo toString() sobreescrito para devolver la nota
     * @return String que representa la nota en simbologia en notacion de parentesis
     */
    public String toString2()
    {
        if(this.numeronota==120)
        {
            return "\n";
        }
        else
        {
            if(this.modificador==-1)
            {
                return "("+this.numeronota+")";
            }
            else
            {
                return ""+this.numeronota;
            }
        }
    }
}