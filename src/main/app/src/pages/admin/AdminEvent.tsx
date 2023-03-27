import { useEffect, useState } from "react";
import restApi from "../../utils/restApi";

const AdminEvent = () => {
    
    const [events, setEvents] = useState([]);
    
    const getEvents =async () => {
        const result = await restApi({url : "/api/event/admin?username=exhibitor1", method : "GET"})
        if(result.status === 200){
            setEvents(result.body)
        }
    }
    
    useEffect(() => {
        getEvents();
    }, [])
    
    return (
        <div className="standList">
        {events.length > 0 ? events.map((item,i) => {
            return <div key={"event-"+i} className="standItem">
                <div>
                    <h4>{item["title"]}</h4>
                    <p>{item["description"]}</p>
                </div>
            </div>
        }) : <div>Ingen Arrengementer</div>}
    </div>
    )
}


export default AdminEvent;