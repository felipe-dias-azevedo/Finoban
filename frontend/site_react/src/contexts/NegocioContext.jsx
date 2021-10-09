import { createContext, useEffect, useState } from "react";
import api from '../services/api';

export const NegocioContext = createContext();

export function NegocioProvider({ children }) {

    const [dataDashboard, setDataDashboard] = useState();

    const headers = {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
        'Authorization': 'Bearer ' + sessionStorage.getItem("tokenAuth")
    }

    const opts = {
        headers: headers
    }

    useEffect(() => {
        api.get("/dashboard", opts).then(e => {
            const data = e.data;
            setDataDashboard(data);
            sessionStorage.setItem('dataDash', JSON.stringify(data));
        }).catch(e => {
            console.error(e);
        });
    }, []);

    return (
        <NegocioContext.Provider
            value={{
                dataDashboard,
                setDataDashboard
            }}
        >
            {children}
        </NegocioContext.Provider>
    );
}