import React from "react";
import {RouteObject} from "react-router-dom";
import FrontPage from "./pages/FontPage";

const routes: RouteObject[] = [
    {
        path: "",
        element: <FrontPage />
    }
];

export default routes;