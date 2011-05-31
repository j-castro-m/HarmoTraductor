/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package harmotraductor;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.TextField.*;
import javax.swing.JFrame.*;
import javax.swing.BoxLayout.*;
/**
 *
 * @author alu03009
 */
class miJLabel extends JLabel
{
    int numinterno;
    miJLabel()
    {
        super();
        numinterno = 0;
    }

    miJLabel(int a)
    {
        super();
        numinterno = a;
    }
}


class formulario extends JFrame
{
    JMenuBar barraMenu = new JMenuBar();
    JTextArea notastxt = new JTextArea(2,5);
    String texto = " ";
    JPanel cajaNotas = new JPanel();
    public ArrayList <miJLabel> imnotas = new <miJLabel> ArrayList(58);


    void crearimages(ArrayList a)
    {
        int i = 0;

        System.out.println(a.size()+" "+imnotas.size());
        for(i=0;i<a.size();i++)
        {
           imnotas.add(new miJLabel(((Nota)(a.get(i))).getnum()));
           (imnotas.get(i)).setIcon(new ImageIcon(((Nota)(a.get(i))).getpath()));
        }
    }

    void dentrotexto(String a)
       {
             notastxt.setText(a) ;
       }

    void cargar(Partitura a)
    {
        //notastxt.setLineWrap(true);
        this.setLayout(new BorderLayout());
        this.add(notastxt, BorderLayout.NORTH);
        this.setJMenuBar(barraMenu);
        this.add(cajaNotas, BorderLayout.CENTER);
        cajaNotas.setLayout(new GridLayout(a.filasCancion(),a.columnasCancion()));
        //cajaNotas.setLayout(new FlowLayout());
        System.out.println("Columnas"+a.columnasCancion());
        System.out.println("Filas"+a.filasCancion());
        int j = 0;
        int actual = 0;
        for(j=1;j<imnotas.size();j++)
        {
            
            System.out.println("anadido icon - "+j);
            if(imnotas.get(j).numinterno==120&&actual!=0)
            {
                for(int alpha = actual;alpha<a.columnasCancion();alpha++)
                {
                    cajaNotas.add(new JLabel(""));
                    System.out.println("metida repetidamente"+imnotas.get(0).numinterno);
                }
                actual = 0;
            }
            else
            {
            cajaNotas.add(imnotas.get(j));
            actual=actual+1;
            System.out.println("Actual="+actual);
            }
            
            
        }
        this.setSize(1000, 600);
        this.setVisible(true);
    }
}



public class Main {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // TODO code application logic here
        String dir = "./docs/";
        File f = new File(dir);
        String[] salida;
        salida = f.list();
        String archivo = dir+salida[0];
        String fichero = new String();
        char head = 'a';
        formulario form = new formulario();
        Partitura Cancion = new Partitura();

        try{
        FileReader cabezal = new FileReader(archivo);
        BufferedReader bf = new BufferedReader(cabezal);

        
        int notatube;
        while((notatube = bf.read())!=-1){

        if(notatube!=32&&notatube!=41)
        {

            if(notatube==10)
            {
                System.out.println("Salto de Linea");
                Cancion.addnota(120);
            }
 else
            {
          if(notatube==40)
          {
              Cancion.addnota((-1)*(bf.read()-48));
          }
            else
          {
            Cancion.addnota(notatube-48);
          }

            }
        }
        }


        
        
        

        form.dentrotexto(Cancion.toString());




        }
   	catch (Exception ex_3)
	{
	System.out.println (ex_3.getMessage());
        }
        form.crearimages(Cancion.notas);
        form.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        form.cargar(Cancion);


    }

}


class Partitura
{

   public ArrayList <Nota> notas = new <Nota> ArrayList();
   public ArrayList letra = new ArrayList();

   public int filasCancion()
    {
       int num = 0 ;
       for(int i = 0; i<notas.size();i++)
       {
           if(((notas.get(i)).getnum())==(120))
           {
               num=num+1;
           }

       }
     return (num-1);
    }

   public int columnasCancion()
    {
       int num = 0 ;
       int numax = 0;
       for(int i = 0; i<notas.size();i++)
       {
               if(((notas.get(i)).getnum())==120)
               {
                   num=0;
               }
              else
               {
                   num=num+1;
                   if(num>=numax)
                   {
                       numax=num;
                   }
               }
       }
     return numax;
   }
   public void addnota (int a)
    {
       System.out.println("anadida -> "+a);
       Nota note = new Nota(a);
       notas.add(note);
     }

   public void deletenota (int index)
    {
       notas.remove(index);
    }


   public void duplinota(int index)
    {
       Nota dupli;
       dupli = notas.get(index);
       notas.add(index,dupli);
    }

   public String toString()
    {
       String prev = notas.toString();
       prev = prev.replace("[\n,"," ");
       prev = prev.replace(',',' ');
       prev = prev.replace("\n]","");
       return prev;
   }
}

class Nota
{
    int numeronota;
    int modificador;
    String imagename;

    Nota(int a)
    {
        if(a>0)
        {
            numeronota=a;
            modificador=0;
            imagename = "./images/"+numeronota+".gif";
            System.out.println(imagename);
        }
        else
        {
            numeronota=(a*-1);
            modificador=-1;
            imagename = "./images/("+numeronota+").gif";
            System.out.println(imagename);
        }
    }

    public String getpath()
    {
        return imagename;
    }


    public int getnum()
    {
        return numeronota;
    }

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
          return "(-"+this.numeronota+") ";
        }
        else
        {
            return "("+this.numeronota+") ";
        }
        }
    }
}
