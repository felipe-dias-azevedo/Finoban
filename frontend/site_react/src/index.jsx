import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import { DarkModeProvider } from './contexts/DarkModeContext';

ReactDOM.render(
  <React.StrictMode>
    <DndProvider backend={HTML5Backend}>
      <DarkModeProvider>
        <App />
      </DarkModeProvider>
    </DndProvider>
  </React.StrictMode>,
  document.getElementById('root')
);