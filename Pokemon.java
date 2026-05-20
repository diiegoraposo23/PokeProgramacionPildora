public class Pokemon implements Comparable<Pokemon> {
    protected int nPokedex;
    protected TipoPokemon tipo;
    protected String nombre;
    protected int nivel;
    protected int experiencia;

    protected int vidaMaxima;
    protected int vidaActual;
    protected int statAtaque;
    protected int statDefensa;

    protected Ataque[] ataques;
    protected EstadoAlterado estado;
    protected String rutaSprite;

    public Pokemon(int nPokedex, TipoPokemon tipo, String nombre, int nivel, int vidaMaxima, int statAtaque, int statDefensa, String rutaSprite) {
        this.nPokedex = nPokedex;
        this.tipo = tipo;
        this.nombre = nombre;
        this.nivel = nivel;
        this.experiencia = 0;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
        this.statAtaque = statAtaque;
        this.statDefensa = statDefensa;
        this.ataques = new Ataque[4];
        this.estado = EstadoAlterado.NINGUNO;
        this.rutaSprite = rutaSprite;
    }

    public String getBarraVidaVisual() {
        int longitudBarra = 10;
        int bloquesLlenos = (int) Math.round(((double) vidaActual / vidaMaxima) * longitudBarra);
        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < longitudBarra; i++) {
            barra.append(i < bloquesLlenos ? "||" : " ");
        }

        barra.append("]").append(vidaActual).append("/").append(vidaMaxima).append("PS");
        if (estado != EstadoAlterado.NINGUNO) barra.append("[").append(estado).append("]");
        return barra.toString();
    }

    public String ganarExperiencia(int expGanada) {
        this.experiencia += expGanada;
        StringBuilder sb = new StringBuilder(nombre + " gana " + expGanada + " EXP.\n");
        if (this.experiencia >= 100) {
            this.experiencia -= 100;
            this.nivel++;
            this.vidaMaxima += 5;
            this.vidaActual = vidaMaxima;
            this.statAtaque += 2;
            this.statDefensa += 2;
            sb.append("i").append(nombre).append(" subió al Nivel ").append(nivel).append("!\n");
        }
        return sb.toString();
    }

    public String aplicarEfectosDeEstado() {
        if ()
    }

    
}
