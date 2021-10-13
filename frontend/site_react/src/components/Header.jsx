import React, { useContext } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { WiMoonAltWaningGibbous1 } from "react-icons/wi";
import LogoFinobanLight from '../assets/images/logo-finoban-light.svg';
import LogoFinobanDark from '../assets/images/logo-finoban-dark.svg';
import { DarkModeContext } from '../contexts/DarkModeContext';
import Breadcrumbs from './Breadcrumbs';

function Header() {

    const history = useHistory();
    const { isDarkEnable, changeDarkModeState } = useContext(DarkModeContext);

    return (
        <header>
            <div className="topheader">
                <div className="topheader-logo">
                    <Link to="/">
                        {isDarkEnable ? (
                            <img className="logo" src={LogoFinobanDark} alt="Logo Finoban" />
                        ) :
                            (
                                <img className="logo" src={LogoFinobanLight} alt="Logo Finoban" />
                            )}

                    </Link>
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
                            <Link to="/openbanking">
                                OPENBANKING
                            </Link>
                        </li>
                    </ul>
                </div>
                <div className="topheader-options">
                    <button className="btn-topheader" onClick={() => history.push("/cadastro")}>
                        Cadastro
                    </button>
                    <button className="btn-topheader" onClick={() => history.push("/login")}>
                        Login
                    </button>
                    <WiMoonAltWaningGibbous1 onClick={changeDarkModeState} id="dark-icon" className="dark-icon" />
                </div>
            </div>
            <div className="subheader shadow-header">
                <Breadcrumbs/>
            </div>
        </header>
    );
}

export default Header;