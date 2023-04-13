import {Link} from "react-router-dom";
import Auth from "../../utils/auth";
import EditStand from "../../components/EditStand/EditStand";
const ExhibitorEditPage = () => {
    const auth = new Auth();

    return <div style={{display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column"}}>
        <div style={{marginTop: "10px"}}>
            <Link style={{position: "absolute", left: "10px"}} to="/Exhibitor">
                <button type={"submit"}>Exhibitor page</button>
            </Link>
        </div>
        <h1 style={{textAlign: "center", width: "fit-content"}}>Rediger stand</h1>
        <br/>
        <EditStand isAdd={false} getStandUrl={`/api/admin/exhibitor/stand?exhibitor=${auth.getUser()?.username}`} />
    </div>
}
export default ExhibitorEditPage;