package racadauto;

import Conexion.Conexion;
import Conexion.Trabajador;
import com.mysql.jdbc.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Principal extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection cn = (Connection) con.getConnection();
    Trabajador mod;
    
    public Principal() {
        
        initComponents();
    }

    public Principal(Trabajador mod) {
        
        JOptionPane.showMessageDialog(null,
                "BIENVENIDO, " + mod.getNombre() + " " + mod.getPaterno() + " " + mod.getMaterno(), "WELCOME",
                JOptionPane.INFORMATION_MESSAGE);
        
        initComponents();
        setLocationRelativeTo(null);
        this.mod = mod;

        LBL_trabajador.setText(mod.getNombre() + " " + mod.getPaterno() + " " + mod.getMaterno());
        LBL_cargo.setText(mod.getCargo());

        if (mod.getCargo().equals("JEFE DE TALLER")) {

        } else if (mod.getCargo().equals("SUPERVISOR")) {

            subEliminarServicio.setEnabled(false);
            subEliminarCategoria.setEnabled(false);
            subEliminarTrabajador.setEnabled(false);
            subEliminarCargo.setEnabled(false);
            subEliminarItem.setEnabled(false);
            subEliminarMedida.setEnabled(false);
            subEliminarFamilia.setEnabled(false);
            subEliminarCliente.setEnabled(false);
            subEliminarCiudad.setEnabled(false);
            subEliminarVehiculo.setEnabled(false);
            subEliminarMotor.setEnabled(false);
            subEliminarTipoVehiculo.setEnabled(false);
            subEliminarCombustible.setEnabled(false);
            subEliminarMarca.setEnabled(false);
            subEliminarModelo.setEnabled(false);

        } else {

            menuTrabajadores.setEnabled(false);
            menuInventario.setEnabled(false);
            menuCategoria.setEnabled(false);
            menuServicios.setEnabled(false);
            menuMarca.setEnabled(false);
            menuModelo.setEnabled(false);
            menuTipoVehiculo.setEnabled(false);
            menuCombustible.setEnabled(false);
            menuMotor.setEnabled(false);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem48 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LBL_trabajador = new javax.swing.JLabel();
        LBL_cargo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuLogin = new javax.swing.JMenu();
        subCerrarSesion = new javax.swing.JMenuItem();
        subSalir = new javax.swing.JMenuItem();
        menuTabajos = new javax.swing.JMenu();
        subPresupuesto = new javax.swing.JMenuItem();
        subOT = new javax.swing.JMenuItem();
        subModificarOT = new javax.swing.JMenuItem();
        subFacturarOT = new javax.swing.JMenuItem();
        menuServicios = new javax.swing.JMenu();
        subNuevoServicio = new javax.swing.JMenuItem();
        subModificarServicio = new javax.swing.JMenuItem();
        subBuscarServicio = new javax.swing.JMenuItem();
        subEliminarServicio = new javax.swing.JMenuItem();
        menuCategoria = new javax.swing.JMenu();
        subNuevaCategoria = new javax.swing.JMenuItem();
        subModificarCategoria = new javax.swing.JMenuItem();
        subBuscarCategoria = new javax.swing.JMenuItem();
        subEliminarCategoria = new javax.swing.JMenuItem();
        menuTrabajadores = new javax.swing.JMenu();
        subNuevoTrabajador = new javax.swing.JMenuItem();
        subModificarTrabajador = new javax.swing.JMenuItem();
        subBuscarTrabajador = new javax.swing.JMenuItem();
        subEliminarTrabajador = new javax.swing.JMenuItem();
        menuCargos = new javax.swing.JMenu();
        subNuevoCargo = new javax.swing.JMenuItem();
        subModificarCargo = new javax.swing.JMenuItem();
        subBuscarCargo = new javax.swing.JMenuItem();
        subEliminarCargo = new javax.swing.JMenuItem();
        menuInventario = new javax.swing.JMenu();
        subNuevoItem = new javax.swing.JMenuItem();
        subModificarItem = new javax.swing.JMenuItem();
        subBuscarItem = new javax.swing.JMenuItem();
        subEliminarItem = new javax.swing.JMenuItem();
        subAjustarItem = new javax.swing.JMenuItem();
        menuMedida = new javax.swing.JMenu();
        subNuevaMedida = new javax.swing.JMenuItem();
        subModificarMedida = new javax.swing.JMenuItem();
        subBuscarMedida = new javax.swing.JMenuItem();
        subEliminarMedida = new javax.swing.JMenuItem();
        menuFamilia = new javax.swing.JMenu();
        subNuevaFamilia = new javax.swing.JMenuItem();
        subModificarFamilia = new javax.swing.JMenuItem();
        subBuscarFamilia = new javax.swing.JMenuItem();
        subEliminarFamilia = new javax.swing.JMenuItem();
        menuClientes = new javax.swing.JMenu();
        subNuevoCliente = new javax.swing.JMenuItem();
        subModificarCliente = new javax.swing.JMenuItem();
        subBuscarCliente = new javax.swing.JMenuItem();
        subEliminarCliente = new javax.swing.JMenuItem();
        menuCiudad = new javax.swing.JMenu();
        subNuevaCiudad = new javax.swing.JMenuItem();
        subModificarCiudad = new javax.swing.JMenuItem();
        subBuscarCiudad = new javax.swing.JMenuItem();
        subEliminarCiudad = new javax.swing.JMenuItem();
        menuVehiculos = new javax.swing.JMenu();
        subNuevoVehiculo = new javax.swing.JMenuItem();
        subModificarVehiculo = new javax.swing.JMenuItem();
        subBuscarVehiculo = new javax.swing.JMenuItem();
        subEliminarVehiculo = new javax.swing.JMenuItem();
        menuMarca = new javax.swing.JMenu();
        subNuevaMarca = new javax.swing.JMenuItem();
        subModificarMarca = new javax.swing.JMenuItem();
        subBuscarMarca = new javax.swing.JMenuItem();
        subEliminarMarca = new javax.swing.JMenuItem();
        menuModelo = new javax.swing.JMenu();
        subNuevoModelo = new javax.swing.JMenuItem();
        subModificarModelo = new javax.swing.JMenuItem();
        subBuscarModelo = new javax.swing.JMenuItem();
        subEliminarModelo = new javax.swing.JMenuItem();
        menuTipoVehiculo = new javax.swing.JMenu();
        subNuevoTipoVehiculo = new javax.swing.JMenuItem();
        subModificarTipoVehiculo = new javax.swing.JMenuItem();
        subBuscarTipoVehiculo = new javax.swing.JMenuItem();
        subEliminarTipoVehiculo = new javax.swing.JMenuItem();
        menuMotor = new javax.swing.JMenu();
        subNuevoMotor = new javax.swing.JMenuItem();
        subModificarMotor = new javax.swing.JMenuItem();
        subBuscarMotor = new javax.swing.JMenuItem();
        subEliminarMotor = new javax.swing.JMenuItem();
        menuCombustible = new javax.swing.JMenu();
        subNuevoCombustible = new javax.swing.JMenuItem();
        subModificarCombustible = new javax.swing.JMenuItem();
        subBuscarCombustible = new javax.swing.JMenuItem();
        subEliminarCombustible = new javax.swing.JMenuItem();
        jMenu22 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuInformes = new javax.swing.JMenu();
        subInfTrabajadores = new javax.swing.JMenuItem();
        subInfInventario = new javax.swing.JMenuItem();
        subInfClientes = new javax.swing.JMenuItem();
        subInfVehiculos = new javax.swing.JMenuItem();

        jMenu6.setText("File");
        jMenuBar2.add(jMenu6);

        jMenu7.setText("Edit");
        jMenuBar2.add(jMenu7);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenu9.setText("File");
        jMenuBar3.add(jMenu9);

        jMenu10.setText("Edit");
        jMenuBar3.add(jMenu10);

        jMenuItem5.setText("jMenuItem5");

        jMenuItem14.setText("jMenuItem14");

        jMenuItem16.setText("jMenuItem16");

        jMenuItem19.setText("jMenuItem19");

        jMenuItem22.setText("jMenuItem22");

        jMenuItem25.setText("jMenuItem25");

        jMenuItem12.setText("jMenuItem12");

        jMenuItem9.setText("jMenuItem9");

        jMenuItem40.setText("jMenuItem40");

        jMenuItem45.setText("jMenuItem45");

        jMenuItem48.setText("jMenuItem48");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RACAD AUTOMOTRIZ");
        setMinimumSize(new java.awt.Dimension(500, 400));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Cargo:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Trabajador:");

        LBL_trabajador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        LBL_cargo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/racadauto/Images/Racad.jpeg"))); // NOI18N
        jLabel3.setText("jLabel3");

        menuLogin.setText("Login");

        subCerrarSesion.setText("Cerrar Sesion");
        subCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subCerrarSesionActionPerformed(evt);
            }
        });
        menuLogin.add(subCerrarSesion);

        subSalir.setText("Salir");
        subSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subSalirActionPerformed(evt);
            }
        });
        menuLogin.add(subSalir);

        jMenuBar1.add(menuLogin);

        menuTabajos.setText("Trabajos");

        subPresupuesto.setText("Presupuestar Trabajo");
        subPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subPresupuestoActionPerformed(evt);
            }
        });
        menuTabajos.add(subPresupuesto);

        subOT.setText("Orden de Trabajo");
        subOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subOTActionPerformed(evt);
            }
        });
        menuTabajos.add(subOT);

        subModificarOT.setText("Modificar Trabajo");
        subModificarOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarOTActionPerformed(evt);
            }
        });
        menuTabajos.add(subModificarOT);

        subFacturarOT.setText("Facturar Trabajo");
        subFacturarOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subFacturarOTActionPerformed(evt);
            }
        });
        menuTabajos.add(subFacturarOT);

        menuServicios.setText("Servicios");

        subNuevoServicio.setText("Nuevo Servicio");
        subNuevoServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoServicioActionPerformed(evt);
            }
        });
        menuServicios.add(subNuevoServicio);

        subModificarServicio.setText("Modificar Servicio");
        subModificarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarServicioActionPerformed(evt);
            }
        });
        menuServicios.add(subModificarServicio);

        subBuscarServicio.setText("Consultar Servicio");
        subBuscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarServicioActionPerformed(evt);
            }
        });
        menuServicios.add(subBuscarServicio);

        subEliminarServicio.setText("Eliminar Servicio");
        subEliminarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarServicioActionPerformed(evt);
            }
        });
        menuServicios.add(subEliminarServicio);

        menuTabajos.add(menuServicios);

        menuCategoria.setText("Categoría");

        subNuevaCategoria.setText("Nueva Categoría");
        subNuevaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevaCategoriaActionPerformed(evt);
            }
        });
        menuCategoria.add(subNuevaCategoria);

        subModificarCategoria.setText("Modificar Categoría");
        subModificarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarCategoriaActionPerformed(evt);
            }
        });
        menuCategoria.add(subModificarCategoria);

        subBuscarCategoria.setText("Consultar Categoría");
        subBuscarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarCategoriaActionPerformed(evt);
            }
        });
        menuCategoria.add(subBuscarCategoria);

        subEliminarCategoria.setText("Eliminar Categoría");
        subEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarCategoriaActionPerformed(evt);
            }
        });
        menuCategoria.add(subEliminarCategoria);

        menuTabajos.add(menuCategoria);

        jMenuBar1.add(menuTabajos);

        menuTrabajadores.setText("Trabajadores");

        subNuevoTrabajador.setText("Nuevo Trabajador");
        subNuevoTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoTrabajadorActionPerformed(evt);
            }
        });
        menuTrabajadores.add(subNuevoTrabajador);

        subModificarTrabajador.setText("Modificar Trabajador");
        subModificarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarTrabajadorActionPerformed(evt);
            }
        });
        menuTrabajadores.add(subModificarTrabajador);

        subBuscarTrabajador.setText("Consultar Trabajador");
        subBuscarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarTrabajadorActionPerformed(evt);
            }
        });
        menuTrabajadores.add(subBuscarTrabajador);

        subEliminarTrabajador.setText("Eliminar Trabajador");
        subEliminarTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarTrabajadorActionPerformed(evt);
            }
        });
        menuTrabajadores.add(subEliminarTrabajador);

        menuCargos.setText("Cargos");

        subNuevoCargo.setText("Nuevo Cargo");
        subNuevoCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoCargoActionPerformed(evt);
            }
        });
        menuCargos.add(subNuevoCargo);

        subModificarCargo.setText("Modificar Cargo");
        subModificarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarCargoActionPerformed(evt);
            }
        });
        menuCargos.add(subModificarCargo);

        subBuscarCargo.setText("Consultar Cargo");
        subBuscarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarCargoActionPerformed(evt);
            }
        });
        menuCargos.add(subBuscarCargo);

        subEliminarCargo.setText("Eliminar Cargo");
        subEliminarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarCargoActionPerformed(evt);
            }
        });
        menuCargos.add(subEliminarCargo);

        menuTrabajadores.add(menuCargos);

        jMenuBar1.add(menuTrabajadores);

        menuInventario.setText("Inventario");

        subNuevoItem.setText("Nuevo Item");
        subNuevoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoItemActionPerformed(evt);
            }
        });
        menuInventario.add(subNuevoItem);

        subModificarItem.setText("Modificar Item");
        subModificarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarItemActionPerformed(evt);
            }
        });
        menuInventario.add(subModificarItem);

        subBuscarItem.setText("Consultar Item");
        subBuscarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarItemActionPerformed(evt);
            }
        });
        menuInventario.add(subBuscarItem);

        subEliminarItem.setText("Eliminar Item");
        subEliminarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarItemActionPerformed(evt);
            }
        });
        menuInventario.add(subEliminarItem);

        subAjustarItem.setText("Ajustar Item");
        subAjustarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subAjustarItemActionPerformed(evt);
            }
        });
        menuInventario.add(subAjustarItem);

        menuMedida.setText("Unidad de Medida");

        subNuevaMedida.setText("Nueva Medida");
        subNuevaMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevaMedidaActionPerformed(evt);
            }
        });
        menuMedida.add(subNuevaMedida);

        subModificarMedida.setText("Modificar Medida");
        subModificarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarMedidaActionPerformed(evt);
            }
        });
        menuMedida.add(subModificarMedida);

        subBuscarMedida.setText("Consultar Medida");
        subBuscarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarMedidaActionPerformed(evt);
            }
        });
        menuMedida.add(subBuscarMedida);

        subEliminarMedida.setText("Eliminar Medida");
        subEliminarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarMedidaActionPerformed(evt);
            }
        });
        menuMedida.add(subEliminarMedida);

        menuInventario.add(menuMedida);

        menuFamilia.setText("Familia");

        subNuevaFamilia.setText("Nueva Familia");
        subNuevaFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevaFamiliaActionPerformed(evt);
            }
        });
        menuFamilia.add(subNuevaFamilia);

        subModificarFamilia.setText("Modificar Familia");
        subModificarFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarFamiliaActionPerformed(evt);
            }
        });
        menuFamilia.add(subModificarFamilia);

        subBuscarFamilia.setText("Consultar Familia");
        subBuscarFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarFamiliaActionPerformed(evt);
            }
        });
        menuFamilia.add(subBuscarFamilia);

        subEliminarFamilia.setText("Eliminar Familia");
        subEliminarFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarFamiliaActionPerformed(evt);
            }
        });
        menuFamilia.add(subEliminarFamilia);

        menuInventario.add(menuFamilia);

        jMenuBar1.add(menuInventario);

        menuClientes.setText("Clientes");

        subNuevoCliente.setText("Nuevo Cliente");
        subNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoClienteActionPerformed(evt);
            }
        });
        menuClientes.add(subNuevoCliente);

        subModificarCliente.setText("Modificar Cliente");
        subModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarClienteActionPerformed(evt);
            }
        });
        menuClientes.add(subModificarCliente);

        subBuscarCliente.setText("Consultar Cliente");
        subBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarClienteActionPerformed(evt);
            }
        });
        menuClientes.add(subBuscarCliente);

        subEliminarCliente.setText("Eliminar Cliente");
        subEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarClienteActionPerformed(evt);
            }
        });
        menuClientes.add(subEliminarCliente);

        menuCiudad.setText("Ciudad");

        subNuevaCiudad.setText("Nueva Ciudad");
        subNuevaCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevaCiudadActionPerformed(evt);
            }
        });
        menuCiudad.add(subNuevaCiudad);

        subModificarCiudad.setText("Modificar Ciudad");
        subModificarCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarCiudadActionPerformed(evt);
            }
        });
        menuCiudad.add(subModificarCiudad);

        subBuscarCiudad.setText("Consular Ciudad");
        subBuscarCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarCiudadActionPerformed(evt);
            }
        });
        menuCiudad.add(subBuscarCiudad);

        subEliminarCiudad.setText("Eliminar Ciudad");
        subEliminarCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarCiudadActionPerformed(evt);
            }
        });
        menuCiudad.add(subEliminarCiudad);

        menuClientes.add(menuCiudad);

        jMenuBar1.add(menuClientes);

        menuVehiculos.setText("Vehiculos");

        subNuevoVehiculo.setText("Ingresar Vehiculo");
        subNuevoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoVehiculoActionPerformed(evt);
            }
        });
        menuVehiculos.add(subNuevoVehiculo);

        subModificarVehiculo.setText("Modificar Vehiculo");
        subModificarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarVehiculoActionPerformed(evt);
            }
        });
        menuVehiculos.add(subModificarVehiculo);

        subBuscarVehiculo.setText("Consultar Vehículo");
        subBuscarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarVehiculoActionPerformed(evt);
            }
        });
        menuVehiculos.add(subBuscarVehiculo);

        subEliminarVehiculo.setText("Eliminar Vehiculo");
        subEliminarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarVehiculoActionPerformed(evt);
            }
        });
        menuVehiculos.add(subEliminarVehiculo);

        menuMarca.setText("Marca");

        subNuevaMarca.setText("Nueva Marca");
        subNuevaMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevaMarcaActionPerformed(evt);
            }
        });
        menuMarca.add(subNuevaMarca);

        subModificarMarca.setText("Modificar Marca");
        subModificarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarMarcaActionPerformed(evt);
            }
        });
        menuMarca.add(subModificarMarca);

        subBuscarMarca.setText("Consultar Marca");
        subBuscarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarMarcaActionPerformed(evt);
            }
        });
        menuMarca.add(subBuscarMarca);

        subEliminarMarca.setText("Eliminar Marca");
        subEliminarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarMarcaActionPerformed(evt);
            }
        });
        menuMarca.add(subEliminarMarca);

        menuVehiculos.add(menuMarca);

        menuModelo.setText("Modelo");

        subNuevoModelo.setText("Nuevo Modelo");
        subNuevoModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoModeloActionPerformed(evt);
            }
        });
        menuModelo.add(subNuevoModelo);

        subModificarModelo.setText("Modificar Modelo");
        subModificarModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarModeloActionPerformed(evt);
            }
        });
        menuModelo.add(subModificarModelo);

        subBuscarModelo.setText("Consultar Modelo");
        subBuscarModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarModeloActionPerformed(evt);
            }
        });
        menuModelo.add(subBuscarModelo);

        subEliminarModelo.setText("Eliminar Modelo");
        subEliminarModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarModeloActionPerformed(evt);
            }
        });
        menuModelo.add(subEliminarModelo);

        menuVehiculos.add(menuModelo);

        menuTipoVehiculo.setText("Tipo Vehículo");

        subNuevoTipoVehiculo.setText("Nuevo Tipo Vehículo");
        subNuevoTipoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoTipoVehiculoActionPerformed(evt);
            }
        });
        menuTipoVehiculo.add(subNuevoTipoVehiculo);

        subModificarTipoVehiculo.setText("Modificar Tipo Vehículo");
        subModificarTipoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarTipoVehiculoActionPerformed(evt);
            }
        });
        menuTipoVehiculo.add(subModificarTipoVehiculo);

        subBuscarTipoVehiculo.setText("Consultar Tipo Vehiculo");
        subBuscarTipoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarTipoVehiculoActionPerformed(evt);
            }
        });
        menuTipoVehiculo.add(subBuscarTipoVehiculo);

        subEliminarTipoVehiculo.setText("Eliminar Tipo Vehiculo");
        subEliminarTipoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarTipoVehiculoActionPerformed(evt);
            }
        });
        menuTipoVehiculo.add(subEliminarTipoVehiculo);

        menuVehiculos.add(menuTipoVehiculo);

        menuMotor.setText("Tipo Motor");

        subNuevoMotor.setText("Nuevo Motor");
        subNuevoMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoMotorActionPerformed(evt);
            }
        });
        menuMotor.add(subNuevoMotor);

        subModificarMotor.setText("Modificar Motor");
        subModificarMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarMotorActionPerformed(evt);
            }
        });
        menuMotor.add(subModificarMotor);

        subBuscarMotor.setText("Consultar motor");
        subBuscarMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarMotorActionPerformed(evt);
            }
        });
        menuMotor.add(subBuscarMotor);

        subEliminarMotor.setText("Eliminar Motor");
        subEliminarMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subEliminarMotorActionPerformed(evt);
            }
        });
        menuMotor.add(subEliminarMotor);

        menuVehiculos.add(menuMotor);

        menuCombustible.setText("Tipo Combustible");

        subNuevoCombustible.setText("Nuevo Combustible");
        subNuevoCombustible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subNuevoCombustibleActionPerformed(evt);
            }
        });
        menuCombustible.add(subNuevoCombustible);

        subModificarCombustible.setText("Modificar Combustible");
        subModificarCombustible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subModificarCombustibleActionPerformed(evt);
            }
        });
        menuCombustible.add(subModificarCombustible);

        subBuscarCombustible.setText("Consultar Combustible");
        subBuscarCombustible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBuscarCombustibleActionPerformed(evt);
            }
        });
        menuCombustible.add(subBuscarCombustible);

        subEliminarCombustible.setText("Eliminar Combustible");
        menuCombustible.add(subEliminarCombustible);

        menuVehiculos.add(menuCombustible);

        jMenuBar1.add(menuVehiculos);

        jMenu22.setText("Repuestos");

        jMenuItem18.setText("Ingresar Repuesto");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu22.add(jMenuItem18);

        jMenuItem2.setText("Cambiar Repuesto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu22.add(jMenuItem2);

        jMenuBar1.add(jMenu22);

        menuInformes.setText("Informes");

        subInfTrabajadores.setText("Trabajadores");
        subInfTrabajadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subInfTrabajadoresActionPerformed(evt);
            }
        });
        menuInformes.add(subInfTrabajadores);

        subInfInventario.setText("Inventario");
        subInfInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subInfInventarioActionPerformed(evt);
            }
        });
        menuInformes.add(subInfInventario);

        subInfClientes.setText("Clientes");
        subInfClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subInfClientesActionPerformed(evt);
            }
        });
        menuInformes.add(subInfClientes);

        subInfVehiculos.setText("Vehículos");
        subInfVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subInfVehiculosActionPerformed(evt);
            }
        });
        menuInformes.add(subInfVehiculos);

        jMenuBar1.add(menuInformes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LBL_trabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_cargo, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void subNuevoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoItemActionPerformed
        // TODO add your handling code here:
        Ingresar_item v1 = new Ingresar_item();
        v1.setVisible(true);
    }//GEN-LAST:event_subNuevoItemActionPerformed

    private void subBuscarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarItemActionPerformed
        // TODO add your handling code here:
        Consultar_item v2 = new Consultar_item();
        v2.setVisible(true);
    }//GEN-LAST:event_subBuscarItemActionPerformed

    private void subEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarItemActionPerformed
        // TODO add your handling code here:
        Eliminar_item v3 = new Eliminar_item();
        v3.setVisible(true);
    }//GEN-LAST:event_subEliminarItemActionPerformed

    private void subNuevoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoVehiculoActionPerformed
        Ingresar_vehiculo c = new Ingresar_vehiculo();
        c.setVisible(true);
    }//GEN-LAST:event_subNuevoVehiculoActionPerformed

    private void subCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subCerrarSesionActionPerformed
        Ingreso_sistema v = new Ingreso_sistema();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_subCerrarSesionActionPerformed

    private void subNuevoTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoTrabajadorActionPerformed
        Ingresar_trabajador r = new Ingresar_trabajador();
        r.setVisible(true);
    }//GEN-LAST:event_subNuevoTrabajadorActionPerformed

    private void subEliminarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarTrabajadorActionPerformed
        Eliminar_trabajador z = new Eliminar_trabajador();
        z.setVisible(true);
    }//GEN-LAST:event_subEliminarTrabajadorActionPerformed

    private void subEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarClienteActionPerformed
        Eliminar_cliente y = new Eliminar_cliente();
        y.setVisible(true);
    }//GEN-LAST:event_subEliminarClienteActionPerformed

    private void subEliminarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarVehiculoActionPerformed
        Eliminar_vehiculo x = new Eliminar_vehiculo();
        x.setVisible(true);
    }//GEN-LAST:event_subEliminarVehiculoActionPerformed

    private void subNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoClienteActionPerformed
        Ingresar_cliente m = new Ingresar_cliente();
        m.setVisible(true);
    }//GEN-LAST:event_subNuevoClienteActionPerformed

    private void subNuevoCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoCargoActionPerformed
        Ingresar_cargo s = new Ingresar_cargo();
        s.setVisible(true);
    }//GEN-LAST:event_subNuevoCargoActionPerformed

    private void subNuevaCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevaCiudadActionPerformed
        Ingresar_ciudad c = new Ingresar_ciudad();
        c.setVisible(true);
    }//GEN-LAST:event_subNuevaCiudadActionPerformed

    private void subModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarClienteActionPerformed
        Modificar_cliente rr = new Modificar_cliente();
        rr.setVisible(true);
    }//GEN-LAST:event_subModificarClienteActionPerformed

    private void subModificarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarItemActionPerformed
        Modificar_item2 vv = new Modificar_item2();
        vv.setVisible(true);
    }//GEN-LAST:event_subModificarItemActionPerformed

    private void subModificarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarTrabajadorActionPerformed
        Modificar_trabajador zz = new Modificar_trabajador();
        zz.setVisible(true);
    }//GEN-LAST:event_subModificarTrabajadorActionPerformed

    private void subModificarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarVehiculoActionPerformed
        Modificar_vehiculo ww = new Modificar_vehiculo();
        ww.setVisible(true);
    }//GEN-LAST:event_subModificarVehiculoActionPerformed

    private void subAjustarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subAjustarItemActionPerformed
        Ajustar_item xx = new Ajustar_item(mod);
        xx.setVisible(true);
    }//GEN-LAST:event_subAjustarItemActionPerformed

    private void subPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subPresupuestoActionPerformed
        Presupuestar_trabajo yyz = new Presupuestar_trabajo();
        yyz.setVisible(true);
    }//GEN-LAST:event_subPresupuestoActionPerformed

    private void subOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subOTActionPerformed
        Orden_trabajo yes = new Orden_trabajo();
        yes.setVisible(true);
    }//GEN-LAST:event_subOTActionPerformed

    private void subModificarOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarOTActionPerformed
        Modificar_OT a = new Modificar_OT();
        a.setVisible(true);
    }//GEN-LAST:event_subModificarOTActionPerformed

    private void subFacturarOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subFacturarOTActionPerformed
    }//GEN-LAST:event_subFacturarOTActionPerformed

    private void subSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_subSalirActionPerformed

    private void subBuscarTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarTrabajadorActionPerformed
        Consultar_trabajador aa = new Consultar_trabajador();
        aa.setVisible(true);
    }//GEN-LAST:event_subBuscarTrabajadorActionPerformed

    private void subModificarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarCargoActionPerformed
        Modificar_cargo b = new Modificar_cargo();
        b.setVisible(true);
    }//GEN-LAST:event_subModificarCargoActionPerformed

    private void subBuscarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarCargoActionPerformed
        Consultar_cargo bb = new Consultar_cargo();
        bb.setVisible(true);
    }//GEN-LAST:event_subBuscarCargoActionPerformed

    private void subEliminarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarCargoActionPerformed
        Eliminar_cargo a = new Eliminar_cargo();
        a.setVisible(true);
    }//GEN-LAST:event_subEliminarCargoActionPerformed

    private void subModificarCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarCiudadActionPerformed
        Modificar_ciudad cc = new Modificar_ciudad();
        cc.setVisible(true);
    }//GEN-LAST:event_subModificarCiudadActionPerformed

    private void subBuscarCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarCiudadActionPerformed
        Consultar_ciudad dd = new Consultar_ciudad();
        dd.setVisible(true);
    }//GEN-LAST:event_subBuscarCiudadActionPerformed

    private void subEliminarCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarCiudadActionPerformed
        Eliminar_ciudad d = new Eliminar_ciudad();
        d.setVisible(true);
    }//GEN-LAST:event_subEliminarCiudadActionPerformed

    private void subNuevaMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevaMedidaActionPerformed
        Ingresar_medida e = new Ingresar_medida();
        e.setVisible(true);
    }//GEN-LAST:event_subNuevaMedidaActionPerformed

    private void subModificarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarMedidaActionPerformed
        Modificar_medida ee = new Modificar_medida();
        ee.setVisible(true);
    }//GEN-LAST:event_subModificarMedidaActionPerformed

    private void subBuscarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarMedidaActionPerformed
        Consultar_medida f = new Consultar_medida();
        f.setVisible(true);
    }//GEN-LAST:event_subBuscarMedidaActionPerformed

    private void subEliminarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarMedidaActionPerformed
        Eliminar_medida ff = new Eliminar_medida();
        ff.setVisible(true);
    }//GEN-LAST:event_subEliminarMedidaActionPerformed

    private void subNuevaFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevaFamiliaActionPerformed
        Ingresar_familia g = new Ingresar_familia();
        g.setVisible(true);
    }//GEN-LAST:event_subNuevaFamiliaActionPerformed

    private void subModificarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarFamiliaActionPerformed
        Modificar_familia gg = new Modificar_familia();
        gg.setVisible(true);
    }//GEN-LAST:event_subModificarFamiliaActionPerformed

    private void subBuscarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarFamiliaActionPerformed
        Consultar_familia h = new Consultar_familia();
        h.setVisible(true);
    }//GEN-LAST:event_subBuscarFamiliaActionPerformed

    private void subEliminarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarFamiliaActionPerformed
        Eliminar_familia hh = new Eliminar_familia();
        hh.setVisible(true);
    }//GEN-LAST:event_subEliminarFamiliaActionPerformed

    private void subBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarClienteActionPerformed
        Consultar_cliente i = new Consultar_cliente();
        i.setVisible(true);
    }//GEN-LAST:event_subBuscarClienteActionPerformed

    private void subNuevoServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoServicioActionPerformed
        Ingresar_servicio ii = new Ingresar_servicio();
        ii.setVisible(true);
    }//GEN-LAST:event_subNuevoServicioActionPerformed

    private void subNuevaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevaCategoriaActionPerformed
        Ingresar_categoria j = new Ingresar_categoria();
        j.setVisible(true);
    }//GEN-LAST:event_subNuevaCategoriaActionPerformed

    private void subModificarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarServicioActionPerformed
        Modificar_servicio jj = new Modificar_servicio();
        jj.setVisible(true);
    }//GEN-LAST:event_subModificarServicioActionPerformed

    private void subBuscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarServicioActionPerformed
        Consultar_servicio k = new Consultar_servicio();
        k.setVisible(true);
    }//GEN-LAST:event_subBuscarServicioActionPerformed

    private void subEliminarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarServicioActionPerformed
        Eliminar_servicio kk = new Eliminar_servicio();
        kk.setVisible(true);
    }//GEN-LAST:event_subEliminarServicioActionPerformed

    private void subModificarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarCategoriaActionPerformed
        Modificar_categoria l = new Modificar_categoria();
        l.setVisible(true);
    }//GEN-LAST:event_subModificarCategoriaActionPerformed

    private void subBuscarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarCategoriaActionPerformed
        Consultar_categoria ll = new Consultar_categoria();
        ll.setVisible(true);
    }//GEN-LAST:event_subBuscarCategoriaActionPerformed

    private void subEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarCategoriaActionPerformed
        Eliminar_categoria m = new Eliminar_categoria();
        m.setVisible(true);
    }//GEN-LAST:event_subEliminarCategoriaActionPerformed

    private void subBuscarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarVehiculoActionPerformed
        Consultar_vehiculo mm = new Consultar_vehiculo();
        mm.setVisible(true);
    }//GEN-LAST:event_subBuscarVehiculoActionPerformed

    private void subNuevaMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevaMarcaActionPerformed
        Ingresar_marca o = new Ingresar_marca();
        o.setVisible(true);
    }//GEN-LAST:event_subNuevaMarcaActionPerformed

    private void subNuevoCombustibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoCombustibleActionPerformed
        Ingresar_combustible oo = new Ingresar_combustible();
        oo.setVisible(true);
    }//GEN-LAST:event_subNuevoCombustibleActionPerformed

    private void subNuevoMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoMotorActionPerformed
        Ingresar_motor p = new Ingresar_motor();
        p.setVisible(true);
    }//GEN-LAST:event_subNuevoMotorActionPerformed

    private void subNuevoTipoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoTipoVehiculoActionPerformed
        Ingresar_tipovehiculo pp = new Ingresar_tipovehiculo();
        pp.setVisible(true);
    }//GEN-LAST:event_subNuevoTipoVehiculoActionPerformed

    private void subNuevoModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subNuevoModeloActionPerformed
        Ingresar_modelo q = new Ingresar_modelo();
        q.setVisible(true);
    }//GEN-LAST:event_subNuevoModeloActionPerformed

    private void subModificarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarMarcaActionPerformed
        Modificar_marca qq = new Modificar_marca();
        qq.setVisible(true);
    }//GEN-LAST:event_subModificarMarcaActionPerformed

    private void subModificarCombustibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarCombustibleActionPerformed
        Modificar_combustible r = new Modificar_combustible();
        r.setVisible(true);
    }//GEN-LAST:event_subModificarCombustibleActionPerformed

    private void subModificarTipoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarTipoVehiculoActionPerformed
        Modificar_tipovehiculo rr = new Modificar_tipovehiculo();
        rr.setVisible(true);
    }//GEN-LAST:event_subModificarTipoVehiculoActionPerformed

    private void subModificarMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarMotorActionPerformed
        Modificar_motor s = new Modificar_motor();
        s.setVisible(true);
    }//GEN-LAST:event_subModificarMotorActionPerformed

    private void subModificarModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subModificarModeloActionPerformed
        Modificar_modelo ss = new Modificar_modelo();
        ss.setVisible(true);
    }//GEN-LAST:event_subModificarModeloActionPerformed

    private void subBuscarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarMarcaActionPerformed
        Consultar_marca t = new Consultar_marca();
        t.setVisible(true);
    }//GEN-LAST:event_subBuscarMarcaActionPerformed

    private void subBuscarTipoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarTipoVehiculoActionPerformed
        Consultar_tipovehiculo tt = new Consultar_tipovehiculo();
        tt.setVisible(true);
    }//GEN-LAST:event_subBuscarTipoVehiculoActionPerformed

    private void subBuscarMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarMotorActionPerformed
        Consultar_motor u = new Consultar_motor();
        u.setVisible(true);
    }//GEN-LAST:event_subBuscarMotorActionPerformed

    private void subBuscarCombustibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarCombustibleActionPerformed
        Consultar_combustible uu = new Consultar_combustible();
        uu.setVisible(true);
    }//GEN-LAST:event_subBuscarCombustibleActionPerformed

    private void subBuscarModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBuscarModeloActionPerformed
        Consultar_modelo v = new Consultar_modelo();
        v.setVisible(true);
    }//GEN-LAST:event_subBuscarModeloActionPerformed

    private void subEliminarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarMarcaActionPerformed
        Eliminar_marca vv = new Eliminar_marca();
        vv.setVisible(true);
    }//GEN-LAST:event_subEliminarMarcaActionPerformed

    private void subEliminarModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarModeloActionPerformed
        Eliminar_modelo w = new Eliminar_modelo();
        w.setVisible(true);
    }//GEN-LAST:event_subEliminarModeloActionPerformed

    private void subEliminarTipoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarTipoVehiculoActionPerformed
        Eliminar_tipovehiculo ww = new Eliminar_tipovehiculo();
        ww.setVisible(true);
    }//GEN-LAST:event_subEliminarTipoVehiculoActionPerformed

    private void subEliminarMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subEliminarMotorActionPerformed
        Eliminar_motor ww = new Eliminar_motor();
        ww.setVisible(true);
    }//GEN-LAST:event_subEliminarMotorActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        Ingresar_repuestos x = new Ingresar_repuestos();
        x.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void subInfTrabajadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subInfTrabajadoresActionPerformed
        try {

            JasperReport reporte = null;
            String path = "src\\racadauto\\Reportes\\Trabajadores.jasper";

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, cn);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_subInfTrabajadoresActionPerformed

    private void subInfClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subInfClientesActionPerformed
        try {

            JasperReport reporte = null;
            String path = "src\\racadauto\\Reportes\\Clientes.jasper";

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, cn);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_subInfClientesActionPerformed

    private void subInfVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subInfVehiculosActionPerformed
        try {

            JasperReport reporte = null;
            String path = "src\\racadauto\\Reportes\\Vehiculos.jasper";

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, cn);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_subInfVehiculosActionPerformed

    private void subInfInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subInfInventarioActionPerformed
        try {

            JasperReport reporte = null;
            String path = "src\\racadauto\\Reportes\\Inventario.jasper";

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, cn);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_subInfInventarioActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Cambiar_repuestos pf = new Cambiar_repuestos();
        pf.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBL_cargo;
    private javax.swing.JLabel LBL_trabajador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenu menuCargos;
    private javax.swing.JMenu menuCategoria;
    private javax.swing.JMenu menuCiudad;
    private javax.swing.JMenu menuClientes;
    private javax.swing.JMenu menuCombustible;
    private javax.swing.JMenu menuFamilia;
    private javax.swing.JMenu menuInformes;
    private javax.swing.JMenu menuInventario;
    private javax.swing.JMenu menuLogin;
    private javax.swing.JMenu menuMarca;
    private javax.swing.JMenu menuMedida;
    private javax.swing.JMenu menuModelo;
    private javax.swing.JMenu menuMotor;
    private javax.swing.JMenu menuServicios;
    private javax.swing.JMenu menuTabajos;
    private javax.swing.JMenu menuTipoVehiculo;
    private javax.swing.JMenu menuTrabajadores;
    private javax.swing.JMenu menuVehiculos;
    private javax.swing.JMenuItem subAjustarItem;
    private javax.swing.JMenuItem subBuscarCargo;
    private javax.swing.JMenuItem subBuscarCategoria;
    private javax.swing.JMenuItem subBuscarCiudad;
    private javax.swing.JMenuItem subBuscarCliente;
    private javax.swing.JMenuItem subBuscarCombustible;
    private javax.swing.JMenuItem subBuscarFamilia;
    private javax.swing.JMenuItem subBuscarItem;
    private javax.swing.JMenuItem subBuscarMarca;
    private javax.swing.JMenuItem subBuscarMedida;
    private javax.swing.JMenuItem subBuscarModelo;
    private javax.swing.JMenuItem subBuscarMotor;
    private javax.swing.JMenuItem subBuscarServicio;
    private javax.swing.JMenuItem subBuscarTipoVehiculo;
    private javax.swing.JMenuItem subBuscarTrabajador;
    private javax.swing.JMenuItem subBuscarVehiculo;
    private javax.swing.JMenuItem subCerrarSesion;
    private javax.swing.JMenuItem subEliminarCargo;
    private javax.swing.JMenuItem subEliminarCategoria;
    private javax.swing.JMenuItem subEliminarCiudad;
    private javax.swing.JMenuItem subEliminarCliente;
    private javax.swing.JMenuItem subEliminarCombustible;
    private javax.swing.JMenuItem subEliminarFamilia;
    private javax.swing.JMenuItem subEliminarItem;
    private javax.swing.JMenuItem subEliminarMarca;
    private javax.swing.JMenuItem subEliminarMedida;
    private javax.swing.JMenuItem subEliminarModelo;
    private javax.swing.JMenuItem subEliminarMotor;
    private javax.swing.JMenuItem subEliminarServicio;
    private javax.swing.JMenuItem subEliminarTipoVehiculo;
    private javax.swing.JMenuItem subEliminarTrabajador;
    private javax.swing.JMenuItem subEliminarVehiculo;
    private javax.swing.JMenuItem subFacturarOT;
    private javax.swing.JMenuItem subInfClientes;
    private javax.swing.JMenuItem subInfInventario;
    private javax.swing.JMenuItem subInfTrabajadores;
    private javax.swing.JMenuItem subInfVehiculos;
    private javax.swing.JMenuItem subModificarCargo;
    private javax.swing.JMenuItem subModificarCategoria;
    private javax.swing.JMenuItem subModificarCiudad;
    private javax.swing.JMenuItem subModificarCliente;
    private javax.swing.JMenuItem subModificarCombustible;
    private javax.swing.JMenuItem subModificarFamilia;
    private javax.swing.JMenuItem subModificarItem;
    private javax.swing.JMenuItem subModificarMarca;
    private javax.swing.JMenuItem subModificarMedida;
    private javax.swing.JMenuItem subModificarModelo;
    private javax.swing.JMenuItem subModificarMotor;
    private javax.swing.JMenuItem subModificarOT;
    private javax.swing.JMenuItem subModificarServicio;
    private javax.swing.JMenuItem subModificarTipoVehiculo;
    private javax.swing.JMenuItem subModificarTrabajador;
    private javax.swing.JMenuItem subModificarVehiculo;
    private javax.swing.JMenuItem subNuevaCategoria;
    private javax.swing.JMenuItem subNuevaCiudad;
    private javax.swing.JMenuItem subNuevaFamilia;
    private javax.swing.JMenuItem subNuevaMarca;
    private javax.swing.JMenuItem subNuevaMedida;
    private javax.swing.JMenuItem subNuevoCargo;
    private javax.swing.JMenuItem subNuevoCliente;
    private javax.swing.JMenuItem subNuevoCombustible;
    private javax.swing.JMenuItem subNuevoItem;
    private javax.swing.JMenuItem subNuevoModelo;
    private javax.swing.JMenuItem subNuevoMotor;
    private javax.swing.JMenuItem subNuevoServicio;
    private javax.swing.JMenuItem subNuevoTipoVehiculo;
    private javax.swing.JMenuItem subNuevoTrabajador;
    private javax.swing.JMenuItem subNuevoVehiculo;
    private javax.swing.JMenuItem subOT;
    private javax.swing.JMenuItem subPresupuesto;
    private javax.swing.JMenuItem subSalir;
    // End of variables declaration//GEN-END:variables
}
