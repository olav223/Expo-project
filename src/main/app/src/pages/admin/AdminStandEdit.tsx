import { useEffect, useState } from "react";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";
import { useParams } from "react-router";
import './Admin.css'

const AdminStandEdit = () => {

    const [stand, setStand] = useState<StandModel | null>(null)

    useEffect(() => {
        getStand();
    });

    const [title, setTitle] = useState(stand?.title)
    const [desciption, setDesciption] = useState(stand?.description)
    const [image, setImage] = useState(stand?.image)
    const [url, setUrl] = useState(stand?.url)
    const [responsible, setResponsible] = useState(stand?.responsibleID)

    const updateTitle = (event : any) => {
            setTitle(event.target.value)
    }
    const updateDesciption = (event : any) => {
        setDesciption(event.target.value)
    }
    const updateImage = (event : any) => {
        setImage(event.target.value)
    }
    const updateUrl = (event : any) => {
        setUrl(event.target.value)
    }
    const updateResponsible = (event : any) => {
        setResponsible(event.target.value)
    }

    const {id} = useParams()
    
    const update = async () => {

        await restApi({url : `/api/stand/update`, method : "POST", body : 
        {
                "id" : id,
                "title" : title,
                "description" : desciption,
                "image" : image,
                "url" : url,
                "eventID" : stand?.eventID,
                "responsibleID" : responsible
        }
    })}

    const getStand = async() => {
        const result = await restApi({url : `/api/stand?id=${id}`, method : "GET"})

        if(result.status === 200){
            setStand(result.body)
        }
    }
    function QRCode(){
        let url = 'https://api.qrserver.com/v1/create-qr-code/?data=http://ider-database.westeurope.cloudapp.azure.com:8080/Prosjekt-Expo-0.0.1-SNAPSHOT/stand?id='
        return<div className="qr">
            <img src={url + stand?.id} alt="QrCode"/>
        </div>
    }

    return( 
    <div className="wrapper">
        <form>
        <input type="text" placeholder={`${stand?.title}`} onChange={updateTitle}/>
        <input type="text" placeholder={`${stand?.description}`} onChange={updateDesciption}/>
        <input type="text" placeholder={`${stand?.image}`} onChange={updateImage}/>
        <input type="text" placeholder={`${stand?.url}`} onChange={updateUrl}/>
        <input type="text" placeholder={`${stand?.responsibleID}`} onChange={updateResponsible}/>

        <button type="submit" onClick={update} className="save-button">Save</button>
        </form>
        <QRCode/>
    </div>
    )
}

export default AdminStandEdit;
