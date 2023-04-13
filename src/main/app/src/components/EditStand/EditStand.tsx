import {useEffect, useState} from "react";
import {StandModel} from "../../model/Stand";
import restApi from "../../utils/restApi";
import "./EditStand.css";

interface EditStandProps {
    getStandUrl: string;
    isAdd: boolean;
    eventId?:string | null;
}

const EditStand = (props: EditStandProps) => {
    const [stand, setStand] = useState<StandModel | null>(null)

    useEffect(() => {
        if (!props.isAdd) getStand();
        else setStand({id:0,url:"",responsibleID:"",eventID:1,image:"",description:"",title:""})
    }, [])

    const handleImageUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
        const file = event.target.files ? event.target.files[0] : null; // add null check
        if (file) {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                const imageData = reader.result;
                console.log(imageData);
            }
        }
    }

    const getStand = async () => {
        const result = await restApi({url: props.getStandUrl, method: "GET"})

        if (result.status === 200) {
            setStand(result.body)
        }
    }

    const update = async () => {
        const result = await restApi({
            url: `/api/stand/update`, method: "POST", body:
                {
                    "id": stand!.id,
                    "title": stand!.title,
                    "description": stand!.description,
                    "image": stand!.image,
                    "url": stand!.url,
                    "eventID": stand!.eventID,
                    "responsibleID": stand!.responsibleID
                }
        });
        if (result.status == 200) window.history.back()
    }

    const add = async() => {
        const result = await restApi({url : '/api/stand/add', method : 'POST',
            body :
                {
                    "title" : stand!.title,
                    "description" : stand!.description,
                    "image" : stand!.image,
                    "url"       : stand!.url,
                    "eventID" : stand!.eventID,
                    "responsibleID" : stand!.responsibleID === "" ? null : stand!.responsibleID
                }
        })
        if (result.status == 200) window.history.back();
    }

    return <div className={"modify-stand-parent"}>
        <label>Stand navn</label>
        <input type="text" defaultValue={stand?.title} onChange={(e) => stand!.title = e.target.value}/>
        <label>Stand beskrivelse</label>
        <textarea
            rows={5}
            cols={50}
            style={{height: "300px"}}
            onChange={(e) => stand!.description = e.target.value}
            defaultValue={stand?.description}
        ></textarea>
        <label>Stand bilde</label>
        <input type="file" onChange={handleImageUpload}/>
        <button style={{marginBottom:"100px"}} type="submit" onClick={props.isAdd ? add : update} className="save-button">Save</button>
    </div>
}

export default EditStand;