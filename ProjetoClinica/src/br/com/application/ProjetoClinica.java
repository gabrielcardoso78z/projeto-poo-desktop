package br.com.application;

import br.com.gui.PacienteWindow;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author gabrielcardoso
 */
public class ProjetoClinica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PacienteWindow tela = new PacienteWindow();
                    tela.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
