package am.aua.linalg;

import javax.swing.SwingUtilities;
import am.aua.linalg.ui.Calculator;

public class Main {
        public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::createAndShowGUI);
    }
}
 