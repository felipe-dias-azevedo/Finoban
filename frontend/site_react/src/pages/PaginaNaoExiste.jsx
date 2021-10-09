import React from 'react';
import ImageNotFound from '../assets/images/error-404-page-3100465-2582986.png';

const Pagina = () => { 
    return (
        <>
        <div className="main">
            <div className="content">
                <div className="text">
                    <h1>OPS!</h1>
                    <p>Parece que a página que você tentou acessar está com problema</p>
                </div>
                <div className="image">
                    <img src={ImageNotFound}></img>
                </div>
            </div>
        </div>
        </>
    )
}

export default function PaginaNaoExiste() {
    return (
        <>
            <Pagina />
        </>
    )
}