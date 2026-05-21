import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.util.Random;

public class Combate {
    public static boolean iniciar(Entrenador jugador, Pokemon rival) {
        Pokemon p1 = jugador.getEquipo().get(0);
        ImageIcon iconP1 = new ImageIcon(p1.getRutaSprite());
        ImageIcon iconRival = new ImageIcon(rival.getRutaSprite());

        JOptionPane.showMessageDialog(null, "¡Un " + rival.getNombre() + " salvaje apareció!", "Combate", JOptionPane.INFORMATION_MESSAGE, iconRival);

        Random rnd = new Random();

        while (p1.estaVivo() && rival.estaVivo()) {
            String info = "RIVAL: " + rival.getNombre() + "\n" + rival.getBarraVidaVisual() + "\n\n" +
                        "TU: " + p1.getNombre() + "\n" + p1.getBarraVidaVisual() + "\n\n¿Qué hacer?";

            int accion = JOptionPane.showOptionDialog(null, info, "Turno",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, iconP1,
                new Object[]{"Atacar", "Capturar"}, "Atacar");
            
            if (accion == 1) {
                // Intento de captura. Código de la práctica de CapturaPokemon adaptado.
                if (intentarCaptura(rival, rnd)) {
                    JOptionPane.showMessageDialog(null, "¡1.. 2.. 3.. ¡Has capturado a " + rival.getNombre() + "!");
                    jugador.agregarPokemon(rival); // Añade el capturado al equipo.
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "¡El Pokémon se ha escapado!");
                }
            } else {
                // Elegir Ataque.
                Ataque[] atqs = p1.getAtaques();
                String[] nombresAtq = { atqs[0].getNombre(), atqs[1].getNombre(), atqs[2].getNombre(), atqs[3].getNombre() };
                int atqElegido = JOptionPane.showOptionDialog(null, "Elige un ataque:", "Ataque",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, nombresAtq, nombresAtq[0]);

                if(atqElegido == -1) atqElegido = 0;
                String logJugador = p1.atacar(rival, atqs[atqElegido]);
                JOptionPane.showMessageDialog(null, logJugador, "¡Ataque!", JOptionPane.INFORMATION_MESSAGE, iconP1);
            }

            if (!rival.estaVivo()) break;

            // Turno Rival.
            Ataque atqRival = rival.getAtaques()[rnd.nextInt(4)];
            String logRival = rival.atacar(p1, atqRival);
            JOptionPane.showMessageDialog(null, logRival, "Turno Rival", JOptionPane.WARNING_MESSAGE, iconRival);

            // Efectos de Estado al final del turno.
            String estados = p1.aplicarEfectosDeEstado() + rival.aplicarEfectosDeEstado();
            if (!estados.isEmpty()) JOptionPane.showMessageDialog(null, estados, "Daño por Estado", JOptionPane.ERROR_MESSAGE);
        }

        if (p1.estaVivo()) {
            String logExp = p1.ganarExperiencia(60); 
            JOptionPane.showMessageDialog(null, "¡" + rival.getNombre() + " se debilitó!\n¡Has ganado!\n\n" + logExp);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "¡Tu Pokémon se ha debilitado!\nHas perdido..");
            return false;
        }
    }

    // Captura.
    private static boolean intentarCaptura(Pokemon salvaje, Random rnd) {
        int catchRate = 45;
        double ballBonus = 1.0;
        double statusBonus = (salvaje.getEstado() != EstadoAlterado.NINGUNO) ? 1.5 : 1.0;

        double parteHP = (3.0 * salvaje.getVidaMaxima() - 2.0 * salvaje.getVidaActual()) / (3.0 * salvaje.getVidaMaxima());
        double a = parteHP * catchRate * ballBonus * statusBonus;
        if (a < 1) a = 1;

        if (a >= 255) return true;

        double f = 1048560.0 / Math.sqrt(Math.sqrt(16711680.0 / a));
        for (int i = 1; i <= 4; i++) {
            if (rnd.nextInt(65546) >= f) return false;
        }
        return true;
    }
    
}
