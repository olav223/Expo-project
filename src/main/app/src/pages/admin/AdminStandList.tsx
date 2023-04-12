import { useEffect, useState } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";
import AddStand from "../../components/AddStandButton";
import './Admin.css'
import {StandList} from "../../components/StandList/StandList";
import Popup from "../../components/Popup/Popup";

const AdminStandList = () => {
    const [showAdd,setShowAdd] = useState(false);
    const [newStand] = useState<StandModel>({title:"",url:"",responsibleID:"",image:"",id:-1,description:"",eventID:-1})

    const {id} = useParams();
    const numID = parseInt(`${id}`)

    const deleteStand = async(id : any) => {
        const result = await restApi({url : `/api/stand`, method : 'DELETE', body : id});
        if (result.status == 200) window.location.reload();
    }

    useEffect(() => {
        newStand.eventID = numID;
    }, [])

    const AddStandPopup = () => {
        return <Popup closeTrigger={() => setShowAdd(false)} show={showAdd} component={<div>
            <div>
                <form>
                    <label>
                        Tittel:
                    </label>
                    <input type="text" onChange={(e) => newStand.title = e.target.value}/>
                    <label>
                        Beskrivelse:
                    </label>
                    <input type="desciption"onChange={(e) => newStand.description = e.target.value} />
                    <label>
                        Bilde:
                    </label>
                    <input type="text" onChange={(e) => newStand.image = e.target.value} />
                    <label>
                        URL:
                    </label>
                    <input type="text" onChange={(e) => newStand.url = e.target.value} />
                    <label>
                        Ansvarlig:
                    </label>
                    <input type="text" onChange={(e) => newStand.responsibleID = e.target.value} />
                </form>
                <AddStand {...newStand} />
            </div>
        </div>} />
    }

    return <div className="standList">
        <h2>Stands</h2>
        <button className={"submit-btn"} onClick={() => setShowAdd(true)}>Legg til en ny stand</button>
        <AddStandPopup />
        <StandList eventId={id} diableHeader components={(stand) =>
            <div style={{textAlign: "left"}}>
                <Link to={`/admin/stand/edit/${stand.id}`}>
                    <button type="submit">
                        Endre
                    </button>
                </Link>
                <button type="submit" className="delete-button" onClick={() => deleteStand(stand.id)}>Slett</button>
            </div>
        } />
    </div>
}

export default AdminStandList;
