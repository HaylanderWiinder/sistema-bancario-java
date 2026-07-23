package service;

import model.Conta;
import model.Movimentacao;
import model.enums.TipoMovimentacao;
import repository.MovimentacaoRepository;

import java.time.LocalDateTime;

public class MovimentacaoService {

    MovimentacaoRepository movimentacaoRepository = new MovimentacaoRepository();

    public void registrar(Conta conta,
                          TipoMovimentacao tipo,
                          double valor,
                          String descricao){

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setConta(conta);
        movimentacao.setTipo(tipo);
        movimentacao.setValor(valor);
        movimentacao.setDescricao(descricao);
        movimentacao.setDataHora(LocalDateTime.now());

        movimentacaoRepository.salvar(movimentacao);



    }
}
