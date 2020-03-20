/**
 * Clasa Catalog (impreuna cu Document)
 * implementeaza interfata Serializabile
 * pentru ca obiectele create vor fi serializate (si deserializate)
 * folosind clasele ObjectInputStream si ObjectOutputStream
 */
package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void add(Document document) {
        documents.add(document);
    }

    public Document findById(String id) {
        return documents.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public String getPath() {
        return path;
    }

    /**
     *
     * @param document
     * @return
     * containsDocument verifica daca documentul dat se afla in catalog
     */
    public boolean containsDocument(Document document) {
        for (Document doc : this.documents)
            if (doc == document)
                return true;
        return false;
    }
}

