import restApi from "../../utils/restApi";
import {useEffect, useState} from "react";
import "./StandList.css";
import {Link} from "react-router-dom";
import shortenText from "../../utils/shortenText";

export const StandList = () => {
    const [stands, setStands] = useState([]);

    const url = window.location.href.split("?")[1];
    const params = new URLSearchParams(url);
    let eventId = "1";

    const getStands = async() => {
        const result = await restApi({url: "/api/stand/all?eventID="+eventId, method: "GET"});
        if (result.status === 200) {
            setStands(result.body)
        }
    }

    useEffect(() => {
        if (params.has("eventId")) eventId = params.get("eventId") ?? "";
        getStands();
    }, []);

    return <div className="standList">
        <h2>Stands</h2>
        {stands.length > 0 ? stands.map((item,i) => {
            return <div key={"stand-"+i} className="standItem box">
                <div>
                    <h4 style={{marginTop: 0}}>{item["title"]}</h4>
                    <p>{shortenText(item["description"], 275)}</p>
                </div>
                <Link to={"/stand?id="+item["id"]+"&event="+item["eventID"]}>
                    <button type={"submit"} style={{fontSize: "12px",padding: "12px 25px",float:"left"}}>INFO</button>
                </Link>
            </div>
        }) : <div>Ingen stands</div>}
    </div>
}