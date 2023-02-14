package mx.com.kaizentec.erp.cadAnd.modulos.alumnos;


import mx.com.kaizentec.erp.cadAnd.DaoSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import mx.com.kaizentec.erp.cadAnd.entidades.alumnos.TAlumnos;


public class DTAlumnos {


    //
    //
    //  mostrar todas los registros
    public List<TAlumnos> mostrarTodos (String nombre, int numeroRegistros, int estatus) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            String queryString = "from Proveedor where nombre like :nombre and  estatus = :status";
            Query query = session.createQuery(queryString);
            query.setString("nombre", '%' + nombre + '%');
            query.setInteger("status", estatus);
            query.setMaxResults(numeroRegistros);

            // ejecutar el query
            List<TAlumnos> objeto = query.list();
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
    public TAlumnos mostrarPorId (int id) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            String queryString = "from Proveedor where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            List<TAlumnos> objeto = query.list();
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
    public TAlumnos mostrarPorNombre (String nombre) throws Exception {

        Session session = null;

        try {

            // iniciar la session y transacion
            session = DaoSession.getSession();

            // armar el query
            String queryString = "from Proveedor where lower(rfc) = lower(:rfc) and estatus = 1";
            Query query = session.createQuery(queryString);
            query.setString("rfc", nombre);
            List<TAlumnos> objeto = query.list();
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
    public int crear (TAlumnos objeto) throws Exception {

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
    public boolean actualizar (TAlumnos objeto) throws Exception {

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
            TAlumnos objeto = (TAlumnos) session.load(TAlumnos.class, id);
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
            TAlumnos objeto = (TAlumnos) session.load(TAlumnos.class, id);
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
