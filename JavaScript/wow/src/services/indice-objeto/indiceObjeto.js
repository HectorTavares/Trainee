const indiceObjeto = (objetoProcurado,array = [],indice = 0) => {
    const stringArrayObjeto = JSON.stringify(array[indice]);
    const stringObjetoProcurado = JSON.stringify(objetoProcurado);
      
    if(stringArrayObjeto == stringObjetoProcurado){
        return indice;
    }

    if(indice > array.length && array[indice] === undefined){
        return -1;
    }
    


    return indiceObjeto(objetoProcurado,array, indice + 1);
}

export default indiceObjeto;