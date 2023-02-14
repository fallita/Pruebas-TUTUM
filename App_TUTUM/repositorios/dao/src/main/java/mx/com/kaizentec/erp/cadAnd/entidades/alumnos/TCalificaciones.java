package mx.com.kaizentec.erp.cadAnd.entidades.alumnos;
// Generated 10/02/2023 01:06:03 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * TCalificaciones generated by hbm2java
 */
public class TCalificaciones  implements java.io.Serializable {


     private Integer idTCalificaciones;
     private TAlumnos TAlumnos;
     private TMaterias TMaterias;
     private BigDecimal calificacion;
     private Date fechaRegistro;

    public TCalificaciones() {
    }

	
    public TCalificaciones(TAlumnos TAlumnos, TMaterias TMaterias) {
        this.TAlumnos = TAlumnos;
        this.TMaterias = TMaterias;
    }
    public TCalificaciones(TAlumnos TAlumnos, TMaterias TMaterias, BigDecimal calificacion, Date fechaRegistro) {
       this.TAlumnos = TAlumnos;
       this.TMaterias = TMaterias;
       this.calificacion = calificacion;
       this.fechaRegistro = fechaRegistro;
    }
   
    public Integer getIdTCalificaciones() {
        return this.idTCalificaciones;
    }
    
    public void setIdTCalificaciones(Integer idTCalificaciones) {
        this.idTCalificaciones = idTCalificaciones;
    }
    public TAlumnos getTAlumnos() {
        return this.TAlumnos;
    }
    
    public void setTAlumnos(TAlumnos TAlumnos) {
        this.TAlumnos = TAlumnos;
    }
    public TMaterias getTMaterias() {
        return this.TMaterias;
    }
    
    public void setTMaterias(TMaterias TMaterias) {
        this.TMaterias = TMaterias;
    }
    public BigDecimal getCalificacion() {
        return this.calificacion;
    }
    
    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }




}


