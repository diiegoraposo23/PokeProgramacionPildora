import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GestorPokedex {
    private HashMap<Integer, String[]> baseDatos = new HashMap<>();

    public void cargarDatosCSV(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                baseDatos.put(id, datos);
            }
            System.out.println("Pokédex cargada: " + baseDatos.size() + " Pokémon disponibles.");
        } catch (IOException e) {
            System.out.println("Error al cargar la Pokédex. ¿Existe el archivo pokemon.csv?");
        }
    }

    public int getTotalPokemon() {
        return baseDatos.size();
    }

    public Pokemon generarPokemon(int id, int nivel) {
        String[] d = baseDatos.get(id);
        if (d == null) return null; 

        String nombre = d[1];
        TipoPokemon tipo = TipoPokemon.valueOf(d[2]);
        int hp = Integer.parseInt(d[3]) + (nivel * 2);
        int atk = Integer.parseInt(d[4]) + nivel;
        int def = Integer.parseInt(d[5]) + nivel;
        String sprite = d[6];
        int nivelEvo = Integer.parseInt(d[7]);
        int idEvo = Integer.parseInt(d[8]);

        switch (tipo) {
            case FUEGO: 
                return new PokemonFuego(id, nombre, nivel, hp, atk, def, sprite, nivelEvo, idEvo);
            case AGUA: 
                return new PokemonAgua(id, nombre, nivel, hp, atk, def, sprite, nivelEvo, idEvo);
            case PLANTA: 
                return new PokemonPlanta(id, nombre, nivel, hp, atk, def, sprite, nivelEvo, idEvo);
            case NORMAL:
            case ELECTRICO:
            case HIELO:
            case LUCHA:
            case VENENO:
            case TIERRA:
            case VOLADOR:
            case PSIQUICO:
            case BICHO:
            case ROCA:
            case FANTASMA:
            case DRAGON:
                return new PokemonGenerico(id, tipo, nombre, nivel, hp, atk, def, sprite, nivelEvo, idEvo);
            default: 
                System.out.println("Error: Tipo desconocido (" + tipo + ") en el ID " + id);
                return null;
        }
    }
}