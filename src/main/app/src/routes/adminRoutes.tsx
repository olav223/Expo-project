import { Route, Routes } from "react-router-dom";
import AdminPage from "../pages/admin/AdminPage";
import AdminEvent from "../pages/admin/AdminEvent";
import AdminEventEdit from "../pages/admin/AdminEventEdit";
import AdminStandEdit from "../pages/admin/AdminStandEdit";
import AdminStandList from "../pages/admin/AdminStandList";
import React from "react";
import JuryPage from "../pages/jury/JuryPage";
import QrCodePage from "../pages/admin/QrCodePage";

const adminRoutes = <Routes>
    <Route index element={ <AdminPage /> } />
    <Route path="/events" element={ <AdminEvent /> } />
    <Route path="/events/edit/:id" element={ <AdminEventEdit /> } />
    <Route path="/stand/edit/:id" element={ <AdminStandEdit /> } />
    <Route path="/events/:id/stands" element={ <AdminStandList /> } />
    <Route path={ "/jury" } element={ <JuryPage /> } />
    <Route path={"/qr/:eventId"} element={<QrCodePage />} />
</Routes>;

export default adminRoutes;