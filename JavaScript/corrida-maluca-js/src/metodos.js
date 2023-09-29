// escrevr aqui os metodos da corrida maluca 

export function escolherPista(listaPistas, idPista) {
    return listaPistas.find(pista => {
        return pista['id'] == idPista
    })
}

export function escolherPersonagen(listaPersonagens, idPersonagen) {
    return listaPersonagens.find(personagen => {
        return personagen['id'] == idPersonagen
    })
}

export function calcularVantagemTipoPista(pista, personagen) {

    const novoPersonagen = {
        ...personagen
    }

    if (pista['tipo'] == personagen['vantagem']) {
        novoPersonagen['velocidade'] += 2
        novoPersonagen['drift'] += 2;
        novoPersonagen['aceleracao'] += 2
    }
    return novoPersonagen
}

export function calcularDebuffDePista(pista, personagen) {

    const novoPersonagen = {
        ...personagen
    }
    const debuff = pista['debuff'] * -1

    novoPersonagen['velocidade'] -= debuff
    novoPersonagen['drift'] -= debuff
    novoPersonagen['aceleracao'] -= debuff

    return novoPersonagen
}

export function configurarPersonagen(pista, personagen) {

    const personagenCalculo1 = calcularDebuffDePista(pista, personagen)

    const personagenCalculo2 = calcularVantagemTipoPista(pista, personagenCalculo1)

    if (personagenCalculo2['velocidade'] < 0) { //colocar está parte no calcula de movimento do round
        personagenCalculo2['velocidade'] = 0
    }
    if (personagenCalculo2['drift'] < 0) {
        personagenCalculo2['drift'] = 0
    }
    if (personagenCalculo2['aceleracao'] < 0) {
        personagenCalculo2['aceleracao'] = 0
    }

    const personagenFinal = {
        ...personagenCalculo2,
        posicao: 0,
        buffPosicao: 0,
        buffsPosicaoUtilizados: 0,
        
    }


    return personagenFinal
}

export function EscolherAliado(personagen, idAliado) {

    const personagenAtualizado = {
        ...personagen,
        aliado: idAliado
    }

    return personagenAtualizado
}

export function EscolherInimigo(personagen, idInimigo) {

    const personagenAtualizado = {
        ...personagen,
        inimigo: idInimigo
    }

    return personagenAtualizado
}

export function rodada(corredores, pista, rodada,contadores) {
    //variaveis de contador para buff de posicao
    
    //varieaveis de contador para o buff de posicao
    corredores.forEach(corredor => {
        const buffPosicao = corredor['buffPosicao']
        corredor['buffPosicao'] = 0;

        let movimento = velocidade(rodada, corredor) + buffPosicao

        //calcula buff de aliado
        if (corredor['aliadoEstaNaCorrida']) {
            movimento += calculaBuffAliado(corredor, corredores)
        }

        //calcula debuff 
        if (corredor['inimigoEstaNaCorrida']) {
            movimento -= calculaDebuffInimigo(corredor, corredores)
        }

        if (movimento < 0) {
            movimento = 0
        }

        if (corredor['id'] == 1 && movimento + corredor['posicao'] >= pista['tamanho']) {
           
        } else {
            corredor['posicao'] += movimento
        }

        //console.log("Rodada : " + rodada + " /   Nome : " + corredor['nome'] + " /   Posicao : " + corredor['posicao']);

        calcularBuffPosicao(pista, corredor,contadores)

    })
}

export function Corrida(listaCorredores, pista) {

    const corredoresStep1 = []
    const contadores = [0,0,0,0]

    listaCorredores.forEach(corredor => {
        corredoresStep1.push(configurarPersonagen(pista, corredor))
    })

    const corredores = corredoresStep1.map(corredorstp => {
        return verificarSeAliadoEInimigoEstaoNaCorrida(listaCorredores, corredorstp)
    })


    let continuar = true;
    for (let round = 1; continuar; round++) {
        //Executa a rodada

        rodada(corredores, pista, round,contadores)

        //ordenando os corredores 
        corredores.sort((a, b) => {
            if (a['posicao'] < b['posicao']) {
                return 1
            }
            if (a['posicao'] > b['posicao']) {
                return -1
            }
            return 0
        });

        corredores.forEach(corredor => {
            if (corredor['posicao'] >= pista['tamanho']) {
                continuar = false;
            }
        })

    }

    
    return corredores;
}

export function velocidade(numeroVolta, corredor) {

    if (numeroVolta < 4) return corredor.aceleracao
    if (numeroVolta % 4 == 0) return corredor.drift
    if (numeroVolta > 4) return corredor.velocidade

}


export function calcularBuffPosicao(pista, personagen,contadores) {

    const posicaoBuff = pista['posicoesBuffs']
    let buff = 0;


    for (let i = 0; i < 4; i++) {
        if (personagen['buffsPosicaoUtilizados'] <= i && posicaoBuff[i] <= personagen['posicao']) {
            personagen['buffsPosicaoUtilizados'] += 1
            buff = contadores[i]
            contadores[i]++

        }

    }

    personagen['buffPosicao'] = buff;


  
}

export function verificarSeAliadoEInimigoEstaoNaCorrida(listaCorredores, corredor) {

    const corredorSetado = {
        ...corredor,
        aliadoEstaNaCorrida: false,
        inimigoEstaNaCorrida: false
    }



    const idAliado = corredor['aliado']
    const idInimigo = corredor['inimigo']

    //Verifica Se tem um aliado na corrida
    if (escolherPersonagen(listaCorredores, idAliado)) {
        corredorSetado['aliadoEstaNaCorrida'] = true;
    }

    //Verifica se tem um inimigo na corrida
    if (escolherPersonagen(listaCorredores, idInimigo)) {
        corredorSetado['inimigoEstaNaCorrida'] = true;
    }


    return corredorSetado
}

export function calculaDebuffInimigo(corredor, corredores) {

    const inimigo = corredores.find(possivelInimigo => {
        return possivelInimigo['id'] == corredor['inimigo']
    })

    const posicao = corredor['posicao']

    if (inimigo['posicao'] >= (posicao - 2) && inimigo['posicao'] <= (posicao + 2)) {
        return 1
    }

    return 0
}

export function calculaBuffAliado(corredor, corredores) {

    const aliado = corredores.find(possivelAliado => {
        return possivelAliado['id'] == corredor['aliado']
    })

    const posicao = corredor['posicao']

    if (aliado['posicao'] >= (posicao - 2) && aliado['posicao'] <= (posicao + 2)) {
        return 1
    }

    return 0
}