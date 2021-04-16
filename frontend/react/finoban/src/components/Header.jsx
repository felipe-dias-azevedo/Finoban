import React from 'react';

function Header() {
    return (
        <>
            <header className="menu">

                <div className="pt1">
                    <div className="container">
                        <h1>Finoban</h1>
                        <div className="botoes">
                            <ul>
                                <li><a href="/">Cadastro</a></li>
                                <li><a href="/">Login</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div className="pt2">
                    <div className="container">
                        <a href="/">Home</a>
                        <div className="links">
                            <ul>
                                <li><a href="/">CONTATOS</a></li>
                                <li><a href="/">PARCEIROS</a></li>
                                <li><a href="/">OPENBANKING</a></li>
                                <li><a href="/">SOBRE</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </header>
        </>
    );
}

export default Header;