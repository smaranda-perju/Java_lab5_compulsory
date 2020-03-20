package main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.server.ExportException;

public class Main {
    public static void main(String args[]) throws IOException, InvalidCatalogException, URISyntaxException, ClassNotFoundException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws IOException {
        try{
            Catalog catalog =
                    new Catalog("Java Resources", "d:/Users/smaranda/Desktop/catalog.ser");
            Document doc = new Document("java1", "Java Course 1",
                    "d:/Users/smaranda/Desktop/intro_slide_en.pdf", true);
            doc.addTag("type", "Slides");
            catalog.add(doc);

            CatalogUtil.save(catalog);
        } catch(Exception exc){
            System.out.println(exc);
        }

    }

    private void testLoadView() throws InvalidCatalogException, IOException, ClassNotFoundException, URISyntaxException {
        try{
            Catalog catalog = CatalogUtil.load("d:/Users/smaranda/Desktop/catalog.ser");
            Document doc = catalog.findById("java1");
            CatalogUtil.view(catalog,doc);
        } catch(Exception exc){
            System.out.println(exc);
        }

    }
}