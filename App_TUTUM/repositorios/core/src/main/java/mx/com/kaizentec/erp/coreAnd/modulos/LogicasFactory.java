package mx.com.kaizentec.erp.coreAnd.modulos;


import mx.com.kaizentec.erp.coreAnd.modulos.alumnos.LTCalificaciones;


// USO:
// LogicasFactory.getInstance().


public class LogicasFactory {


   
    public LTCalificaciones lTCalificaciones = LTCalificaciones.getInstance();

   

    //
    //
    //  PATRON SINGLETON
    //
    //
    private static LogicasFactory instanciaUnica;

    private LogicasFactory() {

    }

    private synchronized static void createInstance() {

        if (instanciaUnica == null) {
            instanciaUnica = new LogicasFactory();
        }
    }

    public static LogicasFactory getInstance() {

        createInstance();
//        System.out.println("instancia factoy::: " + instanciaUnica);
        return instanciaUnica;
    }
    //
    //
    //   FIN PATRON SINGLETON
    //
    //

}
