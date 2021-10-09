import { createContext, useEffect, useState } from "react";
import api from '../services/api';

export const NegocioContext = createContext();

export function NegocioProvider({ children }) {

    const [dataDashboard, setDataDashboard] = useState();

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