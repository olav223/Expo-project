import { useEffect, useState } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";

const AdminStandList = () => {
    
    const [stands, setStands] = useState<StandModel [] | null>(null);
    const eventId = useLocation();
    const query = new URLSearchParams(window.location.pathname);

    //TODO Hente event id fra url og bruke denne til å gi stand listen
    const getStands = async() => {
        const result = await restApi({url: `/api/stand/all?eventID=1`, method: "GET"});
        if (result.status === 200) {
            setStands(result.body)
        }
    }

    useEffect(() => {
        getStands();
    }, []);

    return <div className="standList">
        <h2>Stands</h2>
        {stands != null ? stands.map((stand,i) => {
            return <div key={"stand-"+i} className="standItem box">
                <div>
                    <h4>{stand.title}</h4>
                    <p>{stand.description}</p>
                    <Link to={`/admin/stand/edit?id=${stand.id}`}>
                        <button type="submit"className="stands-button">
                            Edit
                        </button>
                    </Link>
                </div>
            </div>
        }) : <div>Ingen stands</div>}
    </div>
}

export default AdminStandList;