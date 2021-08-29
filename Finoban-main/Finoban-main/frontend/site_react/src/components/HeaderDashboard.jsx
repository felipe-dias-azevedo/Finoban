import React from 'react';
import { Link } from 'react-router-dom';
import Breadcrumbs from '@material-ui/core/Breadcrumbs';
import LinkMaterial from '@material-ui/core/Link';
import Api from '../services/api';
import { useHistory } from 'react-router';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';


function ModalSucesso(props) {

    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Você foi deslogado com sucesso! Redirecionando para página principal...
            </Modal.Title>
            </Modal.Header>
            <Modal.Body>
              
            </Modal.Body>
        </Modal>
    );
}


function Header() {

    const [modalShowSucesso, setModalShowSucesso] = React.useState(false);

    const history = useHistory();

    function efetuarLogoff() {
        var emailUsuario = localStorage.getItem("emailUsuario");
        console.log(emailUsuario);

        setModalShowSucesso(true);

        const req = {
            email: emailUsuario
        }

        Api.post('/logoff', req, {
        }).then(e => {
            console.log(e.data);
            if(e.status == 200) {
                setModalShowSucesso(true);
                setTimeout(() => {
                    setModalShowSucesso(false);
                    history.push({
                      pathname: '/'
                    });
                  }, 3000);
            } 
        }).catch(e => {
            console.error(e)
        });
    }

    return (
        <>
            <header className="menu">
                <div className="pt1">
                    <div className="container">
                        <h1>Finoban</h1>
                        <div className="botoes">
                            <ul>
                                <li><Link onClick={efetuarLogoff}>Sair</Link></li>
                                <ModalSucesso
                show={modalShowSucesso}
                onHide={() => setModalShowSucesso(false)}
            />
                            </ul>
                        </div>
                    </div>
                </div>
                <div className="pt2">
                    <div className="container">
                        <Breadcrumbs aria-label="breadcrumb">
                            <Link to="/" className="fw-500">
                                Home
                            </Link>
                            <Link to="/simulador" className="fw-500">
                                Simulador
                        </Link>
                        <Link aria-current="page">
                                Dashboard
                        </Link>
                        </Breadcrumbs>
                        <div className="links">
                            <ul>
                                <li><Link to="/">CONTATO</Link></li>
                                <li><Link to="/">PARCEIROS</Link></li>
                                <li><Link to="/">OPENBANKING</Link></li>
                                <li><Link to="/">SOBRE</Link></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </header>
        </>
    );
}

export default Header;