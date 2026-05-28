## Características.

* **Arquitectura POO:** Uso extensivo de herencia, clases abstractas, polimorfismo y encapsulamiento para definir las jerarquías de entidades.

* **Persistencia de Datos:** Implementación de flujos de entrada/salida (I/O) y la interfaz `Serializable` para el guardado y carga del estado de la aplicación de forma local.

* **Control de Flujo Avanzado:** Integración de algoritmos proabilísticos para la simulación de mecánicas complejas (fórmula matemática de captura extraída de las Generaciones III y IV).

* **Estructura de Control:** Uso de `Enums` para la tipificación estricta de elementos y estados alterados, y colecciones dinámicas (`ArrayList`) para la gestión de entidades de memoria.


## Funcionalidades del Simulador.

* **Sistemas de Combate Completo:** Soporte para 16 tipos, calculando multiplicadores de daño (debilidades y resistencia) dinámicamente.

* **Gestión de Entidades:** Cada criatura dispone de estadísticas base (HP, Ataque, Defensa), un set de hasta 4 movimientos intercambiables y progresión mediante puntos de experiencia (EXP) y subida de nivel.

* **Estados Alterados:** Sistema de penalizaciones por turno (daño residual por quemadura, envenenamientos, etc.)

* **Gestión del Equipo (PC):** Interfaz para la modificación del equipo activo, curación y liberación de entidades.

## Interfaz del usuario (GUI)

* Cuadros de diálogo modales (`JOptionPane`) para la navegación por menús y toma de decisiones.

* Representación gráfica del estado de salud mediante barras de progreso procesadas en formato de texto.

* Carga dinámica de recursos gráficos (sprites en formato `.ppg`) mediante `ImageIcon` durante los eventos de combate.