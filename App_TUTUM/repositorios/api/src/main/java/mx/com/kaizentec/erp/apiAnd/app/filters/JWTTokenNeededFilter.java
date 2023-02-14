package mx.com.kaizentec.erp.apiAnd.app.filters;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import mx.com.kaizentec.erp.coreAnd.entidades.administracionConfiguracion.CustomError;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {


    private final String SECRET = "Bga7FdRhQVSi5k_oWVrwPzJfmsmxgqz98feWbuLzELkRCOd";
    private final String ISSUER = "KaizentecMX";

    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        //System.out.println("#### authorizationHeader : " + authorizationHeader);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            System.out.println("#### invalid authorizationHeader : " + authorizationHeader);
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Validate the token
            //Jwts.parser().setSigningKey(key).parseClaimsJws(token);

            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

//            System.out.println("#### secret : " + SECRET);
//            System.out.println("#### isuer : " + ISSUER);
//            System.out.println("#### valid token : " + token);

        } catch (Exception e) {
            System.out.println("#### invalid token : " + token);

            System.err.println("ERROR DE PERMISOS: " + e.getMessage());
            CustomError errorResponse = new CustomError("401", "auth-001", "Usuario no autenticado");
            errorResponse.setMensaje("Usted no se encuentra autenticado, verifiue con soporte.");

            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(errorResponse).build());
        }
    }


}