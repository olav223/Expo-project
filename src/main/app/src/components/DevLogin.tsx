import {useState} from "react";
import Auth from "../utils/auth";
import notification from "../utils/notification";

const DevLogin = () => {
    const [vis,setVis] = useState(false);
    const auth = new Auth();

    const Options = () => {
        return <div>
            <h3>Velg logg inn type</h3>
            <button type={"submit"} onClick={() => login(0)} >Admin</button>
            <button type={"submit"} onClick={() => login(1)} style={{marginLeft:"10px"}}>Jury</button>
            <button type={"submit"} onClick={() => login(2)} style={{marginLeft:"10px"}}>Exhibitor</button>
            </div>
    }

    const login = (accessLvl:number) => {
        const data:UserModel = {username: "Name", accessLevel: accessLvl};
        auth.storeUser(data);
        notification({text: "Logget inn", type: "success"});
        auth.redirect(data);
    }

    return <div style={{marginLeft:"10px"}}>
        {vis && (<Options />)}
        <button onClick={() => setVis(!vis)}>{vis ? "Lukk" : "DEV Login"}</button>
    </div>
}

export default DevLogin;