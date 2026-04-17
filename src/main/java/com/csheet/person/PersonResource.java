package com.csheet.person;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import com.csheet.person.dtos.CreatePersonDto;

@Path("/people")
public class PersonResource {

    @Inject PersonService personService;

    @GET
    @Path("/{id}")
    public Person getPerson(@RestPath String id) {
        try {
            Person person = Person.findById(new ObjectId(id));

            if (person == null) {
                throw new NotFoundException();
            }

            return person;

        } catch (IllegalArgumentException e) {
            throw new NotFoundException();
        }
    }

    @POST
    @Path("")
    @ResponseStatus(201)
    public String create(CreatePersonDto personRequest) {
        
        return personService.create(personRequest);
    }

}
