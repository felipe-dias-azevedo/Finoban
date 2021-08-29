import React from 'react';

function CampoItem(props) {
    return (
        <div className="box box-item center">
            <p>{props.label}:</p>
            <br/>
            <p> <strong>{props.valor}</strong> </p>
        </div>
    );
}

export default CampoItem;