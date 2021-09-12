import React from 'react';
import Jose from '../assets/images/ze.png'

function CardTime(props) {
    return (
        <>
            <div className="container">
                <div className="box-time">
                    
                        <img src={Jose} alt="" className="img" />

                    <div class="name">
                        
                            <h4>{props.nome}</h4>
                        
                    </div>
                </div>

            </div>

        </>
    );

}
export default CardTime;