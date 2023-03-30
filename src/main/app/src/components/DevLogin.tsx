import {useState} from "react";
import Auth from "../utils/auth";
import notification from "../utils/notification";

const DevLogin = () => {
    const [vis,setVis] = useState(false);
    const auth = new Auth();
    const Options = () => {
        return <>
            <h3>Velg logg inn type</h3>
            <button type={"submit"} onClick={() => login(0)}>Admin</button>
            <button type={"submit"} onClick={() => login(1)}>Jury</button>
            <button type={"submit"} onClick={() => login(2)}>Exhibitor</button>
        </>
    }

    const login = (accessLvl:number) => {
        const data:UserModel = {username: "Name", access: accessLvl};
        auth.storeUser(data);
        notification({text: "Logget inn", type: "success"})
    }

    return <>
        {vis && (<Options />)}
        <button onClick={() => setVis(!vis)}>{vis ? "Lukk" : "DEV Login"}</button>
    </>
}

export default DevLogin;