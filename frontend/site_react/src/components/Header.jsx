import React from 'react';
import { Link, useHistory } from 'react-router-dom';
import { WiMoonAltWaningGibbous1 } from "react-icons/wi";
import { FaSun } from "react-icons/fa";
import { FaMoon } from "react-icons/fa";
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


    const darkMode = () => {
        const $html = document.querySelector('html');
        const $darkIcon = document.querySelector('#dark-icon');
        const $logo = document.querySelector('#logo');

        $darkIcon.addEventListener('click', () => {
            $logo.setAttribute('src', LogoFinobanDark);
            $html.classList.toggle('dark-mode');
        })
    }



    return (
        <header>
            <div className="topheader">
                <div className="topheader-logo">
                    <Link to="/">
                        <img className="logo" id="logo" src={LogoFinobanLight} alt="" />
                    </Link>
                </div>
                <div className="topheader-options">
                    <button onClick={() => redirecionarParaPagina("cadastro")}>
                        Cadastro
                    </button>
                    <button onClick={() => redirecionarParaPagina("login")}>
                        Login
                    </button>
                    <WiMoonAltWaningGibbous1 onClick={() => darkMode()} id="dark-icon" className="dark-icon" />
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
                            <Link to="/">
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