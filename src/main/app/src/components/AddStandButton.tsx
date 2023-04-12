import { StandModel } from "../model/Stand"
import restApi from "../utils/restApi"

const AddStand = (props : StandModel) => {

    const add = async() => {
        const result = await restApi({url : '/api/stand/add', method : 'POST',
        body : 
            {
                "title" : props.title,
                "description" : props.description,
                "image" : props.image,
                "url"   : props.url,
                "eventID" : props.eventID,
                "responsibleID" : props.responsibleID === "" ? null : props.responsibleID
            }
        })
        if (result.status == 200) window.location.reload();
    }

    return (
        <div>
            <button type="submit" onClick={add}>Legg til stand</button>
        </div>
    )
}

export default AddStand