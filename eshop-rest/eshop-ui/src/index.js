import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

import './language/i18n'
import './validation'


ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);
