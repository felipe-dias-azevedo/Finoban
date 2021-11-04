import React, { useEffect } from "react";

function TesteArquivoCard(props) {

    const porcentSucesso = (props.porcentagemSucesso * 100);

    return (
        <>
            <div className="card-testes-arquivos shadow">
                <span>{props.nomeArquivo}</span>
                <div className="barra-progresso-porcentagem-sucesso">
                    <span style={{ width: `${porcentSucesso}%`, backgroundColor: `hsl(calc(${porcentSucesso} * 1.2), 80%, 50%)` }} className="text-porcentagem-sucesso">{porcentSucesso}%</span>
                </div>
                <span className="text-qtd-funcoes">{props.quantidadeFuncoes}</span>
                <span className="text-tempo-exec">{props.tempoExecucao}s</span>
            </div>
        </>
    )

}

export default TesteArquivoCard;