package br.com.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author gabrielcardoso
 */
public class SobrePacienteWindow extends JFrame {

    private JPanel contentPane;
    private JLabel lblTitulo;
    private JLabel lblDesenvolvedor;
    private JLabel lblUniversidade;
    private JButton btnOk;
    private PacienteWindow pacienteWindow;

    public SobrePacienteWindow(PacienteWindow pacienteWindow) {
        addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                        fecharJanela();
                }
        });

        this.initComponents();
        this.pacienteWindow = pacienteWindow;
    }

    private void fecharJanela() {
        this.dispose();
        this.pacienteWindow.setVisible(true);
    }

    private void initComponents() {
        setTitle("Sobre");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblTitulo = new JLabel("Cad. Pacientes");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 27));
        lblTitulo.setBounds(79, 28, 283, 75);
        contentPane.add(lblTitulo);

        lblDesenvolvedor = new JLabel("Aluno: Gabriel Pinto Cardoso");
        lblDesenvolvedor.setHorizontalAlignment(SwingConstants.CENTER);
        lblDesenvolvedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDesenvolvedor.setBounds(79, 105, 283, 35);
        contentPane.add(lblDesenvolvedor);

        lblUniversidade = new JLabel("UTFPR");
        lblUniversidade.setHorizontalAlignment(SwingConstants.CENTER);
        lblUniversidade.setBounds(79, 140, 283, 14);
        contentPane.add(lblUniversidade);

        btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fecharJanela();
            }
        });
        btnOk.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnOk.setBounds(118, 352, 203, 41);
        contentPane.add(btnOk);

        setLocationRelativeTo(null);
    }
}
