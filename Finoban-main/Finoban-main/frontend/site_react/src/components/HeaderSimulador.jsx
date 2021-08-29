import React from 'react';
import { Link } from 'react-router-dom';
import Breadcrumbs from '@material-ui/core/Breadcrumbs';
import LinkMaterial from '@material-ui/core/Link';

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
                        <Breadcrumbs aria-label="breadcrumb">
                            <Link to="/" className="fw-500">
                                Home
                            </Link>
                            <Link to="/simulador" aria-current="page">
                                Simulador
                        </Link>
                        </Breadcrumbs>
                        <div className="links">
                            <ul>
                                <li><Link to="/">CONTATO</Link></li>
                                <li><Link to="/">PARCEIROS</Link></li>
                                <li><Link to="/">OPENBANKING</Link></li>
                                <li><Link to="/">SOBRE</Link></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </header>
        </>
    );
}

export default Header;