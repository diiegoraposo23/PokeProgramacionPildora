public class TablaEfectividad {
    // Matriz 16x16.
    private static final double[][] matriz = new double[18][18];

    static {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                matriz[i][j] = 1.0;
            }
        }

        // Definición de todas las efectividades de 1º Generación.

        // Normal.
        setEfectividad(TipoPokemon.NORMAL, TipoPokemon.ROCA, 0.5);
        setEfectividad(TipoPokemon.NORMAL, TipoPokemon.FANTASMA, 0.0);

        // Fuego.
        setEfectividad(TipoPokemon.FUEGO, TipoPokemon.PLANTA, 2.0);
        setEfectividad(TipoPokemon.FUEGO, TipoPokemon.HIELO, 2.0);
        setEfectividad(TipoPokemon.FUEGO, TipoPokemon.BICHO, 2.0);
        setEfectividad(TipoPokemon.FUEGO, TipoPokemon.FUEGO, 0.5);
        setEfectividad(TipoPokemon.FUEGO, TipoPokemon.AGUA, 0.5);
        setEfectividad(TipoPokemon.FUEGO, TipoPokemon.ROCA, 0.5);
        setEfectividad(TipoPokemon.FUEGO, TipoPokemon.DRAGON, 0.5);

        // Agua.
        setEfectividad(TipoPokemon.AGUA, TipoPokemon.FUEGO, 2.0);
        setEfectividad(TipoPokemon.AGUA, TipoPokemon.TIERRA, 2.0);
        setEfectividad(TipoPokemon.AGUA, TipoPokemon.ROCA, 2.0);
        setEfectividad(TipoPokemon.AGUA, TipoPokemon.AGUA, 0.5);
        setEfectividad(TipoPokemon.AGUA, TipoPokemon.PLANTA, 0.5);
        setEfectividad(TipoPokemon.AGUA, TipoPokemon.DRAGON, 0.5);

        // Eléctrico.
        setEfectividad(TipoPokemon.ELECTRICO, TipoPokemon.AGUA, 2.0);
        setEfectividad(TipoPokemon.ELECTRICO, TipoPokemon.VOLADOR, 2.0);
        setEfectividad(TipoPokemon.ELECTRICO, TipoPokemon.ELECTRICO, 0.5);
        setEfectividad(TipoPokemon.ELECTRICO, TipoPokemon.PLANTA, 0.5);
        setEfectividad(TipoPokemon.ELECTRICO, TipoPokemon.DRAGON, 0.5);
        setEfectividad(TipoPokemon.ELECTRICO, TipoPokemon.TIERRA, 0.0);

        // Planta.
        setEfectividad(TipoPokemon.PLANTA, TipoPokemon.AGUA, 2.0);
        setEfectividad(TipoPokemon.PLANTA, TipoPokemon.TIERRA, 2.0);
        setEfectividad(TipoPokemon.PLANTA, TipoPokemon.ROCA, 2.0);
        setEfectividad(TipoPokemon.PLANTA, TipoPokemon.FUEGO, 0.5);
        setEfectividad(TipoPokemon.PLANTA, TipoPokemon.PLANTA, 0.5);
        setEfectividad(TipoPokemon.PLANTA, TipoPokemon.VENENO, 0.5);
        setEfectividad(TipoPokemon.PLANTA, TipoPokemon.VOLADOR, 0.5);
        setEfectividad(TipoPokemon.PLANTA, TipoPokemon.BICHO, 0.5);
        setEfectividad(TipoPokemon.PLANTA, TipoPokemon.DRAGON, 0.5);

        // Hielo.
        setEfectividad(TipoPokemon.HIELO, TipoPokemon.PLANTA, 2.0);
        setEfectividad(TipoPokemon.HIELO, TipoPokemon.TIERRA, 2.0);
        setEfectividad(TipoPokemon.HIELO, TipoPokemon.VOLADOR, 2.0);
        setEfectividad(TipoPokemon.HIELO, TipoPokemon.DRAGON, 2.0);
        setEfectividad(TipoPokemon.HIELO, TipoPokemon.AGUA, 0.5);
        setEfectividad(TipoPokemon.HIELO, TipoPokemon.HIELO, 0.5);

        // Lucha.
        setEfectividad(TipoPokemon.LUCHA, TipoPokemon.NORMAL, 2.0);
        setEfectividad(TipoPokemon.LUCHA, TipoPokemon.HIELO, 2.0);
        setEfectividad(TipoPokemon.LUCHA, TipoPokemon.ROCA, 2.0);
        setEfectividad(TipoPokemon.LUCHA, TipoPokemon.VENENO, 0.5);
        setEfectividad(TipoPokemon.LUCHA, TipoPokemon.VOLADOR, 0.5);
        setEfectividad(TipoPokemon.LUCHA, TipoPokemon.PSIQUICO, 0.5);
        setEfectividad(TipoPokemon.LUCHA, TipoPokemon.BICHO, 0.5);
        setEfectividad(TipoPokemon.LUCHA, TipoPokemon.FANTASMA, 0.0);

        // Veneno.
        setEfectividad(TipoPokemon.VENENO, TipoPokemon.PLANTA, 2.0);
        setEfectividad(TipoPokemon.VENENO, TipoPokemon.BICHO, 2.0);
        setEfectividad(TipoPokemon.VENENO, TipoPokemon.VENENO, 0.5);
        setEfectividad(TipoPokemon.VENENO, TipoPokemon.TIERRA, 0.5);
        setEfectividad(TipoPokemon.VENENO, TipoPokemon.ROCA, 0.5);
        setEfectividad(TipoPokemon.VENENO, TipoPokemon.FANTASMA, 0.5);

        // Tierra.
        setEfectividad(TipoPokemon.TIERRA, TipoPokemon.FUEGO, 2.0);
        setEfectividad(TipoPokemon.TIERRA, TipoPokemon.ELECTRICO, 2.0);
        setEfectividad(TipoPokemon.TIERRA, TipoPokemon.VENENO, 2.0);
        setEfectividad(TipoPokemon.TIERRA, TipoPokemon.ROCA, 2.0);
        setEfectividad(TipoPokemon.TIERRA, TipoPokemon.PLANTA, 0.5);
        setEfectividad(TipoPokemon.TIERRA, TipoPokemon.BICHO, 0.5);
        setEfectividad(TipoPokemon.TIERRA, TipoPokemon.VOLADOR, 0.0);

        // Volador.
        setEfectividad(TipoPokemon.VOLADOR, TipoPokemon.PLANTA, 2.0);
        setEfectividad(TipoPokemon.VOLADOR, TipoPokemon.LUCHA, 2.0);
        setEfectividad(TipoPokemon.VOLADOR, TipoPokemon.BICHO, 2.0);
        setEfectividad(TipoPokemon.VOLADOR, TipoPokemon.ELECTRICO, 0.5);
        setEfectividad(TipoPokemon.VOLADOR, TipoPokemon.ROCA, 0.5);

        // Psiquico.
        setEfectividad(TipoPokemon.PSIQUICO, TipoPokemon.LUCHA, 2.0);
        setEfectividad(TipoPokemon.PSIQUICO, TipoPokemon.VENENO, 2.0);
        setEfectividad(TipoPokemon.PSIQUICO, TipoPokemon.PSIQUICO, 0.5);

        // Bicho.
        setEfectividad(TipoPokemon.BICHO, TipoPokemon.PLANTA, 2.0);
        setEfectividad(TipoPokemon.BICHO, TipoPokemon.VENENO, 2.0);
        setEfectividad(TipoPokemon.BICHO, TipoPokemon.PSIQUICO, 2.0);
        setEfectividad(TipoPokemon.BICHO, TipoPokemon.FUEGO, 0.5);
        setEfectividad(TipoPokemon.BICHO, TipoPokemon.LUCHA, 0.5);
        setEfectividad(TipoPokemon.BICHO, TipoPokemon.VOLADOR, 0.5);
        setEfectividad(TipoPokemon.BICHO, TipoPokemon.FANTASMA, 0.5);

        // Roca.
        setEfectividad(TipoPokemon.ROCA, TipoPokemon.FUEGO, 2.0);
        setEfectividad(TipoPokemon.ROCA, TipoPokemon.HIELO, 2.0);
        setEfectividad(TipoPokemon.ROCA, TipoPokemon.VOLADOR, 2.0);
        setEfectividad(TipoPokemon.ROCA, TipoPokemon.BICHO, 2.0);
        setEfectividad(TipoPokemon.ROCA, TipoPokemon.LUCHA, 0.5);
        setEfectividad(TipoPokemon.ROCA, TipoPokemon.TIERRA, 0.5);

        // Fantasma.
        setEfectividad(TipoPokemon.FANTASMA, TipoPokemon.FANTASMA, 2.0);
        setEfectividad(TipoPokemon.FANTASMA, TipoPokemon.NORMAL, 0.0);
        setEfectividad(TipoPokemon.FANTASMA, TipoPokemon.PSIQUICO, 0.0); 

        // Dragón.
        setEfectividad(TipoPokemon.DRAGON, TipoPokemon.DRAGON, 2.0);
    }

    private static void setEfectividad(TipoPokemon atacante, TipoPokemon defensor, double multiplicador) {
        matriz[atacante.ordinal()][defensor.ordinal()] = multiplicador;
    }

    public static double obtenerMultiplicador(TipoPokemon tipoAtaque, TipoPokemon tipoDefensor) {
        return matriz[tipoAtaque.ordinal()][tipoDefensor.ordinal()];
    }
    
}
