package it.unicam.cs.ids.hackhub.service.adapter;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Team;
import org.springframework.stereotype.Component;

@Component
public class SistemaPagamentoAdapter implements IPagamentoAdapter{

    private final PagamentoService pagamentoService;

    public SistemaPagamentoAdapter(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @Override
    public boolean erogaPremio(Hackathon hackathon, Team teamVincitore) {
        String causale = "Premio hackathon ID: " + hackathon.getId() +
                " al team vincitore ID: " + teamVincitore.getId();

        return pagamentoService.effettuaPagamento(causale, hackathon.getPremioInDenaro());
    }
}
