import React from 'react';

function TesteArquivoCard(props) {

    return (
        <>
            <div className="card-testes-arquivos shadow">
                <span>{props.nomeArquivo}</span>
                <span className="text-porcentagem-sucesso">{props.porcentagemSucesso}%</span>
                <span>{props.quantidadeFuncoes}</span>
                <span>{props.tempoExecucao}s</span>
            </div>
        </>
    )

}

export default TesteArquivoCard;