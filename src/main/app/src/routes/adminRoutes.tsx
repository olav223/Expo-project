import {Route, Routes} from "react-router-dom";
import AdminPage from "../pages/admin/AdminPage";
import AdminEvent from "../pages/admin/AdminEvent";
import AdminEventEdit from "../pages/admin/AdminEventEdit";
import AdminStandEdit from "../pages/admin/AdminStandEdit";
import React from "react";

const adminRoutes = <Routes>
    <Route index element={ <AdminPage /> } />
    <Route path="/event" element={ <AdminEvent /> } />
    <Route path="/event/edit" element={ <AdminEventEdit /> } />
    <Route path="/stand/edit" element={ <AdminStandEdit /> } />
</Routes>

export default adminRoutes;