package com.fincatto.nfe.classes.lote.envio;

import org.simpleframework.xml.Element;

import com.fincatto.nfe.classes.NFBase;

public class NFLoteEnvioRetornoRecebimentoInfo extends NFBase {

    @Element(name = "nRec", required = true)
    private String recibo;

    @Element(name = "tMed", required = true)
    private String tempoMedio;

    public NFLoteEnvioRetornoRecebimentoInfo() {
        this.recibo = null;
        this.tempoMedio = null;
    }

    public void setRecibo(final String recibo) {
        this.recibo = recibo;
    }

    public void setTempoMedio(final String tempoMedio) {
        this.tempoMedio = tempoMedio;
    }
}