import React from 'react';

function CampoItem(props) {
    return (
        <div className="box box-item center">
            <p className="fonte-15">{props.label}:</p>
            <br/>
            <p className="fonte-16"> <strong>{props.valor}</strong> </p>
        </div>
    );
}

export default CampoItem;