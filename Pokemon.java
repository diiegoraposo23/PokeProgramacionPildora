import java.io.Serializable;
import java.util.Objects;

// Añadido Serializable
public abstract class Pokemon implements Comparable<Pokemon>, Serializable { 
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

    protected int nivelEvolucion;
    protected int idEvolucion;

    public Pokemon (int nPokedex, TipoPokemon tipo, String nombre, int nivel, int vidaMaxima, int statAtaque, int statDefensa, String rutaSprite, int nivelEvolucion, int idEvolucion) {
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
        this.nivelEvolucion = nivelEvolucion;
        this.idEvolucion = idEvolucion;
    }

    public String getBarraVidaVisual() {
        int longitudBarra = 10;
        int bloquesLlenos = (int) Math.round(((double) vidaActual / vidaMaxima) * longitudBarra);
        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < longitudBarra; i++) {
            barra.append(i < bloquesLlenos ? "█" : "░");
        }
        barra.append("]").append(vidaActual).append("/").append(vidaMaxima).append(" PS");
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
        if (estado == EstadoAlterado.QUEMADO || estado == EstadoAlterado.ENVENENADO) {
            int danyoEstado = Math.max(1, vidaMaxima / 8);
            this.vidaActual -= danyoEstado;
            if(this.vidaActual < 0) this.vidaActual = 0;
            return nombre + " sufre daño por estar " + estado + " (-" + danyoEstado + " PS).\n";
        }
        return "";
    }

    public abstract String atacar(Pokemon rival, Ataque ataqueUsado);
    public void recibirDanyo(int cantidad) {
        int danyoReal = cantidad - this.statDefensa;
        if (danyoReal <= 0) danyoReal = 1;
        this.vidaActual -= danyoReal;
        if (this.vidaActual < 0) this.vidaActual = 0;
    }

    public boolean estaVivo() { return this.vidaActual > 0; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pokemon pokemon = (Pokemon) obj;
        return nPokedex == pokemon.nPokedex;
    }

    @Override
    public int hashCode() { return Objects.hash(nPokedex); }

    @Override
    public int compareTo(Pokemon otro) { return Integer.compare(this.nPokedex, otro.nPokedex); }

    @Override
    public String toString() {
        return nombre + " (Lv." + nivel + ") [" + tipo + "] " + getBarraVidaVisual();
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getVidaActual() { return vidaActual; }
    public Ataque[] getAtaques() { return ataques; }
    public String getRutaSprite() { return rutaSprite; }
    public EstadoAlterado getEstado() { return estado; }
    public void setEstado(EstadoAlterado estado) { this.estado = estado; }
    public int getNivel() { return nivel; }
    public int getNivelEvolucion() { return nivelEvolucion; }
    public int getIdEvolucion() { return idEvolucion; }
    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int exp) { this.experiencia = exp; }
}