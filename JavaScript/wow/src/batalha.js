import { subirNivel } from '../src/personagem';

export const calcularODanoRecebido = (
    danoDoPersonagemAtacante,
    vigorDoPersonagemDefensivo,
) => {
    const danoCausado = danoDoPersonagemAtacante - vigorDoPersonagemDefensivo;
    if (danoCausado > 0) {
        return danoCausado;
    }
    return 0;
};

export const receberDano = (atacante, personagem) => {
    if (atacante.vida > 0) {
        const danoAtacante = atacante.dano;
        const vigorpersonagem = personagem.vigor;

        const danoRecebido = calcularODanoRecebido(danoAtacante, vigorpersonagem);
        const personagemAposReceberDano = {
            ...personagem,
            vida: personagem.vida - danoRecebido,
        };

        return personagemAposReceberDano;
    }

    return { ...personagem };
};

export const definirOsTurnos = (ordemDosTurnos) => {
    if (ordemDosTurnos <= 0 || ordemDosTurnos > 2) {
        return Math.floor(Math.random() * (2 - 1 + 1) + 1);
    }
    return ordemDosTurnos;
};

export const definirQuemAtacaPrimeiro = (
    personagemA,
    personagemB,
    ordemDosTurnos,
) => {
    if (ordemDosTurnos == 1) {
        return personagemA;
    }
    return personagemB;
};

export const executarLuta = (personagens, quemAtacaPrimeiro) => {
    const primeiroAtacar = personagens.find((personagem) => {
        return personagem === quemAtacaPrimeiro;
    });

    const segundoAtacar = personagens.find((personagem) => {
        return personagem !== quemAtacaPrimeiro;
    });

    return [
        receberDano(primeiroAtacar, segundoAtacar),
        receberDano(segundoAtacar, primeiroAtacar),
    ];
};

export const subirNivelVencedor = (vencedor) => {
    const vencedorLevelUp = subirNivel(vencedor,1);
    return vencedorLevelUp;
}


export const batalha = (personagemA, personagemB, personagensEstadoOriginal = [personagemA,personagemB] ,ordemDosTurnos = 0) => {
    verificarSeVigorDoPersonagensEhMaisAltoQueODano(personagemA, personagemB);

    const novaOrdemDosTurnos = definirOsTurnos(ordemDosTurnos);
    const quemAtacaPrimeiro = definirQuemAtacaPrimeiro(
        personagemA,
        personagemB,
        novaOrdemDosTurnos,
    );

    const personagensStatusAlterados = executarLuta(
        [personagemA, personagemB],
        quemAtacaPrimeiro,
    );


    const derrotado = personagensStatusAlterados.find((personagem) => {
        return personagem.vida <= 0;
    });

    if (!derrotado) {
        const novoPersonagemA = personagensStatusAlterados.find((personagem) => {
            return personagemA.nome === personagem.nome;
        });

        const novoPersonagemB = personagensStatusAlterados.find((personagem) => {
            return personagemB.nome === personagem.nome;
        });


        return batalha(novoPersonagemA, novoPersonagemB,personagensEstadoOriginal ,novaOrdemDosTurnos);
    }

    const vencedor = personagensEstadoOriginal.find((personagem) => {
        return personagem.nome !== derrotado.nome;
    });


    return subirNivelVencedor(vencedor);
};

export const verificarSeVigorDoPersonagensEhMaisAltoQueODano = (
    personagemA,
    personagemB,
) => {
    const testePersonagemA = personagemA.dano < personagemB.vigor || personagemA.dano == personagemB.vigor;
    const testePersonagemB = personagemB.dano < personagemA.vigor || personagemB.dano == personagemA.vigor;

    if (testePersonagemA && testePersonagemB) {
        throw new Error('Empate, nenhum personagem consegue dar dano!');
    }

    return false;
};
