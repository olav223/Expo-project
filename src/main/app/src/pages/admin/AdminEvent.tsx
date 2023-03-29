import { useEffect, useState } from "react";
import restApi from "../../utils/restApi";
import Event from "../../model/ExpoEvent"
import { Link } from "react-router-dom";

const AdminEvent = () => {
    
    const [events, setEvents] = useState<Event [] | null>(null);
    
    const getEvents = async () => {
        const result = await restApi({url : "/api/admin/events/all", method : "GET"})
        if(result.status === 200){
            setEvents(result.body)
        }
    }
    //TODO
    //Denne kaster en PSQLException i backend (forventer en int)
    const getEventByAdmin = async (username:string) => {
        const result = await restApi({url : `/api/admin/events?username=${ username }`, method : "GET"})
        if(result.status === 200)
            setEvents(result.body)
    }
    
    useEffect(() => {
        getEvents();
    }, [])
    
    return (
        <div className="standList">
        {events != null ? events?.map((event) => {
            return <div key={"event-"+event.id} className="standItem">
                <div>
                    <h4>
                        <Link to={`/edit?id=${event.id}`} style={{textDecoration : 'none', color:'black'}}>{event.name}</Link>
                    </h4>
                    <p></p>
                </div>
            </div>
        }) : <div>Ingen Arrengementer</div>}
    </div>
    )
}


export default AdminEvent;