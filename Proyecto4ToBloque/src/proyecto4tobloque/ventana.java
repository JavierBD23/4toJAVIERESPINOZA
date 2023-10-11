 package proyecto4tobloque;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Color;

public class ventana extends JFrame {

    JPanel panelInicioSesion, panelNewUser, panelClientes;
    JTextField txtUsuario;
    JPasswordField txtContra;
    usuario misUsuarios[] = new usuario[10];
    cliente misClientes[] = new cliente[100];
    int contadorClientes = 10; //FAVOR NO TOCAR, CASI NO HACE NADA PERO ME AYUDA A MI :D
    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        misUsuarios[0] = new usuario("Admin", "123");
        misUsuarios[1] = new usuario("Otro", "321");
        //
        misClientes[0] = new cliente("741", "Hugo", 24, 'M');
        misClientes[1] = new cliente("742", "Ambrocio", 39, 'M');
        misClientes[2] = new cliente("743", "Kevin", 30, 'M');
        misClientes[3] = new cliente("744", "Facundo", 24, 'M');
        misClientes[4] = new cliente("745", "Francisco", 22, 'M');
        misClientes[5] = new cliente("432", "Ayala", 27, 'F');
        misClientes[6] = new cliente("654", "Gemima", 42, 'F');
        misClientes[7] = new cliente("969", "Saul", 45, 'M');
        misClientes[8] = new cliente("784", "Karen", 19, 'F');
        misClientes[9] = new cliente("420", "Consuelo", 62, 'F');
    }

    public void componentesInicioSesion() {
 
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        this.setTitle("Inicio Sesion");
 
        JLabel lblUsuario = new JLabel("Ingrese su usuario");
        lblUsuario.setBounds(50, 50, 150, 20);
        panelInicioSesion.add(lblUsuario);
        JLabel lblContra = new JLabel("Ingrese su contraseña");
        lblContra.setBounds(50, 80, 150, 20);
        panelInicioSesion.add(lblContra);
        
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 50, 150, 20);
        panelInicioSesion.add(txtUsuario);
        txtContra = new JPasswordField();
        txtContra.setBounds(180, 80, 150, 20);
        panelInicioSesion.add(txtContra);
        JButton btnIniciar = new JButton("Log in");
        btnIniciar.setBounds(50, 110, 150, 20);
        panelInicioSesion.add(btnIniciar);
        ActionListener InicioSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contra = txtContra.getText();
                if (BuscarUser(usuario, contra)) {
                    panelInicioSesion.setVisible(false);
                    componentesClientes();
                }
            }
        };
        btnIniciar.addActionListener(InicioSesion);
 
        JButton btnNewUser = new JButton("Register");
        btnNewUser.setBounds(50, 140, 150, 20);
        panelInicioSesion.add(btnNewUser);
        ActionListener newuser = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesNewUser();
                panelInicioSesion.setVisible(false);
                componentesNewUser();
            }
        };
        btnNewUser.addActionListener(newuser);
        panelInicioSesion.repaint();
    }

    public boolean BuscarUser(String usuario, String contra) {
        boolean encontrado = false;
        String nombre = "";
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(usuario) && misUsuarios[i].getPass().equals(contra)) {
                    encontrado = true;
                    nombre = misUsuarios[i].getName();
                    break;
                }
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Bienvenido");
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
        return encontrado;
    }

    public void componentesNewUser() {
 
        panelNewUser = new JPanel();
        panelNewUser.setLayout(null);
        this.getContentPane().add(panelNewUser);
        this.setTitle("New User");
        JLabel NewNombre = new JLabel("Ingrese nombre de usuario");
        NewNombre.setBounds(50, 50, 250, 20);
        panelNewUser.add(NewNombre);
        JLabel NewContra = new JLabel("Ingrese su contraseña de usuario");
        NewContra.setBounds(50, 80, 250, 20);
        panelNewUser.add(NewContra);
 
        JTextField txtUsuarioo = new JTextField();
        txtUsuarioo.setBounds(250, 50, 150, 20);
        panelNewUser.add(txtUsuarioo);
        JTextField txtContraa = new JPasswordField();
        txtContraa.setBounds(250, 80, 150, 20);
 
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 150, 150, 20);
        panelNewUser.add(btnGuardar);
        ActionListener guardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtUsuarioo.getText();
                String contra = txtContraa.getText();
                SaveUser(nombre, contra);
                txtUsuarioo.setText("");
                txtContraa.setText("");
            }
        };
        btnGuardar.addActionListener(guardar);
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 180, 150, 20);
        panelNewUser.add(btnVolver);
        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesInicioSesion();
                panelNewUser.setVisible(false);
            }
        };
        btnVolver.addActionListener(volver);
        panelNewUser.repaint();
    }

    public boolean SaveUser(String nombre, String contra) {
        boolean guardado = false;
        if (nombre.equals("") || contra.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
        } else {
            if (comprobarDuplicadoUser(nombre)) {
                JOptionPane.showMessageDialog(null, "Usuario Existente");
            } else {
                boolean vacio = false;
                int posicion = -1;
                for (int i = 0; i <= misUsuarios.length - 1; i++) {
                    if (misUsuarios[i] == null) {
                        vacio = true;
                        posicion = i;
                        break;
                    }
                }
                if (vacio) {
                    misUsuarios[posicion] = new usuario(nombre, contra);
                    JOptionPane.showMessageDialog(null, "Usuario Guardado  !");
                    guardado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "La memoria esta llena");
                }
            }
        }
        return guardado;
    }

    public boolean comprobarDuplicadoUser(String nombre) {
        boolean duplicado = false;
        for (int i = 0; i <= 9; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(nombre)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }
 

    public void componentesClientes() {
        char M = 'M';
        char F = 'F';
        int ContadorM = 0;
        int ContadorF = 0;
 
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        this.getContentPane().add(panelClientes);
        this.setTitle("Dashboard de clientes");
        
        JLabel lblClientes = new JLabel("Clientes almacenados");
        lblClientes.setBounds(50, 50, 250, 30);
        panelClientes.add(lblClientes);
        DefaultTableModel datos = new DefaultTableModel();
        datos.addColumn("NIT");
        datos.addColumn("Nombre");
        datos.addColumn("Edad");
        datos.addColumn("Genero");
        for (int i = 0; i <= misClientes.length - 1; i++) {
            if (misClientes[i] != null) {
                String temporal[] = {misClientes[i].getNit(), misClientes[i].getNombre(), String.valueOf(misClientes[i].getEdad()), String.valueOf(misClientes[i].getGenero())};
                datos.addRow(temporal);
            }
        }
         
        for (int i = 0; i <= misClientes.length - 1; i++) {
            if (misClientes[i] != null) {
                if (misClientes[i].getGenero()==(M)) {
                    ContadorM=ContadorM+1;
                } else if (misClientes[i].getGenero()==(F)) {
                    ContadorF=ContadorF+1;
                }
            }
        }
 
        JTable tablaClientes = new JTable(datos);
 
        JScrollPane barraClientes = new JScrollPane(tablaClientes);
        barraClientes.setBounds(50, 80, 450, 250);
        panelClientes.add(barraClientes);
        DefaultPieDataset generoGrafico = new DefaultPieDataset();
        generoGrafico.setValue("Masculino", ContadorM);
        generoGrafico.setValue("Femenino", ContadorF);
        JFreeChart graficoCircular = ChartFactory.createPieChart("Generos", generoGrafico, true, true, false);
        ChartPanel panelCircular = new ChartPanel(graficoCircular);
        panelCircular.setBounds(500, 80, 300, 200);
        panelClientes.add(panelCircular);
        this.setSize(850, 400);
        //Boton panel Nuevo Cliente
        JButton btnNewClient = new JButton("Nuevo Cliente");
        btnNewClient.setBounds(50, 20, 150, 20);
        panelClientes.add(btnNewClient);
        ActionListener nuevocl = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesNuevoCliente();
                panelClientes.setVisible(false);
            }
        };
        btnNewClient.addActionListener(nuevocl);
       
        JButton btnBack = new JButton("Volver");
        btnBack.setBounds(240, 20, 150, 20);
        panelClientes.add(btnBack);
        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesInicioSesion();
                panelClientes.setVisible(false);
            }
        };
        btnBack.addActionListener(volver);
    }
     public void componentesNuevoCliente() {
         
        JPanel panelNuevoCliente = new JPanel();
        panelNuevoCliente.setLayout(null);
        this.getContentPane().add(panelNuevoCliente);
        this.setTitle("Nuevo Cliente");

        JLabel lblNit = new JLabel("NIT de el  Cliente");
        lblNit.setBounds(50, 50, 250, 20);
        panelNuevoCliente.add(lblNit);
        JLabel lblNombre = new JLabel("Nombre de el Cliente");
        lblNombre.setBounds(50, 80, 250, 20);
        panelNuevoCliente.add(lblNombre);
        JLabel lblEdad = new JLabel("Edad de el Cliente");
        lblEdad.setBounds(50, 110, 250, 20);
        panelNuevoCliente.add(lblEdad);
        JLabel lblGenero = new JLabel("Género del Cliente (M/F)");
        lblGenero.setBounds(50, 140, 250, 20);
        panelNuevoCliente.add(lblGenero);

        JTextField txtNit = new JTextField();
        txtNit.setBounds(250, 50, 150, 20);
        panelNuevoCliente.add(txtNit);
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(250, 80, 150, 20);
        panelNuevoCliente.add(txtNombre);
        JTextField txtEdad = new JTextField();
        txtEdad.setBounds(250, 110, 150, 20);
        panelNuevoCliente.add(txtEdad);
        JTextField txtGenero = new JTextField();
        txtGenero.setBounds(250, 140, 150, 20);
        panelNuevoCliente.add(txtGenero);

        JButton btnSaveClient = new JButton("Guardar Cliente");
        btnSaveClient.setBounds(50, 180, 150, 20);
        panelNuevoCliente.add(btnSaveClient);

        ActionListener saveClient = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Nit = txtNit.getText();
                String nombre = txtNombre.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                char genero = txtGenero.getText().charAt(0);

                SaveClient(Nit, nombre, edad, genero);
                txtNit.setText("");
                txtNombre.setText("");
                txtEdad.setText("");
                txtGenero.setText("");
            }
        };
        btnSaveClient.addActionListener(saveClient);

        JButton btnRegresaar = new JButton("Volver a Clientes");
        btnRegresaar.setBounds(50, 210, 150, 20);
        panelNuevoCliente.add(btnRegresaar);

        ActionListener backToClients = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelNuevoCliente.setVisible(false);
                componentesClientes();
            }
        };
        btnRegresaar.addActionListener(backToClients);

        panelNuevoCliente.repaint();
    }

    public boolean SaveClient(String Nit,String nombre, int edad, char genero) {
        boolean guardado = false;
        if (Nit.equals("") || nombre.equals("") || String.valueOf(edad).equals("") || String.valueOf(genero).equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
        } else {
            if (comprobarDuplicadoNIT(Nit)) {
                JOptionPane.showMessageDialog(null, "NIT de Cliente Existente");
            } else {
                boolean vacio = false;
                int posicion = -1;
                for (int i = 0; i <= misClientes.length - 1; i++) {
                    if (misClientes[i] == null) {
                        vacio = true;
                        posicion = i;
                        break;
                    }
                }
                if (vacio) {
                    misClientes[posicion] = new cliente(Nit, nombre, edad, genero);
                    JOptionPane.showMessageDialog(null, "Cliente guardado correctamente");
                    guardado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "El almacenamiento de clientes está lleno.");
                }
            }
        }
        return guardado;
    }

    public boolean comprobarDuplicadoNIT(String NIT) {
        boolean duplicado = false;
        for (int i = 0; i <= misClientes.length-1; i++) {
            if (misClientes[i] != null) {
                if (misClientes[i].getNit().equals(NIT)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }
}
  