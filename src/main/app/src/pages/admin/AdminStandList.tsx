import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";
import './Admin.css'
import {StandList} from "../../components/StandList/StandList";

const AdminStandList = () => {
    const {id} = useParams();

    const deleteStand = async(id : any) => {
        const result = await restApi({url : `/api/stand`, method : 'DELETE', body : id});
        if (result.status == 200) window.location.reload();
    }

    return <div className="standList">
        <h2>Stands</h2>
        <Link to={`/admin/stand/edit/-1?eventId=${id}`}><button className={"submit-btn"}>Legg til en ny stand</button></Link>
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
