
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import model.dao.ClienteDAO;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Clientes extends javax.swing.JFrame {

    FileInputStream fis; //usado para ler arquivos de imagem

    int tamanho; //o tamanho da imagem

    public Clientes() {

        initComponents();

        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código", "Nome", "Data"}, 0); //define o modelo da tabela
        jTable1.setModel(modelo);

        readJTable(); // carrega os dados da tabela
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        upload = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblfoto = new javax.swing.JLabel();
        btnCarregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        upload.setBackground(new java.awt.Color(204, 204, 204));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel2.setText("nome");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("data");

        jLabel1.setText("codigo");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cod", "nome", "data"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setText("cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("atualizar");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("limpar");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("excluir");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton1)
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addGap(35, 35, 35)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton5)
                    .addComponent(jButton3))
                .addGap(16, 16, 16))
        );

        lblfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camera3 (1).png"))); // NOI18N

        btnCarregar.setText("upload");
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 3, 24)); // NOI18N
        jLabel3.setText("CERRADO");

        jTextField1.setEnabled(false);

        jButton4.setText("bubble sort");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 3, 24)); // NOI18N
        jLabel5.setText("ORDENAÇÃO");

        jButton6.setText("selection sort");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("quick sort");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout uploadLayout = new javax.swing.GroupLayout(upload);
        upload.setLayout(uploadLayout);
        uploadLayout.setHorizontalGroup(
            uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uploadLayout.createSequentialGroup()
                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(uploadLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(uploadLayout.createSequentialGroup()
                                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(uploadLayout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(19, 19, 19))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uploadLayout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)))
                                    .addGroup(uploadLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(31, 31, 31)))
                                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(jTextField1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(124, 124, 124))
                            .addGroup(uploadLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(uploadLayout.createSequentialGroup()
                                        .addGap(191, 191, 191)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(uploadLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jButton4)
                        .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(uploadLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel5))
                            .addGroup(uploadLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jButton6)
                                .addGap(83, 83, 83)
                                .addComponent(jButton7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        uploadLayout.setVerticalGroup(
            uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uploadLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(uploadLayout.createSequentialGroup()
                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(uploadLayout.createSequentialGroup()
                        .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(uploadLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(uploadLayout.createSequentialGroup()
                                .addGap(191, 191, 191)
                                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uploadLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(uploadLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(25, 25, 25)
                                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(uploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(141, 141, 141))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(upload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(upload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Cliente c = new Cliente(); 
        ClienteDAO dao = new ClienteDAO();

        c.setNome(jTextField2.getText());
        c.setData(jTextField3.getText());

        dao.create(c, fis, tamanho); //adiciona no banco

        readJTable(); //atualiza e chama a tabela
        jButton5.setEnabled(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (jTable1.getSelectedRow() != -1) { //verifica se uma linha foi selecionada

            Cliente c = new Cliente();
            ClienteDAO dao = new ClienteDAO();
            c.setCodigo((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            c.setNome(jTextField2.getText());
            c.setData(jTextField3.getText());

            dao.update(c); // altera no banco
            dao.update2(c, fis, tamanho);

            readJTable(); //atualiza e chama a tabela
             jTextField1.setText(""); //limpa os campos
             jTextField2.setText("");
             jTextField3.setText("");

             lblfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camera3 (1).png"))); //deixa a foto inicial do lblfoto   
             
             jButton2.setEnabled(false); //desativa
             jButton3.setEnabled(false);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (jTable1.getSelectedRow() != -1) { //verifica se uma linha foi selecionada

            jButton2.setEnabled(true); // habilita os botoes
            jButton3.setEnabled(true);
            jButton5.setEnabled(true);

            jButton1.setEnabled(false); //desabilita o botao cadastrar

            jTextField1.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()); //preenche os textfield
            jTextField2.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            jTextField3.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());

            ClienteDAO dao = new ClienteDAO();
            int codigo = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0); //transforma em int o valor do codigo da linha selecionada
            Cliente cliente = dao.readCodigo(codigo); //chama o metodo para buscar detalhes no banco 

            if (cliente.getFoto() != null) { //verifica se possui a foto no banco 
                try {
                    Image image = ImageIO.read(new ByteArrayInputStream(cliente.getFoto())); //tranforma os bytes da foto em imagem
                    lblfoto.setIcon(new ImageIcon(image.getScaledInstance(lblfoto.getWidth(), lblfoto.getHeight(), Image.SCALE_SMOOTH))); //adiciona a imagem no lblfoto
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao carregar imagem: " + e.getMessage());
                }
            } else {
                lblfoto.setIcon(null);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha para ser alterada.");
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        carregarFoto();
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ClienteDAO cdao = new ClienteDAO();

        List<Cliente> clientes = cdao.readDado(); //retorna uma lista de objetos lidos do banco
        long inicio = System.currentTimeMillis(); //registra o tempo inicial
        cdao.bubbleSort(clientes); //chama o metodo bubble
        long fim = System.currentTimeMillis(); //registra o tempo final

        double segundos = (fim - inicio) / 1000.0; //transforma em s

        atualizarTabela(clientes);
        JOptionPane.showMessageDialog(null, "Tempo de execução: " + segundos + " segundos");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        jButton2.setEnabled(false); //desativa
        jButton3.setEnabled(false);

        jButton1.setEnabled(true); //habilita

        jTextField1.setText(""); //limpa os campos
        jTextField2.setText("");
        jTextField3.setText("");

        lblfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camera3 (1).png"))); //deixa a foto inicial do lblfoto
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTable1.getSelectedRow() != -1) { //verifica se tem alguma linha selecionada

            Cliente c = new Cliente();
            ClienteDAO dao = new ClienteDAO();
            c.setCodigo((int) jTable1.getValueAt(jTable1.getSelectedRow(), 0)); //pega os dados da linha selecionada e atribui ao c

            dao.delete(c); //exclui

            readJTable(); //atualiza

            jTextField1.setText(""); //limpa os campos
            jTextField2.setText("");
            jTextField3.setText("");

            lblfoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camera3 (1).png")));

            jButton2.setEnabled(false); //desativa
            jButton3.setEnabled(false);
            jButton5.setEnabled(false);

            jButton1.setEnabled(true); //habilita
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ClienteDAO cdao = new ClienteDAO();

        List<Cliente> clientes = cdao.readDado(); //retorna uma lista de objetos lidos do banco

        long inicio = System.currentTimeMillis(); //registra o tempo inicial
        cdao.selectionSort(clientes); //chama a ordenacao selection
        long fim = System.currentTimeMillis(); //registra o tempo final

        double segundos = (fim - inicio) / 1000.0; //tranforma em s

        //atualiza a tabela
        JOptionPane.showMessageDialog(null, "Tempo de execução: " + segundos + " segundos");
        atualizarTabela(clientes);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ClienteDAO cdao = new ClienteDAO();
        List<Cliente> clientes = cdao.readDado();

        long inicio = System.currentTimeMillis();
        cdao.quickSort(clientes, 0, clientes.size() - 1);
        long fim = System.currentTimeMillis();

        double segundos = (fim - inicio) / 1000.0;

        JOptionPane.showMessageDialog(null, "Tempo de execução: " + segundos + " segundos");
        atualizarTabela(clientes);
    }//GEN-LAST:event_jButton7ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCarregar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblfoto;
    private javax.swing.JPanel upload;
    // End of variables declaration//GEN-END:variables

    private void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel(); //permite manipular os dados
        modelo.setNumRows(0); //garante que a tabela esteja vazia antes de adicionar novos dados
        ClienteDAO cdao = new ClienteDAO();

        List<Cliente> clientes = cdao.readDado();

        for (Cliente c : clientes) {
            modelo.addRow(new Object[]{ //adiciona uma nova linha na tabela
                c.getCodigo(),
                c.getNome(),
                c.getData(),});
        }
    }

    public void atualizarTabela(List<Cliente> clientes) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); //permite manipular os dados
        model.setRowCount(0);

        for (Cliente cliente : clientes) {

            model.addRow(new Object[]{cliente.getCodigo(), cliente.getNome(), cliente.getData()});
            //adiciona uma nova linha ao modelo da tabela com os dados atuais
        }

    }

    private void carregarFoto() {
        JFileChooser jfc = new JFileChooser(); //permite ao usuario navegar no sistema de arquivos
        jfc.setDialogTitle("selecionar arquivo"); //define o titulo do selecionador de arquivos
        jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens(*.PNG, *.JPG,*.JPEG)", "png", "jpg", "jpeg"));
        //aplica um filtro de arquivos
        int resultado = jfc.showOpenDialog(this); //exibe a caixa para o usuario escolher a imagem
        if (resultado == JFileChooser.APPROVE_OPTION) { //veifica se o usuario escolheu uma imagem
            try {
                fis = new FileInputStream(jfc.getSelectedFile()); //cria um novo FileInputStream para ler os bytes da imagem 
                tamanho = (int) jfc.getSelectedFile().length(); //armazena o tamanho do arquivo
                Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblfoto.getWidth(), lblfoto.getHeight(), Image.SCALE_SMOOTH);
                //redimensiona a imagem para adicionar ao lblfoto
                lblfoto.setIcon(new ImageIcon(foto)); //adiciona a imagem ao lblfoto
                lblfoto.updateUI(); //atualiza a interface
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar a foto: " + e.getMessage());

            }
        }

    }

}
