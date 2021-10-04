import { createContext, useContext, useState } from "react";

const UserContext = createContext();

export function UserProvider({ children }) {

    const [dataUsuario, setDataUsuario] = useState();
    const [authorization, setAuthorization] = useState("");

    return (
        <UserContext.Provider
            value={{
                dataUsuario,
                setDataUsuario,
                authorization,
                setAuthorization
            }}
        >
            {children}
        </UserContext.Provider>
    )
    
}

export function useUser() {
    const context = useContext(UserContext);
    if (!context) throw new Error("useUser deve ser utilizado em conjunto com um UserProvider");
    const { dataUsuario, setDataUsuario, authorization, setAuthorization } = context;
    return { dataUsuario, setDataUsuario, authorization, setAuthorization };
}