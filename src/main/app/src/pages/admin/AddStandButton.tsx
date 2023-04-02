import restApi from "../../utils/restApi"

const AddStand = (
    props : {
        title : string,
        description : string,
        image : string,
        url : string,
        eventID : number
        responsible : string,
    }
) => {

    const add = async() => {
        await restApi({url : '/api/stand/add', method : 'POST',
        body : 
            {
                "title" : props.title,
                "description" : props.description,
                "image" : props.image,
                "url"   : props.url,
                "eventID" : props.eventID,
                "responsibleID" : props.responsible 
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