/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kaizentec.erp.apiAnd.app;


import mx.com.kaizentec.erp.apiAnd.app.filters.CORSFilter;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletException;
import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;


/**
 * @author joel_barron
 */
public class Main {


    public static void main(String[] args) throws Exception, LifecycleException {
        new Main().start();


    }

    public void start() throws ServletException, LifecycleException, MalformedURLException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // solucion 413
        Connector c = tomcat.getConnector();
        c.setMaxPostSize(999999990);
        c.setMaxHeaderCount(999999990);
        c.setMaxSavePostSize(999999990);
        c.setAsyncTimeout(20000);
        tomcat.setConnector(c);

        // config extra
        LocalDate localDate = LocalDate.now();
//        LocalTime localTime = LocalTime.now();
//
//        LocalDateTime fechaHora = LocalDateTime.of(localDate, localTime);
//
//        System.out.println(fechaHora);
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
        // fin config extra

        Context context = tomcat.addWebapp("/", new File(".").getAbsolutePath());

        Tomcat.addServlet(context, "jersey-container-servlet", resourceConfig());
        context.addServletMapping("/*", "jersey-container-servlet");

        tomcat.start();
        tomcat.getServer().await();
    }

    private ServletContainer resourceConfig() {

        App app = new App();
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        app.register(new CORSFilter());
        return new ServletContainer(app);
    }
}
