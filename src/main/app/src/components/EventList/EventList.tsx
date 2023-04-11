import "./EventList.css"
import {useEffect, useState} from "react";
import restApi from "../../utils/restApi";
import {Link} from "react-router-dom";

const EventList = () => {
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

    const formatDate = (date:string):string => {
        const newDate = new Date(date);
        return newDate.getDate() + "." + (newDate.getMonth()+1) + "." + newDate.getFullYear();
    }

    return events.length > 0 ?<>
        <h3 style={{textAlign: "center"}}>Andre hendelser</h3>
        <div className={"eventlist-parent"}>
            {events.map((event) => {
                return <Link to={"/stands?eventId="+event.id}>
                    <div className={"event-item box"} style={{backgroundImage:`url(${event.image})`}}>
                        <div className={"overlay"}></div>
                        <div className={"content center"}>
                            <h2>{event.name}</h2>
                            <p>
                                {formatDate(event.eventStart)}
                                {" - "}
                                {formatDate(event.eventEnd)}
                            </p>
                        </div>
                    </div>
                </Link>
            })}
        </div>
    </> : null;
}

export default EventList;