import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import FrontPage from "./pages/FontPage";
import StandPage from "./pages/StandPage";
import LoginPage from "./pages/LoginPage";
import Navbar from "./components/Navbar/Navbar";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import StandsPage from "./pages/StandsPage";
import Protected from "./utils/Protected";
import adminRoutes from "./routes/adminRoutes";
import Page404 from "./pages/Page404";
import juryRoutes from "./routes/juryRoutes";
import exhibitorRoutes from "./routes/exhibitorRoutes";
import {useVisitorData} from "@fingerprintjs/fingerprintjs-pro-react";

function App() {
    const {isLoading, error, data} = useVisitorData(
        {extendedResult: true},
        {immediate: true}
    );

    if (isLoading) return <div>Loading...</div>;
    if (error || data === undefined) return <div>Fy fy!!</div>;

    const exhibitorElms = exhibitorRoutes(data.visitorId);

    return <BrowserRouter basename={ process.env.PUBLIC_URL }>
        <Navbar />
        <ToastContainer autoClose={3000} />
        <Routes>
            <Route index element={ <FrontPage /> } />
            <Route path="/stands" element={ <StandsPage /> } />
            <Route path="/stand" element={ <StandPage id={data.visitorId} /> } />
            <Route path="/login" element={ <LoginPage /> } />
            <Route path="/admin/*" element={ <Protected accesslvl={0}>{adminRoutes}</Protected> } />
            <Route path="/jury/*" element={ <Protected accesslvl={1}>{juryRoutes}</Protected> } />
            <Route path="/exhibitor/*" element={ <Protected accesslvl={2}>{exhibitorElms}</Protected> } />
            <Route path="*" element={<Page404 />} />
        </Routes>
    </BrowserRouter>
}

export default App;
