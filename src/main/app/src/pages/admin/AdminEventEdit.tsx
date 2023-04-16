import {useParams} from "react-router";
import Backbtn from "../../components/Backbtn/Backbtn";
import EditEvent from "../../components/EditEvent/EditEvent";

const AdminEventEdit = () => {
    const {id} = useParams()

    return <>
        <div style={{display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column", position: "relative"}}>
            <Backbtn />
            <h2>{id==="-1" ? "Lag event" : "Rediger event"}</h2>
            <EditEvent isAdd={id==="-1"} getEventUrl={`/api/admin/event?eventID=${id}`} />
        </div>
    </>
}

export default AdminEventEdit;