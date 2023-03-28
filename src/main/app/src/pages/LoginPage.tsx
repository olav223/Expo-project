import {useState} from "react";
import Auth from "../utils/auth";
import {useNavigate, useRoutes} from "react-router";

const LoginPage = () => {
    const [code,setCode] = useState("");
    const navigate = useNavigate();

    const auth = new Auth();

    const login = async() => {
        //const rest:boolean = await auth.signIn(code);
        //if (rest) navigate("/");
    }

    return <div>
        <input type="text" placeholder="Skriv inn koden" onChange={(e) => setCode(e.target.value)} />
        <button onClick={login}>Logg inn</button>
    </div>
}

export default LoginPage;