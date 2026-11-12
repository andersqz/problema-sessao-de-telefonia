
package view;

import java.util.Map;
import javax.swing.table.DefaultTableModel;
import model.Sessao;
import service.SessaoService;


public class TelaListaSessao extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaListaSessao.class.getName());

    private SessaoService sessaoService = new SessaoService();
    private final String caminho = "sessoes.txt";

    public TelaListaSessao() {
        initComponents();
        tblSessoes.setDefaultEditor(Object.class, null);
        tblSessoes.setAutoCreateRowSorter(true);
        preencherTabela();
    }
    
    public void preencherTabela() {
        Map<String, Sessao> mapaSessoes = sessaoService.carregarSessoesDoArquivo(caminho);
        
        DefaultTableModel modelo = (DefaultTableModel) tblSessoes.getModel();
        modelo.setRowCount(0);
        
        for (Sessao s : mapaSessoes.values()) {
            modelo.addRow(s.obterDados());
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSessoes = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblSessoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Session ID", "IMSI", "IMEI", "MCC", "MNC", "Cell ID", "LAC", "Bearer", "Início", "Fim", "Duração (s)", "IP", "SIP Call ID", "Codec", "Bytes up", "Bytes down", "Lat", "Lon", "Encryption"
            }
        ));
        jScrollPane1.setViewportView(tblSessoes);

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(this::btnFecharActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1331, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFechar)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnFechar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
                dispose();    }//GEN-LAST:event_btnFecharActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new TelaListaSessao().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSessoes;
    // End of variables declaration//GEN-END:variables
}
