package com.fincatto.documentofiscal.cte400.classes.nota;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.cte400.classes.envio.CTeProtocolo;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Eldevan Nery Junior on 09/10/17.
 */
@Root(name = "cteProc")
public class CTeProcessado extends DFBase {
    private static final long serialVersionUID = -765312048472045116L;

    /**
     * Tipo IP versão 4
     */
    @Attribute(name = "ipTransmissor", required = false)
    private String ipTransmissor;

    @Attribute(name = "versao")
    private String versao;

    @Element(name = "CTe")
    private CTeNota cte;

    @Element(name = "protCTe")
    private CTeProtocolo protocolo;

    public String getIpTransmissor() {
        return this.ipTransmissor;
    }

    public void setIpTransmissor(final String ipTransmissor) {
        this.ipTransmissor = ipTransmissor;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public CTeNota getCte() {
        return this.cte;
    }

    public void setCte(final CTeNota cte) {
        this.cte = cte;
    }

    public CTeProtocolo getProtocolo() {
        return this.protocolo;
    }

    public void setProtocolo(final CTeProtocolo protocolo) {
        this.protocolo = protocolo;
    }
}
