import java.util.Random;

public class PokemonAgua extends Pokemon {
    public PokemonAgua(int nPokedex, String nombre, int nivel, int vidaMaxima, int ataque, int defensa, String sprite, int nivelEvo, int idEvo) {
        super(nPokedex, TipoPokemon.AGUA, nombre, nivel, vidaMaxima, ataque, defensa, sprite, nivelEvo, idEvo);
        ataques[0] = new Ataque("Placaje", 40, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
        ataques[1] = new Ataque("Pistola Agua", 40, TipoPokemon.AGUA, EstadoAlterado.NINGUNO, 0);
        ataques[2] = new Ataque("Rayo Burbuja", 65, TipoPokemon.AGUA, EstadoAlterado.NINGUNO, 0);
        ataques[3] = new Ataque("Hidrobomba", 110, TipoPokemon.AGUA, EstadoAlterado.NINGUNO, 0);
    }

       @Override
    public String atacar(Pokemon rival, Ataque atq) {
        int danyoBase = atq.getPoder() > 0 ? atq.getPoder() + this.statAtaque : 0;
        StringBuilder log = new StringBuilder(this.nombre + " usa " + atq.getNombre() + "!\n");

        if (atq.getPoder() > 0) {
            // Consulta a la matriz usando el tipo de ataque y el tipo del rival.
            double multiplicador = TablaEfectividad.obtenerMultiplicador(atq.getTipo(), rival.tipo);

            // Calcula el daño final (danyoBase * 0.5, * 1.0, * 2.0 o * 0.0)
            int danyoFinal = (int) (danyoBase * multiplicador);

            // Escribe los mensajes según el multiplicador.
            if (multiplicador > 1.0) {
                log.append("¡Es súper efectivo!");
            } else if (multiplicador < 1.0 && multiplicador > 0.0) {
                log.append("No es muy eficaz.\n"); 
            } else if (multiplicador == 0.0) {
                log.append("No afecta a ").append(rival.getNombre()).append("...\n");
            }

            // Aplica el daño.
            rival.recibirDanyo(danyoFinal);
        }

        // Aplicar efectos secundarios si los hay.
        if (atq.getEfectoSecundario() != EstadoAlterado.NINGUNO && rival.getEstado() == EstadoAlterado.NINGUNO) {
            if (new Random().nextInt(100) < atq.getProbabilidadEfecto()) {
                rival.setEstado(atq.getEfectoSecundario());
                log.append(rival.getNombre()).append(" ha sido ").append(atq.getEfectoSecundario()).append(".\n");
            }
        }
        return log.toString();
    }
}