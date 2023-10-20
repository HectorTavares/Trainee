import { useHttp } from '..'
import { useMemo } from 'react'

export const useMeLevaAiAPI = () => {
  const http = useHttp('http://localhost:8100/me-leva-ai')

  const cadastrarMotorista = async motorista => {
    const response = await http.post('/motoristas', motorista)
    return response
  }

  const getMotoristas = async () => {
    const response = await http.get('/motoristas')
    return response
  }

  const getMotoristaByCpf = async cpf => {
    const response = await http.get(`/motoristas/${cpf}`)
    return response
  }

  const deleteMotorista = async cpf => {
    const response = await http.exclude(`/motoristas/${cpf}`)
    return response
  }

  const sacar = async (cpf, valor) => {
    const response = await http.put(`motoristas/${cpf}/conta-virtual?valor=${valor}`)
    return response
  }

  const cadastrarPassageiro = async passageiro => {
    const response = await http.post('/passageiros', passageiro)
    return response
  }

  const getPassageiros = async () => {
    const response = await http.get('/passageiros')
    return response
  }

  const depositar = async (cpf, valor) => {
    const response = await http.put(`/passageiros/${cpf}/conta-virtual?valor=${valor}`)
    return response
  }

  const getPassageiroByCpf = async cpf => {
    const response = await http.get(`/passageiros/${cpf}`)
    return response
  }

  const cadastrarVeiculo = async veiculo => {
    const response = await http.post(`/veiculos`, veiculo)
    return response
  }

  const getVeiculos = async () => {
    const response = await http.get(`/veiculos`)
    return response
  }

  const chamarCorrida = async (coordenadas, cpfPassageiro) => {
    const response = await http.post(`/corridas/passageiros/${cpfPassageiro}`, coordenadas)
    return response
  }

  const iniciarCorrida = async idCorrida => {
    const response = await http.post(`/corridas/${idCorrida}`)
    return response
  }

  const terminarCorrida = async idCorrida => {
    const response = await http.post(`/corridas/corridas/${idCorrida}`)
    return response
  }

  const avaliarPassageiro = async (idCorrida, nota) => {
    const response = await http.post(`/corridas/${idCorrida}/motoristas/avaliacao`, nota)
    return response
  }
  const avaliarMotorista = async (idCorrida, nota) => {
    const response = await http.post(`/corridas/${idCorrida}/passageiros/avaliacao`, nota)
    return response
  }

  return useMemo(
    () => ({
      cadastrarMotorista,
      getMotoristas,
      getMotoristaByCpf,
      deleteMotorista,
      sacar,
      cadastrarPassageiro,
      getPassageiros,
      depositar,
      getPassageiroByCpf,
      cadastrarVeiculo,
      getVeiculos,
      chamarCorrida,
      iniciarCorrida,
      terminarCorrida,
      avaliarPassageiro,
      avaliarMotorista,
    }),
    []
  )
}
