import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AdminEvent from "./pages/admin/AdminEvent";
import FrontPage from "./pages/FontPage";
import StandPage from "./pages/StandPage";

function App() {
  return <BrowserRouter basename={process.env.PUBLIC_URL}>
    <Routes>
      <Route index element={<FrontPage />}/>
      <Route path="/stand" element={<StandPage />} />
      <Route path="/admin" element={<AdminEvent/>}/>
    </Routes>
  </BrowserRouter>
}
export default App;
