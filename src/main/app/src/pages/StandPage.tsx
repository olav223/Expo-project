import restApi from "../utils/restApi";
import {useEffect, useState} from "react";
import VotingStars from "../components/VotingStars/VotingStars";

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
        <div>
            <VotingStars standId={parseInt(params.get("id") ?? "")} />
            <img src={stand?.image} />
            <h2>{stand?.title}</h2>
            <p>{stand?.description}</p>
        </div>
        :
        <div>Mangler stand id i url</div>
}

export default StandPage;