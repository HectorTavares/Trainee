import Discord from 'discord.js'
import { dogApi } from './src/api/dogApi'
import { foodishApi } from './src/api/foodishApi'
import { animechanApi } from './src/api/animechanApi'
import { mandarCitacao } from './src/util'

const client = new Discord.Client()
const prefixo = '#'

client.on('ready', () => {
  console.log(`Logado em ${client.user.tag}!`)
})

client.on('message', async msg => {
  if (msg.content[0] !== prefixo) {
    return null
  } else {
    await comandos(msg)
  }
})

async function comandos(msg) {
  const conteudo = msg.content.toLowerCase()
  if (conteudo === prefixo.concat('help')) {
    msg.reply(
      ' ``` COMANDOS BOT HECTOR \r\n\
    #cachorro \r\n\
        Retorna uma foto aleatória de cachorro \r\n\
    #pix \r\n\
        Retorna uma mensagem aleatória de pix \r\n\
    #comida \r\n\
        Retorna uma foto aleatória de comida \r\n\
    #citacao \r\n\
        Retorna uma citação aleatória de algum personagem de anime \r\n\
    #citacao.titulo-anime \r\n\
        Retorna uma citações de personagens do anime escolhido \r\n\
            ex: #citacao.titulo-naruto \r\n\
    #citacao.personagem-nome \r\n\
        Retorna uma citações do personagem escolhido \r\n\
            ex: #citacao.personagem-mustang ```'
    )
  }

  if (conteudo === prefixo.concat('cachorro')) {
    msg.channel.send(await dogApi().pegarUmaImagemAleatoriaDeCachorro())
  }

  if (conteudo === prefixo.concat('pix')) {
    msg.reply(
      `pix para ${msg.author.username} enviado com sucesso no valor de R$ ${
        Math.floor(Math.random() * 99) + 1
      },00 `
    )
  }

  if (conteudo === prefixo.concat('comida')) {
    msg.channel.send(await foodishApi().pegarUmaImagemAleatoriaDeComida())
  }

  if (conteudo === prefixo.concat('citacao')) {
    const citacao = await animechanApi().pegarUmaCitacaoAleatoria()
    mandarCitacao(msg, citacao)
  }

  const paramentros = conteudo.split('-')

  if (paramentros[0] === prefixo.concat('citacao.titulo')) {
    const citacao = await animechanApi().pegarUmaCitacaoPeloTituloDoAnime(paramentros[1])
    mandarCitacao(msg, citacao)
  }

  if (paramentros[0] === prefixo.concat('citacao.personagem')) {
    const citacao = await animechanApi().pegarUmaCitacaoPeloNomeDoPersonagem(paramentros[1])
    mandarCitacao(msg, citacao)
  }
}

client.login('ODg2Mjg0Mjc1MzgzNjg1MjAx.YTzWjQ.gsp4PQCTmTzdbR8SvqDyFcGosxc')
