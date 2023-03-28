import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import FrontPage from "./pages/FontPage";
import StandPage from "./pages/StandPage";
import LoginPage from "./pages/LoginPage";
import AdminPage from "./pages/admin/AdminPage";
import AdminEvent from "./pages/admin/AdminEvent";
import AdminEventEdit from "./pages/admin/AdminEventEdit";
import AdminStandEdit from "./pages/admin/AdminStandEdit";

function App() {
  return <BrowserRouter basename={process.env.PUBLIC_URL}>
    <Routes>
      <Route index element={<FrontPage />}/>
      <Route path="/stand" element={<StandPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/admin" element={<AdminPage />} />
      <Route path="/admin/event" element={<AdminEvent />} />
      <Route path="/admin/event/edit" element={<AdminEventEdit />} />
      <Route path="/admin/stand/edit" element={<AdminStandEdit />} />
      <Route path="/stand" element={<StandPage />} />
    </Routes>
  </BrowserRouter>
}
export default App;
