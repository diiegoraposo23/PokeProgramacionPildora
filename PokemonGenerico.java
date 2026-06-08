import java.util.Random;

public class PokemonGenerico extends Pokemon {
    public PokemonGenerico(int nPokedex, TipoPokemon tipo, String nombre, int nivel, int vidaMaxima, int ataque, int defensa, String sprite, int nivelEvo, int idEvo) {
        super(nPokedex, tipo, nombre, nivel, vidaMaxima, ataque, defensa, sprite, nivelEvo, idEvo);
        ataques[0] = new Ataque("Placaje", 40, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
        ataques[1] = new Ataque("Golpe " + tipo.toString(), 50, tipo, EstadoAlterado.NINGUNO, 0);
        ataques[2] = new Ataque("Ataque Fuerte", 80, tipo, EstadoAlterado.NINGUNO, 0);
        ataques[3] = new Ataque("Foco Energía", 0, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
    }

}