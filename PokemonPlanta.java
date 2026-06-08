import java.util.Random;

public class PokemonPlanta extends Pokemon {
    public PokemonPlanta(int nPokedex, String nombre, int nivel, int vidaMaxima, int ataque, int defensa, String sprite, int nivelEvo, int idEvo) {
        super(nPokedex, TipoPokemon.PLANTA, nombre, nivel, vidaMaxima, ataque, defensa, sprite, nivelEvo, idEvo);
        ataques[0] = new Ataque("Placaje", 40, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
        ataques[1] = new Ataque("Látigo Cepa", 45, TipoPokemon.PLANTA, EstadoAlterado.NINGUNO, 0);
        ataques[2] = new Ataque("Hoja Afilada", 55, TipoPokemon.PLANTA, EstadoAlterado.NINGUNO, 0);
        ataques[3] = new Ataque("Polvo Veneno", 0, TipoPokemon.VENENO, EstadoAlterado.ENVENENADO, 100);
    }

}