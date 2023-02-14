/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kaizentec.apiUtils.daos.produccion;

import mx.com.kaizentec.apiUtils.utilerias.Conexion;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DaoAlumno {
    Conexion con=new Conexion();
    private ResultSet rs = null;

    String id;
    public DaoAlumno() {
        
    }
        
    public Connection regresaConexion()
    {
        con.conexion();
        return con.getCon();
    }
}
