package it.unicam.cs.ids.hackhub.service.adapter;

import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    public boolean effettuaPagamento(String causale, double importo){
        System.out.println("Pagamento effettuato");
        System.out.println("Causale: " + causale);
        System.out.println("Importo: " + importo);

        return true;

    }
}
