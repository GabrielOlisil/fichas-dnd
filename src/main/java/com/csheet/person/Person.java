package com.csheet.person;


import java.util.Map;

import org.bson.types.ObjectId;

import com.csheet.person.utils.Attributes;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="person")
public class Person extends PanacheMongoEntity {
    public String name;
    public String antecedente;
    public String playerName;
    public int level;

    public String raca;

    public String classe;


    public Attributes atributosBase;
    
    public int hpAtual;

    public String alinhamento;
    public int xp;

    public Map<String, Object> escolhas;

 
    
    

    public static Person findPerson(String id){
        return findById(new ObjectId(id));
    }


}

