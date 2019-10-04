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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.EmpleadoBD;
import control.Validar;
import modelo.ModEmpleado;

public class Empleado extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JLabel lblCedula;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JLabel lblDireccion;
	private JTextField txtCedula;
	private JTextField txtUser;
	private JTextField txtPass;

	private JButton btnGuardar;
	private JLabel lblNewLabel;
	private JButton btnModificar;
	private JButton btnListar;
	private JTable table;
	private JScrollPane scrollPane;
	Validar valida = new Validar();
	int modifica = 0;
	EmpleadoBD EBD = new EmpleadoBD();
	private JTextField txtbuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empleado frame = new Empleado();
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
	public Empleado() {
		setTitle("Empleado");
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

		lblCedula = new JLabel("CEDULA");
		lblCedula.setBounds(19, 66, 106, 26);
		contentPane.add(lblCedula);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(19, 104, 116, 26);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(19, 142, 101, 26);
		contentPane.add(lblApellido);

		lblDireccion = new JLabel("CARGO");
		lblDireccion.setBounds(19, 241, 106, 26);
		contentPane.add(lblDireccion);

		lblTelefono = new JLabel("USER");
		lblTelefono.setBounds(19, 175, 106, 26);
		contentPane.add(lblTelefono);

		lblEmail = new JLabel("PASSWORD");
		lblEmail.setBounds(19, 206, 106, 26);
		contentPane.add(lblEmail);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("Iconos/logoPersonas.png"));
		lblNewLabel.setBounds(543, 93, 190, 190);
		contentPane.add(lblNewLabel);

		txtCedula = new JTextField();
		txtCedula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				if (valida.validarCedula(txtCedula.getText()) == true) {

				} else {

					JOptionPane.showMessageDialog(null, "cedula incorrecta");
				}

			}
		});
		txtCedula.setBounds(141, 67, 174, 33);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(141, 101, 174, 33);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setBounds(141, 138, 174, 33);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);

		txtUser = new JTextField();
		txtUser.setBounds(141, 172, 174, 33);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		txtPass = new JTextField();
		txtPass.setBounds(141, 203, 174, 33);
		contentPane.add(txtPass);
		txtPass.setColumns(10);

		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("empleado");
		combo.addItem("jefe");
		combo.setBounds(141, 238, 174, 33);
		contentPane.add(combo);
		/*
		 * txtCargo = new JTextField(); txtCargo.setBounds(141, 238, 174, 33);
		 * contentPane.add(txtCargo); txtCargo.setColumns(10);
		 */

		btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (valida.validarCedula(txtCedula.getText()) == true) {

					ModEmpleado empleado = new ModEmpleado();

					empleado.setId(Integer.parseInt(txtid.getText()));
					empleado.setCedula(txtCedula.getText());
					empleado.setNombre(txtNombre.getText());
					empleado.setApellido(txtApellido.getText());
					empleado.setPassword(txtPass.getText());
					empleado.setUser(txtUser.getText());
					System.out.println(combo.getSelectedItem().toString() + "este es el select");
					empleado.setCargo(combo.getSelectedItem().toString());

					if (modifica == 0) {

						EBD.Insertar(empleado);
						VaciarcajaTexto();
						CodigoCliente();

					} else {
						System.out.println("update");
						EBD.update(empleado);

						modifica = 0;
						VaciarcajaTexto();
						CodigoCliente();

					}
				} else {
					System.out.println("cedula invalida");
				}

			}

		});
		btnGuardar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnGuardar.setIcon(new ImageIcon("Iconos/iconoGuardar.png"));
		btnGuardar.setBounds(318, 294, 151, 33);
		contentPane.add(btnGuardar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (valida.validarCedula(txtCedula.getText()) == true) {

					ModEmpleado datos = EBD.SelectCedula(txtCedula.getText());
					txtid.setText(datos.getId() + "");
					txtNombre.setText(datos.getNombre());
					txtApellido.setText(datos.getApellido());

					txtPass.setText(datos.getPassword());
					txtUser.setText(datos.getUser());

					modifica = 1;

				} else {
					System.out.println("cedula invalida");
				}

			}
		});
		btnModificar.setIcon(new ImageIcon("Iconos/iconoModificar.png"));
		btnModificar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnModificar.setBounds(347, 77, 151, 33);
		contentPane.add(btnModificar);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Id", "Cedula", "Nombre", "Apellido ", "User", "Cargo" }));
		table.setBounds(24, 387, 803, 238);
		// contentPane.add(table);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 387, 803, 238);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);

		txtbuscar = new JTextField();
		txtbuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int cont = 0;

				limpiarTable();
				List<ModEmpleado> d = EBD.ListarSelectNombre(txtbuscar.getText());

				for (ModEmpleado t : d) {
					

					table.setValueAt(t.getId(), cont, 0);
					table.setValueAt(t.getCedula(), cont, 1);
					table.setValueAt(t.getNombre(), cont, 2);
					table.setValueAt(t.getApellido(), cont, 3);
					table.setValueAt(t.getUser(), cont, 4);
					table.setValueAt(t.getCargo(), cont, 5);
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
		EmpleadoBD EBD = new EmpleadoBD();

		txtid.setText(EBD.NextCodProv() + "");

	}

	private void VaciarcajaTexto() {
		txtid.setText("");
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellido.setText("");

		txtPass.setText("");

		txtUser.setText("");

	}

	private void rellenar() {
		limpiarTable();
		EmpleadoBD EBD = new EmpleadoBD();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int filas = 0;
		int cont = 0;
		for (ModEmpleado t : EBD.listar()) {

			model.addRow(new Object[filas]);
			table.setValueAt(t.getId(), cont, 0);
			table.setValueAt(t.getCedula(), cont, 1);
			table.setValueAt(t.getNombre(), cont, 2);
			table.setValueAt(t.getApellido(), cont, 3);
			table.setValueAt(t.getUser(), cont, 4);
			table.setValueAt(t.getCargo(), cont, 5);
			filas++;
			cont++;

		}

	}

	protected void limpiarTable() {

		for (int i = 1; i < table.getRowCount(); i++) {
			table.setValueAt("", i, 0);
			table.setValueAt("", i, 1);
			table.setValueAt("", i, 2);
			table.setValueAt("", i, 3);
			table.setValueAt("", i, 4);
			table.setValueAt("", i, 5);

		}

	}
}
