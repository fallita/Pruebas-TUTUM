/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kaizentec.erp.coreAnd.entidades.administracionConfiguracion;


/**
 * @author joel_barron
 */
public class CustomError {


    private String status;  // status code http 
    private String code;    // cofigo de error kaizentec
    private String error;   // error ej Error en inserccion
    private String mensaje; // developer Message ej. intenta verificar los formatps de los parametros
    private String link = "https://soporte.kaizentec.com.mx";    // link de soporte kaizentec

    public CustomError() {



    }

    public CustomError(String status, String code) {

        this.status = status;
        this.code = code;
    }

    public CustomError(String status, String code, String error) {

        this.status = status;
        this.code = code;
        this.error = error;
    }

    public CustomError(String status, String code, String error, String mensaje, String link) {

        this.status = status;
        this.code = code;
        this.error = error;
        this.mensaje = mensaje;
        this.link = link;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getError() {

        return error;
    }

    public void setError(String error) {

        this.error = error;
    }

    public String getMensaje() {

        return mensaje;
    }

    public void setMensaje(String mensaje) {

        this.mensaje = mensaje;
    }

    public String getLink() {

        return link;
    }

    public void setLink(String link) {

        this.link = link;
    }


}
