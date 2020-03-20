/**
 *Clasa Document (impreuna cu Catalog)
 *implementeaza interfata Serializabile
 *pentru ca obiectele create vor fi serializate (si deserializate)
 *folosind clasele ObjectInputStream si ObjectOutputStream.
 */
package main;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String name;
    private String location;
    private boolean local; //retin daca e local sau nu documentul pentru ca sa fie mai usor in metoda view

    private Map<String, Object> tags = new HashMap<>();

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public Document(String id, String name, String location, boolean local) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.local = local;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public boolean isLocal() {
        return this.local;
    }
}
