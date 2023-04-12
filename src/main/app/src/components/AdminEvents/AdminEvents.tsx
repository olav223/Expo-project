import {useEffect, useState} from "react";
import restApi from "../../utils/restApi";
import {Link} from "react-router-dom";
import "./AdminEvents.css";

const AdminEvents = () => {
    const [events, setEvents] = useState<Array<EventModel>>([]);

    const getEvents = async() => {
        const result = await restApi({url: "/api/admin/events/all", method: "GET"});
        if (result.status === 200) {
            setEvents(result.body)
        }
    }

    useEffect(() => {
        getEvents();
    }, []);

    return events.length > 0 ?<>
        <div className={"admin-events-parent"}>
            <h2>Hendelser du administrerer</h2>
            {events.map((event) => {
                return <div className={"event-item box"} >
                    <div className={"image-layer"} style={{backgroundImage:`url(${event.image})`}}>
                        <div className={"overlay"}></div>
                        <div className={"content center"}>
                            <h2>{event.name}</h2>
                        </div>
                    </div>
                    <div style={{padding: "20px"}}>
                        <Link className={"submit-btn"} to={"events/"+event.id+"/stands"}>Se stands</Link>
                        <Link className={"submit-btn"} to={"events/edit/"+event.id}>Rediger</Link>
                    </div>
                </div>
            })}
        </div>
    </> : null;
}

export default AdminEvents;