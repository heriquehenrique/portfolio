package model.dao;

import connection.ConnectionFactory;
import java.io.FileInputStream;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

public class ClienteDAO {

    public Cliente readCodigo(int codigo) {
        Connection con = ConnectionFactory.getConnection(); //conexao com o banco
        PreparedStatement stmt = null; //executa consultas sql
        ResultSet rs = null; //armazena o resultado da consulta

        Cliente cliente = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM imagens WHERE Codigo = ?"); //inicia o objeto com uma consulta
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery(); //executa a consulta e armazena o resultado

            if (rs.next()) { //verifica se possui resultado
                cliente = new Cliente();
                cliente.setCodigo(rs.getInt("Codigo")); //define o codigo recuperando ele
                cliente.setNome(rs.getString("Nome"));
                cliente.setData(rs.getString("Data"));
                cliente.setFoto(rs.getBytes("Foto")); //define-a recuperando como um array de bytes
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return cliente;
    }

    public void create(Cliente c, FileInputStream fis, int tamanho) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null; //executa consultas sql

        try {
            stmt = con.prepareStatement("INSERT INTO imagens (Nome, Data, Foto) VALUES (?, ?, ?)"); //inicia uma consulta
            stmt.setString(1, c.getNome()); //define o primeiro parametro como nome
            stmt.setString(2, c.getData());
            stmt.setBinaryStream(3, fis, tamanho);

            stmt.executeUpdate(); //executa a consulta, adicionando o registro na tabela
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco: " + ex.getMessage());

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Cliente> readDado() {  //define um metodo que retorna uma lista
        Connection con = ConnectionFactory.getConnection(); //conexao
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>(); //cria a lista

        try {

            stmt = con.prepareStatement("SELECT codigo, nome, data FROM imagens ORDER BY DATA"); //inicia uma consulta
            rs = stmt.executeQuery(); //executa a consulta e armazena

            while (rs.next()) { //enquanto houver resultados
                Cliente cliente = new Cliente(); //cria uma nova instancia para armazenar os dados
                cliente.setCodigo(rs.getInt("Codigo")); //define o dado recuperando do banco
                cliente.setNome(rs.getString("Nome"));
                cliente.setData(rs.getString("Data"));

                clientes.add(cliente); //adiciona na lista
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //ConnectionFactory.closeConnection(con, stmt);
        }
        return clientes;
    }

    public void update(Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null; //executa consultas sql

        try {
            stmt = con.prepareStatement("UPDATE imagens SET Nome = ?, Data = ? WHERE Codigo = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getData());
            //stmt.setBinaryStream(3, fis, tamanho);
            stmt.setInt(3, c.getCodigo());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            //ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
        public void update2(Cliente c, FileInputStream fis,int tamanho) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null; //executa consultas sql

        try {
            stmt = con.prepareStatement("UPDATE imagens SET Foto = ? WHERE Codigo = ?");
            stmt.setBinaryStream(1, fis, tamanho);
            stmt.setInt(2, c.getCodigo());

            stmt.executeUpdate();

         
        } catch (SQLException ex) {
        } finally {
            //ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    

    public void delete(Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM imagens WHERE Codigo = ?");
            stmt.setInt(1, c.getCodigo());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso.");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao exlcuir " + ex);

        } finally {
            //ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void bubbleSort(List<Cliente> clientes) {
        int tamanho = clientes.size();
        Cliente aux;
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - i - 1; j++) {
                if (clientes.get(j).getCodigo() > clientes.get(j + 1).getCodigo()) {
                    aux = clientes.get(j);
                    clientes.set(j, clientes.get(j + 1));
                    clientes.set(j + 1, aux);
                }
            }
        }
    }

    public void selectionSort(List<Cliente> clientes) {
        int tamanho = clientes.size();
        Cliente aux;
        int posicao_menor;
        for (int i = 0; i < tamanho - i - 1; i++) {
            posicao_menor = i;
            for (int j = i + 1; j < tamanho - 1; j++) {
                if (clientes.get(j).getCodigo() < clientes.get(posicao_menor).getCodigo()) {
                    posicao_menor = j;
                }
            }
            aux = clientes.get(posicao_menor);
            clientes.set(posicao_menor, clientes.get(i));
            clientes.set(i, aux);
        }
    }

    public void quickSort(List<Cliente> clientes, int esquerda, int direita) {
        int esq = esquerda;
        int dir = direita;
        int pivo = clientes.get((esq + dir) / 2).getCodigo();
        Cliente troca;

        while (esq <= dir) {
            while (clientes.get(esq).getCodigo() < pivo) {
                esq = esq + 1;
            }
            while (clientes.get(dir).getCodigo() > pivo) {
                dir = dir - 1;
            }
            if (esq <= dir) {
                troca = clientes.get(esq);
                clientes.set(esq, clientes.get(dir));
                clientes.set(dir, troca);
                esq = esq + 1;
                dir = dir - 1;
            }
        }
        if (esquerda < dir) {
            quickSort(clientes, esquerda, dir);
        }

        if (esq < direita) {
            quickSort(clientes, esq, direita);
        }
    }
}
