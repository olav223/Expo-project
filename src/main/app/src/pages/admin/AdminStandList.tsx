import { useEffect, useState } from "react";
import Popup from 'reactjs-popup';
import { Link, useLocation, useParams } from "react-router-dom";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";
import AddStand from "../../components/AddStandButton";
import './Admin.css'

const AdminStandList = () => {
    
    const [stands, setStands] = useState<StandModel [] | null>(null);
    const [title, setTitle] = useState("");
    const [desciption, setDesciption] = useState("")
    const [image, setImage] = useState("")
    const [url, setUrl] = useState("")
    const [responsible, setResponsible] = useState("")

    const {id} = useParams();

    const numID = parseInt(`${id}`)
    
    const handleTitle = (event : any) => {
        setTitle(event.target.value)
    } 

    const handleDescription = (event : any) => {
        setDesciption(event.target.value)
    } 

    const handleImage = (event : any) => {
        setImage(event.target.value)
    } 

    const handleUrl = (event : any) => {
        setUrl(event.target.value)
    }
    const handleResponsible = (event : any) => {
        setResponsible(event.target.value)
    } 

    //FIXME
    //Uansett hvilken id som vi gir, blir alle standene vist uavhengig av iden
    const getStands = async() => {
        const result = await restApi({url: `/api/stand/all?eventID=${id}`, method: "GET"});
        if (result.status === 200) {
            setStands(result.body)
        }
    }

    const deleteStand = async(id : any) => {
        await restApi({url : `/api/stand`, method : 'DELETE', body : id
    });
    }

    useEffect(() => {
        getStands();
    }, []);

    return <div className="standList">
        <h2>Stands</h2>
        <Popup trigger={<button>Add new stand</button>} position='bottom center' contentStyle={{
            background: "lightgray",
            padding: "5px",
        }}>
            <div>
                Popup test
                <form>
                    <label>
                        Title:
                    </label>
                    <input type="text" onChange={handleTitle}/>
                    <label>
                        Description:
                    </label>
                    <input type="desciption"onChange={handleDescription}/>
                    <label>
                        Image:
                    </label>
                    <input type="text" onChange={handleImage}/>
                    <label>
                        URL:
                    </label>
                    <input type="text" onChange={handleUrl}/>
                    <label>
                        Responsible: 
                    </label>
                    <input type="text" onChange={handleResponsible}/>
                </form>
                <AddStand id={0} title={title} description={desciption} image={image} url={url} eventID={numID} responsibleID={responsible}/>
            </div>
        </Popup>
        {stands != null ? stands.map((stand,i) => {
            return <div key={"stand-"+i} className="standItem box">
                <div>
                    <h4>{stand.title}</h4>
                    <p>{stand.description}</p>
                    <Link to={`/admin/stand/edit/${stand.id}`}>
                        <button type="submit"className="stands-button">
                            Edit
                        </button>
                    </Link>
                    <button onClick={() => deleteStand(stand.id)} className="stands-button">
                        Slett stand    
                    </button>
                </div>
            </div>
        }) : <div>Ingen stands</div>}
    </div>
}

export default AdminStandList;
