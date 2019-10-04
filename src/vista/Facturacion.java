package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.ClienteBD;
import control.EmpleadoBD;
import control.FacturaBD;
import control.IvaDescuento;
import control.ProductoBD;
import control.dfDetalleBD;
import modelo.Detalle_fac;
import modelo.ModCliente;
import modelo.ModEmpleado;
import modelo.ModFacCabecera;
import modelo.ModProducto;
import reportes.EnvioCorreo;
import reportes.FacturaReporte;
import reportes.Impreso;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.IOException;

import javax.swing.ScrollPaneConstants;



public class Facturacion extends JFrame {

	private JPanel contentPane;
	private JTextField txtCliente;
	private JTextField txtCedula;
	private JTextField txtFecha;
	private JTextField txtNomEmpleado;
	private JTextField txtDescuento;
	private JTextField txtTotal;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCodProduc;
	private JTable table;
	private JTextField txtSubtotal;
	private JTextField txtDes;
	private JTextField txtIva;
	private JTextField txtPago;
	private JTextField txtCambio;
	private JLabel lblempleadoL;
	private 	JScrollPane scrollPane;
	private JButton btnImprimir;
	int cantidad=1;
	int validador=0;
	double descuento = 0;
	double total=0;
	double sum=0;
	int idEmpleado;
	int idCliente;
	int stocktotal;
	int formaPago=1;
	int numCaja=1;
	ProductoBD PBD= new ProductoBD();
	EmpleadoBD EBD= new EmpleadoBD();
	ModFacCabecera MFC=new ModFacCabecera();
	IvaDescuento calculo = new IvaDescuento();
	
	ClienteBD CBD= new ClienteBD();
	FacturaBD FBD= new FacturaBD();
	int filas=0;
	List<ModProducto> listProduc = new ArrayList<ModProducto>();
	
	 private final static Logger LOGGER = Logger.getLogger("mx.hash.impresionpdf.Impresor");


	private JTable tablePro;
	private JTextField txtbuscar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Facturacion frame = new Facturacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Facturacion() {
		

		
		setBounds(200, 40, 1215, 968);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		SimpleDateFormat forma_dato = new SimpleDateFormat("dd-MM-yyyy");
		Calendar tiempo = new GregorianCalendar();

		JPanel panel = new JPanel();
		panel.setBounds(0, 1, 1187, 260);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		
		
		

		JLabel lblCedula = new JLabel("Cedula/RUC");
		lblCedula.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblCedula.setBounds(16, 54, 103, 33);
		panel.add(lblCedula);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblCliente.setBounds(16, 98, 109, 33);
		panel.add(lblCliente);

		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblDireccion.setBounds(16, 142, 103, 33);
		panel.add(lblDireccion);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblFecha.setBounds(460, 11, 56, 33);
		panel.add(lblFecha);

		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("High Tower Text", Font.BOLD, 20));
		lblTotal.setBounds(881, 11, 86, 41);
		panel.add(lblTotal);

		JLabel lblNumcedula = new JLabel("Num Factura");
		lblNumcedula.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNumcedula.setBounds(198, 11, 103, 33);
		panel.add(lblNumcedula);

		JLabel lblNumFactuta = new JLabel("Id Factura");
		lblNumFactuta.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNumFactuta.setBounds(16, 11, 103, 33);
		panel.add(lblNumFactuta);

		JLabel lblFormaDeCobro = new JLabel("Forma de Pago:");
		lblFormaDeCobro.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblFormaDeCobro.setBounds(646, 142, 139, 33);
		panel.add(lblFormaDeCobro);

		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblEmpleado.setBounds(16, 181, 92, 33);
		panel.add(lblEmpleado);

		JLabel lblDescuento = new JLabel("Descuento:   %");
		lblDescuento.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblDescuento.setBounds(646, 186, 120, 33);
		panel.add(lblDescuento);

		txtCliente = new JTextField();
		txtCliente.setEnabled(false);
		txtCliente.setColumns(10);
		txtCliente.setBounds(129, 100, 246, 33);
		panel.add(txtCliente);

		txtCedula = new JTextField();
		txtCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.out.println(txtCedula.getText().substring(0,3));
				if(txtCedula.getText().length()==10 && txtCedula.getText() != "9999999999"	) {
					
				 ModCliente datos= CBD.SelectCedula(txtCedula.getText());
				 if(datos!= null) {
					 idCliente= datos.getIdPersona();
				txtCliente.setText(datos.getNombre()+" "+datos.getApellido());
				txtDireccion.setText(datos.getDireccion());
				txtTelefono.setText(datos.getTelefono());
				
				 }else {
						JOptionPane.showMessageDialog(null, "no existe cliente" );
					 
					/* Personas p= new Personas();
					 p.setVisible(true);
					 */
				 }

				}else {
					if (txtCedula.getText().length()<10 && txtCedula.getText().length()>=3 && txtCedula.getText().substring(0, 3).equals("999")) {
						System.out.println("consumidor final");
						
						txtCedula.setText("9999999999");
						ModCliente datos= CBD.SelectCedula(txtCedula.getText());
						
							 idCliente= datos.getIdPersona();
						txtCliente.setText(datos.getNombre()+" "+datos.getApellido());
						txtDireccion.setText(datos.getDireccion());
						txtTelefono.setText(datos.getTelefono());
						
						
						
					}else {
						JOptionPane.showMessageDialog(null, "revisar cedula" );
						idCliente=0;
						txtCliente.setText("");
						txtDireccion.setText("");
						txtTelefono.setText("");
						
						
						
					}
					
					
					
				}
				
				
			
			}
		});
		txtCedula.setColumns(10);
		txtCedula.setBounds(129, 56, 131, 33);
		panel.add(txtCedula);

		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setText(forma_dato.format(tiempo.getTime()));
		txtFecha.setBounds(524, 13, 86, 33);
		panel.add(txtFecha);

		txtNomEmpleado = new JTextField();
		txtNomEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			ModEmpleado mem=	EBD.SelectCedula(txtNomEmpleado.getText());
			
			lblempleadoL.setText("Empleado: "+mem.getNombre());
			idEmpleado= mem.getId();
			}
		});
		txtNomEmpleado.setColumns(10);
		txtNomEmpleado.setBounds(129, 185, 480, 33);
		panel.add(txtNomEmpleado);

		txtDescuento = new JTextField();
		txtDescuento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				descuento=Double.parseDouble(txtDescuento.getText());
				Caltotal(0);
			}
		});
		txtDescuento.setText("0");
		txtDescuento.setColumns(10);
		txtDescuento.setBounds(768, 186, 103, 33);
		panel.add(txtDescuento);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
		txtTotal.setBounds(881, 86, 174, 134);
		panel.add(txtTotal);
		txtTotal.setColumns(10);	

		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(129, 144, 480, 33);
		panel.add(txtDireccion);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTelfono.setBounds(399, 105, 71, 19);
		panel.add(lblTelfono);

		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setBounds(480, 98, 129, 33);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				formaPago=comboBox.getSelectedIndex()+1;
				 
			}
		});
		comboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		comboBox.setBounds(769, 150, 97, 20);
		comboBox.addItem("Efectivo");
		comboBox.addItem("Targeta");
		comboBox.addItem("Cheque");
		panel.add(comboBox);

		JLabel lblNewLabel = new JLabel("----------");
		lblNewLabel.setForeground(new Color(0, 102, 153));
		lblNewLabel.setFont(new Font("GungsuhChe", Font.PLAIN, 20));
		lblNewLabel.setBounds(311, 17, 139, 25);
		panel.add(lblNewLabel);
		
		lblempleadoL = new JLabel("empleado : ");
		lblempleadoL.setBounds(129, 229, 235, 14);
		panel.add(lblempleadoL);
		
		JButton btnClienete = new JButton("Cliente");
		btnClienete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Personas p= new Personas();
				 p.setVisible(true);
				
			}
		});
		btnClienete.setBounds(304, 53, 120, 33);
		btnClienete.setIcon(new ImageIcon("Iconos/IconoAgregar.png"));
		panel.add(btnClienete);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 515, 1187, 422);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		table = new JTable();
		actiontable();
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "DESCRIPCION", "CANTIDAD", "P.V.P", "SUBTOTAL"
			}
		));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(210);
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.setBounds(10, 63, 414, 187);
		// contentPane.add(table);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 972, 205);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(table);
		panel_1.add(scrollPane);

		JLabel lblSub = new JLabel("SUBTOTAL: ");
		lblSub.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSub.setBounds(749, 231, 98, 20);
		panel_1.add(lblSub);

		JLabel lblTotalDescuento = new JLabel("TOTAL DCTO:");
		lblTotalDescuento.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTotalDescuento.setBounds(749, 280, 108, 33);
		panel_1.add(lblTotalDescuento);

		JLabel lblNewLabel_1 = new JLabel("IVA:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setBounds(749, 328, 39, 20);
		panel_1.add(lblNewLabel_1);

		txtSubtotal = new JTextField();
		txtSubtotal.setBounds(874, 227, 108, 33);
		panel_1.add(txtSubtotal);
		txtSubtotal.setColumns(10);

		txtDes = new JTextField();
		txtDes.setBounds(874, 273, 108, 33);
		panel_1.add(txtDes);
		txtDes.setColumns(10);

		txtIva = new JTextField();
		txtIva.setBounds(874, 324, 108, 33);
		panel_1.add(txtIva);
		txtIva.setColumns(10);

		JLabel lblObservaciones = new JLabel("OBSERVACIONES:");
		lblObservaciones.setFont(new Font("Dialog", Font.BOLD, 15));
		lblObservaciones.setBounds(10, 232, 143, 20);
		panel_1.add(lblObservaciones);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(164, 227, 563, 61);
		panel_1.add(textArea);

		JLabel lblPago = new JLabel("PAGO");
		lblPago.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPago.setBounds(497, 327, 59, 14);
		panel_1.add(lblPago);

		JLabel lblVuelto = new JLabel("CAMBIO");
		lblVuelto.setFont(new Font("Dialog", Font.BOLD, 15));
		lblVuelto.setBounds(497, 367, 69, 14);
		panel_1.add(lblVuelto);

		txtPago = new JTextField();
		txtPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Double cambio=Double.parseDouble(txtPago.getText());
				cambio= cambio-Double.parseDouble(txtTotal.getText());
				cambio= Double.parseDouble(String.format("%.2f",cambio));
				
				txtCambio.setText(cambio+"");
			}
		});
		txtPago.setBounds(603, 313, 124, 33);
		panel_1.add(txtPago);
		txtPago.setColumns(10);

		txtCambio = new JTextField();
		txtCambio.setBounds(603, 360, 124, 33);
		panel_1.add(txtCambio);
		txtCambio.setColumns(10);
		

		 btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent arg0) {
				 
				 Imprime();
			 }
		});
		btnImprimir.setBounds(274, 349, 108, 32);
		panel_1.add(btnImprimir);
		
		contentPane.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){
                //Aqui no funcionara
            }
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_F5){
                    JOptionPane.showMessageDialog(contentPane, "Has pulsado Fn5");
                }
                if(e.getKeyCode()==KeyEvent.VK_F10){
                    System.exit(0);
                   
                }
            }
            public void keyReleased(KeyEvent e){
                //Aqui tambien puedes insertar el codigo
            }
        });
		
	
		
		btnImprimir.addKeyListener(new KeyListener() {
     	   @Override
     	   public void keyTyped(KeyEvent arg0) {}
     	   @Override
     	   public void keyReleased(KeyEvent arg0) {}
     	   @Override
     	   public void keyPressed(KeyEvent event) {
     	    if(event.getKeyCode() == KeyEvent.VK_F1)
     	     System.exit(0);
     	   }
     	  });
		
		
		
		JButton btbBorrar = new JButton("Borrar");
		btbBorrar.setBounds(1002, 100, 153, 43);
		panel_1.add(btbBorrar);
		btbBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
			
				System.out.println( model.getValueAt(table.getSelectedRow(), 4));
				Double suma= 			  (Double) model.getValueAt(table.getSelectedRow(), 4);
				Caltotal(-suma);
			
				
				
				
					model.removeRow(table.getSelectedRow());
				//table.getSelectedRowCount();
				filas--;
			}
		});
		btbBorrar.setIcon(new ImageIcon("Iconos/IconoEliminar.png"));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 272, 1187, 232);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblCantidadDeProducto = new JLabel("Codigo de Producto:");
		lblCantidadDeProducto.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCantidadDeProducto.setBounds(10, 18, 169, 28);
		panel_2.add(lblCantidadDeProducto);

		txtCodProduc = new JTextField();
		txtCodProduc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	cantidad=1;
				ModProducto producto=PBD.Select(txtCodProduc.getText());
				
				if(producto != null) {
				
					
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				if(producto.getStock()>=cantidad) {
				model.addRow(new Object[filas]);
				
				for (int x = 0; x < table.getColumnCount()-1; x++) {
					
					
					model.setValueAt(producto.getCodProducto(), filas,0);
					model.setValueAt(producto.getDescripcion(), filas,1);
					
					
					
					
					
					model.setValueAt(cantidad+"", filas,2);
					model.setValueAt(producto.getPVP(), filas,3);
					sum=producto.getPVP();
					model.setValueAt(sum, filas,4);
					
					
					
				}
				Caltotal(sum);
				
				filas++;
				txtCodProduc.setText("");
				
				cantidad=1;
				}else {
					JOptionPane.showMessageDialog(null, "no existe cantidad ");	
				}
		
			}else {
				JOptionPane.showMessageDialog(null, "no existe producto ");	
			}
				}

		
			
		});
		txtCodProduc.setBounds(191, 20, 110, 28);
		panel_2.add(txtCodProduc);
		txtCodProduc.setColumns(10);
		
		tablePro = new JTable();
		tablePro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			
			
			
			}
		});
		tablePro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int key = arg0.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
				
				
				System.out.println("select");
				
				DefaultTableModel modelPro = (DefaultTableModel)tablePro.getModel();
				
				System.out.println( modelPro.getValueAt(tablePro.getSelectedRow(), 1));
				
					

				cantidad=1;
							
							
						
							
								
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							
							if(Double.parseDouble(modelPro.getValueAt(tablePro.getSelectedRow(), 4)+"")>=cantidad) {
							model.addRow(new Object[filas]);
							
							
								
								
								model.setValueAt(modelPro.getValueAt(tablePro.getSelectedRow(), 0), filas,0);
								model.setValueAt(modelPro.getValueAt(tablePro.getSelectedRow(), 1), filas,1);
								model.setValueAt(cantidad+"", filas,2);
								Double suma=Double.parseDouble(modelPro.getValueAt(tablePro.getSelectedRow(), 3)+"");
								model.setValueAt(suma, filas,3);
								model.setValueAt(suma, filas,4);
								
								
								
							
								
								
								
								
								
								
							
							Caltotal(suma);
							
							filas++;
						
							
							cantidad=1;
							}else {
								JOptionPane.showMessageDialog(null, "no existe cantidad ");	
							}
					
						
							
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			
				
				
				
				
				txtbuscar.setText("");	
				rellenar();
				
			}
			}});
		
		tablePro.setEditingColumn(0);
		tablePro.setEditingColumn(1);
		tablePro.setEditingColumn(2);
		tablePro.setEditingColumn(3);
		tablePro.setEditingColumn(4);
		tablePro.setEditingColumn(5);
		tablePro.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Codigo", "Descripcion", "PrecioCompra", "P.V.P ", "Stock", "Proveedor" }));
		//tablePro.setBounds(6, 6, 709, 238);
		// contentPane.add(table);

		JScrollPane scrollPanePro = new JScrollPane();
		scrollPanePro.setBounds(10, 79, 969, 140);
		scrollPanePro.setVerticalScrollBarPolicy(scrollPanePro.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanePro.setViewportView(tablePro);
		panel_2.add(scrollPanePro);
		
		txtbuscar = new JTextField();
		txtbuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				ProductoBD PBD = new ProductoBD();
				int contPro = 0;

				limpiartable();
				List<ModProducto> d = PBD.SelectDescripcion(txtbuscar.getText());

				for (ModProducto t : d) {
			

					tablePro.setValueAt(t.getCodProducto(), contPro, 0);
					tablePro.setValueAt(t.getDescripcion(), contPro, 1);
					tablePro.setValueAt(t.getPCompra(), contPro, 2);
					tablePro.setValueAt(t.getPVP(), contPro, 3);
					tablePro.setValueAt(t.getStock(), contPro, 4);
					tablePro.setValueAt(t.getIdProveedor(), contPro, 5);
					contPro++;
				}

				;

			}
		});


		txtbuscar.setBounds(191, 50, 304, 28);
		panel_2.add(txtbuscar);
		txtbuscar.setColumns(10);
		
		JLabel lblDescipcionProducto = new JLabel("Descipcion Producto:");
		lblDescipcionProducto.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDescipcionProducto.setBounds(9, 47, 169, 28);
		panel_2.add(lblDescipcionProducto);

		
		rellenar();
		
	}
	
	private void actiontable() {
		
		

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int id=  (int)model.getValueAt(table.getSelectedRow(),0);
				String cantidad= model.getValueAt(table.getSelectedRow(),2)+"";
				if(Integer.parseInt(cantidad) > 1) {
				System.out.println( model.getValueAt(table.getSelectedRow(),0)+"valor de id  000	");
				
				ModProducto producto=PBD.Select(id+"");
				
				if(producto != null) {
				
					
					
				if(producto.getStock()>=Integer.parseInt(cantidad)) {
					
				System.out.println("valor del prodcto si existe");
				
				double valorante=Double.parseDouble(model.getValueAt(table.getSelectedRow(),4)+"");
				 double suma = Double.parseDouble(String.format("%.2f",producto.getPVP()*Integer.parseInt(cantidad)));
				 model.setValueAt(suma, table.getSelectedRow(),4);
					Caltotal(-valorante);
					Caltotal(suma);
				}else {
					JOptionPane.showMessageDialog(null, "no existe cantidad cantidad existente:" +producto.getStock());
					model.setValueAt(producto.getStock(), table.getSelectedRow(),2);
				
					
				}
				}
				
				}else {
					model.setValueAt(1, table.getSelectedRow(),2);
					
				}
			}
			});


		// TODO Auto-generated method stub
		
	}

	protected void Imprime() {
		 MFC=new ModFacCabecera();
				

		 /*

		
		

		if(comprobardatos() == true && txtCliente.getText().length()>2 && lblempleadoL.getText().length() > 12 && table.getColumnCount()>0) {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			
			
			

			 MFC.setId_cliente(idCliente);
			 MFC.setId_empleado(idEmpleado);
			 MFC.setDescuento(Integer.parseInt(txtDescuento.getText()));
			 MFC.setSubTotal(Double.parseDouble(txtSubtotal.getText()));
			 MFC.setTotalDescuento(Double.parseDouble(txtDes.getText()));
			 MFC.setIva(Double.parseDouble(txtIva.getText()));
			 MFC.setTotal(Double.parseDouble(txtTotal.getText()));
			 MFC.setForma_pago(formaPago);
			 MFC.setNum_caja(numCaja);
			 
			 String Nfac=String.format("%015d",FBD.NextCodFac());
				System.out.println(Nfac);
			 MFC.setNum_factura(Nfac);
			 
			 FBD.Insertar(MFC);
			
			
			 for (int i = 0; i < table.getRowCount(); i++) {
	
					int mcod= Integer.parseInt(model.getValueAt(i,0)+"");
					
				

					PBD.UpdateStock(mcod,stocktotal);
					
					Detalle_fac deF= new Detalle_fac();
					deF.setNumFactura(Nfac);
					deF.setIdProducto(mcod);
					deF.setCantidad(Integer.parseInt(model.getValueAt(i,2)+""));
					dfDetalleBD DFBD= new dfDetalleBD();
					DFBD.Insertar(deF);
					
				 }
			 filas=0;
		
			Limpiar();
			
		*/
		 String Nfac="000000000000002";
	
			FacturaReporte fr= new FacturaReporte(Nfac);
			fr.setVisible(true);
			fr.setBounds(20, 20, 700, 900);
			
			System.out.println("continua facturacion");

			
			 Impreso impresor = new Impreso();

		        try {
		            impresor.imprimir(Nfac);
		        } catch (PrinterException | IOException ex) {
		            JOptionPane.showMessageDialog(null, "Error de impresion", "Error", JOptionPane.ERROR_MESSAGE);
		            LOGGER.log(Level.SEVERE, null, ex);
		        }
		        
		        
		        EnvioCorreo  ec=new EnvioCorreo();
				
				
				ec.EnviarCorreo("tenesacaoscar.777@gmail.com",Nfac);
				System.out.println("enviando correo ...");
			
		}
		//}
	
	
	
	//	else {
	//		System.out.println("no se puede facturar");
	//	}
		
		
		

		
		// TODO Auto-generated method stub
		
	

	private void rellenar() {
		limpiartable();
		ProductoBD PBD = new ProductoBD();
		listProduc = PBD.listar();
		DefaultTableModel model_1 = (DefaultTableModel) tablePro.getModel();
		int filasPro = 0;
		int contPro = 0;
		for (ModProducto t : listProduc) {
if(contPro >= validador) {
			model_1.addRow(new Object[filas]);
}
			tablePro.setValueAt(t.getCodProducto(), contPro, 0);
			tablePro.setValueAt(t.getDescripcion(), contPro, 1);
			tablePro.setValueAt(t.getPCompra(), contPro, 2);
			tablePro.setValueAt(t.getPVP(), contPro, 3);
			tablePro.setValueAt(t.getStock(), contPro, 4);
			tablePro.setValueAt(t.getIdProveedor(), contPro, 5);
			filasPro++;
			contPro++;
		}

	}
	
	
	protected void limpiartable() {
validador=0;
		for (int i = 1; i < tablePro.getRowCount(); i++) {
			tablePro.setValueAt("", i, 0);
			tablePro.setValueAt("", i, 1);
			tablePro.setValueAt("", i, 2);
			tablePro.setValueAt("", i, 3);
			tablePro.setValueAt("", i, 4);
			tablePro.setValueAt("", i, 5);
		validador++;
		}

	}
	protected void Limpiar() {



		txtCliente.setText("");
		txtCedula.setText("");
		txtTotal.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtPago.setText("");
		txtCambio.setText("");
		txtSubtotal.setText("");
		txtDes.setText("");
		txtIva.setText("");
		




table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"CODIGO", "DESCRIPCION", "P.V.P", "CANTIDAD", "SUBTOTAL"
				}
			));
			
			table.getColumnModel().getColumn(0).setPreferredWidth(45);
			
			table.getColumnModel().getColumn(1).setPreferredWidth(210);
			table.getColumnModel().getColumn(2).setPreferredWidth(55);
			table.getColumnModel().getColumn(3).setPreferredWidth(55);
			table.getColumnModel().getColumn(4).setPreferredWidth(70);
			table.setBounds(10, 63, 414, 187);
			
			
			actiontable();
			
			
			
			
			scrollPane.setViewportView(table);
			
		descuento = 0;
		 total=0;
		 sum=0;
	
		 idCliente= 0;
		
	
	}

	protected boolean comprobardatos() {

		
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		 for (int i = 0; i < table.getRowCount(); i++) {
			 
			 
			
			 
		System.out.println(model.getValueAt(i,0));
		
			 String cod=model.getValueAt(i,0)+"";
			 
		//	 System.out.println("codigo"+cod);
				ModProducto producto=PBD.Select(cod);
				 String cant= model.getValueAt(i,2)+"";
				if(producto.getStock()>= Integer.parseInt(cant)) {
				
					stocktotal=producto.getStock()-Integer.parseInt(cant);
					System.out.println(stocktotal+"new stock");
				}else {
					JOptionPane.showMessageDialog(null, "producto num "+cod+"su stock es de "+producto.getStock()+"su compra no puede ecceder de este estock");
					
					return false;
				}

			 }
		 return true;
		
		
		
	}

	private void Caltotal(double sum) {
		double aux=0;
		total += sum;
		
		String auxtot;
		auxtot=String.format("%.2f", total);
		auxtot=auxtot.replace(",", ".");
		total=Double.parseDouble(auxtot);
		
	//	total=Double.parseDouble(String.format("%.2f",total));
		txtSubtotal.setText(total+"");
		descuento = Double.parseDouble(txtDescuento.getText());
		
		
		
		txtDes.setText(calculo.calculoDescuento(Double.parseDouble(txtSubtotal.getText()),descuento)+"");
		
		aux = (Double.parseDouble(txtSubtotal.getText()) - Double.parseDouble(txtDes.getText()));
		
	//	String auxiva;
	//	auxiva=String.format("%.2f", total);
	//	auxiva=auxiva.replace(",", ".");
	//	aux=Double.parseDouble(auxiva);
		
	
		
		txtIva.setText(Double.parseDouble(String.format("%.2f",calculo.calculoIva(aux)))+"");
		
	
		
		txtTotal.setText(Double.parseDouble(String.format("%.2f",(aux + Double.parseDouble(txtIva.getText()))))+"");
		

	}
	
	
	public double MetodoRemplazo(double decimales) {
		
		String auxtot;
		auxtot=String.format("%.2f", decimales);
		auxtot=auxtot.replace(",", ".");
		decimales=Double.parseDouble(auxtot);
		
		
		return decimales;
		
	}
	
	
}
