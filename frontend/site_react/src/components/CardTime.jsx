import React from 'react';
import Jose from '../assets/images/ze.png'

function CardTime(props) {
    return (
        <>
            <div className="container">
                <div className="box">

                    <div className="teste">
                        <img src="{Jose}" alt="" className="img" />
                    </div>


                    <div class="name">
                        <label>
                            <h4>{props.nome}</h4>
                        </label>
                    </div>
                </div>

            </div>






        </>
    );

}
export default CardTime;