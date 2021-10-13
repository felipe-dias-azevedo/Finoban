import { createContext, useContext, useEffect, useState } from "react";

const UserContext = createContext();

export default function UserProvider({ children }) {

    const [dataUsuario, setDataUsuario] = useState();
    const [authorization, setAuthorization] = useState("");

    useEffect(() => {
        setDataUsuario(sessionStorage.getItem("dadosUsuario"));
        setAuthorization(sessionStorage.getItem("tokenAuth"));
    }, []);

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