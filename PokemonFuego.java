import java.util.Random;

public class PokemonFuego extends Pokemon {
    public PokemonFuego(int nPokedex, String nombre, int nivel, int vidaMaxima, int ataque, int defensa, String sprite, int nivelEvo, int idEvo) {
        super(nPokedex, TipoPokemon.FUEGO, nombre, nivel, vidaMaxima, ataque, defensa, sprite, nivelEvo, idEvo);
        ataques[0] = new Ataque("Placaje", 40, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
        ataques[1] = new Ataque("Ascuas", 40, TipoPokemon.FUEGO, EstadoAlterado.QUEMADO, 10);
        ataques[2] = new Ataque("Lanzallamas", 90, TipoPokemon.FUEGO, EstadoAlterado.QUEMADO, 10);
        ataques[3] = new Ataque("Pantalla Humo", 0, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
    }

    @Override
    public String atacar(Pokemon rival, Ataque atq) {
        int danyo = atq.getPoder() > 0 ? atq.getPoder() + this.statAtaque : 0;
        StringBuilder log = new StringBuilder(this.nombre + " usa " + atq.getNombre() + "!\n");
        
        if (atq.getPoder() > 0) {
            if (atq.getTipo() == TipoPokemon.FUEGO && rival.tipo == TipoPokemon.PLANTA) {
                danyo *= 2; log.append("¡Es súper eficaz!\n");
            } else if (atq.getTipo() == TipoPokemon.FUEGO && rival.tipo == TipoPokemon.AGUA) {
                danyo /= 2; log.append("No es muy eficaz...\n");
            }
            rival.recibirDanyo(danyo);
        }
        
        if (atq.getEfectoSecundario() != EstadoAlterado.NINGUNO && rival.getEstado() == EstadoAlterado.NINGUNO) {
            if (new Random().nextInt(100) < atq.getProbabilidadEfecto()) {
                rival.setEstado(atq.getEfectoSecundario());
                log.append(rival.getNombre()).append(" ha sido ").append(atq.getEfectoSecundario()).append(".\n");
            }
        }
        return log.toString();
    }
}