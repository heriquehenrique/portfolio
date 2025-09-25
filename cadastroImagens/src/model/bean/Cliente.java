package model.bean;

public class Cliente {

    private int Codigo;
    private String Nome;
    private String Data;
    private byte[] Foto; 

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public byte[] getFoto() {
        return Foto; 
    }

    public void setFoto(byte[] foto) {
        this.Foto = foto; 
    }
}
