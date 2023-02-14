package mx.com.kaizentec.erp.cadAnd.modulos.alumnos;


import mx.com.kaizentec.erp.cadAnd.DaoSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import mx.com.kaizentec.erp.cadAnd.entidades.alumnos.TCalificaciones;


public class DTCalificaciones {


    //
    //
    //  mostrar todas los registros
    public List<TCalificaciones> mostrarTodos (int alumno, int numeroRegistros, int estatus) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            String queryString = "from TCalificaciones where TAlumnos = :alumno";
            Query query = session.createQuery(queryString);
           // query.setString("nombre", '%' + nombre + '%');
            query.setInteger("alumno", alumno);
            query.setMaxResults(numeroRegistros);

            // ejecutar el query
            List<TCalificaciones> objeto = query.list();
            session.getTransaction().commit();

            return objeto;

        } catch (HibernateException e) {
            DaoSession.makeRollBack(session);
            throw new HibernateException(e.getCause());
        } finally {
            DaoSession.closeSession(session);
        }

    }

    //
    //
    //  mostrar un registro por id
    public TCalificaciones mostrarPorId (int id) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            String queryString = "from TCalificaciones where idTCalificaciones = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            List<TCalificaciones> objeto = query.list();
            session.getTransaction().commit();

            return objeto.get(0);

        } catch (HibernateException e) {
            DaoSession.makeRollBack(session);
            throw new HibernateException(e.getCause());
        } finally {
            DaoSession.closeSession(session);
        }

    }

    //
    //
    //  mostrar un registro por nombre
    public TCalificaciones mostrarPorNombre (String nombre) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            String queryString = "from Proveedor where lower(rfc) = lower(:rfc) and estatus = 1";
            Query query = session.createQuery(queryString);
            query.setString("rfc", nombre);
            List<TCalificaciones> objeto = query.list();
            session.getTransaction().commit();

            return objeto.get(0);

        } catch (HibernateException e) {
            DaoSession.makeRollBack(session);
            throw new HibernateException(e.getCause());
        } finally {
            DaoSession.closeSession(session);
        }

    }

    //
    //
    //  crear un registro
    public int crear (TCalificaciones objeto) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            int id = (Integer) session.save(objeto);
            session.getTransaction().commit();
            return id;

        } catch (HibernateException e) {
            DaoSession.makeRollBack(session);
            throw new HibernateException(e.getCause());
        } finally {
            DaoSession.closeSession(session);
        }

    }

    //
    //
    //  actualizar un registro
    public boolean actualizar (TCalificaciones objeto) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            session.update(objeto);
            session.getTransaction().commit();
            return true;

        } catch (HibernateException e) {
            DaoSession.makeRollBack(session);
            throw new HibernateException(e.getCause());
        } finally {
            DaoSession.closeSession(session);
        }

    }

    //
    //
    //  eliminar un registro (eliminacion logica)
    public boolean eliminarLogicamente (int id) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            TCalificaciones objeto = (TCalificaciones) session.load(TCalificaciones.class, id);
            //objeto.setEstatus((byte) 0);
            session.update(objeto);
            session.getTransaction().commit();
            return true;

        } catch (HibernateException e) {
            DaoSession.makeRollBack(session);
            throw new HibernateException(e.getCause());
        } finally {
            DaoSession.closeSession(session);
        }

    }

    //
    //
    //  eliminar un registro (eliminacion logica)
    public boolean eliminarPermanente (int id) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            TCalificaciones objeto = (TCalificaciones) session.load(TCalificaciones.class, id);
            session.delete(objeto);
            session.getTransaction().commit();
            return true;

        } catch (HibernateException e) {
            DaoSession.makeRollBack(session);
            throw new HibernateException(e.getCause());
        } finally {
            DaoSession.closeSession(session);
        }

    }


}
