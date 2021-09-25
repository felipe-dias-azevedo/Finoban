import { createContext, useState } from "react";

export const SwitchGraficoNegocioContext = createContext();

export function SwitchGraficoNegocioProvider({ children }) {

    const [chartsVisible, setChartsVisible] = useState([
        true,true,true,true,true,true,true,true,true,true,true
    ]);

    return (
        <SwitchGraficoNegocioContext.Provider
            value={{
                chartsVisible,
                setChartsVisible
            }}
        >
            {children}
        </SwitchGraficoNegocioContext.Provider>
    );
}