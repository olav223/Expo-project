import {Route, Routes} from "react-router-dom";
import React from "react";
import JuryPage from "../pages/jury/JuryPage";
import JurySummary from "../pages/jury/JurySummary";

const juryRoutes = <Routes>
    <Route index element={ <JuryPage /> } />
    <Route path="/summary" element={ <JurySummary /> } />
</Routes>;
    export default juryRoutes;