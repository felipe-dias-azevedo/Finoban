import React from 'react';
import { FaFacebookSquare } from 'react-icons/fa';
import { FaInstagramSquare } from 'react-icons/fa';
import { FaTwitterSquare } from 'react-icons/fa';

function Footer() {
    return (
        <>
            <div className="footer shadow-footer">
                <section>
                    <h4>Possíveis dúvidas?</h4>
                    <p className="topics" href="/openbanking">O que é o Open Banking?</p>
                    <p className="topics" href="/">O que é o Financiamento Imobiliário?</p>
                    <p className="topics" href="/" >Quais são as taxas?</p>
                </section>
                <section>
                    <h4>Navegação</h4>
                    <p><a id="nav-inicio" className="topics" href="/">Inicio</a></p>
                    <p><a id="nav-sobre-nos" className="topics" href="/sobrenos">Sobre nós</a></p>
                    <p><a id="nav-contato" className="topics" href="/sobrenos">Contato</a></p>
                </section>
                <section>
                    <h4>Parceiros</h4>
                    <p className="topics" > <a id="bandtec" href="http://www.digitalschool.com.br/faculdade/">Bandtec Digital School </a></p>
                    <p className="topics" > <a id="safra" href="https://www.safra.com.br/">Banco Safra S.A. </a> </p>
                </section>
                <section className="icons">
                    <h4>Nossas redes socias</h4>
                    <div>
                        <FaFacebookSquare className="social-icons" style={{ height: 35, width: 35 }} />
                        <FaTwitterSquare className="social-icons" style={{ height: 35, width: 35 }} />
                        <FaInstagramSquare className="social-icons" style={{ height: 35, width: 35 }} />
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