import { useState, useEffect } from "react"
import { FaPlay } from 'react-icons/fa'

function TesteApiExternaCard() {

    const [responseCifraHealthCheck, setResponseCifraHealthCheck] = useState([])
    const [responsePresilHealthCheck, setResponsePresilHealthCheck] = useState([])
    const [responseS16HealthCheck, setResponseS16HealthCheck] = useState([])

    const [bgColorCifra, setBgColorCifra] = useState([])
    const [bgColorPresil, setBgColorPresil] = useState([])
    const [bgColorS16, setBgColorS16] = useState([])

    const apis = [
        {
            nomeAplicacao: "API Cifra",
            bgColor: bgColorCifra,
            responseHealthCheck: responseCifraHealthCheck
        },
        {
            nomeAplicacao: "API Presil",
            bgColor: bgColorPresil,
            responseHealthCheck: responsePresilHealthCheck
        },
        {
            nomeAplicacao: "API S16",
            bgColor: bgColorS16,
            responseHealthCheck: responseS16HealthCheck
        }
    ]

    const getHealthCheck = async (nome_aplicacao) => {
        switch (nome_aplicacao) {
            case "API Cifra":
                getCifraHealthCheck()
                break;
            case "API Presil":
                getPresilHealthCheck()
                break;
            case "API S16":
                getS16HealthCheck()
                break;
            default:
                break;
        }
    }

    const getCifraHealthCheck = async () => {
        const responseHealthCheck =
        {
            statusHealthCheck: "OK"
        }
        setResponseCifraHealthCheck(responseHealthCheck.statusHealthCheck)
        setBgColorCifra(responseHealthCheck.statusHealthCheck == "OK" ? "#64AE6C" : "#DA5C5C")
    }

    const getPresilHealthCheck = async () => {
        const responseHealthCheck = {
            statusHealthCheck: "NOK"
        }
        setResponsePresilHealthCheck(responseHealthCheck.statusHealthCheck)
        setBgColorPresil(responseHealthCheck.statusHealthCheck == "OK" ? "#64AE6C" : "#DA5C5C")
    }

    const getS16HealthCheck = async () => {
        const responseHealthCheck = {
            statusHealthCheck: "OK"
        }
        setResponseS16HealthCheck(responseHealthCheck.statusHealthCheck)
        setBgColorS16(responseHealthCheck.statusHealthCheck == "OK" ? "#64AE6C" : "#DA5C5C")
    }

    useEffect(() => {
        setResponseCifraHealthCheck("N/ testado")
        setResponsePresilHealthCheck("N/ testado")
        setResponseS16HealthCheck("N/ testado")
        setBgColorCifra("#838383")
        setBgColorPresil("#838383")
        setBgColorS16("#838383")
    }, [])

    return (
        <>
            <div className="div-apis-externas">
                {apis.map((project) => (
                    <div className="div-box-titulo-api-externa">
                        <div className="titulo-apis-externas">
                            <h5> Aplicação </h5>
                            <h5> Status </h5>
                            <h5> Testar </h5>
                        </div>
                        <div className="box-projeto-externo-teste-card shadow">

                            <span className="nome-projeto-externo-card-teste">
                                {" "}
                                {project.nomeAplicacao}{" "}
                            </span>
                            <div
                                className="status-health-check"
                                style={{ backgroundColor: project.bgColor }}>
                                {" "}
                                {project.responseHealthCheck}{" "}
                            </div>
                            <div>
                            <FaPlay className="icon-play" onClick={() => {
                                getHealthCheck(project.nomeAplicacao)

                            }} />
                            </div>
                        </div>
                    </div>
                ))}
            </div>

        </>
    )
}

export default TesteApiExternaCard

