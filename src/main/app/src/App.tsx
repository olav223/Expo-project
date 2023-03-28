import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import FrontPage from "./pages/FontPage";
import StandPage from "./pages/StandPage";
import JuryPage from "./pages/jury/JuryPage";

function App() {
  return <BrowserRouter basename={process.env.PUBLIC_URL}>
    <Routes>
      <Route index element={<FrontPage />}/>
      <Route path="/stand" element={<StandPage />} />
      <Route path={"/jury"} element={<JuryPage />} />
    </Routes>
  </BrowserRouter>
}
export default App;
