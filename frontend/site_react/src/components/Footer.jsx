import React from 'react';
import { AiFillFacebook } from 'react-icons/ai';
import { AiFillTwitterSquare } from 'react-icons/ai';
import { AiFillInstagram } from 'react-icons/ai';

function Footer() {
    return (
        <>
            <div className="footer shadow-footer">
                <section>
                    <h4>Possíveis dúvidas?</h4>
                    <p className="topics">O que é o Open Banking?</p>
                    <p className="topics">O que é o Financiamento Imobiliário?</p>
                    <p className="topics">Quais são as taxas?</p>
                </section>
                <section>
                    <h4>Navegação</h4>
                    <p><a className="topics" href="/">Inicio</a></p>
                    <p><a className="topics" href="/">Sobre nós</a></p>
                    <p><a className="topics" href="/">Contato</a></p>
                </section>
                <section>
                    <h4>Parceiros</h4>
                    <p className="topics">Bandtec Digital School</p>
                    <p className="topics">Banco Safra S.A.</p>
                </section>
                <section className="icons">
                    <h4>Nossas redes socias</h4>
                    <div>
                        <AiFillTwitterSquare style={{ height: 50, width: 50 }} />
                        <AiFillFacebook style={{ height: 50, width: 50 }} />
                        <AiFillInstagram style={{ height: 50, width: 50 }} />
                    </div>
                </section>
                <section className="copyright">
                    <p><strong>Finoban</strong> - Copyright ©</p>
                    <p>Todos os direitos reservados</p>
                </section>
            </div>
        </>
    );
}

export default Footer;