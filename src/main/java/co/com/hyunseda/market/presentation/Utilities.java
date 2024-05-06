package co.com.hyunseda.market.presentation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Utilidades varias utilizadas por otras clases
 *
 * @author Libardo
 */
public class Utilities {

    /**
     * Cargar una propiedadd de config.properties
     *
     * @param key llave de la propiedad
     * @return valor de la propiedad
     */
    public static String loadProperty(String key) {
        Properties prop = new Properties();
        InputStream is;

        try {
            is = new FileInputStream("./config.properties");
            prop.load(is);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo");
        }

        return prop.getProperty(key);
    }

    /**
     * Verifica si un String contiene sólo digitos
     *
     * @param str Cadena a verificvar
     * @return true si contiene sólo digitos, false en caso contrario
     */
    public static boolean isNumeric(String str) {

        boolean resultado;

        try {
            Integer.parseInt(str);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    /**
     * Carga una imagen a partir de la url
     * @param name url de la imagen
     * @return Un icono de la imagen
     */
    public static ImageIcon loadImageFromCloud(String name) {

        String imageUrl = name;

        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(ImageIO.read(url));
            return icon;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
