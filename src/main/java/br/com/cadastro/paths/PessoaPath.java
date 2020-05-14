package br.com.cadastro.paths;

import br.com.cadastro.exception.custom.ObjectNotFoundException;
import br.com.cadastro.persistence.model.Pessoa;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.util.Arrays.asList;

@Path("/pessoa")
public class PessoaPath {
    private List<Pessoa> pessoas;
    {
        pessoas = asList(new Pessoa("Igor"), new Pessoa("Rute"), new Pessoa("Hebert"));
    }
    @GET
    @Path("oi")
    @Produces("application/json")
    public Response oi () {
//        return Response.ok(new Gson().toJson("funcionou, aleluia")).build();
        return Response.status(Response.Status.OK).entity(Thread.currentThread().getName()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Path("listar")
    @Produces("application/json")
    public Response listar() {
        return Response.ok(new Gson().toJson(pessoas)).build();
    }

    @GET
    @Path("buscar/{nome}")
    @Produces("application/json")
    public Response buscar(@PathParam("nome") String nome) throws ObjectNotFoundException {
        int index = pessoas.indexOf(new Pessoa(nome));
        if (index < 0)
            throw new ObjectNotFoundException();
        return Response.ok(new Gson().toJson(pessoas.get(index))).build();
    }

    @POST
    @Path("salvar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response salvar(Pessoa pessoa){
        System.out.println(pessoa.getNome());
        return  Response.ok(new Gson().toJson(pessoa)).build();
    }
}