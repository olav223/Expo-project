import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from "./App";
import {FpjsProvider} from "@fingerprintjs/fingerprintjs-pro-react";

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);

root.render(
  <React.StrictMode>
      <FpjsProvider
          loadOptions={{
           apiKey: process.env.REACT_APP_PUBLIC_API_KEY ?? "",
           region: "eu"
          }}
          >
          <App />
      </FpjsProvider>
  </React.StrictMode>
);