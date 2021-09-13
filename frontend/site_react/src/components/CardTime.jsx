import React from 'react';
import Jose from '../assets/images/ze.png'

function CardTime(props) {
    return (
        <>
            <div className="container">
                <div className="box-time">
                    
                        <img src={props.img} alt="" className="img" />

                    <div class="name">
                        
                            <h4>{props.name}</h4> <br/>
                            <h4>{props.func}</h4>
                        
                    </div>
                </div>

            </div>

        </>
    );

}
export default CardTime;