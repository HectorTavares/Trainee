export const SuperArray = (itens = []) => {

  const array = {
    /**
     * Propriedade para acessar os itens
     */

    itens: [...itens],
  }

  /**
   * Adicionar um novo item ao final dos items
   */

  array.push = item => {
    array.itens[array.itens.length] = item;
    return array.itens;
  }

  /**
   * Itera sobre cada um dos elementos do SuperArray enviando o item e o index
   * como segundo parametro
   */

  array.forEach = callback => {
    for (let i = 0; i < array.itens.length; i++) {
        callback(array.itens[i])
    }
  }

  /**
   * Retorna um novo SuperArray com os itens mapeados
   */

  array.map = callback => {
    let novoSuperArray = SuperArray();
    let aux;

    array.forEach(item => {
        aux = callback(item)
        novoSuperArray.push(aux)
    })
    
    return novoSuperArray.itens
  }


  /**
   * Retorna um SuperArray novo com os itens filtrados
   */

  array.filter = callback => {
    let novoSuperArray = SuperArray()

    array.forEach(item => {
      if(callback(item)){
        novoSuperArray.push(item)
      }
    })
    return novoSuperArray.itens
  }


  /**
   * Retorna o primeiro elemento do SuperArray que satisfazer o callback recebido
   * se não encontrar, deve retornar undefined
   */

  array.find = callback => {

    let retorno

    array.forEach(item=>{
      if (callback(item)) {
         retorno  = item
         return retorno
  
      }
    })

    return retorno
  }

  /**
   * Reduz o SuperArray em um único valor
   */


  array.reduce = (callback, valorInicial) => {

    let acumulador = valorInicial

    array.forEach(item=>{
      acumulador = callback(acumulador,item)
      if(!callback(acumulador,item)){
        return acumulador;
      }
    })

    return acumulador
  }

  return array
}
