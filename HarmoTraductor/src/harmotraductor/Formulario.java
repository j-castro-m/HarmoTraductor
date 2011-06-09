/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package harmotraductor;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component.*;
import java.awt.TextField.*;
import javax.swing.JFrame.*;
import javax.swing.BoxLayout.*;
import javax.swing.filechooser.*;
/**
 *
 * @author Joel Castro
 */
 
 /**
 *  Es la que se encarga de gestionar la interface de usuario
 */
public class Formulario extends JFrame implements ActionListener
{
    /** JTextArea donde se mostrará la partitua en texto plano */
    JTextArea notastxt = new JTextArea(10,32);
    /** String en el que ira la representacion de las notas */
    String texto = " ";
    /** JPanel en el que van las etiquetas que representan la partitura */
    JPanel cajaNotas = new JPanel();
    /** JPanel en el que va el JTextArea */
    JPanel zonaNorte = new JPanel();
    /** JPanel reservado para el boton actualizar */
    JPanel menuBotones = new JPanel();
    /** JPanel utilizado para centrar el JTextArea */
    JPanel zonaTexto = new JPanel();
    /** JLabel que representa la codificacion del archivo */
    JLabel codLabel = new JLabel("");
    /** JButton para actualizar la partitura */
    JButton actualizar = new JButton();
    /** ArrayList con todas las etiquetas para mostrar 10x16 */
    ArrayList <JLabel> imnotas = new <JLabel> ArrayList(160);
    /** Partitura para almacenar la cancion */
    Partitura Cancion = new Partitura();
    /** Archivo que se lee, inicializado a "" */
    File ArchivoSeleccionado = new File(" ");
    /** entero que representa la codificacion */
    int type = 0;

        /** Barra de menus */
        JMenuBar barraMenu = new JMenuBar();
        /** JMenu de Archivo y Codificacion*/
        JMenu menuArchivo, menuCod;
        /** Submenus */
        JMenuItem abrir, guardar, guardarComo, exportar, salir;
        /** Submenus */
        JMenuItem simbolos, parentesis;

     /**
     * Constructor: genera un nuevo Formulario, heredando el constructor de JFrame
     * @param a String que será el titulo de la ventana.
     */
    Formulario(String a)
    {
        super(a);
    }

     /**
     * Es el metodo que se encarga de gestionar el menu del programa
     */
   void ponerMenu()
    {
        //creamos los menus y los añadimos a la barra de menu
        menuArchivo=new JMenu("Archivo");
        barraMenu.add(menuArchivo);
        menuCod=new JMenu("Codificacion");
        barraMenu.add(menuCod);


        //Botones del Menu Archivo
        abrir= new JMenuItem("Abrir");
        abrir.setToolTipText("Permite abrir un fichero de texto");
        abrir.addActionListener(this);
        menuArchivo.add(abrir);

        guardar=new JMenuItem("Guardar/Actualizar");
        guardar.setToolTipText("Guarda y actualiza el fichero de texto");
        guardar.addActionListener(this);
        menuArchivo.add(guardar);

        guardarComo=new JMenuItem("Guardar como...");
        guardarComo.setToolTipText("Guarda el texto con el nombre que quieras");
        guardarComo.addActionListener(this);
        menuArchivo.add(guardarComo);

        exportar=new JMenuItem("Exportar a gif");
        exportar.setToolTipText("Exporta la partitura a un GIF");
        exportar.addActionListener(this);
        //exportar.setEnabled(false);
        menuArchivo.add(exportar);

        salir=new JMenuItem("Salir");
        salir.setToolTipText("Sale del programa");
        salir.addActionListener(this);
        menuArchivo.add(salir);

        //Botones del Menu Codificacion
        parentesis= new JMenuItem("Parentesis");
        parentesis.setToolTipText("(5),5,...");
        parentesis.addActionListener(this);
        menuCod.add(parentesis);

        simbolos= new JMenuItem("Simbolos Matematicos");
        simbolos.setToolTipText("-5,5,...");
        simbolos.addActionListener(this);
        menuCod.add(simbolos);


        //asignamos la barra de menus al frame
        this.setJMenuBar(barraMenu);
    }

     /**
     * Metodo que "repinta" las etiquetas segun las notas de la cancion
     * @param a el arrayList que contiene las notas de la cancion
     */
    void crearimages(ArrayList a)
    {
        System.out.println(a.size()+" "+imnotas.size());
        for(int i=0;i<a.size();i++)
        {
           imnotas.add(new JLabel(""+((Nota)(a.get(i))).getnum()));
           (imnotas.get(i)).setIcon(new ImageIcon(getClass().getResource((((Nota)(a.get(i))).getpath()))));
        }
    }

     /**
     * Metodo que carga el formulario vacio, esto se usa cuando se abre el programa
     */
    void cargainicial()
    {

        codLabel.setText("Necesita introducir un archivo");
        actualizar.setIcon(new ImageIcon(getClass().getResource("refresh.gif")));
        actualizar.setBackground(Color.white);
        actualizar.setBorder(BorderFactory.createLineBorder(Color.white));
        actualizar.setMargin(insets());
        actualizar.setActionCommand("bactualizar");
        actualizar.addActionListener(this);


        this.ponerMenu();
        this.setLayout(new BorderLayout());
        zonaNorte.setBackground(Color.white);
        this.add(zonaNorte, BorderLayout.NORTH);
        zonaTexto.setBackground(Color.white);
        notastxt.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(cajaNotas, BorderLayout.CENTER);

        zonaNorte.setLayout(new BorderLayout());
        zonaNorte.add(zonaTexto, BorderLayout.CENTER);

        zonaTexto.setLayout(new FlowLayout());
        zonaTexto.add(notastxt);


        menuBotones.setLayout(new BorderLayout());
        menuBotones.setBackground(Color.white);
        menuBotones.add(actualizar,BorderLayout.WEST);
        menuBotones.add(codLabel,BorderLayout.CENTER);

        zonaNorte.add(menuBotones, BorderLayout.NORTH);
        cajaNotas.setBackground(Color.white);
        cajaNotas.setLayout(new GridLayout(10,16));

        for (int i = 0; i<160;i++)
        {
            JLabel a = new JLabel();
            a.setIcon(new ImageIcon(getClass().getResource("120.gif")));
            a.setDisplayedMnemonic(i);
            imnotas.add(a);
        }

      for(int j=0;j<16;j++)
        {
            for(int i=0;i<10;i++)
            {
                cajaNotas.add(imnotas.get((j*10)+i));
            }
        }

        this.setSize(400, 400);

        this.setVisible(true);

    }


     /**
     * Metodo pinta el formulario con los valores cargados del archivo
     * @param a es la Partitura que se va a repintar
     * @param b es el valor de la nota, a partir de la cual, devuelve la ruta
     */
    void cargar(Partitura a,int b)
    {


        if(b==1)
        {
          codLabel.setText("Codificacion: Parentesis");
          notastxt.getText();
          notastxt.setText(a.toString2());
        }
        else
        {
          codLabel.setText("Codificacion: matemática");
          notastxt.getText();
          notastxt.setText(a.toString());
        }


        int j = 0;
        int i = 0;
        int z = 0;


            for(i=0;i<Cancion.notas.size();i++)
            {
                String bf = Cancion.notas.get(i).imagename;

              (imnotas.get((j*16)+z)).setIcon(new ImageIcon(getClass().getResource(bf)));
               z++;
               if(Cancion.notas.get(i).getnum()==120)
               {
                   j++;
                   z=0;
               }
            }

        Dimension pantalla = new Dimension(900,700);
        this.setMinimumSize(pantalla);
        this.setVisible(true);
    }


    /**
     * 
     * @param evento es el click del raton
     */
    public void actionPerformed(ActionEvent evento)
     {

     String command=evento.getActionCommand();

         if(command.equals("Abrir"))
         {
            JFileChooser fc= new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.addChoosableFileFilter( new FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));
            fc.showOpenDialog(this);
            ArchivoSeleccionado = fc.getSelectedFile();


                int cod = 0;
                Cancion.notas.clear();
                cod = Cancion.cargardeArchivo(ArchivoSeleccionado.getPath(), 1);

                this.cargar(Cancion,cod);
                //this.repaint();



         }
        else if(command.equals("Guardar/Actualizar"))
         {
                try{
                int cod = 0;
                FileWriter guardador = new FileWriter(ArchivoSeleccionado);
                guardador.write(notastxt.getText(),0,(notastxt.getText()).length());
                guardador.close();
                Cancion.notas.clear();
                cod = Cancion.cargardeArchivo(ArchivoSeleccionado.getPath(), 1);

                this.cargar(Cancion,cod);

                }
                catch (Exception ex_3)
                {
                    System.out.println (ex_3.getMessage());
                }


                //System.out.println("Archivo "+ArchivoSeleccionado.getName()+" guardado: "+notastxt.getText());
         }
              else if(command.equals("Guardar como..."))
         {

            JFileChooser fc= new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.addChoosableFileFilter( new FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));
            fc.showSaveDialog(this);
            ArchivoSeleccionado=fc.getSelectedFile();

            PrintWriter escritor;
            try
            {
                escritor =new PrintWriter(new FileWriter(ArchivoSeleccionado));
                notastxt.write(escritor);
            }
            catch(Exception e)
            {}
         }
              else if(command.equals("Exportar a gif"))
         {


            try{
            int rows = Cancion.filasCancion();  
            int cols = Cancion.columnasCancion();
            int chunks = (rows+1) * cols;

            int chunkWidth, chunkHeight;
            File[] imgFiles = new File[chunks];
            File blank = new File("src/harmotraductor/120.gif");

            for (int i = 0; i < chunks; i++) {
                imgFiles[i] = blank;
            }

            int j = 0;
            int z = 0;
            for(int i=0;i<Cancion.notas.size();i++)
            {

               z++;
               if(Cancion.notas.get(i).getnum()!=120)
               {
                String bf = "src/harmotraductor/"+Cancion.notas.get(i).imagename;
                imgFiles[(((j*cols)+z)-1)] = new File(bf);
               }
                else
               {
                  j++;
                  z=0;
                }

            }

           //creating a bufferd image array from image files
            BufferedImage[] buffImages = new BufferedImage[chunks];
            for (int i = 0; i < chunks; i++) {

                buffImages[i] = ImageIO.read(imgFiles[i]);

            }

            type = buffImages[2].getType();
            chunkWidth = buffImages[2].getWidth();
            chunkHeight = buffImages[2].getHeight();

            //Initializing the final image
            BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*(rows+1), type);
            int num = 0;
            for (int i = 0; i < (rows+1); i++) {
                for (j = 0; j < cols; j++) {
                    finalImg.createGraphics().drawImage(buffImages[num], chunkWidth*j, chunkHeight*i, null);
                    num++;
                }
            }
            System.out.println("Imagen creada");
            ImageIO.write(finalImg, "gif", new File((ArchivoSeleccionado.getPath()).replace(".txt",".gif")));
             }
            catch (Exception ex_3)
                {
                    System.out.println (ex_3.getMessage());}
         }

         else if(command.equals("Salir"))
         {

                System.exit(0);
         }

              else if(command.equals("bactualizar"))
         {

                try{
                int cod = 0;
                FileWriter guardador = new FileWriter(ArchivoSeleccionado);
                guardador.write(notastxt.getText(),0,(notastxt.getText()).length());
                guardador.close();
                Cancion.notas.clear();
                cod = Cancion.cargardeArchivo(ArchivoSeleccionado.getPath(), 1);

                this.cargar(Cancion,cod);

                }
                catch (Exception ex_3)
                {
                    System.out.println (ex_3.getMessage());
                }
         }

         else if(command.equals("Parentesis"))
         {
                String nuevanotas = new String();
                nuevanotas = notastxt.getText();
                nuevanotas = nuevanotas.replace("-10","(10)");
                nuevanotas = nuevanotas.replace("-9","(9)");
                nuevanotas = nuevanotas.replace("-8","(8)");
                nuevanotas = nuevanotas.replace("-7","(7)");
                nuevanotas = nuevanotas.replace("-6","(6)");
                nuevanotas = nuevanotas.replace("-5","(5)");
                nuevanotas = nuevanotas.replace("-4","(4)");
                nuevanotas = nuevanotas.replace("-3","(3)");
                nuevanotas = nuevanotas.replace("-2","(2)");
                nuevanotas = nuevanotas.replace("-1","(1)");
                notastxt.setText(nuevanotas);
                codLabel.setText("Codificacion: Parentesis");
         }

         else if(command.equals("Simbolos Matematicos"))
         {
                String nuevanotas = new String();
                nuevanotas = notastxt.getText();
                nuevanotas = nuevanotas.replace("(","-");
                nuevanotas = nuevanotas.replace(")","");
                notastxt.setText(nuevanotas);
                codLabel.setText("Codificacion: matemática");

         }
}

}