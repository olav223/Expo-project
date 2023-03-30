import React from "react";
import AdminEvent from "./pages/admin/AdminEvent";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import FrontPage from "./pages/FontPage";
import StandPage from "./pages/StandPage";
import LoginPage from "./pages/LoginPage";
import AdminPage from "./pages/admin/AdminPage";
import AdminStandEdit from "./pages/admin/AdminStandEdit";
import AdminEventEdit from "./pages/admin/AdminEventEdit";
import JuryPage from "./pages/jury/JuryPage";
import Navbar from "./components/Navbar/Navbar";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import StandsPage from "./pages/StandsPage";

function App() {
  
    return <BrowserRouter basename={ process.env.PUBLIC_URL }>
        <Navbar />
        <ToastContainer autoClose={3000} />
        <Routes>
            <Route index element={ <FrontPage /> } />
            <Route path="/stands" element={ <StandsPage /> } />
            <Route path="/stand" element={ <StandPage /> } />
            <Route path="/login" element={ <LoginPage /> } />
            <Route path="/admin" element={ <AdminPage /> } />
            <Route path="/admin/event" element={ <AdminEvent /> } />
            <Route path="/admin/event/edit" element={ <AdminEventEdit /> } />
            <Route path="/admin/stand/edit" element={ <AdminStandEdit /> } />
            <Route path={ "/jury" } element={ <JuryPage /> } />
        </Routes>
    </BrowserRouter>
}

export default App;
