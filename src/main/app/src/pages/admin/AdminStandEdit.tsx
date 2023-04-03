import { useEffect, useState } from "react";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";
import { useParams } from "react-router";
import './Admin.css'

const AdminStandEdit = () => {

    const [stand, setStand] = useState<StandModel | null>(null)

    useEffect(() => {
        getStand();
    }, []);

    const {id} = useParams()
    
    const update = async () => {

        await restApi({url : `/api/stand/update`, method : "POST", body : 
        {
                "id" : id,
                "title" : stand!.title,
                "description" : stand!.description,
                "image" : stand!.image,
                "url" : stand!.url,
                "eventID" : stand!.eventID,
                "responsibleID" : stand!.responsibleID
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
        <input type="text" placeholder={`${stand?.title}`} onChange={(e) => stand!.title = e.target.value}/>
        <input type="text" placeholder={`${stand?.description}`} onChange={(e) => stand!.description = e.target.value}/>
        <input type="text" placeholder={`${stand?.image}`} onChange={(e) => stand!.image = e.target.value}/>
        <input type="text" placeholder={`${stand?.url}`} onChange={(e) => stand!.url = e.target.value}/>
        <input type="text" placeholder={`${stand?.responsibleID}`} onChange={(e) => stand!.responsibleID = e.target.value}/>

        <button type="submit" onClick={update} className="save-button">Save</button>
        </form>
        <QRCode/>
    </div>
    )
}

export default AdminStandEdit;
