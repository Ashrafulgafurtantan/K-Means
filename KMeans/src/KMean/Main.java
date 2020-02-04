package KMean;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int number;

    public static int  calculateDistence(int [] red,int [] green,int [] blue,BufferedImage img,int i,int j){


        int pixel = img.getRGB(j, i);
        int alpha = (pixel >> 24) & 0xff;
        int Red = (pixel >> 16) & 0xff;
        int Green = (pixel >> 8) & 0xff;
        int Blue = (pixel) & 0xff;


        int Position=0;
        double MinDistence=99999;
        for(int k=0;k<number;k++)
        {
            int a=red[k]-Red;
            int b=green[k]-Green;
            int c=blue[k]-Blue;
            double calculatedDistence =Math.sqrt(Math.pow(a,2) + Math.pow(b,2)  +Math.pow(c,2));
            if(calculatedDistence<MinDistence) {
                Position = k;
                MinDistence=calculatedDistence;
            }
        }
      //  System.out.println("lowest = "+Position);
        return  Position;



    }


    public static void  ImageProcess(int [] red,int [] green,int [] blue,BufferedImage img)throws IOException{

        File f=new File("Testresult.jpg");
        int h = img.getHeight();
        int w = img.getWidth();


        //System.out.println(h +" height and weight is = "+w);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                int pixel = img.getRGB(j, i);
                int alpha = (pixel >> 24) & 0xff;

              int Position=  calculateDistence(red, green, blue, img, i, j);

                int pix=alpha<<24|red[Position]|green[Position]|blue[Position];
                try {
                    img.setRGB(j,i,new Color(red[Position],green[Position],blue[Position]).getRGB());

                } catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("SHIT");
                    System.out.println(i + ", " + j + ", " + pix);
                    int alphay = (pix >> 24) & 0xff;
                    int Redy = (pix >> 16) & 0xff;
                    int Greeny = (pix >> 8) & 0xff;
                    int Bluey = (pix) & 0xff;
                    System.out.println(Redy + ", " + Greeny + ", " + Bluey);
                    e.printStackTrace();
                }



            }
        }
        ImageIO.write(img, "jpg", f);

    }


    public static void MakeColor(int [] red,int [] green,int [] blue)
    {
        Random rand = new Random();
        for(int i=0;i<number;i++)
        {
            int n = rand.nextInt(256);
            red[i]=n;
        }
        for(int i=0;i<number;i++)
        {
            int n = rand.nextInt(256);
            green[i]=n;
        }
        for(int i=0;i<number;i++)
        {
            int n = rand.nextInt(256);
            blue[i]=n;
        }
        System.out.println("All "+number+" colors:" );
        for(int i=0;i<number;i++)
        {
            System.out.println("Red = "+red[i]+",Green = "+green[i]+",Blue = "+blue[i]);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedImage img=null;
        img = ImageIO.read(new File("test.jpg"));
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer = ");
        number= sc.nextInt();
        int [] red=new int [number];
        int [] green=new int [number];
        int [] blue=new int [number];
        MakeColor(red,green,blue);
        ImageProcess(red,green,blue,img);

    }
}
