import java.io.*;

public class GestorGuardado {
    private static final String ARCHIVO = "partida.dat";

    public static void guardarPartida(Entrenador jugador) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(jugador);
            System.out.println("Partida guardada correctamente en " + ARCHIVO);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static Entrenador cargarPartida() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return null; 

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (Entrenador) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }
}