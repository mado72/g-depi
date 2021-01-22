package br.com.bradseg.depi.depositoidentificado.model.enumerated;

/**
 * Identifica o estado do dep�sito no arquivo de transfer�ncia dos dados dos dep�sitos identificados para o Banco Bradesco. Valores
 * v�lidos: 1- Enviar, 2- Enviado, 3- Aceito, 4- Reenvio - 5 - Rejeitado, 6 - Cancelado.
 * @author F�bio Henrique
 */
public enum SituacaoEnvioArquivoTranferencia {
    ENVIAR, ENVIADO, ACEITO, REENVIO, REJEITADO, CANCELADO
}
