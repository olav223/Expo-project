import { StandModel } from "../model/Stand"
import restApi from "../utils/restApi"

type OmitId = Omit<StandModel, "id">;

const AddStand = (props : OmitId) => {

    const add = async() => {
        await restApi({url : '/api/stand/add', method : 'POST',
        body : 
            {
                "title" : props.title,
                "description" : props.description,
                "image" : props.image,
                "url"   : props.url,
                "eventID" : props.eventID,
                "responsibleID" : props.responsibleID 
            }
        })
    }

    return (
        <div>
            <button onClick={add}>Add</button>
        </div>
    )
}

export default AddStand