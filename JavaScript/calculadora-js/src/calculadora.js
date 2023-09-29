export const OPERACAO_INVALIDA = 'OPERACAO_INVALIDA'

export const calculadora = (operacao, valores) => {
 
  let resultado;

  switch (operacao) {
    case 'soma':
      for (let i = 0; i < valores.length; i++) {
        if (i==0) {
          resultado=valores[i];
        }else{
        resultado += valores[i];
        }
      }
      break;
    case 'subtracao':
      for (let i = 0; i < valores.length; i++) {
        if (i==0) {
          resultado=valores[i];
        }else{
          resultado -= valores[i];
        }

      }
      break;
    case 'multiplicacao':
      for (let i = 0; i < valores.length; i++) {
        if (i==0) {
          resultado=valores[i];
        }else{
        resultado *= valores[i];
        }
      }
      break;
    case 'divisao':
      for (let i = 0; i < valores.length; i++) {
        if (i==0) {
          resultado=valores[i];
        }else{
        resultado /= valores[i];
        }
      }
      break;
    default:
      return OPERACAO_INVALIDA;
  }
  return resultado;
}
