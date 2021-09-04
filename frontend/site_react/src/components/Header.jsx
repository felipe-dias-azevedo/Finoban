import React from 'react';
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

    return (
        <header>
            <div className="topheader">
                <div className="topheader-logo">
                    <Link to="/">
                        <img className="logo" src={LogoFinobanLight} alt="" />
                    </Link>
                </div>
                <div className="topheader-options">
                    <button onClick={() => redirecionarParaPagina("cadastro")}>
                        Cadastro
                    </button>
                    <button onClick={() => redirecionarParaPagina("login")}>
                        Login
                    </button>
                    <WiMoonAltWaningGibbous1 className="dark-icon" />
                </div>
            </div>
            <div className="subheader shadow-footer-header">
                <div className="breadcrumb">
                    <Link to="/">Home</Link>
                </div>
                <div className="links">
                    <ul>
                        <li>
                            <Link to="/analise">
                                ANÁLISES DE NEGÓCIO
                            </Link>
                        </li>
                        <li>
                            <Link to="/">
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