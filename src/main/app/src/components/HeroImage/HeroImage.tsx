import "./HeroImage.css";
import {useEffect, useState} from "react";
import restApi from "../../utils/restApi";

const HeroImage = () => {
    const [event, setEvent] = useState<EventModel | null>(null);

    const getEvent = async() => {
        const result = await restApi({url: "/api/admin/events/newest", method: "GET"});
        if (result.status === 200) {
            setEvent(result.body)
        }
    }

    useEffect(() => {
        getEvent();
    }, []);

    return <div className={"heroimage-parent"} style={{backgroundImage: `url(${event?.image})`, backgroundPosition: "bottom"}}>
        <div className={"overlay"}></div>
        <div className={"content center"}>
            <h1>{event?.name}</h1>
        </div>
    </div>
}

export default HeroImage;