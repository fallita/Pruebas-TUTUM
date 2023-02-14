package mx.com.kaizentec.erp.cadAnd;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public abstract class DaoSession {


    //XML based configuration
    private static SessionFactory sessionFactory;

    static {
        try {
            buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Exception while initializing hibernate util...");
            e.printStackTrace();
        }
    }

    private static void buildSessionFactory() {

        System.out.println("-----------------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            System.out.println("¡SE CARGO LA CONFIGURACINO DE HIBERNATE XML CORRECTAMENTE!");



            System.out.println("TIMEOUT:: " + configuration.getProperty("hibernate.c3p0.timeout"));

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("SE CREO EL HIBERNATE SERVICE REGISTRY");

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            ex.printStackTrace();
            System.err.println("ERROR MANEJADO: ERROR AL INICIALIZAR EL FACTORY SESSION.");
        }

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("-----------------------------------");
    }

    public static Session getSession() throws HibernateException {

        if (sessionFactory.isClosed()) {
            System.err.println("EL SESSION FACTORY ESTABA CERRADO, SE INTENTARA ABRIR NUEVAMENTE");
            buildSessionFactory();
        }

        Session ses = sessionFactory.openSession();
        ses.beginTransaction();
        return ses;
    }

//    public static Session getSession() {
//
//        if (sessionFactory == null) {
//            synchronized (DaoSession.class) {
//                if (sessionFactory == null) {
////                    sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//                    buildSessionFactory();
//
//                    Session ses = sessionFactory.openSession();
//                    ses.beginTransaction();
//                    return ses;
//
//                }else {
//                    return null;
//                }
//            }
//        }else {
//            return null;
//        }
//    }
//
//    private static void buildSessionFactory() {
//
//        System.out.println("-----------------------------------");
//        System.out.println("");
//        System.out.println("");
//        System.out.println("");
//
//        try {
//            // Create the SessionFactory from hibernate.cfg.xml
//            AnnotationConfiguration configuration = new AnnotationConfiguration();
//            configuration.configure("/hibernate-andanenes.cfg.xml");
//            System.out.println("¡SE CARGO LA CONFIGURACINO DE HIBERNATE XML CORRECTAMENTE!");
//
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//            System.out.println("SE CREO EL HIBERNATE SERVICE REGISTRY");
//
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        } catch (Throwable ex) {
//            System.err.println("ERROR MANEJADO: ERROR AL INICIALIZAR EL FACTORY SESSION.");
//        }
//
//        System.out.println("");
//        System.out.println("");
//        System.out.println("");
//        System.out.println("-----------------------------------");
//    }

    public static void makeRollBack(Session session) {

        if ((session != null) && (session.isOpen())) {
            session.getTransaction().rollback();
        }
    }

    public static void closeSession(Session session) {

        if ((session != null) && (session.isOpen())) {
            session.close();
        }
    }


}