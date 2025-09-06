/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Registrar usuarios
        biblioteca.registrarUsuario(new Usuario("U1", "Denis"));
        biblioteca.registrarUsuario(new Usuario("U2", "Richard"));

        // Añadir libros
        biblioteca.anadirLibro(new Libro("ISBN-001", "Clean Code", "Robert C. Martin", "Software"));
        biblioteca.anadirLibro(new Libro("ISBN-002", "Effective Java", "Joshua Bloch", "Java"));

        // Prestar libros
        biblioteca.prestarLibro("U1", "ISBN-001");
        biblioteca.prestarLibro("U2", "ISBN-002");

        // Listar libros prestados de Ana
        System.out.println("Libros prestados por Denis:");
        biblioteca.listarPrestados("U1").forEach(System.out::println);

        // Devolver libro
        biblioteca.devolverLibro("U1", "ISBN-001");

        // Buscar por autor
        System.out.println("\nLibros de Bloch:");
        biblioteca.buscarPorAutor("Bloch").forEach(System.out::println);

        // Buscar por categoría
        System.out.println("\nLibros de categoría Software:");
        biblioteca.buscarPorCategoria("Software").forEach(System.out::println);
    }
}
