import { createContext, useEffect, useState } from "react";

export const NegocioContext = createContext();

export function NegocioProvider({ children }) {

    const [dataDashboard, setDataDashboard] = useState();

    useEffect(() => {
        
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