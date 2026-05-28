import java.util.Random;

public class PokemonGenerico extends Pokemon {
    public PokemonGenerico(int nPokedex, TipoPokemon tipo, String nombre, int nivel, int vidaMaxima, int ataque, int defensa, String sprite, int nivelEvo, int idEvo) {
        super(nPokedex, tipo, nombre, nivel, vidaMaxima, ataque, defensa, sprite, nivelEvo, idEvo);
        ataques[0] = new Ataque("Placaje", 40, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
        ataques[1] = new Ataque("Golpe " + tipo.toString(), 50, tipo, EstadoAlterado.NINGUNO, 0);
        ataques[2] = new Ataque("Ataque Fuerte", 80, tipo, EstadoAlterado.NINGUNO, 0);
        ataques[3] = new Ataque("Foco Energía", 0, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
    }

    @Override
    public String atacar(Pokemon rival, Ataque atq) {
        int danyo = atq.getPoder() > 0 ? atq.getPoder() + this.statAtaque : 0;
        StringBuilder log = new StringBuilder(this.nombre + " usa " + atq.getNombre() + "!\n");
        
        if (atq.getPoder() > 0) {
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