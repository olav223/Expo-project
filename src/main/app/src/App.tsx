import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import FrontPage from "./pages/FontPage";

function App() {
  return <BrowserRouter basename={process.env.PUBLIC_URL}>
    <Routes>
      <Route index element={<FrontPage />}/>
    </Routes>
  </BrowserRouter>
}
export default App;
