import {Route, Routes} from "react-router-dom";
import React from "react";
import ExhibitorPage from "../pages/exhibitor/ExhibitorPage";
import ExhibitorEditPage from "../pages/exhibitor/ExhibitorEditPage";

const exhibitorRoutes = (id: string) => <Routes>
    <Route index element={ <ExhibitorPage id={id} /> } />
    <Route path="/edit" element={ <ExhibitorEditPage /> } />
</Routes>;

    export default exhibitorRoutes;