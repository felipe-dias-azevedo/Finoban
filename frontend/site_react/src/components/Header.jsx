import React, { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { WiMoonAltWaningGibbous1 } from "react-icons/wi";
import Loading from '../assets/images/Loading.gif';
import LogoFinobanLight from '../assets/images/logo-finoban-light.svg';
import LogoFinobanDark from '../assets/images/logo-finoban-dark.svg';

function Header() {

    const history = useHistory();
    const [modoEscuroHablitado, setModoEscuroHabilitado] = useState();

    useEffect(() => {
        const escuro = localStorage.getItem('modoEscuro');
        setModoEscuroHabilitado(escuro === "true" ? true : false);
    }, []);

    useEffect(() => {
        console.log("modo =", modoEscuroHablitado);
        document.querySelector('html').className = modoEscuroHablitado ? 'dark-mode' : 'light-mode';
    }, [modoEscuroHablitado]);

    function mudarModoEscuro() {
        document.querySelector('html').className = modoEscuroHablitado ? 'dark-mode' : 'light-mode';
        
        localStorage.setItem('modoEscuro', !modoEscuroHablitado);
        setModoEscuroHabilitado(!modoEscuroHablitado);
    }

    return (
        <header>
            <div className="topheader">
                <div className="topheader-logo">
                    <Link to="/">
                        {modoEscuroHablitado ? (
                            <img className="logo" src={LogoFinobanDark} alt="Logo Finoban"/>
                        ) :
                        (
                            <img className="logo" src={LogoFinobanLight} alt="Logo Finoban"/>
                        )}
                        
                    </Link>
                </div>
                <div className="topheader-options">
                    <button onClick={() => history.push("/cadastro")}>
                        Cadastro
                    </button>
                    <button onClick={() => history.push("/login")}>
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