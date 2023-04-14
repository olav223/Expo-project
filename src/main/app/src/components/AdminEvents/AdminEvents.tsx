import { useEffect, useState } from "react";
import restApi from "../../utils/restApi";
import { Link } from "react-router-dom";
import "./AdminEvents.css";
import Auth from "../../utils/auth";

const AdminEvents = () => {
    const [events, setEvents] = useState<Array<EventModel>>([]);
    const auth = new Auth();

    const getEvents = async () => {
        const result = await restApi({ url: "/api/admin/events?username="+auth.getUser()?.username, method: "GET" });
        if (result.status === 200) {
            setEvents(result.body);
        }
        else {
            console.error("Error fetching events: " + result);
        }
    };

    useEffect(() => {
        getEvents();
    }, []);

    return <div className={ "admin-events-parent" }>
            <h2>Hendelser du administrerer</h2>
            <Link to={`/admin/events/edit/-1`}><button className={"submit-btn"}>Lag ny event</button></Link>
            <br />
            { events.length > 0 ? events.map((event) =>
                <div className={ "event-item box" } key={ event.id }>
                    <div className={ "image-layer" } style={ { backgroundImage: `url(${ event.image })` } }>
                        <div className={ "overlay" } />
                        <div className={ "content center" }>
                            <h2 style={ { paddingBottom: "20px" } }>{ event.name }</h2>
                        </div>
                    </div>
                    <div style={ { padding: "20px" } }>
                        <div style={ { paddingBottom: "20px" } }>
                            <Link className={ "submit-btn" } to={ "events/" + event.id + "/stands" }>Se stands</Link>
                            <Link className={ "submit-btn" } to={ "events/edit/" + event.id }>Rediger</Link>
                        </div>
                        <Link className={ "submit-btn" } to={ `jury?eventID=${ event.id }` }>Se vurderinger</Link>
                    </div>
                </div>) : "Ingen events" }
        </div>
};

export default AdminEvents;