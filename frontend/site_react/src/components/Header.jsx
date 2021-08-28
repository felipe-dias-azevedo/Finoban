import React from 'react';
import { Link } from 'react-router-dom';

function Header() {
    return (
        <>
            <header className="menu">
                <div className="pt1">
                    <div className="container">
                        <h1>Finoban</h1>
                        <div className="botoes">
                            <ul>
                                <li><Link to="/cadastro">Cadastro</Link></li>
                                <li><Link to="/login">Login</Link></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div className="pt2">
                    <div className="container">
                        <Link to="/">Home</Link>
                        <div className="links">
                            <ul>
                                <li><Link to="/analise">ANÁLISES DE NEGÓCIO</Link></li>
                                <li><Link to="/">SOBRE</Link></li>
                                <li><Link to="/">PARCEIROS</Link></li>
                                <li><Link to="/">OPENBANKING</Link></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </header>
        </>
    );
}

export default Header;