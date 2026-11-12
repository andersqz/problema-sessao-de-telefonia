
package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import model.Sessao;


public class SessaoService {
    
    Map<String, Sessao> mapaSessoes = new HashMap<>();
    
    public Map<String, Sessao> carregarSessoesDoArquivo(String caminhoArquivo) {
        mapaSessoes = new HashMap<>();
        
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            
            String linha;
            
            while ((linha = leitor.readLine()) != null) {
                if (linha.trim().isEmpty())
                    continue;
                
                String[] partes = linha.split(";", 2);
                if (partes.length < 2)
                    continue;
                
                String chaveNumero = partes[0].trim();
                String textoRestante = partes[1].trim();
                
                Sessao sessao = extrairDadosTextoPuro(chaveNumero, textoRestante);
                
                if (sessao != null) {
                    mapaSessoes.put(chaveNumero, sessao);
                }
            }
            
        } catch (IOException e) {
            System.err.println("");
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        
        return mapaSessoes;
    }
    
    
    public Sessao buscaPorNumero(Map<String, Sessao> mapaSessoes, String numero) {
        return mapaSessoes.get(numero);
    }
     
    private Sessao extrairDadosTextoPuro(String numero, String texto) {
        
        Sessao sessao = new Sessao();
        
        sessao.setNumero(numero);
        sessao.setSessionID(buscaValor(texto, "session_id"));
        sessao.setImsi(buscaValor(texto, "imsi"));
        sessao.setImei(buscaValor(texto, "imei"));
        sessao.setMcc(buscaValor(texto, "mcc"));
        sessao.setMnc(buscaValor(texto, "mnc"));
        sessao.setCellID(buscaValor(texto, "cell_id"));
        sessao.setLac(buscaValor(texto, "lac"));
        sessao.setBearer(buscaValor(texto, "bearer"));
        sessao.setStartTime(buscaValor(texto, "start_time"));
        sessao.setEndTime(buscaValor(texto, "end_time"));
        sessao.setDurationS(buscaValor(texto, "duration_s"));
        sessao.setIpAddr(buscaValor(texto, "ip_addr"));
        sessao.setSipCallID(buscaValor(texto, "sip_call_id"));
        sessao.setCodec(buscaValor(texto, "codec"));
        sessao.setBytesUp(buscaValor(texto, "bytes_up"));
        sessao.setBytesDown(buscaValor(texto, "bytes_down"));
        sessao.setLat(buscaValor(texto, "lat"));
        sessao.setLon(buscaValor(texto, "lon"));
        sessao.setEncryption(buscaValor(texto, "encryption"));
        
        return sessao;
        
    }
    
    
    private String buscaValor(String texto, String campo) {
        String alvo = "\"" + campo + "\":";
        
        int inicioChave = texto.indexOf(alvo);
        if (inicioChave == -1) 
            return "";
        
        int inicioValor = inicioChave + alvo.length();
        
        String sub = texto.substring(inicioValor).trim();
        
        if (sub.startsWith("\"")) {
            return sub.substring(1, sub.indexOf("\"", 1));
        } else {
            int fimValor = sub.indexOf(",");
            if (fimValor == -1) {
                fimValor = sub.indexOf("}");
            }
            
            return sub.substring(0, fimValor).trim();
        }
        
        
        
    }
}
