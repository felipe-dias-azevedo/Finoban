import Header from "./Header";
import { FaExclamationTriangle } from 'react-icons/fa';
 

const ErrorPage = () => {
    return (
        <div className="error-page">
            <div className="error-page-card shadow">
                <FaExclamationTriangle className="error-page-icon" />
                <h1>
                    Erro!
                </h1>
                <h4>
                    Encontramos um problema durante sua requisição...
                </h4>
                <p>
                    Tente novamente mais tarde.
                </p>
            </div>
        </div>
    );
}

export default ErrorPage;