    package com.csheet.person;

    import com.csheet.person.dtos.CreatePersonDto;
    import com.csheet.person.utils.ClassId;
import com.csheet.person.utils.RaceId;
import com.mongodb.client.MongoClient;
    import com.mongodb.client.MongoCollection;
    import com.mongodb.client.MongoCursor;
    import jakarta.enterprise.context.ApplicationScoped;
    import jakarta.inject.Inject;
    import jakarta.transaction.Transactional;
    import jakarta.ws.rs.BadRequestException;

    import org.bson.Document;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;

    @ApplicationScoped
    public class PersonService {

        private final Map<String, Integer> classHpDie = Map.ofEntries(
                Map.entry(ClassId.BARBARO, 12),
                Map.entry(ClassId.BARDO, 8),
                Map.entry(ClassId.BRUXO, 8),
                Map.entry(ClassId.CLERIGO, 8),
                Map.entry(ClassId.DRUIDA, 8),
                Map.entry(ClassId.FEITICEIRO, 6),
                Map.entry(ClassId.GUERREIRO, 10),
                Map.entry(ClassId.LADINO, 8),
                Map.entry(ClassId.MAGO, 6),
                Map.entry(ClassId.MONGE, 8),
                Map.entry(ClassId.PALADINO, 10),
                Map.entry(ClassId.PATRULEIRO, 10)

        );

        private final List<String> className = List.of(
            ClassId.BARBARO,
            ClassId.BARDO,
            ClassId.BRUXO,
            ClassId.CLERIGO,
            ClassId.DRUIDA,
            ClassId.FEITICEIRO,
            ClassId.GUERREIRO,
            ClassId.LADINO,
            ClassId.MAGO,
            ClassId.PALADINO,
            ClassId.MONGE,
            ClassId.PATRULEIRO
        );

        private final List<String> raceName = List.of(
            RaceId.ANAO,
            RaceId.ELFO,
            RaceId.HALFLING,
            RaceId.HUMANO,
            RaceId.DRACONATO,
            RaceId.GNOME,
            RaceId.MEIO_ELFO,
            RaceId.MEIO_ORC,
            RaceId.TIEFLING
        );

        @Transactional
        public String create(CreatePersonDto personRequest) {

            if(!className.contains(personRequest.classe()) || !raceName.contains(personRequest.raca())){
                throw new BadRequestException();
            }


            var person = new Person();
            person.alinhamento = personRequest.alinhamento();
            person.antecedente = personRequest.antecedente();
            person.atributosBase = personRequest.atributosBase();
            person.classe = personRequest.classe();
            person.escolhas = personRequest.escolhas();
            person.hpAtual = calcHpInicial(personRequest.atributosBase().con(), personRequest.classe());
            person.level = 1;
            person.name = personRequest.name();
            person.playerName = personRequest.playerName();
            person.raca = personRequest.raca();
            person.xp = 0;

            person.persist();

            return "localhost:8080/people/" + person.id;
        }

        private int calcBonusAtributos(int atr) {

            return Math.floorDiv(atr - 10, 2);
        }

        private int calcHpInicial(int constituicao, String classe) {
            return classHpDie.get(classe) + calcBonusAtributos(constituicao);
        }
    }
