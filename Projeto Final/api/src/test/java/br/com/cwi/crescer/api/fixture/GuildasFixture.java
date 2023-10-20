package br.com.cwi.crescer.api.fixture;

import br.com.cwi.crescer.api.representation.guilda.GuildasResponse;

import java.util.ArrayList;
import java.util.List;

public class GuildasFixture {

    private static final String CRESCER_ID = "840227367531184128";

    public static List<GuildasResponse> guildasFixture(){

        List<GuildasResponse> guildas = new ArrayList<>();

        GuildasResponse guildaResponse = new GuildasResponse();
        guildaResponse.setId(CRESCER_ID);

        guildas.add(guildaResponse);
        return guildas;
    }

}
