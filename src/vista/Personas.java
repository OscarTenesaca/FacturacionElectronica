package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.ClienteBD;
import control.Validar;
import modelo.ModCliente;

public class Personas extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JLabel lblCedula;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JLabel lblDireccion;
	private JTextField txtcedula;
	private JTextField txttelefono;
	private JTextField txtemail;
	private JTextField txtdireccion;
	private JButton btnGuardar;
	private JLabel lblNewLabel;
	private JButton btnModificar;
	private JButton btnListar;
	private JTable table;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnCedula;
	private JRadioButton rdbtnRuc;
	private JRadioButton rdbtnPasaporte;
	Validar valida = new Validar();
	int modifica = 0;
	int filas=0;
	ClienteBD CBD = new ClienteBD();
	private JTextField txtbuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personas frame = new Personas();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Personas() {
		setBounds(100, 100, 858, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtid = new JTextField();
		txtid.setBounds(141, 33, 174, 33);
		contentPane.add(txtid);
		txtid.setEnabled(false);
		txtid.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(24, 33, 101, 26);
		contentPane.add(lblId);

		lblCedula = new JLabel("DOCUMENTO");
		lblCedula.setBounds(19, 66, 106, 26);
		contentPane.add(lblCedula);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(19, 104, 116, 26);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(19, 142, 101, 26);
		contentPane.add(lblApellido);

		lblDireccion = new JLabel("DIRECCION");
		lblDireccion.setBounds(19, 241, 106, 26);
		contentPane.add(lblDireccion);

		lblTelefono = new JLabel("TELEFONO");
		lblTelefono.setBounds(19, 175, 106, 26);
		contentPane.add(lblTelefono);

		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(19, 206, 106, 26);
		contentPane.add(lblEmail);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("Iconos/logoPersonas.png"));
		lblNewLabel.setBounds(323, 93, 190, 190);
		contentPane.add(lblNewLabel);

		txtcedula = new JTextField();
		txtcedula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				System.out.println("salio caja");

				if (valida.validarCedula(txtcedula.getText()) == true) {

				} else {

					JOptionPane.showMessageDialog(null, "cedula incorrecta");
				}

			}
		});
		txtcedula.setBounds(141, 67, 174, 33);
		contentPane.add(txtcedula);
		txtcedula.setColumns(10);

		txtnombre = new JTextField();
		txtnombre.setBounds(141, 101, 174, 33);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);

		txtapellido = new JTextField();
		txtapellido.setBounds(141, 138, 174, 33);
		contentPane.add(txtapellido);
		txtapellido.setColumns(10);

		txttelefono = new JTextField();
		txttelefono.setBounds(141, 172, 174, 33);
		contentPane.add(txttelefono);
		txttelefono.setColumns(10);

		txtemail = new JTextField();
		txtemail.setBounds(141, 203, 174, 33);
		contentPane.add(txtemail);
		txtemail.setColumns(10);

		txtdireccion = new JTextField();
		txtdireccion.setBounds(141, 238, 174, 33);
		contentPane.add(txtdireccion);
		txtdireccion.setColumns(10);

		btnGuardar = new JButton("Guardar");
		
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int tipodoc = 0;
				if (rdbtnCedula.isSelected() == true) {
					tipodoc = 1;
				}
				if (rdbtnRuc.isSelected() == true) {
					tipodoc = 2;
				}
				if (rdbtnPasaporte.isSelected() == true) {
					tipodoc = 3;
				}

				if (valida.validarCedula(txtcedula.getText()) == true) {

					ModCliente cliente = new ModCliente();

					cliente.setIdPersona(Integer.parseInt(txtid.getText()));
					cliente.setCedula(txtcedula.getText());
					cliente.setNombre(txtnombre.getText());
					cliente.setApellido(txtapellido.getText());
					cliente.setTelefono(txttelefono.getText());
					cliente.setDireccion(txtdireccion.getText());
					cliente.setEmail(txtemail.getText());
					cliente.setTipoDocumento(tipodoc);

					if (modifica == 0) {

						CBD.Insertar(cliente);
						VaciarcajaTexto();
						CodigoCliente();

					} else {
						System.out.println("update");
						CBD.update(cliente);

						modifica = 0;
						VaciarcajaTexto();
						CodigoCliente();

					}
				} else {
					System.out.println("cedula invalida");
				}
				rellenar();
			}
			

		});
		btnGuardar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnGuardar.setIcon(new ImageIcon("Iconos/iconoGuardar.png"));
		btnGuardar.setBounds(318, 294, 151, 33);
		contentPane.add(btnGuardar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (valida.validarCedula(txtcedula.getText()) == true) {

					ClienteBD CBD = new ClienteBD();
					ModCliente datos = CBD.SelectCedula(txtcedula.getText());
					txtid.setText(datos.getIdPersona() + "");
					txtnombre.setText(datos.getNombre());
					txtapellido.setText(datos.getApellido());
					txtdireccion.setText(datos.getDireccion());
					txtemail.setText(datos.getEmail());
					txttelefono.setText(datos.getDireccion());

					modifica = 1;

				} else {
					System.out.println("cedula invalida");
				}

			}
		});
		btnModificar.setIcon(new ImageIcon("Iconos/iconoModificar.png"));
		btnModificar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnModificar.setBounds(547, 61, 151, 33);
		contentPane.add(btnModificar);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Cedula", "Nombre", "Apellido", "Telefono ", "E-mail", "Direccion" }));
		table.setBounds(24, 387, 803, 238);
		// contentPane.add(table);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 387, 803, 238);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);

		rdbtnCedula = new JRadioButton("cedula");
		rdbtnCedula.setBounds(321, 68, 66, 23);
		contentPane.add(rdbtnCedula);

		rdbtnRuc = new JRadioButton("ruc");
		rdbtnRuc.setBounds(389, 68, 56, 23);
		contentPane.add(rdbtnRuc);

		rdbtnPasaporte = new JRadioButton("pasaporte");
		rdbtnPasaporte.setBounds(445, 68, 109, 23);
		contentPane.add(rdbtnPasaporte);
		ButtonGroup tipo = new ButtonGroup();
		tipo.add(rdbtnCedula);
		tipo.add(rdbtnRuc);
		tipo.add(rdbtnPasaporte);

		txtbuscar = new JTextField();
		txtbuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int cont = 0;

				limpiarTable();
				
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
			
			
				

				
				List<ModCliente> d = CBD.ListarSelectcedula(txtbuscar.getText());

				for (ModCliente t : d) {

					model.addRow(new Object[filas]);
					table.setValueAt(t.getCedula(), cont, 0);
					table.setValueAt(t.getNombre(), cont, 1);
					table.setValueAt(t.getApellido(), cont, 2);
					table.setValueAt(t.getTelefono(), cont, 3);
					table.setValueAt(t.getEmail(), cont, 4);
					table.setValueAt(t.getDireccion(), cont, 5);
			
					cont++;

				}

			}

		});
		txtbuscar.setBounds(99, 339, 305, 20);
		contentPane.add(txtbuscar);
		txtbuscar.setColumns(10);

		JLabel lblnombre = new JLabel("NOMBRE");
		lblnombre.setBounds(10, 342, 46, 14);
		contentPane.add(lblnombre);

		CodigoCliente();
		rellenar();

	}

	private void CodigoCliente() {
		ClienteBD CBD = new ClienteBD();

		txtid.setText(CBD.NextCodProv() + "");

	}

	private void VaciarcajaTexto() {
		txtid.setText("");
		txtcedula.setText("");
		txtapellido.setText("");
		txtdireccion.setText("");
		txtemail.setText("");
		txtnombre.setText("");
		txttelefono.setText("");

	}

	private void rellenar() {
		limpiarTable();
		ClienteBD CBD = new ClienteBD();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int filas = 0;
		int cont = 0;
		for (ModCliente t : CBD.listar()) {

			model.addRow(new Object[filas]);
			table.setValueAt(t.getCedula(), cont, 0);
			table.setValueAt(t.getNombre(), cont, 1);
			table.setValueAt(t.getApellido(), cont, 2);
			table.setValueAt(t.getTelefono(), cont, 3);
			table.setValueAt(t.getEmail(), cont, 4);
			table.setValueAt(t.getDireccion(), cont, 5);
			filas++;
			cont++;

		}

	}

	protected void limpiarTable() {
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Cedula", "Nombre", "Apellido", "Telefono ", "E-mail", "Direccion" }));
		table.setBounds(24, 387, 803, 238);
		scrollPane.setViewportView(table);

		for (int i = 1; i < table.getRowCount(); i++) {
			table.setValueAt("", i, 0);
			table.setValueAt("", i, 1);
			table.setValueAt("", i, 2);
			table.setValueAt("", i, 3);
			table.setValueAt("", i, 4);
			table.setValueAt("", i, 5);

		}
		filas=0;
		
	}
}
