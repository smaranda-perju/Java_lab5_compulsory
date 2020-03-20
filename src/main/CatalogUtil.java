/**
 *Clasa CatalogUtil se ocupa de operatiile externe ale catalogului
 */
package main;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    /**
     *
     * @param path
     * @return
     * @throws InvalidCatalogException
     * @throws IOException
     * @throws ClassNotFoundException
     * pentru metoda load am avut ca sursa de inspiratie:
     * https://teamtreehouse.com/community/loading-the-treetsser-file?fbclid=IwAR3bpnShqYrVSRo_BMDFDj_E3gP88O2ivZJbIFpOvCCKkG8ziJzW-UokGvE
     */
    public static Catalog load(String path)
            throws InvalidCatalogException, IOException, ClassNotFoundException {
        Catalog cat = new Catalog("new", path);
        try (
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            cat = (Catalog) ois.readObject();
        } catch (IOException io) {
            System.out.println("Error reading file");
            io.printStackTrace();
        } catch (ClassNotFoundException cn) {
            System.out.println("Error loading catalog");
            cn.printStackTrace();
        }
        return cat;

    }

    /**
     *
     * @param catalog
     * @param document
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws URISyntaxException
     * Metoda view verifica daca  documentul apartine catalogului.
     * Daca nu apartine are loc exceptia IllegalArgumentException.
     * Daca apartine verifica daca documentul se afla la o adresa locala.
     * Daca da, atunci creez un obiect de tip file cu adresa documentului
     * si il deschid folosind functia open din clasa Desktop.
     * Daca documentul se afla la o adresa web, creez un obiect de tipul URI
     * cu adresa documentului si il deschid cu functia browse din clasa Desktop
     */
    public static void view(Catalog catalog, Document document) throws IOException, IllegalArgumentException, URISyntaxException {
        if (catalog.containsDocument(document)) {
            Desktop desktop = Desktop.getDesktop();
            if (document.isLocal()) {
                File myFile = new File(document.getLocation());
                desktop.open(myFile);
            } else {
                URI uri = new URI(document.getLocation());
                desktop.browse(uri);
            }
        } else
            throw new IllegalArgumentException("The document with the path " + document.getLocation() + " is not in the catalog");

    }

}
