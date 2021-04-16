import React from 'react';

function Button(props) {
    return (
        <>
            <button className="btn">{props.valor}</button>
        </>
    );
}

export default Button;