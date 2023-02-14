/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kaizentec.apiUtils.servlets.desarrolloIngenieria;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.kaizentec.apiUtils.daos.produccion.DaoAlumno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Erik
 */
public class imprimirFichaTecnica extends HttpServlet {

    private static final String UPLOAD_DIR = System.getProperty("user.home") + File.separator + "andanenes-images" + File.separator;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
           DaoAlumno daoAlumno = new DaoAlumno();
            ///
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
                
           
            String path = getServletContext().getRealPath("/reportes/alumnos");
            
            JasperCompileManager.compileReportToFile(path+"/TUTUMAlumno.jrxml", path+"/TUTUMAlumno.jasper");
           
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path + "/TUTUMAlumno.jasper");
            Map parameters = new HashMap<String, Object>();
           
            byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, daoAlumno.regresaConexion());
            OutputStream outStream = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setContentLength(byteStream.length);
            outStream.write(byteStream, 0, byteStream.length);
            response.setHeader("Content-Disposition", "attachment;filename=mireporte.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "cache");
//        response.setHeader("Cache-Control", "private");
            response.getOutputStream().close();
        } catch (JRException ex) {
            Logger.getLogger(imprimirFichaTecnica.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
