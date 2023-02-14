package mx.com.kaizentec.erp.apiAnd.app;


import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


/**
 * @author joel_barron
 */
@ApplicationPath("/v3")
public class App extends ResourceConfig {


    public App() {
        packages("mx.com.kaizentec.erpApi.rest");
    }


}
