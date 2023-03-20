import restApi from "../utils/restApi";
import {useEffect, useState} from "react";
import "./StandList.css";

export const StandList = () => {
    const [stands, setStands] = useState([]);

    const getStands = async() => {
        const result = await restApi({url: "/api/stand/all?eventID=1", method: "GET"});
        if (result.status === 200) {
            setStands(result.body)
        }
    }

    const vote = async(id:number) => {
        const result = await restApi({url: "/api/stand?id="+id, method: "POST"});
        if (result.status === 200) {
            window.alert("Stemme sendt inn!");
        }
    }

    useEffect(() => {
        getStands();
    }, []);

    return <div className="standList">
        {stands.length > 0 ? stands.map((item,i) => {
            return <div key={"stand-"+i} className="standItem">
                <div>
                    <h4>{item["name"]}</h4>
                    <p>{item["description"]}</p>
                </div>
                <button onClick={() => vote(2)}>Stem på</button>
            </div>
        }) : <div>Ingen stands</div>}
    </div>
}