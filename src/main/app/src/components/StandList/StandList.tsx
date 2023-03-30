import restApi from "../../utils/restApi";
import {useEffect, useState} from "react";
import "./StandList.css";
import {Link} from "react-router-dom";

export const StandList = () => {
    const [stands, setStands] = useState([]);

    const getStands = async() => {
        const result = await restApi({url: "/api/stand/all?eventID=1", method: "GET"});
        if (result.status === 200) {
            setStands(result.body)
        }
    }

    useEffect(() => {
        getStands();
    }, []);

    return <div className="standList">
        {stands.length > 0 ? stands.map((item,i) => {
            return <div key={"stand-"+i} className="standItem">
                <div>
                    <h4>{item["title"]}</h4>
                    <p>{item["description"]}</p>
                </div>
                <Link to={"/stand?id="+item["id"]}>Mer info</Link>
            </div>
        }) : <div>Ingen stands</div>}
    </div>
}