import java.util.Random;

public class PokemonAgua extends Pokemon {
    public PokemonAgua(int nPokedex, String nombre, int nivel, int vidaMaxima, int ataque, int defensa, String sprite, int nivelEvo, int idEvo) {
        super(nPokedex, TipoPokemon.AGUA, nombre, nivel, vidaMaxima, ataque, defensa, sprite, nivelEvo, idEvo);
        ataques[0] = new Ataque("Placaje", 40, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
        ataques[1] = new Ataque("Pistola Agua", 40, TipoPokemon.AGUA, EstadoAlterado.NINGUNO, 0);
        ataques[2] = new Ataque("Rayo Burbuja", 65, TipoPokemon.AGUA, EstadoAlterado.NINGUNO, 0);
        ataques[3] = new Ataque("Hidrobomba", 110, TipoPokemon.AGUA, EstadoAlterado.NINGUNO, 0);
    }

}