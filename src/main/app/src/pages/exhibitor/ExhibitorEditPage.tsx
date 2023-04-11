import {Link, useLocation, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import restApi from "../../utils/restApi";
import {StandModel} from "../../model/Stand";
import {useParams} from "react-router";
import Auth from "../../utils/auth";
const ExhibitorEditPage = () => {
    const auth = new Auth();
    const navigate = useNavigate();

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

    const [stand, setStand] = useState<StandModel | null>(null)

    useEffect(() => {
        getStand();
    }, []);

    const getStand = async () => {
        const result = await restApi({url: `/api/admin/exhibitor/stand?exhibitor=${auth.getUser()?.username}`, method: "GET"})

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
            if (result.status == 200) {
                navigate("/Exhibitor")
            }
        }

        return <div>
            <div style={{display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column"}}>
                <div style={{marginTop: "10px"}}>
                    <Link style={{position: "absolute", left: "10px"}} to="/Exhibitor">
                        <button type={"submit"}>Exhibitor page</button>
                    </Link>
                </div>
                <h1 style={{textAlign: "center", width: "fit-content"}}>Exhibitor edit page</h1>
                <br/>
                <input type="text" style={{border: "1px solid black", padding: "5px"}} defaultValue={stand?.title} placeholder={"Enter stand name here:"} onChange={(e) => stand!.title = e.target.value}/>
                <br/>
                <textarea
                    rows={5}
                    cols={50}
                    style={{border: "1px solid black", padding: "5px", height: "200px", width: "600px"}}
                    placeholder="Enter your stand description here:"
                    onChange={(e) => stand!.description = e.target.value}
                    defaultValue={stand?.description}
                ></textarea>
                <br/>
                <input type="file" onChange={handleImageUpload}/>
                <br/>
                <button style={{marginBottom:"10px"}} type="submit" onClick={update} className="save-button">Save</button>
            </div>
        </div>

}
export default ExhibitorEditPage;