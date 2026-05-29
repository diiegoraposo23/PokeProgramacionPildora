import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class InterfazCombate extends JFrame {
    private Entrenador jugador;
    private Pokemon pJugador;
    private Pokemon pRival;
    private Random rnd;

    // Elementos de la interfaz.
    private JLabel lblRivalSprite, lblRivalNombre, lblRivalPS;
    private JLabel lblJugadorSprite, lblJugadorNombre, lblJugadorPS;
    private JTextArea txtlog;
    private JButton[] btnAtaques;
    private JButton btnCapturar, btnHuir;

    public InterfazCombate(Entrenador jugador, Pokemon rival) {
        this.jugador = jugador;
        this.pJugador = jugador.getEquipo().get(0);
        this.pRival = rival;
        this.rnd = new Random();

        configurarVentana();
        inicializarComponentes();
        actualizarPantalla();

        // Hace la ventana visible.
        this.setVisible(true);
    }

    private void configurarVentana() {
        this.setTitle("Simulador de Combate");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null); // Centra en la pantalla.
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        // Panel Superior (Sprites y vida.).
        JPanel panelBatalla = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBatalla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel 1: vacío o info extra.
        panelBatalla.add(new JLabel(""));

        // Panel 2: Rival.
        JPanel panelRival = new JPanel(new BorderLayout());
        lblRivalNombre = new JLabel();
        lblRivalPS = new JLabel();
        lblRivalSprite = new JLabel(new ImageIcon(pRival.getRutaSprite()));
        panelRival.add(lblRivalNombre, BorderLayout.NORTH);
        panelRival.add(lblRivalSprite, BorderLayout.CENTER);
        panelRival.add(lblRivalPS, BorderLayout.SOUTH);
        panelBatalla.add(panelRival);

        // Panel 3. Jugador.
        JPanel panelJugador = new JPanel(new BorderLayout());
        lblJugadorNombre = new JLabel();
        lblJugadorPS = new JLabel();
        lblJugadorSprite = new JLabel(new ImageIcon(pJugador.getRutaSprite()));
        panelJugador.add(lblJugadorNombre, BorderLayout.NORTH);
        panelJugador.add(lblJugadorSprite, BorderLayout.CENTER);
        panelJugador.add(lblJugadorPS, BorderLayout.SOUTH);
        panelBatalla.add(panelJugador);

        // Panel 4. Vacío.
        panelBatalla.add(new JLabel(""));

        this.add(panelBatalla, BorderLayout.NORTH);

        // Panel Central (Registro de texto).
        txtlog = new JTextArea();
        txtlog.setEditable(false);
        txtlog.setFont(new Font("Monospaced", Font.PLAIN,12));
        JScrollPane scrollLog = new JScrollPane(txtlog);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Registro de Batalla"));
        this.add(scrollLog, BorderLayout.CENTER);

        // Panel Inferior (Botones de acción).
        JPanel panelAcciones = new JPanel(new GridLayout(2, 3, 5, 5));
        panelAcciones.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        btnAtaques = new JButton[4];
        Ataque[] ataques = pJugador.getAtaques();
        for (int i = 0; i < 4; i++) {
            final int index = i;
            btnAtaques[i] = new JButton(ataques[i].getNombre());
            btnAtaques[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    procesarTurno(ataques[index]);
                }
            });
            panelAcciones.add(btnAtaques[i]);
        }

        btnCapturar = new JButton("Capturar");
        btnCapturar.setBackground(new Color(255, 100, 100));
        btnCapturar.addActionListener(e -> intentarCaptura());
        panelAcciones.add(btnCapturar);

        btnHuir = new JButton("Huir");
        btnHuir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Has huido del combate de forma segura.");
            this.dispose(); // Cierra esta ventana y vuelve al menú principal.
        });
        panelAcciones.add(btnHuir);

        this.add(panelAcciones, BorderLayout.SOUTH);

        agregarLog("¡Un " + pRival.getNombre() + " salvaje apareció!");
    }

    private void actualizarPantalla() {
        lblRivalNombre.setText("Rival: "+ pRival.getNombre() + " (Lv." + pRival.getNivel() + ")");
        lblRivalPS.setText("PS: " + pRival.getVidaActual() + " / " + pRival.getVidaMaxima());

        lblJugadorNombre.setText("Tú: " + pJugador.getNombre() + " (Lv." + pJugador.getNivel() + ")");
        lblJugadorPS.setText("PS: " + pJugador.getVidaActual() + " / " + pJugador.getVidaMaxima());
    }

    private void agregarLog(String mensaje) {
        txtlog.append(mensaje + "\n");
        txtlog.setCaretPosition(txtlog.getDocument().getLength()); // Auto-scroll hacia abajo.
    }

    private void procesarTurno(Ataque ataqueJugador) {
        // 1. Turno del jugador.
        String logJugador = pJugador.atacar(pRival, ataqueJugador);
        agregarLog(logJugador);

        if (!pRival.estaVivo()) {
            finalizarCombate(true);
            return;
        }

        // 2. Turno del rival.
        Ataque ataqueRival = pRival.getAtaques()[rnd.nextInt(4)];
        String logRival = pRival.atacar(pJugador, ataqueRival);
        agregarLog(logRival);

        if (!pJugador.estaVivo()) {
            finalizarCombate(false);
            return;
        }

        // 3. Efectos de estado al final del turno.
        String estados = pJugador.aplicarEfectosDeEstado() + pRival.aplicarEfectosDeEstado();
        if (!estados.isEmpty()) agregarLog(estados);

        if (!pJugador.estaVivo()) finalizarCombate(false);
        else if (!pRival.estaVivo()) finalizarCombate(true);

        actualizarPantalla();
    }

    private void intentarCaptura() {
        int catchRate = 45;
        double statusBonus = (pRival.getEstado() != EstadoAlterado.NINGUNO) ? 1.5 : 1.0;
        double parteHP = (3.0 * pRival.getVidaMaxima() - 2.0 * pRival.getVidaActual()) / (3.0 * pRival.getVidaMaxima());
        double a = parteHP * catchRate * 1.0 * statusBonus;
        if (a < 1) a = 1;

        boolean capturado = true;
        if (a < 255) {
            double f = 1048560.0 / Math.sqrt(Math.sqrt(16711680.0 / a));
            for (int i = 1; i <= 4; i++) {
                if (rnd.nextInt(65536) >= f) {
                    capturado = false;
                    break;
                }
            }
        }

        if (capturado) {
            pRival.vidaActual = pRival.getVidaMaxima(); // Cura antes de guardar.
            if (jugador.agregarPokemon(pRival)) {
                JOptionPane.showMessageDialog(this, "Has capturado a " + pRival.getNombre() + "!");
            } else {
                JOptionPane.showMessageDialog(this, "El equipo está lleno " + pRival.getNombre() + " ha sido liberado.");
            }
            this.dispose();
        } else {
            agregarLog("Oh no!" + pRival.getNombre() + " ha escapado de la Pokéball.");

            // Si falla la captura, el rival tiene derecho a atacar ese turno.
            Ataque ataqueRival = pRival.getAtaques()[rnd.nextInt(4)];
            agregarLog(pRival.atacar(pJugador, ataqueRival));
            actualizarPantalla();

            if (!pJugador.estaVivo()) finalizarCombate(false);
        }
    }

    private void finalizarCombate(boolean victoria) {
        actualizarPantalla();
        desactivarBotones();

        if(victoria) {
            String logExp = pJugador.ganarExperiencia(100);
            JOptionPane.showMessageDialog(this, "¡Victoria!\n" + logExp);
        } else {
            JOptionPane.showMessageDialog(this, "Tu Pokémon se ha debilitado. Has perdido!");
        }
        this.dispose(); // Cierra la ventana del combate.
    }

    private void desactivarBotones() {
        for (JButton btn : btnAtaques) btn.setEnabled(false);
        btnCapturar.setEnabled(false);
    }
}