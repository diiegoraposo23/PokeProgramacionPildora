import java.util.Random;

public class PokemonFuego extends Pokemon {
    public PokemonFuego(int nPokedex, String nombre, int nivel, int vidaMaxima, int ataque, int defensa, String sprite, int nivelEvo, int idEvo) {
        super(nPokedex, TipoPokemon.FUEGO, nombre, nivel, vidaMaxima, ataque, defensa, sprite, nivelEvo, idEvo);
        ataques[0] = new Ataque("Placaje", 40, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
        ataques[1] = new Ataque("Ascuas", 40, TipoPokemon.FUEGO, EstadoAlterado.QUEMADO, 10);
        ataques[2] = new Ataque("Lanzallamas", 90, TipoPokemon.FUEGO, EstadoAlterado.QUEMADO, 10);
        ataques[3] = new Ataque("Pantalla Humo", 0, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
    }
}