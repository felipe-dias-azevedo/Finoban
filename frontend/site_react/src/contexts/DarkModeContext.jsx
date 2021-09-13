import { createContext, useEffect, useState } from "react";

export const DarkModeContext = createContext();

export function DarkModeProvider({ children }) {
    
    const [isDarkEnable, setIsDarkEnable] = useState();

    useEffect(() => {
        const isDark = localStorage.getItem('darkMode');
        setIsDarkEnable(isDark === 'true' ? true : false);
    }, []);

    useEffect(() => {
        document.querySelector('html').className = isDarkEnable ? 'dark-mode' : 'light-mode';
    }, [isDarkEnable]);
    
    const changeDarkModeState = () => {
        localStorage.setItem('darkMode', !isDarkEnable);
        setIsDarkEnable(!isDarkEnable);
    }
    
    return (
        <DarkModeContext.Provider
            value={{
                isDarkEnable,
                changeDarkModeState
            }}
        >
            {children}
        </DarkModeContext.Provider>
    )
}