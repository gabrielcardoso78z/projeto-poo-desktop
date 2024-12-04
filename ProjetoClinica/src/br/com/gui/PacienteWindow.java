package br.com.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.entities.Paciente;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import br.com.service.PacienteService;

/**
 *
 * @author gabrielcardoso
 */
public class PacienteWindow extends JFrame {

    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenu menuArquivo;
    private JMenuItem itemSair;
    private JMenu menuAjuda;
    private JMenuItem itemSobre;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblCpf;
    private JFormattedTextField txtCpf;
    private JLabel lblTelefone;
    private JFormattedTextField txtTelefone;
    private JSeparator separator;
    private JButton btnCadastrar;
    private JButton btnLimparCampos;
    private JPanel painelPacientes;
    private JTable tblPacientes;
    private JScrollPane scrollPane;
    
    private JLabel lblBusca;
    private JTextField txtBusca;

    private MaskFormatter mascaraCpf;
    private MaskFormatter mascaraTelefone;

    private PacienteService pacienteService;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public PacienteWindow() {
        this.criarMascaras();
        this.initComponents();

        this.pacienteService = new PacienteService();

        this.buscarPacientes("");
        this.limparComponentes();
    }

    private void limparComponentes() {
        this.txtNome.setText("");
        this.txtCpf.setText("");
        this.txtTelefone.setText("");
    }

    public void buscarPacientes(String busca) {
    try {
        DefaultTableModel modelo = (DefaultTableModel) tblPacientes.getModel();
        modelo.setRowCount(0);

        List<Paciente> pacientes = this.pacienteService.buscarTodos();

        for (Paciente paciente : pacientes) {
            if (paciente.getNome().toLowerCase().contains(busca.toLowerCase()) ||
                paciente.getCpf().contains(busca)) { // Pode incluir CPF ou outros campos
                modelo.addRow(new Object[] { 
                    paciente.getId(), 
                    paciente.getNome(), 
                    paciente.getCpf(),
                    paciente.getTelefone() 
                });
            }
        }

    } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos pacientes.", "Busca de Pacientes", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }


    private void criarMascaras() {
        try {
            this.mascaraCpf = new MaskFormatter("###.###.###-##");
            this.mascaraTelefone = new MaskFormatter("(##) #####-####");
        } catch (ParseException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void cadastrarPaciente() {
        try {
            Paciente paciente = new Paciente();

            paciente.setNome(this.txtNome.getText());
            paciente.setCpf(this.txtCpf.getText());
            paciente.setTelefone(this.txtTelefone.getText());

            this.pacienteService.cadastrar(paciente);
            this.buscarPacientes("");
        } catch (SQLException | IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar um novo paciente.", "Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finalizarAplicacao() {
        System.exit(0);
    }

    private void abrirJanelaSobre() {
        SobrePacienteWindow janelaSobre = new SobrePacienteWindow(this);
        janelaSobre.setVisible(true);
        this.setVisible(false);
    }

    public void initComponents() {

        setTitle("Paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(100, 100, 650, 709);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuArquivo = new JMenu("Arquivo");
        menuBar.add(menuArquivo);

        itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                        finalizarAplicacao();
                }
        });
        menuArquivo.add(itemSair);

        menuAjuda = new JMenu("Ajuda");
        menuBar.add(menuAjuda);

        itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        abrirJanelaSobre();
                }
        });
        menuAjuda.add(itemSobre);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNome.setBounds(25, 21, 46, 23);
        contentPane.add(lblNome);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNome.setBounds(81, 22, 520, 22);
        contentPane.add(txtNome);
        txtNome.setColumns(10);

        lblCpf = new JLabel("CPF");
        lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCpf.setBounds(25, 66, 46, 23);
        contentPane.add(lblCpf);

        txtCpf = new JFormattedTextField(mascaraCpf);
        txtCpf.setBounds(81, 67, 143, 22);
        contentPane.add(txtCpf);

        lblTelefone = new JLabel("Telefone");
        lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTelefone.setBounds(250, 66, 65, 23);
        contentPane.add(lblTelefone);

        txtTelefone = new JFormattedTextField(mascaraTelefone);
        txtTelefone.setBounds(317, 67, 143, 22);
        contentPane.add(txtTelefone);

        separator = new JSeparator();
        separator.setBounds(25, 120, 576, 12);
        contentPane.add(separator);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cadastrarPaciente();
            }
        });
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCadastrar.setBounds(284, 140, 154, 38);
        contentPane.add(btnCadastrar);

        btnLimparCampos = new JButton("Limpar Campos");
        btnLimparCampos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                limparComponentes();
            }
        });
        btnLimparCampos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLimparCampos.setBounds(447, 140, 154, 38);
        contentPane.add(btnLimparCampos);

        painelPacientes = new JPanel();
        painelPacientes.setBorder(new TitledBorder(null, "Lista de Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        painelPacientes.setBounds(25, 180, 576, 275);
        contentPane.add(painelPacientes);
        painelPacientes.setLayout(null);

        tblPacientes = new JTable();
        tblPacientes.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nome", "CPF", "Telefone"}
        ));
        addMouseListener();
        scrollPane = new JScrollPane(tblPacientes);
        scrollPane.setBounds(10, 23, 556, 242);
        painelPacientes.add(scrollPane);

        lblBusca = new JLabel("Busca");
        lblBusca.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblBusca.setBounds(25, 498, 46, 23);
        contentPane.add(lblBusca);

        txtBusca = new JTextField();
        txtBusca.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtBusca.setBounds(81, 499, 433, 22);
        contentPane.add(txtBusca);
        txtBusca.setColumns(10);

        // Atualizar pacientes ao digitar na busca
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarPacientes(txtBusca.getText());
            }
        });
    }
    
    private void addMouseListener() {
        tblPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblPacientes.getSelectedRow();
                if (row != -1) {
                    // Pega os dados da linha selecionada
                    int id = (int) tblPacientes.getValueAt(row, 0);
                    String nome = (String) tblPacientes.getValueAt(row, 1);
                    String cpf = (String) tblPacientes.getValueAt(row, 2);
                    String telefone = (String) tblPacientes.getValueAt(row, 3);

                    // Chama o método para abrir a janela de edição
                    openEditWindow(id, nome, cpf, telefone);
                }
            }
        });
    }
    
    private void openEditWindow(int id, String nome, String cpf, String telefone) {
        EditPacienteWindow janelaEdicao = new EditPacienteWindow(id, nome, cpf, telefone, this);
        janelaEdicao.setVisible(true);
        this.setVisible(false); 
    }
    
}
