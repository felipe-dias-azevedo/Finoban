import { useState, useEffect } from 'react'
import { FaPlay } from 'react-icons/fa'
import api from '../../services/healthcheck';
import dateHelper from '../../utils/dateHelper'

function TesteApiExternaCard() {

    const [responseCifraHealthCheck, setResponseCifraHealthCheck] = useState([])
    const [responsePresilHealthCheck, setResponsePresilHealthCheck] = useState([])
    const [responseS16HealthCheck, setResponseS16HealthCheck] = useState([])

    const [bgColorCifra, setBgColorCifra] = useState([])
    const [bgColorPresil, setBgColorPresil] = useState([])
    const [bgColorS16, setBgColorS16] = useState([])

    const apis = [
        {
            nomeAplicacao: 'API Cifra',
            bgColor: bgColorCifra,
            responseHealthCheck: responseCifraHealthCheck
        },
        {
            nomeAplicacao: 'API Presil',
            bgColor: bgColorPresil,
            responseHealthCheck: responsePresilHealthCheck
        },
        {
            nomeAplicacao: 'API S16',
            bgColor: bgColorS16,
            responseHealthCheck: responseS16HealthCheck
        }
    ]

    const getHealthCheck = async (nome_aplicacao) => {
        switch (nome_aplicacao) {
            case 'API Cifra':
                getCifraHealthCheck()
                break;
            case 'API Presil':
                getPresilHealthCheck()
                break;
            case 'API S16':
                getS16HealthCheck()
                break;
            default:
                break;
        }
    }

    const getApisHealthCheck = async () => {
        try {
            const responseHealthCheck = await api.get('/apis-externas');
            return responseHealthCheck.data
        } catch (error) {
            const mockResponseHealthCheck = {
                statusApi16Bank: 'OK',
                statusApiCifra: 'OK',
                statusApiPresil: 'NOK'
            }
            console.log({error: error, mock: mockResponseHealthCheck});
            return mockResponseHealthCheck
        }
    }

    const getCifraHealthCheck = async () => {
        const responseHealthCheck = await getApisHealthCheck()

        setResponseCifraHealthCheck(responseHealthCheck.statusApiCifra)
        setBgColorCifra(responseHealthCheck.statusApiCifra == 'OK' ? '#64AE6C' : '#DA5C5C')
    }

    const getPresilHealthCheck = async () => {
        const responseHealthCheck = await getApisHealthCheck()

        setResponsePresilHealthCheck(responseHealthCheck.statusApiPresil)
        setBgColorPresil(responseHealthCheck.statusApiPresil == 'OK' ? '#64AE6C' : '#DA5C5C')
    }

    const getS16HealthCheck = async () => {
        const responseHealthCheck = await getApisHealthCheck()

        setResponseS16HealthCheck(responseHealthCheck.statusApi16Bank)
        setBgColorS16(responseHealthCheck.statusApi16Bank == 'OK' ? '#64AE6C' : '#DA5C5C')
    }

    useEffect(() => {
        setResponseCifraHealthCheck('N/ testado')
        setResponsePresilHealthCheck('N/ testado')
        setResponseS16HealthCheck('N/ testado')
        setBgColorCifra('#838383')
        setBgColorPresil('#838383')
        setBgColorS16('#838383')
    }, [])

    return (
        <>
            <div className='div-apis-externas'>
                {apis.map((project) => (
                    <div className='div-box-titulo-api-externa'>
                        <div className='titulo-apis-externas'>
                            <h5> Aplicação </h5>
                            <h5> Status (health) </h5>
                            <h5> Testar </h5>
                        </div>
                        <div className='box-projeto-externo-teste-card shadow'>

                            <span className='nome-projeto-externo-card-teste'>
                                {' '}
                                {project.nomeAplicacao}{' '}
                            </span>
                            <div
                                className='status-health-check'
                                style={{ backgroundColor: project.bgColor }}>
                                {' '}
                                {project.responseHealthCheck}{' '}
                            </div>
                            <div>
                                <FaPlay className='icon-play' onClick={() => {
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

