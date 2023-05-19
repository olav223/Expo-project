import { useEffect, useState } from "react";
import restApi from "../../utils/restApi";
import { Link } from "react-router-dom";
import "./AdminEvents.css";
import Notification from "../../utils/notification";

const AdminEvents = () => {
    const [events, setEvents] = useState<Array<EventModel>>([]);

    const getEvents = async () => {
        const result = await restApi({ url: "/api/admin/events/all", method: "GET" });
        if (result.status === 200) {
            setEvents(result.body);
        }
        else {
            console.error("Error fetching events: " + result);
        }
    };

    const deleteEvent = async (id:number) => {
        if (window.confirm("Er du sikker på at du vil slette?")) {
            const result = await restApi({ url: "/api/admin/event?eventID="+id, method: "DELETE" });
            if (result.status === 200) {
                Notification({type: "success", text: "Event slettet"});
                getEvents()
            }
            else {
                Notification({type: "error", text: "Sletting feilet!"});
            }
        }
    }

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
                                <h2>{ event.name }</h2>
                            </div>
                     </div>
                    <div style={ { padding: "20px" } }>
                        <div style={ { paddingBottom: "10px", display: "flex", gap: "5px" } }>
                            <Link className={ "submit-btn" } style={{margin: "0", flex: 2}} to={ "events/" + event.id + "/stands" }>Se stands</Link>
                            <Link className={ "submit-btn" } style={{margin: "0", background: "darkorange"}} to={ "events/edit/" + event.id }>
                                <span className="material-symbols-outlined" style={{fontSize: "15px", verticalAlign: "middle"}}>edit</span>
                            </Link>
                        </div>
                        <div style={ { display: "flex", gap: "5px" } }>
                            <Link className={ "submit-btn" } style={{margin: "0", flex: 2}} to={ `jury?eventID=${ event.id }` }>Se vurderinger</Link>
                            <button className={ "submit-btn" } style={{margin: "0", background: "darkred"}} onClick={() => deleteEvent(event.id)}>
                                <span className="material-symbols-outlined" style={{fontSize: "15px", verticalAlign: "middle"}}>delete</span>
                            </button>
                        </div>
                    </div>
                </div>) : "Ingen events" }
        </div>
};

export default AdminEvents;