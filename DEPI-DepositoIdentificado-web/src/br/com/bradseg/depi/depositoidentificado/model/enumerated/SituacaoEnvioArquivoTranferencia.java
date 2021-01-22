package br.com.bradseg.depi.depositoidentificado.model.enumerated;

/**
 * Identifica o estado do depósito no arquivo de transferência dos dados dos depósitos identificados para o Banco Bradesco. Valores
 * válidos: 1- Enviar, 2- Enviado, 3- Aceito, 4- Reenvio - 5 - Rejeitado, 6 - Cancelado.
 * @author Fábio Henrique
 */
public enum SituacaoEnvioArquivoTranferencia {
    ENVIAR, ENVIADO, ACEITO, REENVIO, REJEITADO, CANCELADO
}
