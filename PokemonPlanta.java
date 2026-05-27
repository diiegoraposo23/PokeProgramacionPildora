import java.util.Random;

public class PokemonPlanta extends Pokemon {
    public PokemonPlanta(int nPokedex, String nombre, int nivel, int vidaMaxima, int ataque, int defensa, String sprite) {
        super(nPokedex, TipoPokemon.PLANTA, nombre, nivel, vidaMaxima, ataque, defensa, sprite);
        ataques[0] = new Ataque("Placaje", 40, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
        ataques[1] = new Ataque("Látigo Cepa", 45, TipoPokemon.PLANTA, EstadoAlterado.NINGUNO, 0);
        ataques[2] = new Ataque("Hoja Afilada", 55, TipoPokemon.PLANTA, EstadoAlterado.NINGUNO, 0);
        ataques[3] = new Ataque("Polvo Veneno", 0, TipoPokemon.VENENO, EstadoAlterado.ENVENENADO, 100);
    }

    @Override
    public String atacar(Pokemon rival, Ataque atq) {
        int danyo = atq.getPoder() > 0 ? atq.getPoder() + this.statAtaque : 0;
        StringBuilder log = new StringBuilder(this.nombre + " usa " + atq.getNombre() + "!\n");
        
        if (atq.getPoder() > 0) {
            if (atq.getTipo() == TipoPokemon.PLANTA && rival.tipo == TipoPokemon.AGUA) {
                danyo *= 2; log.append("¡Es súper eficaz!\n");
            } else if (atq.getTipo() == TipoPokemon.PLANTA && rival.tipo == TipoPokemon.FUEGO) {
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