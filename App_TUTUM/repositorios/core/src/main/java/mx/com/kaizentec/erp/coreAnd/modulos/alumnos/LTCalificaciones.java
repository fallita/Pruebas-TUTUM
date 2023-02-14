package mx.com.kaizentec.erp.coreAnd.modulos.alumnos;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import mx.com.kaizentec.erp.coreAnd.entidades.administracionConfiguracion.CustomError;
import org.hibernate.HibernateException;

import java.util.List;
import mx.com.kaizentec.erp.cadAnd.entidades.alumnos.TAlumnos;
import mx.com.kaizentec.erp.cadAnd.entidades.alumnos.TCalificaciones;
import mx.com.kaizentec.erp.cadAnd.entidades.alumnos.TMaterias;
import mx.com.kaizentec.erp.cadAnd.modulos.alumnos.DTCalificaciones;
import mx.com.kaizentec.erp.coreAnd.entidades.alumnos.TCalificacionesDTB;
import static org.hibernate.internal.util.collections.CollectionHelper.arrayList;


public class LTCalificaciones {


    //  daos necesarios
    public final DTCalificaciones Dao = new DTCalificaciones();

    //
    //
    //  metodos
    //
    //

    /**
     * Este metodo se encarga de mostrar todos los registros
     * <br/><br/>
     * REGLAS DE LÓGICA:
     * <p>
     * - se debe mostrar solo los registros con el estatus indicado
     *
     * @param nombre          recibe el nombre del registro a cosultar
     * @param numeroRegistros recibe el numero de registros a mostrar
     * @param estatus         recibe el tipo de estatus para validar
     * @return Listado de objetos (si se completo con éxito la operacion) | CustomError (si es que ocurrió un error)
     */
    public Object mostrarTodos(int alumno, int numeroRegistros, int estatus) {

        try {

            List<TCalificaciones> consulta = this.Dao.mostrarTodos(alumno, numeroRegistros, estatus);
            List<TCalificacionesDTB> listCalificaciones = new ArrayList<TCalificacionesDTB>();
            DateFormat formateadorFechaCorta = DateFormat.getDateInstance(DateFormat.SHORT);

            
            for(TCalificaciones c : consulta){
                TCalificacionesDTB calificaciones = new TCalificacionesDTB();
                calificaciones.setIdTCalificaciones(c.getIdTCalificaciones());
                calificaciones.setCalificacion(c.getCalificacion());
                calificaciones.setFechaRegistro(formateadorFechaCorta.format(c.getFechaRegistro()));
                
                System.out.println(formateadorFechaCorta.format(c.getFechaRegistro()));
                
                calificaciones.setAlumnoId(c.getTAlumnos().getIdTUsuarios());
                calificaciones.setAlumnoNombre(c.getTAlumnos().getNombre());
                calificaciones.setMateriaNombre(c.getTMaterias().getNombre());
                calificaciones.setCalificacion(c.getCalificacion());
                calificaciones.setMateriaId(c.getTMaterias().getIdTMaterias());
                listCalificaciones.add(calificaciones);
            }
            return listCalificaciones;

        } catch (HibernateException e) {
            System.err.println("ERROR MANEJADO LOGICA: ERROR EN HIBERNATE");
            CustomError errorResponse = new CustomError("409", "hb-005", "Error en Hibernate");
            errorResponse.setMensaje("Ocurrió un error al realizar la operacion en BD, contacta a soporte");
            return errorResponse;
        } catch (Exception e) {
            System.err.println("ERROR MANEJADO LOGICA: " + e.getMessage());
            CustomError errorResponse = new CustomError("409", "log-009", "Conflicto");
            errorResponse.setMensaje("Ocurrió un error en el servidor, contacta a soporte");
            return errorResponse;
        }

    }

    /**
     * Este metodo se encarga de mostrar un solo registro por id
     * <br/><br/>
     * REGLAS DE LÓGICA:
     * <p>
     * - no tiene reglas de logica
     *
     * @param id recibe el id del registro a consultar
     * @return Un objeto (si se completo con éxito la operacion) | CustomError (si es que ocurrió un error)
     */
    public Object mostrarPorId(int id) {

        try {

            DateFormat formateadorFechaCorta = DateFormat.getDateInstance(DateFormat.SHORT);
            
            TCalificaciones c = this.Dao.mostrarPorId(id);
            TCalificacionesDTB calificaciones = new TCalificacionesDTB();
            calificaciones.setIdTCalificaciones(c.getIdTCalificaciones());
            calificaciones.setCalificacion(c.getCalificacion());
            calificaciones.setFechaRegistro(formateadorFechaCorta.format(c.getFechaRegistro()));
            calificaciones.setAlumnoId(c.getTAlumnos().getIdTUsuarios());
            calificaciones.setAlumnoNombre(c.getTAlumnos().getNombre());
            calificaciones.setMateriaNombre(c.getTMaterias().getNombre());
            calificaciones.setCalificacion(c.getCalificacion());
            calificaciones.setMateriaId(c.getTMaterias().getIdTMaterias());
            return calificaciones;

        } catch (HibernateException e) {
            System.err.println("ERROR MANEJADO LOGICA: ERROR EN HIBERNATE");
            CustomError errorResponse = new CustomError("409", "hb-005", "Error en Hibernate");
            errorResponse.setMensaje("Ocurrió un error al realizar la operacion en BD, contacta a soporte");
            return errorResponse;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("ERROR MANEJADO LOGICA: ERROR EN HIBERNATE: NO SE ENCONTRO EL REGISTRO");
            CustomError errorResponse = new CustomError("409", "log-010", "No se encontro un registro con los datos proporcionados");
            errorResponse.setMensaje("Ocurrió un error al realizar la operacion en BD, contacta a soporte");
            return errorResponse;
        } catch (Exception e) {
            System.err.println("ERROR MANEJADO LOGICA: ");
            e.printStackTrace();
            CustomError errorResponse = new CustomError("409", "log-009", "Conflicto");
            errorResponse.setMensaje("Ocurrió un error en el servidor, contacta a soporte");
            return errorResponse;

        }

    }

    

    /**
     * Este metodo se encarga de crear un registro
     * <br/><br/>
     * REGLAS DE LÓGICA:
     * <p>
     * - no tiene reglas de logica
     *
     * @param objeto recibe el un objeto del tipo de esta clase
     * @return Un objeto con la creacion (si se completo con éxito la operacion) | CustomError (si es que ocurrió un error)
     */
    public Object crear(TCalificacionesDTB objeto) {

        try {

            Date date=new Date();
            TCalificaciones cal = new TCalificaciones();
            TAlumnos alumno = new TAlumnos();
            TMaterias materia = new TMaterias(); 
            alumno.setIdTUsuarios(objeto.alumnoId);
            materia.setIdTMaterias(objeto.materiaId);
            cal.setCalificacion(objeto.getCalificacion());
            cal.setFechaRegistro(date);
            cal.setTAlumnos(alumno);
            cal.setTMaterias(materia);
            
            int id = this.Dao.crear(cal);

            if (id > 0)
                return this.mostrarPorId(id);
            else
                return null;


        } catch (HibernateException e) {
            System.err.println("ERROR MANEJADO LOGICA: ERROR EN HIBERNATE");
            CustomError errorResponse = new CustomError("409", "hb-005", "Error en Hibernate");
            errorResponse.setMensaje("Ocurrió un error al realizar la operacion en BD, contacta a soporte");
            return errorResponse;
        } catch (Exception e) {
            System.err.println("ERROR MANEJADO LOGICA: " + e.getMessage());
            CustomError errorResponse = new CustomError("409", "log-009", "Conflicto");
            errorResponse.setMensaje("Ocurrió un error en el servidor, contacta a soporte");
            return errorResponse;

        }

    }

    /**
     * Este metodo se encarga de actualizar un registro
     * <br/><br/>
     * REGLAS DE LÓGICA:
     * <p>
     * - verificar que exista el registro
     *
     * @param objeto recibe el un objeto del tipo de esta clase
     * @return Un objeto con la actualizacion (si se completo con éxito la operacion) | CustomError (si es que ocurrió un error)
     */
    public Object actualizar(TCalificacionesDTB objeto) {

        try {

            // llamar metodo de esta clase por que ahi ya se maneja el error si no existe el registro
            Object consultaRegistroExiste = this.mostrarPorId(objeto.getIdTCalificaciones());
            TCalificaciones cal = new TCalificaciones();
            TCalificacionesDTB objetoExistente;

            try {

                objetoExistente = (TCalificacionesDTB) consultaRegistroExiste;
                
                Date date=new Date();
            TAlumnos alumno = new TAlumnos();
            TMaterias materia = new TMaterias(); 
            alumno.setIdTUsuarios(objeto.alumnoId);
            materia.setIdTMaterias(objeto.materiaId);
            cal.setIdTCalificaciones(objeto.idTCalificaciones);
            cal.setCalificacion(objeto.getCalificacion());
            cal.setFechaRegistro(date);
            cal.setTAlumnos(alumno);
            cal.setTMaterias(materia);

            } catch (Exception e) {
                return (CustomError) consultaRegistroExiste;
            }
            
            

            if (this.Dao.actualizar(cal))
                return (TCalificacionesDTB) this.mostrarPorId(objeto.getIdTCalificaciones());
            else
                throw new HibernateException("Eror al realizar la operación");


        } catch (HibernateException e) {
            System.err.println("ERROR MANEJADO LOGICA: ERROR EN HIBERNATE");
            CustomError errorResponse = new CustomError("409", "hb-005", "Error en Hibernate");
            errorResponse.setMensaje("Ocurrió un error al realizar la operacion en BD, contacta a soporte");
            return errorResponse;
        } catch (Exception e) {
            System.err.println("ERROR MANEJADO LOGICA: " + e.getMessage());
            CustomError errorResponse = new CustomError("409", "log-009", "Conflicto");
            errorResponse.setMensaje("Ocurrió un error en el servidor, contacta a soporte");
            return errorResponse;

        }

    }

    /**
     * Este metodo se encarga de eliminar un registro ya sea logicamente o permanente
     * <br/><br/>
     * REGLAS DE LÓGICA:
     * <p>
     * - verificar que sea valido el parametro de eliminacion
     * -verificar que exista el registro
     *
     * @param id              recibe el id del objeto a eliminar
     * @param tipoEliminacion recibe un tipo de eliminacion "logica" | "permanente"
     * @return Un valor true (si se completo con éxito la operacion) | CustomError (si es que ocurrió un error)
     */
    public Object eliminar(int id) {

        try {

                // llamar metodo de esta clase por que ahi ya se maneja el error si no existe el registro
                Object consultaRegistroExiste = this.mostrarPorId(id);
                TCalificacionesDTB objetoExistente;

                try {

                    objetoExistente = (TCalificacionesDTB) consultaRegistroExiste;

                } catch (Exception e) {
                    return (CustomError) consultaRegistroExiste;
                }

               
                    return this.Dao.eliminarPermanente(objetoExistente.getIdTCalificaciones());




        } catch (HibernateException e) {
            System.err.println("ERROR MANEJADO LOGICA: ERROR EN HIBERNATE");
            CustomError errorResponse = new CustomError("409", "hb-005", "Error en Hibernate");
            errorResponse.setMensaje("Ocurrió un error al realizar la operacion en BD, contacta a soporte");
            return errorResponse;
        } catch (Exception e) {
            System.err.println("ERROR MANEJADO LOGICA: " + e.getMessage());
            CustomError errorResponse = new CustomError("409", "log-009", "Conflicto");
            errorResponse.setMensaje("Ocurrió un error en el servidor, contacta a soporte");
            return errorResponse;

        }

    }


    //
    //
    //  PATRON SINGLETON
    //
    //
    private static LTCalificaciones instanciaUnica;

    private LTCalificaciones() {

    }

    private synchronized static void createInstance() {

        if (instanciaUnica == null) {
            instanciaUnica = new LTCalificaciones();
        }
    }

    public static LTCalificaciones getInstance() {

        createInstance();
        return instanciaUnica;
    }

    //
    //
    //   FIN PATRON SINGLETON
    //
    //


}
