package racadauto;


public class Principal extends javax.swing.JFrame {

    
    public Principal() {
        initComponents();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        log_ingre = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        init_work = new javax.swing.JMenu();
        start_work = new javax.swing.JMenuItem();
        update_work = new javax.swing.JMenuItem();
        mod_work = new javax.swing.JMenuItem();
        end_work = new javax.swing.JMenuItem();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu16 = new javax.swing.JMenu();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        new_emp = new javax.swing.JMenuItem();
        mod_emp = new javax.swing.JMenuItem();
        search_emp = new javax.swing.JMenuItem();
        elim_emp = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        new_cargo = new javax.swing.JMenuItem();
        mod_cargo = new javax.swing.JMenuItem();
        search_cargo = new javax.swing.JMenuItem();
        elim_cargo = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        newitem = new javax.swing.JMenuItem();
        mod_item = new javax.swing.JMenuItem();
        pedidoingreso = new javax.swing.JMenuItem();
        deletitem = new javax.swing.JMenuItem();
        adj_item = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        new_client = new javax.swing.JMenuItem();
        mod_client = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        elim_client = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        new_city = new javax.swing.JMenuItem();
        modi_city = new javax.swing.JMenuItem();
        consu_city = new javax.swing.JMenuItem();
        elim_city = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        new_car = new javax.swing.JMenuItem();
        mod_car = new javax.swing.JMenuItem();
        elim_car = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RACAD AUTOMOTRIZ");
        setMinimumSize(new java.awt.Dimension(500, 400));
        setResizable(false);

        jMenu2.setText("Login");

        log_ingre.setText("Ingresar");
        log_ingre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_ingreActionPerformed(evt);
            }
        });
        jMenu2.add(log_ingre);

        jMenuItem21.setText("Salir");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem21);

        jMenuBar1.add(jMenu2);

        init_work.setText("Trabajos");

        start_work.setText("Presupuestar Trabajo");
        start_work.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_workActionPerformed(evt);
            }
        });
        init_work.add(start_work);

        update_work.setText("Orden de Trabajo");
        update_work.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_workActionPerformed(evt);
            }
        });
        init_work.add(update_work);

        mod_work.setText("Modificar Trabajo");
        mod_work.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mod_workActionPerformed(evt);
            }
        });
        init_work.add(mod_work);

        end_work.setText("Facturar Trabajo");
        end_work.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                end_workActionPerformed(evt);
            }
        });
        init_work.add(end_work);

        jMenu15.setText("Servicios");

        jMenuItem23.setText("Nuevo Servicio");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem23);

        jMenuItem24.setText("Modificar Servicio");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem24);

        jMenuItem26.setText("Consultar Servicio");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem26);

        jMenuItem27.setText("Eliminar Servicio");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu15.add(jMenuItem27);

        init_work.add(jMenu15);

        jMenu16.setText("Categoría");

        jMenuItem33.setText("Nueva Categoría");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem33);

        jMenuItem34.setText("Modificar Categoría");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem34);

        jMenuItem35.setText("Consultar Categoría");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem35);

        jMenuItem36.setText("Eliminar Categoría");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu16.add(jMenuItem36);

        init_work.add(jMenu16);

        jMenuBar1.add(init_work);

        jMenu3.setText("Empleados");

        new_emp.setText("Nuevo Empleado");
        new_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_empActionPerformed(evt);
            }
        });
        jMenu3.add(new_emp);

        mod_emp.setText("Modificar Empleado");
        mod_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mod_empActionPerformed(evt);
            }
        });
        jMenu3.add(mod_emp);

        search_emp.setText("Consultar Empleado");
        search_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_empActionPerformed(evt);
            }
        });
        jMenu3.add(search_emp);

        elim_emp.setText("Eliminar Empleado");
        elim_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elim_empActionPerformed(evt);
            }
        });
        jMenu3.add(elim_emp);

        jMenu1.setText("Cargos");

        new_cargo.setText("Nuevo Cargo");
        new_cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_cargoActionPerformed(evt);
            }
        });
        jMenu1.add(new_cargo);

        mod_cargo.setText("Modificar Cargo");
        mod_cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mod_cargoActionPerformed(evt);
            }
        });
        jMenu1.add(mod_cargo);

        search_cargo.setText("Consultar Cargo");
        search_cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_cargoActionPerformed(evt);
            }
        });
        jMenu1.add(search_cargo);

        elim_cargo.setText("Eliminar Cargo");
        elim_cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elim_cargoActionPerformed(evt);
            }
        });
        jMenu1.add(elim_cargo);

        jMenu3.add(jMenu1);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Inventario");

        newitem.setText("Nuevo Item");
        newitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newitemActionPerformed(evt);
            }
        });
        jMenu4.add(newitem);

        mod_item.setText("Modificar Item");
        mod_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mod_itemActionPerformed(evt);
            }
        });
        jMenu4.add(mod_item);

        pedidoingreso.setText("Consultar Item");
        pedidoingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedidoingresoActionPerformed(evt);
            }
        });
        jMenu4.add(pedidoingreso);

        deletitem.setText("Eliminar Item");
        deletitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletitemActionPerformed(evt);
            }
        });
        jMenu4.add(deletitem);

        adj_item.setText("Ajustar Item");
        adj_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adj_itemActionPerformed(evt);
            }
        });
        jMenu4.add(adj_item);

        jMenu12.setText("Unidad de Medida");

        jMenuItem6.setText("Nueva Medida");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem6);

        jMenuItem7.setText("Modificar Medida");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem7);

        jMenuItem8.setText("Consultar Medida");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem8);

        jMenuItem10.setText("Eliminar Medida");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem10);

        jMenu4.add(jMenu12);

        jMenu13.setText("Familia");

        jMenuItem11.setText("Nueva Familia");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem11);

        jMenuItem13.setText("Modificar Familia");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem13);

        jMenuItem15.setText("Consultar Familia");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem15);

        jMenuItem17.setText("Eliminar Familia");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem17);

        jMenu4.add(jMenu13);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Clientes");

        new_client.setText("Nuevo Cliente");
        new_client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_clientActionPerformed(evt);
            }
        });
        jMenu5.add(new_client);

        mod_client.setText("Modificar Cliente");
        mod_client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mod_clientActionPerformed(evt);
            }
        });
        jMenu5.add(mod_client);

        jMenuItem20.setText("Consultar Cliente");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem20);

        elim_client.setText("Eliminar Cliente");
        elim_client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elim_clientActionPerformed(evt);
            }
        });
        jMenu5.add(elim_client);

        jMenu14.setText("Ciudad");

        new_city.setText("Nueva Ciudad");
        new_city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_cityActionPerformed(evt);
            }
        });
        jMenu14.add(new_city);

        modi_city.setText("Modificar Ciudad");
        modi_city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modi_cityActionPerformed(evt);
            }
        });
        jMenu14.add(modi_city);

        consu_city.setText("Consular Ciudad");
        consu_city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consu_cityActionPerformed(evt);
            }
        });
        jMenu14.add(consu_city);

        elim_city.setText("Eliminar Ciudad");
        elim_city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elim_cityActionPerformed(evt);
            }
        });
        jMenu14.add(elim_city);

        jMenu5.add(jMenu14);

        jMenuBar1.add(jMenu5);

        jMenu8.setText("Vehiculos");

        new_car.setText("Ingresar Vehiculo");
        new_car.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_carActionPerformed(evt);
            }
        });
        jMenu8.add(new_car);

        mod_car.setText("Modificar Vehiculo");
        mod_car.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mod_carActionPerformed(evt);
            }
        });
        jMenu8.add(mod_car);

        elim_car.setText("Eliminar Vehiculo");
        elim_car.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elim_carActionPerformed(evt);
            }
        });
        jMenu8.add(elim_car);

        jMenuItem18.setText("Modificar Otros");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem18);

        jMenuBar1.add(jMenu8);

        jMenu11.setText("Informes");

        jMenuItem28.setText("Trabajos");
        jMenu11.add(jMenuItem28);

        jMenuItem29.setText("Empleados");
        jMenu11.add(jMenuItem29);

        jMenuItem30.setText("Inventario");
        jMenu11.add(jMenuItem30);

        jMenuItem31.setText("Clientes");
        jMenu11.add(jMenuItem31);

        jMenuItem32.setText("Vehículos");
        jMenu11.add(jMenuItem32);

        jMenuItem2.setText("Ingresos y Egresos");
        jMenu11.add(jMenuItem2);

        jMenuBar1.add(jMenu11);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void newitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newitemActionPerformed
        // TODO add your handling code here:
        Ingresar_item v1=new Ingresar_item();
        v1.setVisible(true); 
    }//GEN-LAST:event_newitemActionPerformed

    private void pedidoingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedidoingresoActionPerformed
        // TODO add your handling code here:
        Consultar_item v2=new Consultar_item();
        v2.setVisible(true); 
    }//GEN-LAST:event_pedidoingresoActionPerformed

    private void deletitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletitemActionPerformed
        // TODO add your handling code here:
        Eliminar_item v3=new Eliminar_item();
        v3.setVisible(true); 
    }//GEN-LAST:event_deletitemActionPerformed

    private void new_carActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_carActionPerformed
        Ingresar_vehiculo c = new Ingresar_vehiculo();
        c.setVisible(true); 
    }//GEN-LAST:event_new_carActionPerformed

    private void log_ingreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_ingreActionPerformed
        Ingreso_sistema v=new Ingreso_sistema();
        v.setVisible(true); 
    }//GEN-LAST:event_log_ingreActionPerformed

    private void new_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_empActionPerformed
        Ingresar_empleado r=new Ingresar_empleado();
        r.setVisible(true);
    }//GEN-LAST:event_new_empActionPerformed

    private void elim_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elim_empActionPerformed
        Eliminar_empleado z=new Eliminar_empleado();
        z.setVisible(true); 
    }//GEN-LAST:event_elim_empActionPerformed

    private void elim_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elim_clientActionPerformed
        Eliminar_cliente y = new Eliminar_cliente();
        y.setVisible(true); 
    }//GEN-LAST:event_elim_clientActionPerformed

    private void elim_carActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elim_carActionPerformed
        Eliminar_vehiculo x = new Eliminar_vehiculo();
        x.setVisible(true); 
    }//GEN-LAST:event_elim_carActionPerformed

    private void new_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_clientActionPerformed
        Ingresar_cliente m=new Ingresar_cliente();
        m.setVisible(true);
    }//GEN-LAST:event_new_clientActionPerformed

    private void new_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_cargoActionPerformed
        Ingresar_cargo s=new Ingresar_cargo();
        s.setVisible(true);
    }//GEN-LAST:event_new_cargoActionPerformed

    private void new_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_cityActionPerformed
        Ingresar_ciudad c = new Ingresar_ciudad();
        c.setVisible(true);
    }//GEN-LAST:event_new_cityActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        Otros_vehiculo q = new Otros_vehiculo();
        q.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void mod_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mod_clientActionPerformed
        Modificar_cliente rr=new Modificar_cliente();
        rr.setVisible(true);
    }//GEN-LAST:event_mod_clientActionPerformed

    private void mod_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mod_itemActionPerformed
        Modificar_item vv=new Modificar_item();
        vv.setVisible(true);    
    }//GEN-LAST:event_mod_itemActionPerformed

    private void mod_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mod_empActionPerformed
        Modificar_empleado zz=new Modificar_empleado();
        zz.setVisible(true);
    }//GEN-LAST:event_mod_empActionPerformed

    private void mod_carActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mod_carActionPerformed
        Modificar_vehiculo ww = new Modificar_vehiculo();
        ww.setVisible(true);
    }//GEN-LAST:event_mod_carActionPerformed

    private void adj_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adj_itemActionPerformed
        Ajustar_item xx=new Ajustar_item();
        xx.setVisible(true);
    }//GEN-LAST:event_adj_itemActionPerformed

    private void start_workActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_workActionPerformed
        r2_1 yyz=new r2_1();
        yyz.setVisible(true);
    }//GEN-LAST:event_start_workActionPerformed

    private void update_workActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_workActionPerformed
        r2_2 yes=new r2_2();
        yes.setVisible(true);
    }//GEN-LAST:event_update_workActionPerformed

    private void mod_workActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mod_workActionPerformed
        r2_3 kek=new r2_3();
        kek.setVisible(true);
    }//GEN-LAST:event_mod_workActionPerformed

    private void end_workActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_end_workActionPerformed
        r2_4 wii=new r2_4();
        wii.setVisible(true);
    }//GEN-LAST:event_end_workActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void search_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_empActionPerformed
        Consultar_empleado aa = new Consultar_empleado();
        aa.setVisible(true);
    }//GEN-LAST:event_search_empActionPerformed

    private void mod_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mod_cargoActionPerformed
        Modificar_cargo b = new Modificar_cargo();
        b.setVisible(true);
    }//GEN-LAST:event_mod_cargoActionPerformed

    private void search_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_cargoActionPerformed
        Consultar_cargo bb = new Consultar_cargo();
        bb.setVisible(true);
    }//GEN-LAST:event_search_cargoActionPerformed

    private void elim_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elim_cargoActionPerformed
        Eliminar_cargo a = new Eliminar_cargo();
        a.setVisible(true);
    }//GEN-LAST:event_elim_cargoActionPerformed

    private void modi_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modi_cityActionPerformed
        Modificar_ciudad cc = new Modificar_ciudad();
        cc.setVisible(true);
    }//GEN-LAST:event_modi_cityActionPerformed

    private void consu_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consu_cityActionPerformed
        Consultar_ciudad dd = new Consultar_ciudad();
        dd.setVisible(true);
    }//GEN-LAST:event_consu_cityActionPerformed

    private void elim_cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elim_cityActionPerformed
        Eliminar_ciudad d = new Eliminar_ciudad();
        d.setVisible(true);
    }//GEN-LAST:event_elim_cityActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Ingresar_medida e = new Ingresar_medida();
        e.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Modificar_medida ee = new Modificar_medida();
        ee.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Consultar_medida f = new Consultar_medida();
        f.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        Eliminar_medida ff = new Eliminar_medida();
        ff.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        Ingresar_familia g = new Ingresar_familia();
        g.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        Modificar_familia gg = new Modificar_familia();
        gg.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        Consultar_familia h = new Consultar_familia();
        h.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        Eliminar_familia hh = new Eliminar_familia();
        hh.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        Consultar_cliente i = new Consultar_cliente();
        i.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        Ingresar_servicio ii = new Ingresar_servicio();
        ii.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        Ingresar_categoria j = new Ingresar_categoria();
        j.setVisible(true);
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        Modificar_servicio jj = new Modificar_servicio();
        jj.setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        Consultar_servicio k = new Consultar_servicio();
        k.setVisible(true);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        Eliminar_servicio kk = new Eliminar_servicio();
        kk.setVisible(true);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        Modificar_categoria l = new Modificar_categoria();
        l.setVisible(true);
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        Consultar_categoria ll = new Consultar_categoria();
        ll.setVisible(true);
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        Eliminar_categoria m = new Eliminar_categoria();
        m.setVisible(true);
    }//GEN-LAST:event_jMenuItem36ActionPerformed

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
    private javax.swing.JMenuItem adj_item;
    private javax.swing.JMenuItem consu_city;
    private javax.swing.JMenuItem deletitem;
    private javax.swing.JMenuItem elim_car;
    private javax.swing.JMenuItem elim_cargo;
    private javax.swing.JMenuItem elim_city;
    private javax.swing.JMenuItem elim_client;
    private javax.swing.JMenuItem elim_emp;
    private javax.swing.JMenuItem end_work;
    private javax.swing.JMenu init_work;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem log_ingre;
    private javax.swing.JMenuItem mod_car;
    private javax.swing.JMenuItem mod_cargo;
    private javax.swing.JMenuItem mod_client;
    private javax.swing.JMenuItem mod_emp;
    private javax.swing.JMenuItem mod_item;
    private javax.swing.JMenuItem mod_work;
    private javax.swing.JMenuItem modi_city;
    private javax.swing.JMenuItem new_car;
    private javax.swing.JMenuItem new_cargo;
    private javax.swing.JMenuItem new_city;
    private javax.swing.JMenuItem new_client;
    private javax.swing.JMenuItem new_emp;
    private javax.swing.JMenuItem newitem;
    private javax.swing.JMenuItem pedidoingreso;
    private javax.swing.JMenuItem search_cargo;
    private javax.swing.JMenuItem search_emp;
    private javax.swing.JMenuItem start_work;
    private javax.swing.JMenuItem update_work;
    // End of variables declaration//GEN-END:variables
}
