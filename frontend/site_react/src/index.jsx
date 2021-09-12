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
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';

ReactDOM.render(
  <React.StrictMode>
    <DndProvider backend={HTML5Backend}>
      <App />
    </DndProvider>
  </React.StrictMode>,
  document.getElementById('root')
);