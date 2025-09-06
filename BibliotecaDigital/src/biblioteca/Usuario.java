/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

/**
 *
 * @author usuario
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private final String id;
    private final String nombre;
    private final List<String> isbnsPrestados;

    public Usuario(String id, String nombre) {
        if (id == null || id.isEmpty() || nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Id y nombre no pueden ser nulos o vacíos");
        }
        this.id = id;
        this.nombre = nombre;
        this.isbnsPrestados = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    public List<String> getIsbnsPrestados() {
        return Collections.unmodifiableList(isbnsPrestados);
    }

    public void prestarLibro(String isbn) {
        isbnsPrestados.add(isbn);
    }

    public boolean devolverLibro(String isbn) {
        return isbnsPrestados.remove(isbn);
    }

    @Override
    public String toString() {
        return String.format("Usuario %s (%s)", nombre, id);
    }
}
