import React from 'react';

function Footer() {
    return (
        <>
            <div className="rodape">
                <section>
                    <h3>Possíveis dúvidas?</h3>
                    <p>O que é o Open Banking?</p>
                    <p>O que é o Financiamento Imboliario?</p>
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
                <section>
                    <h3>Nossas redes socias</h3>
                </section>
                <section className="logo">
                    <h1>Finoban © - Copyright</h1>
                    <p>Todos os direitos reservados</p>
                </section>
            </div>
        </>
    );
}

export default Footer;