package br.com.gui;

import br.com.entities.Paciente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.service.PacienteService;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author gabrielcardoso
 */
public class EditPacienteWindow extends JFrame {
    private JTextField txtNome, txtCpf;
    private JFormattedTextField txtTelefone;
    private JButton btnSalvar, btnExcluir, btnCancelar;
    private int idPaciente;
    private PacienteService pacienteService;
    private PacienteWindow pacienteWindow;

    public EditPacienteWindow(int id, String nome, String cpf, String telefone, PacienteWindow pacienteWindow) {
        this.idPaciente = id;
        this.pacienteService = new PacienteService();
        this.pacienteWindow = pacienteWindow;

        setTitle("Editar Paciente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Configurar layout
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Campo Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        contentPane.add(lblNome, gbc);

        txtNome = new JTextField(nome);
        txtNome.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(txtNome, gbc);

        // Campo CPF (desabilitado, já que não pode ser editado)
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lblCpf, gbc);

        txtCpf = new JTextField(cpf);
        txtCpf.setFont(new Font("Arial", Font.PLAIN, 14));
        txtCpf.setEnabled(false); // CPF não editável
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPane.add(txtCpf, gbc);

        // Campo Telefone
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(lblTelefone, gbc);
        
        // Máscara para o telefone (formato: (XX) XXXX-XXXX ou (XX) XXXXX-XXXX)
        MaskFormatter telefoneFormatter = null;
        try {
            telefoneFormatter = new MaskFormatter("(##) #####-####");
            telefoneFormatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Criando o campo com a máscara
        txtTelefone = new JFormattedTextField(telefoneFormatter);
        txtTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTelefone.setText(telefone);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPane.add(txtTelefone, gbc);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalvar.setBackground(new Color(102, 204, 102)); // Verde claro
        btnSalvar.setForeground(Color.BLACK); // Texto preto para contraste
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarEdicao(cpf);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        contentPane.add(btnSalvar, gbc);

        // Botão Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setForeground(Color.BLACK); // Texto preto para contraste
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelarEdicao();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 3;
        contentPane.add(btnCancelar, gbc);

        // Botão Excluir
        btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
        btnExcluir.setBackground(new Color(255, 51, 51)); // Vermelho intenso
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirRegistro(cpf);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(btnExcluir, gbc);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                pacienteWindow.setVisible(true); 
                dispose(); 
            }
        });
    }

    private void salvarEdicao(String cpfPaciente) {
        try {
            String nome = txtNome.getText();
            String telefone = txtTelefone.getText();
            String cpf = cpfPaciente;

            Paciente paciente = new Paciente();
            paciente.setId(this.idPaciente);
            paciente.setCpf(cpf);
            paciente.setNome(nome);
            paciente.setTelefone(telefone);

            pacienteService.atualizarPaciente(paciente);

            JOptionPane.showMessageDialog(this, "Paciente atualizado com sucesso!");

            this.dispose();
            pacienteWindow.buscarPacientes("");  // Atualiza a lista de pacientes na janela principal
            pacienteWindow.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar a edição do paciente.");
        }
    }

    private void cancelarEdicao() {
        this.dispose();
        pacienteWindow.setVisible(true); // Retorna para a tela de listagem de pacientes
    }

    private void excluirRegistro(String cpfPaciente) {
        int opcao = JOptionPane.showConfirmDialog(this,
            "Você tem certeza que deseja excluir este paciente?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (opcao == JOptionPane.YES_OPTION) {
            try {
                pacienteService.excluirPaciente(cpfPaciente);
                JOptionPane.showMessageDialog(this, "Paciente excluído com sucesso.");
                this.dispose();  // Fecha a janela de edição
                pacienteWindow.buscarPacientes("");  // Atualiza a lista de pacientes na janela principal
                pacienteWindow.setVisible(true); // Exibe a janela principal novamente
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir o paciente.");
            }
        }
    }
}
