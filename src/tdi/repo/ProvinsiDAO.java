/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdi.repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tdi.model.Provinsi;

/**
 *
 * @author St0rm
 */
@Repository
public class ProvinsiDAO {
        
    @Autowired
    private JdbcTemplate jdbctemplate;
    
    @Autowired
    private NamedParameterJdbcTemplate namedparamterjdbctemplate;
    
    public List<Provinsi> getAllProvinsi(String nama){   
    Map<String,String> param = new HashMap<>();
        param.put("paramNama", nama);
        return namedparamterjdbctemplate.query("select id_propinsi, id_negara, nm_propinsi from m_propinsi where nm_propinsi=:paramNama",//where nm_propinsi like '%'||?||'%' 
                param,(rs,rowNum)->{
                    Provinsi p = new Provinsi();
                    p.setIdProv(rs.getInt("id_propinsi"));
                    p.setId_negara(rs.getInt("id_negara"));
                    p.setNama_propinsi(rs.getString("nm_propinsi"));
                    return p;
            });
    }
    
    //harus return
//    public void hababebafu(){
//      List<Provinsi> listProv = jdbctemplate.query("select id_propinsi, id_negara, nm_propinsi from m_propinsi",
//                new Object[]{}, (rm,rowNum)->{
//                   Provinsi p = new Provinsi();
//                   p.setIdProv(rm.getInt("id_propinsi"));
//                   p.setId_negara(rm.getInt("id_negara"));
//                   p.setNama_propinsi(rm.getString("nm_propinsi"));
//                });
//    }
    
    public List<Provinsi> getAllProvinsi(){
        return jdbctemplate.query("select id_propinsi, id_negara, nm_propinsi from m_propinsi",//where nm_propinsi like '%'||?||'%' 
                new Object[]{},(rs,rowNum)->{
                    Provinsi p = new Provinsi();
                    p.setIdProv(rs.getInt("id_propinsi"));
                    p.setId_negara(rs.getInt("id_negara"));
                    p.setNama_propinsi(rs.getString("nm_propinsi"));
                    return p;
            });
    }
    @Transactional
    public void save(int id, int id_negara, String nama){
        jdbctemplate.update("insert into m_propinsi(id_propinsi, id_negara, nm_propinsi) values(?,?,?)", id,id_negara,nama);
        System.out.println("provinsi berhasil ditambahkan");
    }
    public void saveAlter(Provinsi prov){
        Map<String,Object> param = new HashMap<>();
        param.put("idprov",prov.getIdProv());
        param.put("idnegara", prov.getId_negara());
        param.put("namaprov", prov.getNama_propinsi());
        namedparamterjdbctemplate.update("insert into m_propinsi(id_propinsi,id_negara,nm_propinsi) "
                + "values(:idprov,:idnegara,:namaprov)",param);
        System.out.println("Provinsi telah dimasukkan");
    }
    public void saveExecute(Provinsi prov){
        String sql = String.format("insert into m_propinsi(id_propinsi,id_negara,nm_propinsi) values(%s,%s,'%s')",prov.getIdProv(),prov.getId_negara(),prov.getNama_propinsi());
        jdbctemplate.execute(sql);
        System.out.println("Provinsi berhasil ditambah!");
    }
    
    @Transactional
    public void delete(int id){
        jdbctemplate.update("delete from m_propinsi where id_propinsi=?", id);
        System.out.println("Provinsi berhasil dihapus");
    }
    
    
    @Transactional
    public void update(int id, int idnegara, String nama){
        String sql = "update m_propinsi set id_negara=?, nm_propinsi=? where id_propinsi=?";
        Object[] params = {idnegara,nama,id};
        int[] types = {Types.INTEGER,Types.VARCHAR,Types.INTEGER};
        int rows = jdbctemplate.update(sql, params, types);
        System.out.println(rows+" row(s) updated");
    }
    public Provinsi getProvinsiByID(int id){
        return jdbctemplate.queryForObject("select id_propinsi, id_negara, nm_propinsi from m_propinsi where id_propinsi=?", 
                new Object[]{id},(rs,rowNum)->{
                    Provinsi p = new Provinsi();
                    p.setIdProv(rs.getInt("id_propinsi"));
                    p.setId_negara(rs.getInt("id_negara"));
                    p.setNama_propinsi(rs.getString("nm_propinsi"));
                    return p;
            });
    }
    //using bean wrapper
    public Provinsi getByProvinsiByIDAlter(String nama){
        Provinsi temp = (Provinsi) jdbctemplate.queryForObject("select id_propinsi as idProv, id_negara, nm_propinsi as nama_propinsi from m_propinsi where nm_propinsi=?", new Object[]{nama}, new BeanPropertyRowMapper(Provinsi.class));
        return temp;
    }
    public int[] saveBatch(List<Provinsi> listProv){
        int[] updateCounts = jdbctemplate.batchUpdate("insert into m_propinsi(id_propinsi, id_negara, nm_propinsi) values(?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, listProv.get(i).getIdProv());
                        ps.setInt(2, listProv.get(i).getId_negara());
                        ps.setString(3, listProv.get(i).getNama_propinsi());
                    }
                    @Override
                    public int getBatchSize() {
                        return listProv.size(); //To change body of generated methods, choose Tools | Templates.
                    }
                });
        return updateCounts;
    }
}
