import { useParams } from "react-router";
import './Admin.css'
import EditStand from "../../components/EditStand/EditStand";
import Backbtn from "../../components/Backbtn/Backbtn";

/**
 * Side for å endre info om en stand.
 * @author Torbjørn Vatnelid & Martin Berg Alstad
 */
const AdminStandEdit = () => {
    const {id} = useParams()

    const url = window.location.href.split("?")[1];
    const params = new URLSearchParams(url);

    return (
        <div style={{display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column", position: "relative"}}>
            <Backbtn />
            <h2>{id==="-1" ? "Lag stand" : "Rediger stand"}</h2>
            <EditStand isAdd={id==="-1"} eventId={parseInt(params.get("eventId")!)} getStandUrl={`/api/stand?id=${id}`} />
        </div>
    )
}

export default AdminStandEdit;
