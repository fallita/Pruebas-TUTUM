package mx.com.kaizentec.erp.apiAnd.rest.alumnos;


import mx.com.kaizentec.erp.apiAnd.app.filters.JWTTokenNeeded;
import mx.com.kaizentec.erp.coreAnd.entidades.administracionConfiguracion.CustomError;
import mx.com.kaizentec.erp.coreAnd.modulos.LogicasFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.transaction.Transactional;
import mx.com.kaizentec.erp.cadAnd.entidades.alumnos.TCalificaciones;
import mx.com.kaizentec.erp.coreAnd.entidades.alumnos.TCalificacionesDTB;


@Path("calificaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@JWTTokenNeeded
@Transactional
public class RTCalificaciones {


    //
    //
    //  endpoint para mostrar todos los registros
    //
    @GET
    public Response mostrarTodos(@QueryParam("alumno") int alumno, @QueryParam("rows") int numeroRegistros) {

        // llamar al metodo de la logica
        Object response = LogicasFactory.getInstance().lTCalificaciones.mostrarTodos(alumno, numeroRegistros, 1);

        if (response instanceof CustomError) {

            // si es un erro retornarlo
            return Response.status(Response.Status.CONFLICT).entity((CustomError) response).build();

        } else {

            List<TCalificacionesDTB> respuesta = (List<TCalificacionesDTB>) response;
            return Response.status(Response.Status.OK).entity(respuesta).build();

        }

    }

   
    //
    //
    //  endpoint para crear un registro
    //
    @POST
    public Response crear(TCalificacionesDTB objeto) {

        // llamar al metodo de la logica
        Object response = LogicasFactory.getInstance().lTCalificaciones.crear(objeto);

        if (response instanceof CustomError) {

            // si es un erro retornarlo
            return Response.status(Response.Status.CONFLICT).entity((CustomError) response).build();

        } else {

            TCalificacionesDTB respuesta = (TCalificacionesDTB) response;
            return Response.status(Response.Status.OK).entity(respuesta).build();

        }

    }

    //
    //
    //  endpoint para actualizar un registro
    //
    @PUT
    public Response actualizar(TCalificacionesDTB objeto) {

        // llamar al metodo de la logica
        Object response = LogicasFactory.getInstance().lTCalificaciones.actualizar(objeto);

        if (response instanceof CustomError) {

            // si es un erro retornarlo
            return Response.status(Response.Status.CONFLICT).entity((CustomError) response).build();

        } else {

            TCalificacionesDTB respuesta = (TCalificacionesDTB) response;
            return Response.status(Response.Status.OK).entity(respuesta).build();

        }

    }

    //
    //
    //  endpoint para eliminar un registro
    //
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") Integer id) {

        Object response = LogicasFactory.getInstance().lTCalificaciones.eliminar(id);

        if (response instanceof CustomError) {

            // si es un erro retornarlo
            return Response.status(Response.Status.CONFLICT).entity((CustomError) response).build();

        } else {

            boolean respuesta = (boolean) response;
            return Response.status(Response.Status.OK).entity(respuesta).build();

        }

    }


}
