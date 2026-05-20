public class PokemonAgua extends Pokemon {
    public PokemonAgua(int nPokedex, String nombre, int nivel, int vidaMaxima, int ataque, int defensa, String sprite) {
        super(nPokedex, TipoPokemon.AGUA, nombre, nivel, vidaMaxima, ataque, defensa, sprite);
        ataques[0] = new Ataque("Placaje", 40, TipoPokemon.NORMAL, EstadoAlterado.NINGUNO, 0);
        ataques[1] = new Ataque("Pistola Agua", 40, TipoPokemon.AGUA, EstadoAlterado.NINGUNO, 0);
        ataques[2] = new Ataque("Rayo Burbuja", 65, TipoPokemon.AGUA, EstadoAlterado.NINGUNO, 0);
        ataques[3] = new Ataque("Hidrobomba", 110, TipoPokemon.AGUA, EstadoAlterado.NINGUNO, 0);
    }

    @Override
    public String atacar(Pokemon rival, Ataque atq) {
        int danyo = atq.getPoder() > 0 ? atq.getPoder() + this.statAtaque : 0;
        StringBuilder log = new StringBuilder(this.nombre + " usa " + atq.getNombre() + "!\n");

        if (atq.getPoder() > 0) {
            if (atq.getTipo() == TipoPokemon.AGUA && rival.tipo == TipoPokemon.FUEGO) {
                danyo *= 2; log.append("¡Es super efectivo!\n");
            } else if (atq.getTipo() == TipoPokemon.AGUA && rival.tipo == TipoPokemon.PLANTA) {
                danyo /= 2; log.append("No es muy eficaz..\n");
            }
            rival.recibirDanyo(danyo);
        }
        return log.toString();
    }
}
