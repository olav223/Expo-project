import restApi from "../utils/restApi";
import {useEffect, useState} from "react";
import VotingStars from "../components/VotingStars/VotingStars";
import { StandModel } from "../model/Stand";

const StandPage = () => {
    const [stand, setStand] = useState<StandModel | null>(null);
    const url = window.location.href.split("?")[1];
    const params = new URLSearchParams(url);

    const getStandInfo = async() => {
        const result = await restApi({url: "/api/stand?id="+params.get("id"), method: "GET"});
        if (result.status === 200) {
            const stand:StandModel = result.body as StandModel;
            setStand(stand);
        }
    }

    useEffect(() => {
        getStandInfo();
    }, []);

    return params.has("id") ?
        <div style={{textAlign:"center"}}>
            <h2>{stand?.title}</h2>
            <div style={{textAlign:"center", marginTop: '10px'}}>
            <VotingStars />
            </div>
            <p style={{marginLeft: '50px', marginRight: '50px'}}>{stand?.description}</p>
            <img style={{ width: '50%', height: 'auto', marginBottom: '100px'}}
                 src={stand?.image} />
        </div>
        :
        <div>Mangler stand id i url</div>
}

export default StandPage;