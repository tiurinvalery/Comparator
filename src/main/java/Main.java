import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;




public class Main {
    public static int diference = 1;
    public static double prob = 0.9;//согласно 10% взятых с задания

    public static void main(String args[]) {
      BufferedImage img1 = null;
      try{
          final String imgSource1 = "file:///D:/javaP/MyComporator/image/1.jpg";
          URL url = new URL(imgSource1);
          img1=ImageIO.read(url);
      } catch (IOException e){
          e.printStackTrace();
      }
        BufferedImage img2 = null;
        try{
            final String imgSource2 = "file:///D:/javaP/MyComporator/image/2.jpg";
            URL url2 = new URL(imgSource2);
            img2=ImageIO.read(url2);
        } catch (IOException e){
            e.printStackTrace();
        }
        myCompare(img1,img2);
    }

    private static Point myCompare(BufferedImage img1, BufferedImage img2) {
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();

        int[] r = new int[256];
        int[] g = new int[256];
        int[] b = new int[256];

        for (int i = 0; i < w1; i++) {
            for (int j = 0; j < h1; j++) {
                int[] rgb = img1.getRaster().getPixel(i, j, new int[3]);

                r[rgb[0]]++;
                g[rgb[1]]++;
                b[rgb[2]]++;
            }
        }
        int pixel_sum = 0;

        for (int i = 0; i < 255; i++) {
            if (r[i] == 0 && g[i] == 0 && b[i] == 0) continue;
            pixel_sum++;
        }
        pixel_sum = pixel_sum * 3;

        int compare_counter = 0;

        int w2 = img2.getWidth();
        int h2 = img2.getHeight();
        int max_value_cmp_counter = 0;
        Point target_point = null;

        for (int x = 0; x < (w2 - w1) + 1; x++) {   // x cord of point
            for (int y = 0; y < (h2 - h1) + 1; y++) { // y cord of point
                int[] r_s = new int[256];
                int[] g_s = new int[256];
                int[] b_s = new int[256];

                for (int i = 0; i < w1; i++) {
                    for (int j = 0; j < h1; j++) {
                        int[] rgb = img2.getRaster().getPixel(+i, y + j, new int[3]);

                        r_s[rgb[0]]++;
                        g_s[rgb[1]]++;
                        b_s[rgb[3]]++;
                    }
                }

                int cmp_counter = 0;

                for (int i = 0; i < 255; i++) {
                    if (r[i] == 0 && b[i] == 0 && g[i] == 0) continue;
                    if (r_s[i] == 0 && b_s[i] == 0 && g_s[i] == 0) continue;

                    if (r[i] == r_s[i]) cmp_counter++;
                    if (g[i] == g_s[i]) cmp_counter++;
                    if (b[i] == b_s[i]) cmp_counter++;

                    if (max_value_cmp_counter < cmp_counter) {
                        max_value_cmp_counter = cmp_counter;
                        target_point = new Point(x, y);
                    }
                }
            }
            if (target_point != null) {
                if (max_value_cmp_counter > pixel_sum * prob) {
                   return target_point;
                } else {
                    System.out.println("Не найдено значительного сходства в точке с кординатами х: "+target_point.x+ " y: "+target_point.y);
                }
            }

        }
        return null;
    }
}
