import React from 'react';
import { AiFillFacebook } from 'react-icons/ai';
import { AiFillTwitterSquare } from 'react-icons/ai';
import { AiFillInstagram } from 'react-icons/ai';

function Footer() {
    return (
        <>
            <div className="rodape">
                <section>
                    <h3>Possíveis dúvidas?</h3>
                    <p>O que é o Open Banking?</p>
                    <p>O que é o Financiamento Imobiliário?</p>
                    <p>Quais são as taxas?</p>
                </section>
                <section>
                    <h3>Navegação</h3>
                    <a href="/">Inicio</a>
                    <a href="/">Sobre nós</a>
                    <a href="/">Contato</a>
                </section>
                <section>
                    <h3>Parceiros</h3>
                    <p>Bandtec Digital School</p>
                    <p>Banco Safra S.A.</p>
                </section>
                <section className="icons">
                    <h3>Nossas redes socias</h3>
                    <div>
                        <AiFillTwitterSquare style={{ height: 72, width: 72 }} />
                        <AiFillFacebook style={{ height: 72, width: 72 }} />
                        <AiFillInstagram style={{ height: 72, width: 72 }} />
                    </div>
                </section>
                <section className="logo">
                    <h1> <strong>Finoban</strong> - Copyright ©</h1>
                    <p>Todos os direitos reservados</p>
                </section>
            </div>
        </>
    );
}

export default Footer;