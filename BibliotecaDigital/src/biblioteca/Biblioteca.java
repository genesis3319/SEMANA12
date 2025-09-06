/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

/**
 *
 * @author usuario
 */
import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {
    private final Map<String, Libro> catalogoPorIsbn;
    private final Map<String, Usuario> usuariosPorId;
    private final Set<String> isbnsPrestados;

    public Biblioteca() {
        catalogoPorIsbn = new HashMap<>();
        usuariosPorId = new HashMap<>();
        isbnsPrestados = new HashSet<>();
    }

    public void anadirLibro(Libro libro) {
        if (libro == null) throw new IllegalArgumentException("Libro nulo");
        if (catalogoPorIsbn.containsKey(libro.getIsbn())) {
            throw new IllegalArgumentException("Ya existe un libro con ese ISBN");
        }
        catalogoPorIsbn.put(libro.getIsbn(), libro);
    }

    public void quitarLibro(String isbn) {
        if (isbnsPrestados.contains(isbn)) {
            throw new IllegalStateException("No se puede eliminar un libro que está prestado");
        }
        if (catalogoPorIsbn.remove(isbn) == null) {
            throw new IllegalArgumentException("No existe un libro con ese ISBN");
        }
    }

    public void registrarUsuario(Usuario u) {
        if (u == null) throw new IllegalArgumentException("Usuario nulo");
        if (usuariosPorId.containsKey(u.getId())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese id");
        }
        usuariosPorId.put(u.getId(), u);
    }

    public void darBajaUsuario(String id) {
        Usuario u = usuariosPorId.get(id);
        if (u == null) throw new IllegalArgumentException("Usuario no existe");
        if (!u.getIsbnsPrestados().isEmpty()) {
            throw new IllegalStateException("El usuario tiene libros prestados");
        }
        usuariosPorId.remove(id);
    }

    public void prestarLibro(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        Libro l = catalogoPorIsbn.get(isbn);

        if (u == null) throw new IllegalArgumentException("Usuario no existe");
        if (l == null) throw new IllegalArgumentException("Libro no existe");
        if (isbnsPrestados.contains(isbn)) throw new IllegalStateException("El libro ya está prestado");

        u.prestarLibro(isbn);
        isbnsPrestados.add(isbn);
    }

    public void devolverLibro(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        if (u == null) throw new IllegalArgumentException("Usuario no existe");
        if (!u.getIsbnsPrestados().contains(isbn)) {
            throw new IllegalArgumentException("El usuario no tiene prestado ese libro");
        }
        boolean removed = u.devolverLibro(isbn);
        if (removed) {
            isbnsPrestados.remove(isbn);
        }
    }

    public List<Libro> buscarPorTitulo(String texto) {
        String t = texto == null ? "" : texto.toLowerCase();
        return catalogoPorIsbn.values().stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(t))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarPorAutor(String texto) {
        String t = texto == null ? "" : texto.toLowerCase();
        return catalogoPorIsbn.values().stream()
                .filter(l -> l.getAutor().toLowerCase().contains(t))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarPorCategoria(String texto) {
        String t = texto == null ? "" : texto.toLowerCase();
        return catalogoPorIsbn.values().stream()
                .filter(l -> l.getCategoria().toLowerCase().contains(t))
                .collect(Collectors.toList());
    }

    public List<Libro> listarPrestados(String idUsuario) {
        Usuario u = usuariosPorId.get(idUsuario);
        if (u == null) return Collections.emptyList();
        return u.getIsbnsPrestados().stream()
                .map(isbn -> catalogoPorIsbn.get(isbn))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}

