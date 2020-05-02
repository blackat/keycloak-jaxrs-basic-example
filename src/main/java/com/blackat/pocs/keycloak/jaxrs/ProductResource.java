package com.blackat.pocs.keycloak.jaxrs;

import org.keycloak.representations.AccessToken;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("products")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource
{
    @Inject
    private AccessToken accessToken;

    @Context
    private HttpServletRequest request;

    private List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init()
    {
        products.add(new Product(1, "Potatos", "The farmer"));
        products.add(new Product(2, "Tomatos", "The farmer"));
    }

    @GET
    public List<Product> products()
    {
        return products;
    }
}
