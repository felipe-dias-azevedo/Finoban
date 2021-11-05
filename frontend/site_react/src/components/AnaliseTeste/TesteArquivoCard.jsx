import React, { useEffect } from "react";

function TesteArquivoCard(props) {

    const porcentSucesso = (props.porcentagemSucesso * 100);

    return (
        <>
            <div className="card-testes-arquivos shadow">
                <span>{props.nomeArquivo}</span>
                <div className="barra-progresso-porcentagem-sucesso">
                    <span className="barra-cor-porcentagem-sucesso"
                        style={{ width: `${porcentSucesso}%`, backgroundColor: `hsl(calc(${porcentSucesso} * 1.2), 80%, 50%)` }} >
                    </span>
                    <span className="text-pocentagem-sucesso">{porcentSucesso}%</span>
                </div>
                <span className="text-qtd-funcoes">{props.quantidadeFuncoes}</span>
                <span className="text-tempo-exec">{props.tempoExecucao}s</span>
            </div>
        </>
    )

}

export default TesteArquivoCard;