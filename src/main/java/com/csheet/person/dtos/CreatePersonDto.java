package com.csheet.person.dtos;

import java.util.Map;

import com.csheet.person.utils.Attributes;

public record CreatePersonDto(
    String name, 
    String antecedente, 
    String playerName, 
    String raca, 
    String classe, 
    Attributes atributosBase, 
    String alinhamento,
    Map<String, Object> escolhas
) {}
