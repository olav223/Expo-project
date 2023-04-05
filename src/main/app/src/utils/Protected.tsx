import {ReactNode} from "react";
import Auth from "./auth";
import {Navigate} from "react-router";

interface ProtectedProps {
    accesslvl: number;
    children: ReactNode;
}

const Protected = (props:ProtectedProps) => {
    const auth = new Auth();

    const user = auth.getUser();
    if (user === null || props.accesslvl !== user.accessLevel) return <Navigate to={"/login"} />

    return <div>{props.children}</div>;
}

export default Protected;