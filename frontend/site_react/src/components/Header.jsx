import React from 'react';
import { Link } from 'react-router-dom';
import { WiMoonAltWaningGibbous1 } from "react-icons/wi";

function Header() {
    return (
        <>
            <header className="menu">
                <div className="pt1">
                    <div>
                        <h1 className="logo">Finoban</h1>
                    </div>
                    <div className="botoes">
                        <button className="button"><Link to="/cadastro" >Cadastro</Link></button>
                        <button className="button"><Link to="/login">Login</Link></button>
                        <WiMoonAltWaningGibbous1 className="dark-icon" />
                    </div>
                </div>
                <div className="pt2 shadow-footer-header">
                    <div className="migalha">
                        <ul>
                            <li><Link to="/">Home</Link></li>
                        </ul>
                    </div>
                    <div className="links">
                        <ul>
                            <li><Link to="/analise">ANÁLISES DE NEGÓCIO</Link></li>
                            <li><Link to="/">SOBRE</Link></li>
                            <li><Link to="/">PARCEIROS</Link></li>
                            <li><Link to="/">OPENBANKING</Link></li>
                        </ul>
                    </div>
                </div>
            </header>
        </>
    );
}

export default Header;