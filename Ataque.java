import java.io.Serializable;

// Serializable para poder guardar el progreso
public class Ataque implements Serializable {
    private String nombre;
    private int poder;
    private TipoPokemon tipo;
    private EstadoAlterado efectoSecundario;
    private int probabilidadEfecto;

    public Ataque(String nombre, int poder, TipoPokemon tipo, EstadoAlterado efectoSecundario, int probabilidadEfecto) {
        this.nombre = nombre;
        this.poder = poder;
        this.tipo = tipo;
        this.efectoSecundario = efectoSecundario;
        this.probabilidadEfecto = probabilidadEfecto;
    }

    public String getNombre() { return nombre; }
    public int getPoder() { return poder; }
    public TipoPokemon getTipo() { return tipo; }
    public EstadoAlterado getEfectoSecundario() { return efectoSecundario; }
    public int getProbabilidadEfecto() { return probabilidadEfecto; }
}
