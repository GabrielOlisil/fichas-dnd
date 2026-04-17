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
    int hpAtual, 
    String alinhamento,
    int xp, 
    Map<String, Object> escolhas
) {}
