import view.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Look and Feel nativo del SO
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Lanzar en el hilo de Swing
        SwingUtilities.invokeLater(() -> new LoginView());
    }
}
