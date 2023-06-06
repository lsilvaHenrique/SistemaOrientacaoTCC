package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;

import controller.AlunoController;
import controller.GrupoController;
import controller.OrientacaoController;
import model.Aluno;
import model.Grupo;
import model.Orientacao;

public class Tela extends JFrame {

	private static final long serialVersionUID = -8367072039961003264L;
	private JPanel contentPane;
	private JTextField tfNomeAluno;
	private JTextField tfRaAluno;
	private JTextField tfCodOrientação;
	private JTextField tfCodProfessor;
	private JTextField tfCodGrupoOrient;
	private JTextArea taOrientacao = new JTextArea();
	private AlunoController aCont = new AlunoController();
	private GrupoController gCont = new GrupoController();
	private OrientacaoController oCont = new OrientacaoController();
	private JTextField tfTemaGrupo;
	private List<Aluno> alunos = new ArrayList<>();
	private JTextField tfCodGrupoConsultar;
	private JTextField tfTemaConsultar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() throws ParseException {
		setTitle("Orientação TCC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 633, 322);
		contentPane.add(tabbedPane);
		
		DefaultListModel<String> listModelGrupo = new DefaultListModel<>();
		DefaultListModel<String> listModelConsultar = new DefaultListModel<>();
		DefaultListModel<String> listModelConsultarPorSubArea = new DefaultListModel<>();
		
		JPanel tabGrupo = new JPanel();
		tabbedPane.addTab("Grupo", null, tabGrupo, "Cadastro de Grupos");
		tabGrupo.setLayout(null);
		
		JLabel lblNomeAluno = new JLabel("Nome ");
		lblNomeAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeAluno.setBounds(188, 0, 66, 14);
		tabGrupo.add(lblNomeAluno);
		
		JLabel lblRaAluno = new JLabel("RA");
		lblRaAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRaAluno.setBounds(24, 0, 66, 14);
		tabGrupo.add(lblRaAluno);
		
		tfNomeAluno = new JTextField();
		tfNomeAluno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfNomeAluno.setBounds(187, 21, 229, 20);
		tabGrupo.add(tfNomeAluno);
		tfNomeAluno.setColumns(10);
		
		tfRaAluno = new JTextField();
		tfRaAluno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfRaAluno.setBounds(24, 21, 152, 20);
		tabGrupo.add(tfRaAluno);
		tfRaAluno.setColumns(10);
		JList<String> listAlunos = new JList<>(listModelGrupo);
		listAlunos.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listAlunos.setBounds(24, 52, 582, 123);
		tabGrupo.add(listAlunos);
		
		JComboBox cmbSubAreaGrupo = new JComboBox();
		cmbSubAreaGrupo.setModel(new DefaultComboBoxModel(new String[] {"Banco de Dados", "POO", "Engenharia de Software" 
				, "Redes" , "Auditoria de Sistemas"}));
		cmbSubAreaGrupo.setBounds(24, 209, 229, 22);
		tabGrupo.add(cmbSubAreaGrupo);
		
		JButton btnAlunoAdicionar = new JButton("ADICIONAR ALUNO");
		btnAlunoAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModelGrupo.addElement(tfRaAluno.getText() + " - " + tfNomeAluno.getText());
				
				Aluno aluno = new Aluno(tfNomeAluno.getText(), tfRaAluno.getText());
				alunos.add(aluno);
				tfNomeAluno.setText("");
				tfRaAluno.setText("");
			}
		});
		btnAlunoAdicionar.setBounds(454, 21, 152, 23);
		tabGrupo.add(btnAlunoAdicionar);
		
		JButton btnCadastrarGrupo = new JButton("CADASTRAR GRUPO");
		btnCadastrarGrupo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int codSubArea = cmbSubAreaGrupo.getSelectedIndex() + 1;
		        Grupo grupo = new Grupo(tfTemaGrupo.getText(), codSubArea);
		        
		        gCont.cadastro(grupo);
		        int codGrupo = gCont.getCodGrupo();
		        
		        aCont.cadastro(alunos, codGrupo);
		        
		        cmbSubAreaGrupo.setSelectedIndex(0);
		        tfTemaGrupo.setText("");
		        listModelGrupo.clear();
		        
		        // Exibir mensagem com o código do grupo cadastrado
		        JOptionPane.showMessageDialog(null, "Grupo cadastrado com sucesso. Código do grupo: " + codGrupo);
		    }
		});
		btnCadastrarGrupo.setBounds(24, 260, 152, 23);
		tabGrupo.add(btnCadastrarGrupo);
		
		tfTemaGrupo = new JTextField();
		tfTemaGrupo.setBounds(273, 210, 228, 20);
		tabGrupo.add(tfTemaGrupo);
		tfTemaGrupo.setColumns(10);
		
		JLabel lblTema = new JLabel("Tema");
		lblTema.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTema.setBounds(272, 186, 66, 14);
		tabGrupo.add(lblTema);
		
		JLabel lblSubAreaGrupo = new JLabel("Subárea");
		lblSubAreaGrupo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubAreaGrupo.setBounds(24, 186, 66, 14);
		tabGrupo.add(lblSubAreaGrupo);
		
		JPanel tabConsultar = new JPanel();
		tabbedPane.addTab("Consultar Grupo", null, tabConsultar, null);
		tabConsultar.setLayout(null);
		
		tfCodGrupoConsultar = new JTextField();
		tfCodGrupoConsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCodGrupoConsultar.setColumns(10);
		tfCodGrupoConsultar.setBounds(172, 24, 123, 20);
		tabConsultar.add(tfCodGrupoConsultar);
		
		JLabel lblCdigoDoGrupo = new JLabel("Código do Grupo");
		lblCdigoDoGrupo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCdigoDoGrupo.setBounds(22, 26, 123, 14);
		tabConsultar.add(lblCdigoDoGrupo);
		
		JComboBox cmbSubAreaGrupoConsultar = new JComboBox();
		cmbSubAreaGrupoConsultar.setEnabled(false);
		cmbSubAreaGrupoConsultar.setModel(new DefaultComboBoxModel(new String[] {"Banco de Dados", "POO", "Engenharia de Software" 
				, "Redes" , "Auditoria de Sistemas"}));
		cmbSubAreaGrupoConsultar.setBounds(22, 110, 229, 22);
		tabConsultar.add(cmbSubAreaGrupoConsultar);
		
		JList<String> listAlunosConsultar = new JList<String>(listModelConsultar);
		listAlunosConsultar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listAlunosConsultar.setBounds(22, 160, 582, 123);
		tabConsultar.add(listAlunosConsultar);
		
		JButton btnBuscarGrupo = new JButton("Buscar");
		btnBuscarGrupo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        listModelConsultar.clear();
		        int codGrupo = Integer.parseInt(tfCodGrupoConsultar.getText());
		        Grupo grupo = gCont.busca(codGrupo);
		        cmbSubAreaGrupoConsultar.setSelectedIndex(grupo.getCodSubarea() - 1);
		        tfTemaConsultar.setText(grupo.getTemaTCC());
		        
		        for (Aluno aluno : grupo.getAlunos()) {
		            listModelConsultar.addElement(aluno.getRA() + " - " + aluno.getNome());
		        }
		    }
		});

		btnBuscarGrupo.setBounds(317, 24, 89, 23);
		tabConsultar.add(btnBuscarGrupo);
		
		JLabel lblSubAreaGrupo_1 = new JLabel("Subárea");
		lblSubAreaGrupo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubAreaGrupo_1.setBounds(22, 85, 66, 14);
		tabConsultar.add(lblSubAreaGrupo_1);
		
		JLabel lblTema_1 = new JLabel("Tema");
		lblTema_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTema_1.setBounds(295, 85, 66, 14);
		tabConsultar.add(lblTema_1);
		
		tfTemaConsultar = new JTextField();
		tfTemaConsultar.setEnabled(false);
		tfTemaConsultar.setColumns(10);
		tfTemaConsultar.setBounds(295, 111, 228, 20);
		tabConsultar.add(tfTemaConsultar);
		
		/*JCalendar calendar = new JCalendar();
		calendar.setBounds(379, 36, 198, 114);
		tabOrientação.add(calendar);*/
		
		JPanel tabOrientação = new JPanel();
		tabbedPane.addTab("Orientação", null, tabOrientação, "Registrar Orientação");
		tabOrientação.setLayout(null);
		
		JLabel lblCodOrientação = new JLabel("Número da Orientação");
		lblCodOrientação.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodOrientação.setBounds(31, 24, 131, 27);
		tabOrientação.add(lblCodOrientação);
		
		JLabel lblDataOrientação = new JLabel("Data da Orientação");
		lblDataOrientação.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataOrientação.setBounds(362, 30, 116, 14);
		tabOrientação.add(lblDataOrientação);
		
		JLabel lblObservação = new JLabel("Observação");
		lblObservação.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblObservação.setBounds(31, 163, 89, 14);
		tabOrientação.add(lblObservação);
		
		JLabel lblGrupoOrientacao = new JLabel("Codigo do Grupo");
		lblGrupoOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGrupoOrientacao.setBounds(160, 78, 105, 14);
		tabOrientação.add(lblGrupoOrientacao);
		
		JLabel lblProfessorOrientacao = new JLabel("Codigo do Professor");
		lblProfessorOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProfessorOrientacao.setBounds(31, 78, 131, 14);
		tabOrientação.add(lblProfessorOrientacao);
		
		tfCodOrientação = new JTextField();
		tfCodOrientação.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCodOrientação.setBounds(31, 47, 124, 20);
		tabOrientação.add(tfCodOrientação);
		tfCodOrientação.setColumns(10);
		
		tfCodProfessor = new JTextField();
		tfCodProfessor.setBounds(31, 103, 119, 20);
		tabOrientação.add(tfCodProfessor);
		tfCodProfessor.setColumns(10);
		
		tfCodGrupoOrient = new JTextField();
		tfCodGrupoOrient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCodGrupoOrient.setBounds(160, 103, 104, 20);
		tabOrientação.add(tfCodGrupoOrient);
		tfCodGrupoOrient.setColumns(10);
		
		taOrientacao.setBounds(31, 183, 546, 66);
		tabOrientação.add(taOrientacao);
		
		MaskFormatter formatter = new MaskFormatter("##/##/####");
		JFormattedTextField tfDataOrientacao = new JFormattedTextField(formatter);
		tfDataOrientacao.setBounds(362, 48, 116, 20);
		tabOrientação.add(tfDataOrientacao);
		
		JButton btnCadastrarOrientacao = new JButton("CADASTRAR");
		btnCadastrarOrientacao.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Orientacao orientacao = new Orientacao(taOrientacao.getText(), Integer.parseInt(tfCodGrupoOrient.getText()),
		                Integer.parseInt(tfCodProfessor.getText()), tfDataOrientacao.getText());

		        oCont.cadastro(orientacao);
		        taOrientacao.setText("");
		        tfCodGrupoOrient.setText("");
		        tfCodProfessor.setText("");
		        tfDataOrientacao.setText("");

		        // Exibir mensagem de orientação registrada
		        JOptionPane.showMessageDialog(null, "Orientação registrada");
		    }
		});
		btnCadastrarOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarOrientacao.setBounds(31, 260, 119, 23);
		tabOrientação.add(btnCadastrarOrientacao);
		
		JButton btnBuscarOrientacao = new JButton("BUSCAR");
		btnBuscarOrientacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Orientacao orientacao = oCont.buscarUltimaOrientacao();
				tfCodOrientação.setText(Integer.toString(orientacao.getCodigo()));
				tfCodProfessor.setText(Integer.toString(orientacao.getCodProfessor()));
				tfCodGrupoOrient.setText(Integer.toString(orientacao.getCodGrupo()));
				taOrientacao.setText(orientacao.getObservação());
				tfDataOrientacao.setText(orientacao.getData());
			}
		});
		btnBuscarOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscarOrientacao.setBounds(160, 46, 89, 23);
		tabOrientação.add(btnBuscarOrientacao);
		
		JPanel tabSubArea = new JPanel();
		tabbedPane.addTab("SubArea", null, tabSubArea, "Visualizar SubArea de Grupo");
		tabSubArea.setLayout(null);
		
		JLabel lblSubArea = new JLabel("SubÁrea");
		lblSubArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubArea.setBounds(42, 38, 66, 14);
		tabSubArea.add(lblSubArea);
		
		JComboBox cbEscolherSubArea = new JComboBox();
		cbEscolherSubArea.setModel(new DefaultComboBoxModel(new String[] {"Banco de Dados", "POO", "Engenharia de Software" 
				, "Redes" , "Auditoria de Sistemas"}));
		cbEscolherSubArea.setBounds(108, 36, 190, 22);
		tabSubArea.add(cbEscolherSubArea);
		
		JScrollPane scrollPaneSubArea = new JScrollPane();
		scrollPaneSubArea.setBounds(39, 91, 555, 178);
		tabSubArea.add(scrollPaneSubArea);
		
		JList listGrupoSubArea = new JList(listModelConsultarPorSubArea);
		scrollPaneSubArea.setViewportView(listGrupoSubArea);
		
		JButton btnBuscarPorSubArea = new JButton("Buscar");
		btnBuscarPorSubArea.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        listModelConsultarPorSubArea.clear();
		        int codSubArea = cbEscolherSubArea.getSelectedIndex() + 1;
		        Grupo grupo = gCont.buscaPorSubArea(codSubArea);

		        if (grupo == null) {
		            listModelConsultarPorSubArea.addElement("Nenhum grupo associado à subárea selecionada.");
		        } else {
		            listModelConsultarPorSubArea.addElement("Grupo: " + grupo.getCodigo() + " - " + "Tema: " + grupo.getTemaTCC());
		        }
		    }
		});
		btnBuscarPorSubArea.setBounds(359, 36, 89, 23);
		tabSubArea.add(btnBuscarPorSubArea);
	}
}
