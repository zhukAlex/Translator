/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Алексей
 */
public final class Table extends javax.swing.JFrame {

    /**
     * Creates new form Table
     */
    private String fileName;
    private StaticTable staticTable;
    private ParsTable parsTable;
    private final int ADD = 1;
    private final int CHANGE = 2;
    private final int FIND = 3;
    private final int DELETE = 4;
    private int state;
    
    public Table() {
        try {
            readTable();
        } catch (IOException ex) {
            staticTable = new StaticTable();
        } catch (ClassNotFoundException ex) {

        }
       
        initComponents();
        jTable1.setModel(new MyTable(staticTable.rows));
        initTable(jTable1);
        
        fileName = null;
        state = ADD;
    }
    
    private void initTable(JTable table){
        table.setFocusable(true);
        System.out.println((int)staticTable.indexes.size());
        table.repaint();
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if( !staticTable.rows[ table.getSelectedRow() ].name.equals("") && state == CHANGE ){
                    jTextAssoc.setText( staticTable.rows[ table.getSelectedRow() ].associativitie );
                    jTextPrior.setText( staticTable.rows[ table.getSelectedRow() ].prioritie );
                    jTextName.setText( staticTable.rows[ table.getSelectedRow() ].name );
                    jTextMachine.setText( staticTable.rows[ table.getSelectedRow() ].machineCommand );
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        });
    }
    
    private void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        
        
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport)table.getParent();
        
        Rectangle rect = table.getCellRect(0, 1, true);
        Point pt = viewport.getViewPosition();
        rect.setLocation(rect.x-pt.x, rect.y-pt.y);
        table.scrollRectToVisible(rect);
        
        rect = table.getCellRect(rowIndex, vColIndex, true);
        pt = viewport.getViewPosition();
        rect.setLocation(rect.x-pt.x, rect.y-pt.y);
        
        
        table.scrollRectToVisible(rect);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextName = new javax.swing.JTextField();
        jTextPrior = new javax.swing.JTextField();
        jTextAssoc = new javax.swing.JTextField();
        jTextMachine = new javax.swing.JTextField();
        jLabelName = new javax.swing.JLabel();
        jLabelPrior = new javax.swing.JLabel();
        jLabelAssoc = new javax.swing.JLabel();
        jLabelMachine = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonFind = new javax.swing.JButton();
        jTextPars = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Операция", "Приоритет", "Ассоциативность", "Машинная команда"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Операция", "Строка"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabelName.setText("Операция");

        jLabelPrior.setText("Приоритет");

        jLabelAssoc.setText("Ассоциативность");

        jLabelMachine.setText("Машинная команда");

        jButtonAdd.setText("Добавить");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonFind.setText("Поиск");
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });

        jLabel1.setText("Операция");

        jMenu1.setText("Файл");

        jMenuItem1.setText("Открыть файл");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Операции");

        jMenuItem2.setText("Заполнить таблицу");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Добавить");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem6.setText("Измененить");
        jMenuItem6.setActionCommand("Изменение");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem4.setText("Удалить");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Найти");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPrior, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelPrior))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextAssoc, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelAssoc))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMachine)
                                    .addComponent(jTextMachine, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextPars, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jLabelPrior)
                    .addComponent(jLabelAssoc)
                    .addComponent(jLabelMachine))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPrior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextAssoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextMachine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdd)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextPars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonFind)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Start working with file
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(fileName != null){
            try {
                parsTable = new ParsTable();
                FileInputStream fstream = new FileInputStream(fileName);
                String strLine;
                int number = 0;
                for(Object i : staticTable.indexes){
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));  
                    Pattern pattern = Pattern.compile(staticTable.rows[(int)i].name);
                    number = 0;
                    while ((strLine = br.readLine()) != null){
                        Matcher matcher = pattern.matcher(strLine);
                        while(matcher.find())
                            parsTable.add(staticTable.rows[(int)i].name, "" + number);
                        number++;
                    }      
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTable2.setModel(new MyParsModel(parsTable.getRowPars()));
            jTable2.repaint();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jLabelAssoc.setVisible(true);
        jLabelMachine.setVisible(true);
        jLabelName.setVisible(true);
        jLabelPrior.setVisible(true);
        
        jTextAssoc.setVisible(true);
        jTextMachine.setVisible(true);
        jTextName.setVisible(true);
        jTextPrior.setVisible(true);
        jTextName.setEnabled(true);
        jButtonAdd.setText("Добавить");
        state = ADD;
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    //open File
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        fileName = null;
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if(ret == JFileChooser.APPROVE_OPTION) {
            fileName = fileopen.getSelectedFile().getPath();
            System.out.println("Name of file: " + fileName);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        String name;
        String assoc;
        String prior;
        String machine;
        name = jTextName.getText();
        assoc = jTextAssoc.getText();
        prior = jTextPrior.getText();
        machine = jTextMachine.getText();
        int index;
        switch (state) {
            case ADD:
                if( staticTable.add(name, assoc, prior, machine) )
                {
                    jTable1.setModel(new MyTable(staticTable.rows));
                    jTable1.repaint();
                    scrollToVisible(jTable1, (int)staticTable.indexes.get(staticTable.indexes.size() - 1), 0);
                    
                    try {
                        writeTable();
                    } catch (IOException ex) {
                        Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }   break;
            case CHANGE:
                if( !staticTable.change(name, assoc, prior, machine) ){
                    System.out.println("нет");
                }else{
                    System.out.println("да");
                    jTable1.setModel(new MyTable(staticTable.rows));
                    jTable1.repaint();
                    try {
                        writeTable();
                    } catch (IOException ex) {
                        Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                } 
                break;
            case DELETE:
                if( !staticTable.delete(name) )
                    System.out.println("Не найден!"); 
                else{
                    jTable1.setModel(new MyTable(staticTable.rows));
                    jTable1.repaint();
                    try {
                        writeTable();
                    } catch (IOException ex) {
                        Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
                break;
            case FIND:
                index = staticTable.find(name);
                if( index > 0 )
                    scrollToVisible(jTable1, index, 0);
                else
                    System.out.println("Не найден!"); 
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jLabelAssoc.setVisible(false);
        jLabelMachine.setVisible(false);
        jLabelName.setVisible(true);
        jLabelPrior.setVisible(false);
        
        jTextAssoc.setVisible(false);
        jTextMachine.setVisible(false);
        jTextName.setVisible(true);
        jTextPrior.setVisible(false);
        jTextName.setEnabled(true);
        jButtonAdd.setText("Поиск");
        state = FIND;
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jLabelAssoc.setVisible(false);
        jLabelMachine.setVisible(false);
        jLabelName.setVisible(true);
        jLabelPrior.setVisible(false);
        
        jTextAssoc.setVisible(false);
        jTextMachine.setVisible(false);
        jTextName.setVisible(true);
        jTextPrior.setVisible(false);
        jTextName.setEnabled(true);
        jButtonAdd.setText("Удалить");
        state = DELETE;
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jLabelAssoc.setVisible(true);
        jLabelMachine.setVisible(true);
        jLabelName.setVisible(true);
        jLabelPrior.setVisible(true);
        
        jTextAssoc.setVisible(true);
        jTextMachine.setVisible(true);
        jTextName.setVisible(true);
        jTextPrior.setVisible(true);
        
        jButtonAdd.setText("Изменить");
        state = CHANGE;
        
        jTextName.setEnabled(false);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed
        String name = jTextPars.getText();
        int index = parsTable.find(name);
        if( index > 0 )
            scrollToVisible(jTable2, index, 0);
        else
            System.out.println("Не найден!"); 
    }//GEN-LAST:event_jButtonFindActionPerformed

    public void writeTable() throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream("temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(staticTable);
        oos.flush();
        oos.close();
    }
    
    public void readTable() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("temp.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        staticTable = (StaticTable) oin.readObject();
    }
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
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Table().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonFind;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAssoc;
    private javax.swing.JLabel jLabelMachine;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPrior;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextAssoc;
    private javax.swing.JTextField jTextMachine;
    private javax.swing.JTextField jTextName;
    private javax.swing.JTextField jTextPars;
    private javax.swing.JTextField jTextPrior;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
