/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdi.model;

/**
 *
 * @author St0rm
 */
public class Provinsi {
    private int idProv;
    private int id_negara;
    private String nama_propinsi;

    public Provinsi() {
    }
    
    public Provinsi(int idProv, int id_negara, String nama_propinsi) {
        this.idProv = idProv;
        this.id_negara = id_negara;
        this.nama_propinsi = nama_propinsi;
    }
    
    public Integer getIdProv() {
        return idProv;
    }
    public void setIdProv(Integer idProv) {
        this.idProv = idProv;
    }
    public Integer getId_negara() {
        return id_negara;
    }
    public void setId_negara(Integer id_negara) {
        this.id_negara = id_negara;
    }
    public String getNama_propinsi() {
        return nama_propinsi;
    }
    public void setNama_propinsi(String nama_propinsi) {
        this.nama_propinsi = nama_propinsi;
    }
}
