import java.io.Serializable;
import java.util.ArrayList;

public class Entrenador implements Serializable {
    private String nombre;
    private ArrayList<Pokemon> equipo;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.equipo = new ArrayList<>();
    }

    public boolean agregarPokemon(Pokemon p) {
        if (equipo.size() < 6) {
            equipo.add(p); return true;
        }
        return false;
    }
    
    public void liberarPokemon(int indice) {
        if (indice >= 0 && indice < equipo.size()) {
            equipo.remove(indice);
        }
    }

    public String mostrarEquipo() {
        if (equipo.isEmpty()) return "El equipo está vacío.";
        StringBuilder sb = new StringBuilder("Equipo de " + nombre + ":\n");
        for (int i = 0; i < equipo.size(); i++) {
            sb.append((i+1)).append(". ").append(equipo.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
    
    public String getNombre() { return nombre; }
    public ArrayList<Pokemon> getEquipo() { return equipo; }
}