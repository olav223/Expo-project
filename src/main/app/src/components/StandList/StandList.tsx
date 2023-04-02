import restApi from "../../utils/restApi";
import {useEffect, useState} from "react";
import "./StandList.css";
import {Link} from "react-router-dom";
import shortenText from "../../utils/shortenText";

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
        <h2>Stands</h2>
        {stands.length > 0 ? stands.map((item,i) => {
            return <div key={"stand-"+i} className="standItem box">
                <div>
                    <h4>{item["title"]}</h4>
                    <p>{shortenText(item["description"], 275)}</p>
                </div>
                <Link to={"/stand?id="+item["id"]+"&event="+item["eventID"]}><div className={"stand-link"}>
                    <div className={"center"}>INFO</div>
                </div></Link>
            </div>
        }) : <div>Ingen stands</div>}
    </div>
}