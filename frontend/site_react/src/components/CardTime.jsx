import React from 'react';

function CardTime(props) {
    return (
        <>
                <div className="box-time shadow">
                    <img src={props.img} alt="" className="img-box-time" />
                    <div className="info-box-time">

                        <p className="info-1">{props.name}</p> 

                        <p className="info-2">{props.func}</p>

                    </div>
                </div>

        </>
    );

}
export default CardTime;