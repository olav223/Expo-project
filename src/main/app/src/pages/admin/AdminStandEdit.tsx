import { useEffect, useState } from "react";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";

const AdminStandEdit = () => {

    const [stand, setStand] = useState<StandModel | null>(null)
    
    const update = async () => {

        await restApi({url : `/api/stand/update?stand`, method : "POST", body : {
            "stand" : {
                "title" : document.getElementById("title"),
                "description" : document.getElementById("description")  
            }
        }})}

    const getStand = async() => {
        const result = await restApi({url : `/api/stand?id=1`, method : "GET"})

        if(result.status === 200){
            setStand(result.body)
        }
    }

    useEffect(() => {
        getStand();
    });
        
    return( 
    <div>
        <form>
        <input type={"text"} placeholder={`${stand?.title}`} id="title"/>
        <input type={"text"} placeholder={`${stand?.description}`} id="description"/>
        
        <button type="submit" value={"Save"} onClick={update}>Save</button>
        </form>
        {/* AdminStandEdit */}
    
    
    
    
    
    </div>
    )
}

export default AdminStandEdit;