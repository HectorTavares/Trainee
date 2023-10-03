export function mandarCitacao(msg, citacao) {
  msg.channel.send(
    `**Anime** :  ${citacao.anime}\r\n**Personagem** :  ${citacao.character} \r\n**Citação** : \
${citacao.quote} \r\n \r\n`
  )
}
