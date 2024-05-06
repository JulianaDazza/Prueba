/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.hyunseda.market.presentation;

import co.com.hyunseda.market.domain.Carrito;
import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.service.CarritoService;
import co.com.hyunseda.market.service.CategoryService;
import co.com.hyunseda.market.service.ProductService;
import java.awt.Cursor;
import java.util.ArrayList;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class GUIStoreProducts extends javax.swing.JDialog {
    ProductService productService;
    CategoryService categoryService;
    CarritoService carritoService;
    List<Carrito> itemList;
    /**
     * Creates new form GUIProductsFind
     */
    
    public GUIStoreProducts(java.awt.Frame parent, boolean modal,ProductService productService,CarritoService carritoService) {
        super(parent, modal);
        this.productService = productService;
        this.carritoService = new CarritoService();
        createObservers();
        initComponents();
        initializeTable();
        //loadData();
        setLocationRelativeTo(null); //centrar al ventana
    }
    
    private void initializeTable() {
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Name", "Price","Description","Stock","Categories"
                }
        ));
    }
    
   /* private void fillTable(List<Product> listProducts) {
        initializeTable();
        List<Product> result = productService.listProducts();
        this.initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();

        Object rowData[] = new Object[8];//No columnas
        for (int i = 0; i < listProducts.size(); i++) {
            rowData[0] = result.get(i).getProductId();
            rowData[1] = result.get(i).getProductName();
            rowData[2] = "" +result.get(i).getProductPrice();
            rowData[3] = result.get(i).getProductDescription();
            rowData[4] = "" +result.get(i).getProductStock();
            rowData[5] = "" +result.get(i).getProductCategories();

            model.addRow(rowData);
        }
    }*/
   /* private void fillOnlyTable(Product prmProduct)
    {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
        Object rowData[] = new Object[8];//No columnas
        rowData[0] = prmProduct.getProductId();
        rowData[1] = prmProduct.getProductName();
        rowData[2] = "" +prmProduct.getProductPrice();
        rowData[3] = prmProduct.getProductDescription();
        rowData[4]= "" +prmProduct.getProductStock();
        rowData[5] = "" +prmProduct.getProductCategories();
        
        
        model.addRow(rowData);
    }*/
    
    private void loadData() {
        initializeTable();
        List<Product> result = productService.listProducts();
        this.initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();

       Object rowData[] = new Object[8];//No columnas
        for (int i = 0; i < result.size(); i++) {
            rowData[0] = result.get(i).getProductId();
            rowData[1] = result.get(i).getProductName();
            rowData[2] = "" + result.get(i).getProductPrice();
            rowData[3] = result.get(i).getProductDescription();
            rowData[4] = "" + result.get(i).getProductStock();
             
            String categories = "";
            if (result.get(i).getProductCategories() != null) {
                for (Category cat : result.get(i).getProductCategories()) {
                    categories += "id:" + cat.getCategoryId() + " " + cat.getCategoryName() + ", ";
                }
            }
            if (!categories.isEmpty()) {
                categories = categories.substring(0, categories.length() - 2);
            }
            rowData[5] = "" + categories;
            ////
            

            model.addRow(rowData);
        }     
    }

    
   private void loadDataByName(String name) {
        initializeTable();
        List<Product> result = productService.findProductsByName(name);
        this.initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();

        Object rowData[] = new Object[8];
        for (int i = 0; i < result.size(); i++) {
            rowData[0] = result.get(i).getProductId();
            rowData[1] = result.get(i).getProductName();
            rowData[2] = "" + result.get(i).getProductPrice();
            rowData[3] = result.get(i).getProductDescription();
            rowData[4] = "" + result.get(i).getProductStock();
            
            String categories = "";
            if (result.get(i).getProductCategories() != null) {
                for (Category cat : result.get(i).getProductCategories()) {
                    categories += cat.getCategoryId() + " " + cat.getCategoryName() + ", ";
                }
            }
            if (!categories.isEmpty()) {
                categories = categories.substring(0, categories.length() - 2);
            }
            rowData[5] = "" + categories;

            model.addRow(rowData);
        }
    }

    
    private void loadDataById(int id) {
         // Inicializar la tabla solo una vez al principio
        initializeTable();

        // Obtener el producto por su ID
        Product result = productService.findProductById(id);

        // Verificar si el producto existe
        if (result != null) {
            DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();

            // Crear un array para contener los datos de la fila
            Object rowData[] = new Object[6]; // Número de columnas

            // Llenar el array con los datos del producto
            rowData[0] = result.getProductId();
            rowData[1] = result.getProductName();
            rowData[2] = result.getProductPrice(); // No es necesario convertir a String
            rowData[3] = result.getProductDescription();
            rowData[4] = result.getProductStock(); // No es necesario convertir a String
           

            String categories = "";
            if (result.getProductCategories() != null) {
                for (Category cat : result.getProductCategories()) {
                    categories += cat.getCategoryId() + " " + cat.getCategoryName() + ", ";
                }
            }
            if (!categories.isEmpty()) {
                categories = categories.substring(0, categories.length() - 2);
            }
            rowData[5] = "" + categories;
            
            // Agregar la fila al modelo de la tabla
            model.addRow(rowData);
        } else {
            // Manejar el caso en que el producto no se encuentre
            // Esto podría ser una ventana emergente, un mensaje en la consola, etc.
            JOptionPane.showMessageDialog(null, "Producto no encontrado para el ID: " + id, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        lblImagen = new javax.swing.JLabel();
        pnlNorth = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdoId = new javax.swing.JRadioButton();
        rdoName = new javax.swing.JRadioButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnSearchAll = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnAddToCart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Búsqueda de productos");

        pnlCenter.setLayout(new java.awt.GridLayout(1, 2));

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducts);

        pnlCenter.add(jScrollPane1);

        lblImagen.setText("-");
        lblImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenMouseClicked(evt);
            }
        });
        pnlCenter.add(lblImagen);

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Buscar por:");
        pnlNorth.add(jLabel1);

        buttonGroup1.add(rdoId);
        rdoId.setSelected(true);
        rdoId.setText("Id");
        pnlNorth.add(rdoId);

        buttonGroup1.add(rdoName);
        rdoName.setText("Nombre del producto");
        pnlNorth.add(rdoName);

        txtSearch.setPreferredSize(new java.awt.Dimension(62, 32));
        pnlNorth.add(txtSearch);

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        pnlNorth.add(btnSearch);

        btnSearchAll.setText("Buscar Todos");
        btnSearchAll.setPreferredSize(new java.awt.Dimension(120, 25));
        btnSearchAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAllActionPerformed(evt);
            }
        });
        pnlNorth.add(btnSearchAll);

        getContentPane().add(pnlNorth, java.awt.BorderLayout.PAGE_START);

        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jPanel1.add(btnClose);

        btnAddToCart.setText("Añadir al carrito");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddToCart);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
        GUILogin pMen = new GUILogin();
        pMen.setVisible(true);
        
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSearchAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAllActionPerformed
        /*fillTable(productService.listProducts());*/
        loadData();
    }//GEN-LAST:event_btnSearchAllActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try
        {
            if(rdoId.isSelected() && !"".equals(txtSearch.getText()))
        {
            String id = txtSearch.getText().trim();
            int idProduct = Integer.parseInt(id);
           loadDataById(idProduct); 
        }else if(!"".equals(txtSearch.getText()))
        {
            loadDataByName(txtSearch.getText());
        }else
        {
            JOptionPane.showMessageDialog(null, "No digitó parámetro a buscar", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
        }catch(NullPointerException e)
        {
            JOptionPane.showMessageDialog(null, "Índice fuera de rango", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "Seleccione la entrada correcta", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        try{
            Carrito objCar = new Carrito(productService.findPorName(tblProducts.getValueAt(tblProducts.getSelectedRow(), 1).toString()),0);
            this.carritoService.setCarrito(objCar);
            GUIStockWindow car = new GUIStockWindow(carritoService,this);
            car.setVisible(true);
        }catch(ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(null,"No se ha seleccionado nada","Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void lblImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenMouseClicked
       
    }//GEN-LAST:event_lblImagenMouseClicked

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        int selectedIndex = tblProducts.getSelectedRow();
        if (selectedIndex >= 0 && selectedIndex < tblProducts.getRowCount()) {
            Product selectedProduct = productService.listProducts().get(selectedIndex);

            // Coloca la descripción
            StringBuilder sb = new StringBuilder(64);
            sb.append("<html>" + selectedProduct.getProductDescription()
                    + "<br><br>Precio: " + selectedProduct.getProductPrice()
                    + "<br><br>Categorías: " + selectedProduct.getProductCategories().toString()
                    + "</html>");
            //lblDescription.setText(sb.toString());

            // Coloca el titulo de la imagen
            lblImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(selectedProduct.getProductName()));

            // Coloca la imagen
            String imageURL = selectedProduct.getImage();
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                if (imageURL != null && !imageURL.isEmpty()) {
                    ImageIcon imageIcon = Utilities.loadImageFromCloud(imageURL);
                    if (imageIcon != null) {
                        lblImagen.setIcon(imageIcon);
                        lblImagen.revalidate();
                        lblImagen.repaint();
                    } else {
                        throw new IllegalArgumentException("No se pudo cargar la imagen");
                    }
                } else {
                    throw new IllegalArgumentException("La URL de la imagen es nula o vacía");
                }
            } catch (Exception ex) {
                // Error al cargar la imagen
                lblImagen.setIcon(null);
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Puedes manejar la excepción de manera adecuada según los requisitos de tu aplicación
            } finally {
                setCursor(Cursor.getDefaultCursor());
            }
        } else {
            // La fila seleccionada está fuera de rango o la tabla está vacía
            JOptionPane.showMessageDialog(this, "LA FILA SELECCIONADA ESTA FUERA DE RANGO" + JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblProductsMouseClicked

    
    public void selectedItemImage(){
 
        try{
            Carrito objCar = new Carrito(productService.findPorName(tblProducts.getValueAt(tblProducts.getSelectedRow(), 1).toString()),0);
            this.carritoService.setCarrito(objCar);
            GUIStockWindow car = new GUIStockWindow(carritoService,this);
            car.setVisible(true);
        }catch(ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(null,"No se ha seleccionado nada","Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }
 
    }
    
    public void addItem(Carrito prmItem)
    {
        if(itemList == null)
        {
            this.itemList = new ArrayList<>();
            itemList.add(prmItem);
        }else
        {
            this.itemList.add(prmItem);
        }
         
    }
    
    public List<Carrito> getItemList() {
           return itemList;
    }

       public List<Carrito> cleanItemList(){
           this.itemList = null;
           return itemList;
    }

    private void createObservers() {
       GUIListProductsCar objList = new GUIListProductsCar(carritoService, this);
       this.carritoService.addObserver(objList);
    }
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchAll;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlNorth;
    private javax.swing.JRadioButton rdoId;
    private javax.swing.JRadioButton rdoName;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
