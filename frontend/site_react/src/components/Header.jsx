import React, { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { WiMoonAltWaningGibbous1 } from "react-icons/wi";
import LogoFinobanLight from '../assets/images/logo-finoban-light.svg';
import LogoFinobanDark from '../assets/images/logo-finoban-dark.svg';

function Header() {

    const history = useHistory();

    const redirecionarParaPagina = (pagina) => {
        switch (pagina) {
            case "cadastro":
                history.push("/cadastro");
                break;
            case "login":
                history.push("/login");
                break;
        }

    }

    const [modoEscuroHablitado, setModoEscuroHabilitado] = useState();

    useEffect(() => {
        const escuro = localStorage.getItem('modoEscuro');
        setModoEscuroHabilitado(escuro ? escuro : false);
    }, []);

    function mudarModoEscuro() {
        const $html = document.querySelector('html');
        $html.classList.toggle('dark-mode');
        
        setModoEscuroHabilitado(!modoEscuroHablitado);
        localStorage.setItem('modoEscuro', !modoEscuroHablitado);
    }

    return (
        <header>
            <div className="topheader">
                <div className="topheader-logo">
                    <Link to="/">
                        <img className="logo" src={modoEscuroHablitado ? LogoFinobanLight : LogoFinobanDark} alt=""/>
                    </Link>
                </div>
                <div className="topheader-options">
                    <button onClick={() => redirecionarParaPagina("cadastro")}>
                        Cadastro
                    </button>
                    <button onClick={() => redirecionarParaPagina("login")}>
                        Login
                    </button>
                    <WiMoonAltWaningGibbous1 onClick={() => mudarModoEscuro()} id="dark-icon" className="dark-icon" />
                </div>
            </div>
            <div className="subheader shadow-header">
                <div className="breadcrumb">
                    <div className="breadcrumb-home"><Link to="/">Home</Link></div>
                    <div className="breadcrumb-analise"><Link to="/">Analise</Link></div>
                    <div className="crumb"></div>
                </div>
                <div className="links">
                    <ul>
                        <li>
                            <Link to="/analise">
                                ANÁLISES DE NEGÓCIO
                            </Link>
                        </li>
                        <li>
                            <Link to="/sobrenos">
                                SOBRE
                            </Link>
                        </li>
                        <li>
                            <Link to="/">
                                PARCEIROS
                            </Link>
                        </li>
                        <li>
                            <Link to="/openbanking">
                                OPENBANKING
                            </Link>
                        </li>
                    </ul>
                </div>
            </div>
        </header>
    );
}

export default Header;