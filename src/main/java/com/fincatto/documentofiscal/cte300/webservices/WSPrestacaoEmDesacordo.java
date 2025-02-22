package com.fincatto.documentofiscal.cte300.webservices;

import com.fincatto.documentofiscal.cte.CTeConfig;
import com.fincatto.documentofiscal.cte300.classes.evento.CTeEvento;
import com.fincatto.documentofiscal.cte300.classes.evento.CTeEventoRetorno;
import com.fincatto.documentofiscal.cte300.classes.evento.desacordo.CTeEnviaEventoPrestacaoEmDesacordo;
import com.fincatto.documentofiscal.utils.DFAssinaturaDigital;
import com.fincatto.documentofiscal.validadores.DFXMLValidador;
import org.apache.axiom.om.OMElement;

import java.math.BigDecimal;

class WSPrestacaoEmDesacordo extends WSRecepcaoEvento {
    private static final String DESCRICAO_EVENTO = "Prestacao do Servico em Desacordo";
    private static final BigDecimal VERSAO_LEIAUTE = new BigDecimal("3.00");
    private static final String EVENTO_SERVICO_EM_DESACORDO = "610110";

    WSPrestacaoEmDesacordo(final CTeConfig config) {
        super(config);
    }

    CTeEventoRetorno prestacaoEmDesacordoAssinada(final String chaveAcesso, final String eventoAssinadoXml) throws Exception {
        final OMElement omElementResult = super.efetuaEvento(eventoAssinadoXml, chaveAcesso, WSPrestacaoEmDesacordo.VERSAO_LEIAUTE);
        return this.config.getPersister().read(CTeEventoRetorno.class, omElementResult.toString());
    }

    CTeEventoRetorno prestacaoEmDesacordo(final String chaveAcesso, final String motivo, final String cpfOuCnpj) throws Exception {
        final String xmlAssinado = this.getXmlAssinado(chaveAcesso, motivo, cpfOuCnpj);
        return prestacaoEmDesacordoAssinada(chaveAcesso, xmlAssinado);
    }

    String getXmlAssinado(final String chave, final String observacao, final String cpfOuCnpj) throws Exception {
        final String xml = this.gerarDadosPrestacaoEmDesacordo(chave, observacao, cpfOuCnpj).toString();
        return new DFAssinaturaDigital(this.config).assinarDocumento(xml);
    }

    private CTeEvento gerarDadosPrestacaoEmDesacordo(final String chaveAcesso, final String motivo, final String cpfOuCnpj) throws Exception {
        final CTeEnviaEventoPrestacaoEmDesacordo desacordo = new CTeEnviaEventoPrestacaoEmDesacordo();
        desacordo.setDescricaoEvento(WSPrestacaoEmDesacordo.DESCRICAO_EVENTO);
        desacordo.setIndicadorPrestacaoEmDesacordo(1);
        desacordo.setObservacao(motivo.trim());

        DFXMLValidador.validaEventoPrestacaoEmDesacordoCTe300(desacordo.toString());

        return super.gerarEvento(chaveAcesso, WSPrestacaoEmDesacordo.VERSAO_LEIAUTE, desacordo, WSPrestacaoEmDesacordo.EVENTO_SERVICO_EM_DESACORDO, cpfOuCnpj, 1);
    }
}
