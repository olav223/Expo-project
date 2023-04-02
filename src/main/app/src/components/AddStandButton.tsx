import { StandModel } from "../model/Stand"
import restApi from "../utils/restApi"

const AddStand = (props : StandModel) => {

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