import React from 'react';
import Icon from '../assets/images/bank-2.png'
import Button from './Button';

function BankCard(props) {
    return (
        <>
            <div className="box-banco shadow">
                <div className="icon-bank shadow">
                    <img className="logo-bancos" src={Icon} alt="" />
                </div>
                <h4>{props.banco}</h4>
                <div className="cx-info">
                    <span className="taxa-total"> Taxa Total: </span>
                    <span className="fonte-16"> {props.taxa_t}% </span>
                </div>
                <div className="cx-info">
                    <span className="fonte-14"> Primeira Prestação: </span>
                    <span className="fonte-14"> {props.primeira_p} </span>
                </div>
                <div className="cx-info">
                    <span className="fonte-14"> Valor final do imóvel: </span>
                    <span className="fonte-14"> {props.valor_f} </span>
                </div>
                <div className="cx-info">
                    <span className="fonte-14"> Valor seguros: </span>
                    <span className="fonte-14"> {props.valor_s}% </span>
                </div>

                <Button className="btn-bank-card" />
            </div>
        </>
    );
}

function BankCard2(props) {
    return (
        <>
            <div className="box-banco center">
                <div className="icon-bank center">
                    <img src={Icon} alt="" />
                </div>
                <h4>{props.banco}</h4>
                <div className="cx-info">
                    <span> Taxa Total: </span>
                    <span> {props.taxa_t}% </span>
                </div>
                <div className="cx-info">
                    <span> Primeira Prestação: </span>
                    <span> R$ {props.primeira_p} </span>
                </div>
                <div className="cx-info">
                    <span> Valor final do imovel: </span>
                    <span> R$ {props.valor_f} </span>
                </div>
                <div className="cx-info">
                    <span> Valor seguros: </span>
                    <span> {props.valor_s}% </span>
                </div>

                <Button />
            </div>
        </>
    );
}

export default BankCard;