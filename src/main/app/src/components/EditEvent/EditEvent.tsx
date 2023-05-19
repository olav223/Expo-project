import {useEffect, useState} from "react";
import restApi from "../../utils/restApi";
import "./EditEvent.css";

interface EditEventProps {
    getEventUrl: string;
    isAdd: boolean;
}

const EditStand = (props: EditEventProps) => {
    const [event, setEvent] = useState<EventModel | null>(null)

    useEffect(() => {
        if (!props.isAdd) getEvent();
        else setEvent({id:0,name:"",eventStart:"",image:"",eventEnd:""})
    }, [])

    const getEvent = async () => {
        const result = await restApi({url: props.getEventUrl, method: "GET"})

        if (result.status === 200) {
            setEvent(result.body)
        }
    }

    const update = async () => {
        const result = await restApi({
            url: `/api/admin/event`, method: "PATCH", body:
                {
                    "id": event!.id,
                    "name": event!.name,
                    "eventStart": event!.eventStart.replace("T"," "),
                    "image": event!.image,
                    "eventEnd": event!.eventEnd.replace("T"," "),
                }
        });
        if (result.status == 200) window.history.back()
    }

    const add = async() => {
        const result = await restApi({url : '/api/admin/event', method : 'POST',
            body :
                {
                    "name": event!.name,
                    "eventStart": event!.eventStart.replace("T"," "),
                    "image": event!.image,
                    "eventEnd": event!.eventEnd.replace("T"," "),
                }
        })
        if (result.status == 200) window.history.back();
    }

    return <div className={"modify-stand-parent"}>
        <label>Event navn</label>
        <input type="text" defaultValue={event?.name} onChange={(e) => event!.name = e.target.value}/>
        <label>Start dato</label>
        <input type="datetime-local" defaultValue={event?.eventStart} onChange={(e) => event!.eventStart = e.target.value}/>
        <label>Slutt dato</label>
        <input type="datetime-local" defaultValue={event?.eventEnd} onChange={(e) => event!.eventEnd = e.target.value}/>
        <label>Forsidebilde (url)</label>
        <input type="text" defaultValue={event?.image} onChange={(e) => event!.image = e.target.value}/>
        <button style={{marginBottom:"100px"}} type="submit" onClick={props.isAdd ? add : update} className="save-button">Save</button>
    </div>
}

export default EditStand;