/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kaizentec.apiUtils.utilerias;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erik
 */
public class Conexion {
    private MysqlDataSource mysqlDS = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection con=null; 
    
    public Conexion (){}

    private MysqlDataSource getMysqlDS() {
        return mysqlDS;
    }

    private void setMysqlDS(MysqlDataSource mysqlDS) {
        this.mysqlDS = mysqlDS;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    
    
    public void conexion()
    {
            mysqlDS = new MysqlDataSource();
            
            mysqlDS.setURL("jdbc:mysql://localhost:3306/escuela");
            mysqlDS.setUser("root");
            mysqlDS.setPassword("root");
            
            
        try {
            setCon( mysqlDS.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void desconexionConnection()
    {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
    }
    
    private void desconexionStatement()
    {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
    }
    
    private void desconexionResultSet()
    {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
    }
    
    public void cerrarConexion()
    {
    try {
                    
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }
    
    public ResultSet ejecutaConsulta(String CExisteProgramacion)
    {
            
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(CExisteProgramacion);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ResultSet registraRegrasaId(PreparedStatement preparedStatement)
    {
            
        try {
            
// execute insert SQL stetement
preparedStatement .executeUpdate();
ResultSet result = preparedStatement.getGeneratedKeys();
return result;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }
    
    public boolean ejecutaModificacionFacturacion(PreparedStatement preparedStatement)
    {
            
        try {
            
// execute insert SQL stetement
int cuentaMondificaciones=preparedStatement.executeUpdate();

if(cuentaMondificaciones>0)
return true;
else
    return false;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return false;
    }
    
    public void registra(PreparedStatement preparedStatement)
    {
            
        try {
            
// execute insert SQL stetement
preparedStatement .executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void eliminar(PreparedStatement preparedStatement)
    {
            
        try {
            
// execute insert SQL stetement
preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
