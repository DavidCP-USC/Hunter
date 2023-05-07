
import DAOs.DAOManager;
import Frames.*;
import Modelos.*;
import java.sql.SQLException;
import Conexion.Conexion;

public class Hunter {
    
    public static void main(String[] args) throws SQLException {
        //Manager
        DAOManager.init("postgres", "psql");
        
        //Pagina de login
        Login paginaLogin = new Login();
        paginaLogin.setLocationRelativeTo(null);
        paginaLogin.setVisible(true);
        loginProcess(paginaLogin);
        
        if (DAOManager.getConn() != null){
            try {
                DAOManager.getConn().close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    // LOGIN PAGE
    private static void loginProcess(Login paginaLogin) throws SQLException{
        while(paginaLogin.accedido() == false){ // Espera a que se pulse el boton de login
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
        try{ // Comprueba si el usuario es un cliente
            Clientes c = Conexion.loginCliente(DAOManager.getConn(), paginaLogin.getUser(), paginaLogin.getPassword());
            if (c != null){ // Si es un cliente, abre la ventana de cliente
                paginaLogin.setVisible(false);
                ventanaPanelCliente(c);
                paginaLogin.setVisible(true);
                paginaLogin.nuevoLogin();
                loginProcess(paginaLogin);
            }else{
                try{ // Comprueba si el usuario es una empresa
                    EmpresasVendedoras e = Conexion.loginEmpresa(DAOManager.getConn(), paginaLogin.getUser(), paginaLogin.getPassword());
                    if(e != null){ // Si es una empresa, abre la ventana de empresa
                        paginaLogin.setVisible(false);
                        ventanaPanelEmpresa(e);
                        paginaLogin.setVisible(true);
                        paginaLogin.nuevoLogin();
                        loginProcess(paginaLogin);
                    }else{ // Si no es ni empresa ni cliente, muestra un mensaje de error
                        paginaLogin.credencialesIncorrectas();
                        loginProcess(paginaLogin);   
                    }
                }catch(SQLException sql){
                    sql.printStackTrace();
                }
            }
        }catch(SQLException sql){
            sql.printStackTrace();
        }
    }
    
    // PANELES PRINCIPALES
    private static void ventanaPanelCliente(Clientes c) throws SQLException{
        if(c.getEsAdmin()){ // Comprueba si el cliente es administrador
            // Si es administrador, abre la ventana de administrador
            PanelAdministradorCliente panelAc = new PanelAdministradorCliente();
            panelAc.setLocationRelativeTo(null);
            panelAc.setVisible(true);
            while(panelAc.isDisplayable()){
                try { // Espera a que se pulse algun boton
                    if(panelAc.openProfile()){ // Abre el perfil del cliente
                        panelAc.resetButtonState();
                        panelAc.setVisible(false);
                        ventanaPerfilClienteAdmin(panelAc, c);
                    }else if(panelAc.addCliente()){ // Abre la ventana de añadir cliente
                        panelAc.resetButtonState();
                        panelAc.setVisible(false);
                        ventanaAnhadirCliente(panelAc);
                    }else if(panelAc.deleteCliente()){ // Abre la ventana de eliminar cliente
                        panelAc.resetButtonState();
                        panelAc.setVisible(false);
                        ventanaEliminarCliente(panelAc);
                    }else if(panelAc.listarClientes()){ // Abre la ventana de listar clientes
                        panelAc.resetButtonState();
                        panelAc.setVisible(false);
                        ventanaListaClientes(panelAc);
                    }else if(panelAc.logOut()){ // Cierra la ventana de administrador
                        panelAc.dispose();
                    }else{
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }  
        }else{ // Si no es administrador, abre la ventana de cliente
            PanelCliente panelC = new PanelCliente();
            panelC.setLocationRelativeTo(null);
            panelC.setVisible(true);
            while(panelC.isDisplayable()){ // Espera a que se pulse algun boton
                try {
                    if(panelC.openCart()){ // Abre la ventana de la cesta de la compra
                        panelC.resetButtonState();
                        panelC.setVisible(false);
                        ventanaCesta(panelC, c);
                    }else if(panelC.makeSearch()){ // Realiza una busqueda de productos
                        panelC.resetButtonState();
                        panelC.displayProductos(DAOManager.modelos().consultarCatalogoDisponible(panelC.productToSearch(), null, null, c.getCesta()));
                    }else if(panelC.openProfile()){ // Abre el perfil del cliente
                        panelC.resetButtonState();
                        panelC.setVisible(false);
                        ventanaPerfilClienteNoAdmin(panelC, c);
                    }else if(panelC.contactarProblemas()){ // Abre la ventana de contactar problemas
                        panelC.resetButtonState();
                        panelC.setVisible(false);
                        ventanaContactarProblemas(panelC, c);
                    }else if(panelC.logOut()){ // Cierra la ventana de cliente
                        panelC.dispose();
                    }else{
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }  
        }
    }
    
    private static void ventanaPanelEmpresa(EmpresasVendedoras e) throws SQLException{
        if(e.getEsAdmin()){ // Comprueba si la empresa es administrador
            PanelAdministradorEmpresa panelAe = new PanelAdministradorEmpresa();
            panelAe.setLocationRelativeTo(null);
            panelAe.setVisible(true);
            while(panelAe.isDisplayable()){ // Espera a que se pulse algun boton
                try {
                    if(panelAe.openProfile()){ // Abre el perfil de la empresa
                        panelAe.resetButtonState();
                        panelAe.setVisible(false);
                        ventanaPerfilEmpresaAdmin(panelAe, e);
                    }else if(panelAe.addEmpresa()){ // Abre la ventana de añadir empresa
                        panelAe.resetButtonState();
                        panelAe.setVisible(false);
                        ventanaAnhadirEmpresa(panelAe);
                    }else if(panelAe.deleteEmpresa()){ // Abre la ventana de eliminar empresa
                        panelAe.resetButtonState();
                        panelAe.setVisible(false);
                        ventanaEliminarEmpresa(panelAe);
                    }else if(panelAe.listarEmpresas()){ // Abre la ventana de listar empresas
                        panelAe.resetButtonState();
                        panelAe.setVisible(false);
                        ventanaListaEmpresas(panelAe);
                    }else if(panelAe.logOut()){ // Cierra la ventana de administrador
                        panelAe.dispose();
                    }else{
                        Thread.sleep(100);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }else{ // Si no es administrador, abre la ventana de empresa normal
            PanelEmpresa panelE = new PanelEmpresa();
            panelE.setLocationRelativeTo(null);
            panelE.setVisible(true);
            while(panelE.isDisplayable()){ // Espera a que se pulse algun boton
                try {
                    if(panelE.addModelo()){ // Abre la ventana de añadir modelo
                        panelE.resetButtonState();
                        panelE.setVisible(false);
                        ventanaAnhadirModelo(panelE, e);
                    }else if(panelE.makeSearch()){ // Realiza una busqueda de modelos
                        panelE.resetButtonState();
                        panelE.displayModelos(DAOManager.empresas().consultarModelos(panelE.modelToSearch(), e));
                    }else if(panelE.openProfile()){ // Abre el perfil de la empresa
                        panelE.resetButtonState();
                        panelE.setVisible(false);
                        ventanaPerfilEmpresaNoAdmin(panelE, e);
                    }else if(panelE.abrirContactarProblemas()){ // Abre la ventana de contactar problemas
                        panelE.resetButtonState();
                        panelE.setVisible(false);
                        ventanaContactarProblemasEmpresa(panelE, e);
                    }else if(panelE.logOut()){ // Cierra la ventana de empresa
                        panelE.dispose();
                    }else{
                        Thread.sleep(100);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    // PERFILES
    private static void ventanaPerfilClienteNoAdmin(PanelCliente panelPrincipal, Clientes c){
        PerfilCliente perfilC = new PerfilCliente(c);
        perfilC.setLocationRelativeTo(null);
        perfilC.setVisible(true);
        while(perfilC.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(perfilC.returnHome()){ // Cierra la ventana de perfil
                    panelPrincipal.setVisible(true);
                    perfilC.dispose();
                }else if(perfilC.saveChanges()){ // Guarda los cambios realizados
                    perfilC.resetSaveButton();
                    try{
                        DAOManager.clientes().modificar(perfilC.getDatos());
                        perfilC.submitChanges();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("ya registrado"))
                            perfilC.repeatedError();
                        else{
                            ex.printStackTrace();
                            perfilC.modifiedUnnownError();
                        }
                    }
                }else if(perfilC.logOut()){ // Cierra la ventana de perfil y la de cliente
                    perfilC.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
    
    private static void ventanaPerfilClienteAdmin(PanelAdministradorCliente panelPrincipal, Clientes c){
        PerfilCliente perfilC = new PerfilCliente(c);
        perfilC.setLocationRelativeTo(null);
        perfilC.setVisible(true);
        while(perfilC.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(perfilC.returnHome()){ // Cierra la ventana de perfil
                    panelPrincipal.setVisible(true);
                    perfilC.dispose();
                }else if(perfilC.saveChanges()){ // Guarda los cambios realizados
                    perfilC.resetSaveButton();
                    try{
                        DAOManager.clientes().modificar(perfilC.getDatos());
                        perfilC.submitChanges();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("ya registrado"))
                            perfilC.repeatedError();
                        else{
                            ex.printStackTrace();
                            perfilC.modifiedUnnownError();
                        }
                    }
                }else if(perfilC.logOut()){ // Cierra la ventana de perfil y la de cliente
                    perfilC.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
    
    private static void ventanaPerfilEmpresaNoAdmin(PanelEmpresa panelPrincipal, EmpresasVendedoras e){
        PerfilEmpresa perfilE = new PerfilEmpresa(e);
        perfilE.setLocationRelativeTo(null);
        perfilE.setVisible(true);
        while(perfilE.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(perfilE.returnHome()){ // Cierra la ventana de perfil
                    panelPrincipal.setVisible(true);
                    perfilE.dispose();
                }else if(perfilE.saveChanges()){ // Guarda los cambios realizados
                    perfilE.resetSaveButton();
                    try{
                        DAOManager.empresas().modificar(perfilE.getDatos());
                        perfilE.submitChanges();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("ya registrado"))
                            perfilE.repeatedError();
                        else{
                            ex.printStackTrace();
                            perfilE.modifiedUnnownError();
                        }
                    }
                }else if(perfilE.logOut()){ // Cierra la ventana de perfil y la de empresa
                    perfilE.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
            ex.printStackTrace();
            }
        }
    }
    
    private static void ventanaPerfilEmpresaAdmin(PanelAdministradorEmpresa panelPrincipal, EmpresasVendedoras e){
        PerfilEmpresa perfilE = new PerfilEmpresa(e);
        perfilE.setLocationRelativeTo(null);
        perfilE.setVisible(true);
        while(perfilE.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(perfilE.returnHome()){ // Cierra la ventana de perfil
                    panelPrincipal.setVisible(true);
                    perfilE.dispose();
                }else if(perfilE.saveChanges()){ // Guarda los cambios realizados
                    perfilE.resetSaveButton();
                    try{
                        DAOManager.empresas().modificar(perfilE.getDatos());
                        perfilE.submitChanges();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("ya registrado"))
                            perfilE.repeatedError();
                        else{
                            ex.printStackTrace();
                            perfilE.modifiedUnnownError();
                        }
                    }
                }else if(perfilE.logOut()){ // Cierra la ventana de perfil y la de empresa
                    perfilE.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
            ex.printStackTrace();
            }
        }
    }
    
    // AÑADIR/ELIMINAR/EDITAR CLIENTES/EMPRESAS
    private static void ventanaAnhadirCliente(PanelAdministradorCliente panelPrincipal){
        AnhadirCliente anhadirC = new AnhadirCliente();
        anhadirC.setLocationRelativeTo(null);
        anhadirC.setVisible(true);
        while(anhadirC.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(anhadirC.returnHome()){ // Cierra la ventana de añadir cliente
                    panelPrincipal.setVisible(true);
                    anhadirC.dispose();
                }else if(anhadirC.create()){ // Crea el cliente
                    anhadirC.resetSaveButton();
                    try{
                        DAOManager.clientes().insertar(anhadirC.getDatos());
                        anhadirC.createdSuccesfull();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("ya registrado"))
                            anhadirC.repeatedError();
                        else{
                            ex.printStackTrace();
                            anhadirC.createdUnnownError();
                        }
                    }
                }else if(anhadirC.logOut()){ // Cierra la ventana de añadir cliente y la de administrador
                    anhadirC.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
    
    private static void ventanaEliminarCliente(PanelAdministradorCliente panelPrincipal){
        EliminarCliente eliminarC = new EliminarCliente();
        eliminarC.setLocationRelativeTo(null);
        eliminarC.setVisible(true);
        while(eliminarC.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(eliminarC.returnHome()){ // Cierra la ventana de eliminar cliente
                    panelPrincipal.setVisible(true);
                    eliminarC.dispose();
                }else if(eliminarC.delete()){ // Elimina el cliente
                    eliminarC.resetSaveButton();
                    try{
                        DAOManager.clientes().eliminarPorCredenciales(eliminarC.getDatos());
                        eliminarC.deletedSuccesfull();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("no registrado"))
                            eliminarC.notRegisteredError();
                        else{
                            ex.printStackTrace();
                            eliminarC.deletedUnnowkError();
                        }
                    }
                }else if(eliminarC.logOut()){ // Cierra la ventana de eliminar cliente y la de administrador
                    eliminarC.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
    
    private static void ventanaAnhadirEmpresa(PanelAdministradorEmpresa panelPrincipal){
        AnhadirEmpresa anhadirE = new AnhadirEmpresa();
        anhadirE.setLocationRelativeTo(null);
        anhadirE.setVisible(true);
        while(anhadirE.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(anhadirE.returnHome()){ // Cierra la ventana de añadir empresa
                    panelPrincipal.setVisible(true);
                    anhadirE.dispose();
                }else if(anhadirE.create()){ // Crea la empresa
                    anhadirE.resetSaveButton();
                    try{
                        DAOManager.empresas().insertar(anhadirE.getDatos());
                        anhadirE.createdSuccesfull();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("ya registrado"))
                            anhadirE.repeatedError();
                        else{
                            ex.printStackTrace();
                            anhadirE.createdUnnownError();
                        }
                    }
                }else if(anhadirE.logOut()){ // Cierra la ventana de añadir empresa y la de administrador
                    anhadirE.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
    
    private static void ventanaEliminarEmpresa(PanelAdministradorEmpresa panelPrincipal){
        EliminarEmpresa eliminarE = new EliminarEmpresa();
        eliminarE.setLocationRelativeTo(null);
        eliminarE.setVisible(true);
        while(eliminarE.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(eliminarE.returnHome()){ // Cierra la ventana de eliminar empresa
                    panelPrincipal.setVisible(true);
                    eliminarE.dispose();
                }else if(eliminarE.delete()){ // Elimina la empresa
                    eliminarE.resetSaveButton();
                    try{
                        DAOManager.empresas().eliminarPorCredenciales(eliminarE.getDatos());
                        eliminarE.deletedSuccesfull();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("no registrado"))
                                eliminarE.notRegisteredError();
                        else{
                            ex.printStackTrace();
                            eliminarE.deletedUnnowkError();
                        }
                    }
                }else if(eliminarE.logOut()){ // Cierra la ventana de eliminar empresa y la de administrador
                    eliminarE.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
    
    private static void ventanaListaClientes(PanelAdministradorCliente panelPrincipal){
        ListadoClientes listadoC = new ListadoClientes();
        listadoC.setLocationRelativeTo(null);
        listadoC.setVisible(true);
        while(listadoC.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(listadoC.returnHome()){ // Cierra la ventana de listar clientes
                    panelPrincipal.setVisible(true);
                    listadoC.dispose();
                }else if(listadoC.makeSearch()){ // Realiza una busqueda de clientes
                    listadoC.resetButtonState();
                    listadoC.displayClientes(DAOManager.clientes().consultarClientes(listadoC.usernameToSearch()));
                }else if(listadoC.logOut()){ // Cierra la ventana de listar clientes y la de administrador
                    listadoC.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
    
    private static void ventanaListaEmpresas(PanelAdministradorEmpresa panelPrincipal){
        ListadoEmpresas listadoE = new ListadoEmpresas();
        listadoE.setLocationRelativeTo(null);
        listadoE.setVisible(true);
        while(listadoE.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(listadoE.returnHome()){ // Cierra la ventana de listar empresas
                    panelPrincipal.setVisible(true);
                    listadoE.dispose();
                }else if(listadoE.makeSearch()){ // Realiza una busqueda de empresas
                    listadoE.resetButtonState();
                    listadoE.displayEmpresas(DAOManager.empresas().consultarEmpresas(listadoE.usernameToSearch()));
                }else if(listadoE.logOut()){ // Cierra la ventana de listar empresas y la de administrador
                    listadoE.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
    
    // AÑADIR/ELIMINAR MODELOS/PRODUCTOS
    private static void ventanaAnhadirModelo(PanelEmpresa panelPrincipal, EmpresasVendedoras e) {
        AnhadirModelo modelosE = new AnhadirModelo(e);
        modelosE.setLocationRelativeTo(null);
        modelosE.setVisible(true);
        while(modelosE.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(modelosE.returnHome()){ // Cierra la ventana de añadir modelo
                    panelPrincipal.setVisible(true);
                    modelosE.dispose();
                }else if(modelosE.saveChanges()){ // Crea el modelo
                    System.out.println("pulsado");
                    modelosE.resetSaveButton();
                    try{
                        DAOManager.modelos().insertar(modelosE.getDatos());
                        modelosE.createdSuccesfull();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("ya registrado"))
                            modelosE.repeatedError();
                        else if(ex.getMessage().equals("distribuidor no registrado"))
                            modelosE.noDistribuidorError();
                        else{
                            ex.printStackTrace();
                            modelosE.createdUnnownError();
                        }
                    }
                }
                else if(modelosE.addProductos())
                { // Abre la ventana de añadir producto
                    modelosE.resetSaveButton();
                    modelosE.resetAddButton();
                    modelosE.setVisible(false);
                    ventanaAnhadirProducto(modelosE, e);
                }
                else if(modelosE.logOut()){ // Cierra la ventana de añadir modelo y la de empresa
                    modelosE.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
            ex.printStackTrace();
            }
        }
    }
    
    private static void ventanaAnhadirProducto(AnhadirModelo panelPrincipal, EmpresasVendedoras e){
        AnhadirProducto productosE = new AnhadirProducto(e);
        productosE.setLocationRelativeTo(null);
        productosE.setVisible(true);
        while(productosE.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(productosE.returnHome()){ // Cierra la ventana de añadir producto
                    panelPrincipal.returnHomeNow();
                    productosE.dispose();
                }else if(productosE.saveChanges()){ // Crea el producto
                    productosE.resetSaveButton();
                    try{
                        DAOManager.productos().insertar(productosE.getDatos());
                        productosE.createdSuccesfull();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("ya registrado"))
                            productosE.repeatedError();
                        else if(ex.getMessage().equals("modelo no registrado"))
                            productosE.noModelError();
                        else if(ex.getMessage().equals("precio insuficiente"))
                            productosE.priceError();
                        else{
                            ex.printStackTrace();
                            productosE.createdUnnownError();
                        }
                    }
                }
                else if(productosE.logOut()){ // Cierra la ventana de añadir producto y la de empresa
                    productosE.dispose();
                    panelPrincipal.logOutNow();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
            ex.printStackTrace();
            }
        }
    }
    
    // CESTA DE LA COMPRA
    private static void ventanaCesta(PanelCliente panelPrincipal, Clientes c){
        CestaCompra carrito = new CestaCompra(c);
        carrito.setLocationRelativeTo(null);
        carrito.setVisible(true);
        carrito.displayCesta();
        while(carrito.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(carrito.returnHome()){ // Cierra la ventana de la cesta de la compra
                    panelPrincipal.setVisible(true);
                    carrito.dispose();
                }else if(carrito.addProduct()){ // Añade un producto a la cesta
                    carrito.resetButtonState();
                    try{
                        carrito.addProductToCart();
                        carrito.productAddedCorrectly();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("producto no disponible"))
                            carrito.notAvailableError();
                        else{
                            ex.printStackTrace();
                            carrito.unknownAddError();
                        }
                    }
                }else if(carrito.processCart()){ // Realiza el pedido
                    carrito.resetButtonState();
                    try{
                        carrito.shipProducts();
                        carrito.shipCorrect();
                    }catch(SQLException ex){
                        if(ex.getMessage().equals("no productos"))
                            carrito.emptyCartError();
                        else if(ex.getMessage().equals("no direccion"))
                            carrito.noAddressError();
                        else{
                            ex.printStackTrace();
                            carrito.unknownShipError();
                        }
                    }
                }
                else if(carrito.logOut()){ // Cierra la ventana de la cesta de la compra y la de cliente
                    carrito.dispose();
                    panelPrincipal.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
            ex.printStackTrace();
            }
        }
    }
    
    
    
    private static void ventanaContactarProblemas(PanelCliente panelC, Clientes c) throws SQLException{
        PanelContactarProblemas panelCP = new PanelContactarProblemas();
        panelCP.setLocationRelativeTo(null);
        panelCP.setVisible(true);
        while(panelCP.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(panelCP.returnHome()){ // Cierra la ventana de contactar problemas
                    panelC.setVisible(true);
                    panelCP.dispose();
                }else if(panelCP.saveChanges()){ // Guarda los cambios realizados
                    panelCP.resetSaveButton();
                    Problemas p = DAOManager.problemas().comprobarNumeroPedido(panelCP.getDatos());
                    if (p == null){
                        panelCP.numeroPedidoIncorrecto();
                    }
                    else{
                        if (p.getComentario().equals("")){
                            panelCP.comentarioVacio();
                        }
                        else{
                            // Comprueba si el cliente que compro el producto es el mismo que el que quiere crear el informe
                            if (DAOManager.problemas().comprobarCliente(p, c)){ 
                                DAOManager.problemas().actualizarIdEmpresa(p);
                                DAOManager.problemas().insertar(p);
                                panelCP.guardadoCorrectamente();
                            }
                            else{
                                panelCP.clienteIncorrecto();
                            }
                        }
                        
                    }
                }else if(panelCP.logOut()){ // Cierra la ventana de contactar problemas y la de cliente
                    panelCP.dispose();
                    panelC.dispose();
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }
    
    private static void ventanaContactarProblemasEmpresa(PanelEmpresa panelC, EmpresasVendedoras e) throws SQLException{
        PanelContactarProblemasEmpresa panelCP = new PanelContactarProblemasEmpresa();
        panelCP.setLocationRelativeTo(null);
        panelCP.setVisible(true);
        boolean mostrarResultados = true;
        while(panelCP.isDisplayable()){ // Espera a que se pulse algun boton
            try {
                if(panelCP.returnHome()){ // Cierra la ventana de contactar problemas
                    panelC.setVisible(true);
                    panelCP.dispose();
                }else if(panelCP.logOut()){ // Cierra la ventana de contactar problemas y la de cliente
                    panelCP.dispose();
                    panelC.dispose();
                }else{ // Muestra los problemas
                    if (mostrarResultados){ // Solo muestra los problemas una vez
                        panelCP.displayProblemas(DAOManager.problemas().selectAll(), e);
                        mostrarResultados = false;
                    } 
                        Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
