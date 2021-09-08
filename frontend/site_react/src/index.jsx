import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './assets/css/variable.css';
import './assets/css/global.css';
import './assets/css/header.css';
import './assets/css/home.css';
import './assets/css/light.css';
import './assets/css/dark.css';
import './assets/css/dashboard-negocio.css';
import './assets/css/forms.css';
import './assets/css/footer.css';
import './styles/login-admin.css';
import './styles/sobre-nos.css';
import './styles/openbanking.css';
// import './assets/css/comum.css';
// import './assets/css/simulador.css';
// import './assets/css/dashboard.css';
// import './assets/css/bootstrap.min.css';
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
// import './styles/global.css';
// import './styles/dashboard.css';
// import './styles/home-admin.css';
// import './styles/cadastro.css';
// import './styles/login-admin.css';

ReactDOM.render(
  <React.StrictMode>
    <DndProvider backend={HTML5Backend}>
      <App />
    </DndProvider>
  </React.StrictMode>,
  document.getElementById('root')
);