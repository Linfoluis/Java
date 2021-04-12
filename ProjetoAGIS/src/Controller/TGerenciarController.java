package Controller;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import Entity.Aluno;
import Entity.Avaliacao;
import Entity.Disciplina;
import Entity.Falta;
import Entity.Nota;
import Persistence.AlunoDAO;
import Persistence.AvaliacaoDAO;
import Persistence.DisciplinaDAO;
import Persistence.FaltaDAO;
import Persistence.GenericDAO;
import Persistence.NotasDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class TGerenciarController implements Initializable {
	@FXML
	private ComboBox<Disciplina> cbDisciplina;
	@FXML
	private ComboBox<Aluno> cbAluno;
	@FXML
	private TextField txtNota;
	@FXML
	private TextField txtPeso;
	@FXML
	private Button btnInserirNotas;
	@FXML
	private ComboBox<Avaliacao> cbAvaliacao;
	@FXML
	private TableView tvTabela;
	@FXML
	private TableColumn<Aluno, Integer> tcRA;
	@FXML
	private TableColumn<Aluno, String> tcAluno;
	@FXML
	private TableColumn tcPresencas;
	@FXML
	private DatePicker dpData;
	@FXML
	private Button btnInserirFaltas;
	@FXML
	private ComboBox<Disciplina> cbDisciplina1;
	@FXML
	private Button btnBuscar;
	@FXML
	private TextField txtFalta;
	@FXML
	private ComboBox<Aluno> cbAlunoFalta;
	@FXML
	private Label labelpresencas;
	@FXML
	private TableView<Nota> tvVNotas;
	@FXML
	private TableColumn<Nota, Integer> tcRA1;
	@FXML
	private TableColumn<Nota, Double> tcNota;
	@FXML
	private ComboBox<Disciplina> cbDisciplina2;
	@FXML
	private Button btnVNotasBuscarDisciplina;
	@FXML
	private Tab btnVFaltasBuscarFaltas;
	@FXML
	private ComboBox<Disciplina> cbDisciplina3;
	@FXML
	private Button btnVFaltaBuscaDisciplina;
	@FXML
	private TableColumn tvRA2;
	@FXML
	private Button btnGerarJasper;
	@FXML
	private Button btnGerarJasperFalta;
	@FXML
	private ComboBox<Avaliacao> cbAvaliacao1;

	// ObservableList para Preencher com as Disciplinas do Banco de Dados
	private ObservableList<Disciplina> obsdisciplina;
	// ObservableList para Preencher com as os alunos matriculados naquela disciplina
	private ObservableList<Aluno> obsaluno;
	// ObservableList para Preencher com as provas naquela disciplina
	private ObservableList<Avaliacao> obsavaliacao;
	// ObservableList para Preencher com os pesos da prova da disciplina
	private ObservableList<Avaliacao> obspeso;
	//ObservableList para Preencher a TableView de Faltas
	private ObservableList<Aluno> obsalunofalta;
	//ObservableList para Preencher a TableView de Notas
	private ObservableList<Nota> obsnota;
	// ObservableList para Preencher com as provas naquela disciplina (Tela Notas)
	private ObservableList<Avaliacao> obsavaliacao1;
	
	
	/* Aqui são carregados todos os combobox da tela */
	public void initialize(URL location, ResourceBundle resources) { // FEITO (MOSTRA AS DISCIPLINAS DO BD)
		DisciplinaDAO ddao;
		try {
			ddao = new DisciplinaDAO();
			obsdisciplina = ddao.BuscaDisciplina();
			cbDisciplina.getItems().addAll(obsdisciplina);
			cbDisciplina1.getItems().addAll(obsdisciplina);
			cbDisciplina2.getItems().addAll(obsdisciplina);
			cbDisciplina3.getItems().addAll(obsdisciplina);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void BuscaAluno(MouseEvent event) { // FEITO (MOSTRA OS ALUNOS CADASTRADOS NA DISCIPLINA SELECIONADA NO COMBOBOX)
		try {
			AlunoDAO aldao = new AlunoDAO();
			obsaluno = aldao.BuscaAluno(cbDisciplina.getSelectionModel().getSelectedItem().getCodigoDisciplina());
			cbAluno.getItems().addAll(obsaluno);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void BuscaAvaliação(MouseEvent event) { // FEITO (BUSCA A AVALIAÇÃO DA DISCIPLINA SELECIONADA)
		cbAvaliacao.getItems().clear();
		try {
			AvaliacaoDAO avdao = new AvaliacaoDAO();
			obsavaliacao = avdao
					.BuscaAvaliacao(cbDisciplina.getSelectionModel().getSelectedItem().getCodigoDisciplina());
			cbAvaliacao.getItems().addAll(obsavaliacao);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void BuscaPeso(MouseEvent event) { // FEITO (BUSCA O PESO DA AVALIAÇÃO DA DISCIPLINA SELECIONADA)
		try {
			AvaliacaoDAO avdao = new AvaliacaoDAO();
			txtPeso.setText(String
					.valueOf(avdao.consultaPeso(cbAvaliacao.getSelectionModel().getSelectedItem().getCodAvalicao())));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void InserirNotas(ActionEvent event) { // FEITO (INSERE A NOTA COM A PROCEDURE)
		try {
			NotasDAO ndao = new NotasDAO();
			Nota n = new Nota();
			n.setRA(cbAluno.getSelectionModel().getSelectedItem().getRa());
			n.setCodDisc(cbDisciplina.getSelectionModel().getSelectedItem().getCodigoDisciplina());
			n.setAvalCod(cbAvaliacao.getSelectionModel().getSelectedItem().getCodAvalicao());
			System.out.println(cbAvaliacao.getSelectionModel().getSelectedItem().getTipo());
			n.setnNota(Double.parseDouble(txtNota.getText()));
			ndao.insereNota(n);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		limpaCamposNotaProva();
	}
	
	@FXML
	public void PreencheTabela(ActionEvent event) { // FEITO (PREECHE TABELA COM OS ALUNOS DA DISCIPLINA SELECIONADA NO COMBOBOX)
		try {
			FaltaDAO fdao = new FaltaDAO();
			obsalunofalta = fdao.buscaFalta(cbDisciplina1.getSelectionModel().getSelectedItem().getCodigoDisciplina());
			tcRA.setCellValueFactory(new PropertyValueFactory<>("ra"));
			tcAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
			tcPresencas.setCellValueFactory(new PropertyValueFactory<>("presencas"));
			tvTabela.setItems(obsalunofalta);
			cbAlunoFalta.setItems(obsalunofalta);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void InserirFaltas(ActionEvent event) { // FEITO (INSERE AS FALTAS ATRAVÉS DO TABLEVIEW)
		try {
			FaltaDAO fdao = new FaltaDAO();
			Falta f = new Falta();
			f.setAlunoRA(cbAlunoFalta.getSelectionModel().getSelectedItem().getRa());
			f.setDisccodigo(cbDisciplina1.getSelectionModel().getSelectedItem().getCodigoDisciplina());
			f.setData(dpData.getValue());
			f.setPresencas(Integer.parseInt(txtFalta.getText()));
			f.setnAulas(cbDisciplina1.getSelectionModel().getSelectedItem().getnAulasDisciplina());
			fdao.insereFalta(f);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void preencheAvaliacao(MouseEvent event){ // FEITO (BUSCA A AVALIAÇÃO DA DISCIPLINA SELECIONADA TELA NOTAS)
		AvaliacaoDAO avdao;
		try {
			avdao = new AvaliacaoDAO();
			obsavaliacao1 = avdao.BuscaAvaliacao(cbDisciplina2.getSelectionModel().getSelectedItem().getCodigoDisciplina());
			cbAvaliacao1.getItems().addAll(obsavaliacao1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void BuscarVDisciplina(ActionEvent event) {	// FAZER (MOSTRAR NOTAS DA DISCIPLINA SELECIONADA)
		try {
			NotasDAO ndao = new NotasDAO();
			obsnota = ndao.buscaNotas(cbDisciplina2.getSelectionModel().getSelectedItem().getCodigoDisciplina(), cbAvaliacao1.getSelectionModel().getSelectedItem().getTipo());
			tcRA1.setCellValueFactory(new PropertyValueFactory<>("RA"));
			tcNota.setCellValueFactory(new PropertyValueFactory<>("nNota")); // Por algum motivo desconhecido 
			tvVNotas.setItems(obsnota);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void GerarJasper(ActionEvent event) throws JRException, SQLException, IOException { // FEITO (Gera o Jasper de Notas)
		String erro ="";
		String jasper = "resources/Blank_A4.jasper";
		int i = cbDisciplina2.getSelectionModel().getSelectedItem().getCodigoDisciplina();
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("codigo", i);
		byte[] retorno  = null;
		JasperReport relatorio = (JasperReport)JRLoader.loadObjectFromFile(jasper);
		retorno  = JasperRunManager.runReportToPdf(relatorio, param,new GenericDAO().getConnection());
		File arq = new File("C:\\TEMP\\RelatorioNota.pdf");
		if(arq.exists()) {
			arq.delete();
		}
		FileOutputStream fos = new FileOutputStream(arq);
		fos.write(retorno);
		fos.flush();
		fos.close();
    }

	@FXML
	public void GerarJasperFalta(ActionEvent event) throws IOException, JRException, SQLException { // FEITO (Gera o Jasper de Faltas)
		String erro ="";
		String jasper = "resources/Faltas.jasper";
		int i = cbDisciplina3.getSelectionModel().getSelectedItem().getCodigoDisciplina();
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("codigo", i);
		byte[] retorno  = null;
		JasperReport relatorio = (JasperReport)JRLoader.loadObjectFromFile(jasper);
		retorno  = JasperRunManager.runReportToPdf(relatorio, param,new GenericDAO().getConnection());
		File arq = new File("C:\\TEMP\\RelatorioFaltas.pdf");
		if(arq.exists()) {
			arq.delete();
		}
		FileOutputStream fos = new FileOutputStream(arq);
		fos.write(retorno);
		fos.flush();
		fos.close();
	}
	
	@FXML
	public void BuscarVFaltas(ActionEvent event) { // FAZER (MOSTRAR AS FALTAS DA DISCIPLINA SELECIONADA)
		
	}
	
	@FXML
	public void MascaraNota(KeyEvent event) { // FEITO (UMA INSTANCIA DA CLASSE NOTA PARA A MÁSCARA)
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("#.#");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtNota);
		tff.formatter();
	}

	private void limpaCamposNotaProva() { // FEITO (LIMPA OS CAMPOS APÓS A INSERÇÃO DA NOTA DO ALUNO)
		cbAluno.getItems().clear();
		txtNota.clear();
	}

}
