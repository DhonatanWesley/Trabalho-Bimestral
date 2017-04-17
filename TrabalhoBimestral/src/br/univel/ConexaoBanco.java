package br.univel;

import java.awt.JobAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ConexaoBanco {

    private static Connection con;

    public static void conectar() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pass = "1234";
        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado...");
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconectar() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void inserir(Aluno aluno) {
        String sql = "insert into aluno (id, nome) values (?, ?)";
        
        PreparedStatement ps;
        try {
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, aluno.getId());
            ps.setString(2, aluno.getNome());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizar(String nome, int id) {
    	
    	String sql = "update aluno set nome = (nome) where id = (id)";
        
        PreparedStatement ps;
        try {
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nome);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void apagar(int id) {

    }

    public List<Aluno> buscarTodos() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "select * from aluno";
        PreparedStatement ps;
        try {
            
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno(0, null);
                a.setId(rs.getInt(1));
                a.setNome(rs.getString(2));
                lista.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Aluno buscarUm(int id) {
        return null;
    }
    public static Aluno criarTabela(){
    	UtilSql sql = new UtilSql();
    	
    	String comando = sql.toString();
    	
    	PreparedStatement ps;
        try {
            
            ps = con.prepareStatement(comando);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
		return null;
    }
    
    public static void main(String[] args) {
		conectar();
		criarTabela();
	}

}
