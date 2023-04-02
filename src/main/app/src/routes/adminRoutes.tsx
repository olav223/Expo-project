import {Route, Routes} from "react-router-dom";
import AdminPage from "../pages/admin/AdminPage";
import AdminEvent from "../pages/admin/AdminEvent";
import AdminEventEdit from "../pages/admin/AdminEventEdit";
import AdminStandEdit from "../pages/admin/AdminStandEdit";
import AdminStandList from "../pages/admin/AdminStandList";
import React from "react";

const adminRoutes = <Routes>
    <Route index element={ <AdminPage /> } />
    <Route path="/events" element={ <AdminEvent /> } />
    <Route path="/events/edit" element={ <AdminEventEdit /> } />
    <Route path="/stand/edit/:id" element={ <AdminStandEdit /> } />
    <Route path="/events/:id/stands" element={<AdminStandList/>}/>
</Routes>

export default adminRoutes;