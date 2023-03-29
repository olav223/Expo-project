import {useState} from "react";
import "./LoginAuth.css";
import {useNavigate} from "react-router";
import notification from "../../utils/notification";
import Auth from "../../utils/auth";
const LoginAuth = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const auth = new Auth();
    const navigate = useNavigate();

    const url = window.location.href.split("?")[1];
    const params = new URLSearchParams(url);

    const disableBtn = password == "" || email == "";

    const login = async() => {
        if (disableBtn) {
            notification({type: "error", text: "Mangler felt"});
        } else {
            const res = await auth.login(email,password);
            if (res) {
                notification({type: "success", text: "Logget inn!"});
                redirect();
            } else {
                notification({type: "error", text: "Logg inn feilet"});
            }
        }
    }

    const redirect = () => {
        const url = params.get("url") ?? "";
        if (url !== "") {
            navigate(url);
        } else {
            navigate("/");
        }
    }

    return <div className={"login-container center box"}>
        <h2>Logg inn</h2>
        <input onChange={(e) => setEmail(e.target.value)} placeholder={"Brukernavn"} />
        <input onChange={(e) => setPassword(e.target.value)} type="password" placeholder={"Passord"} />
        <button onClick={login} type="submit" disabled={disableBtn}>Logg inn</button>
    </div>
}

export default LoginAuth;