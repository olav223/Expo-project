import restApi from "../utils/restApi";
import {useLayoutEffect, useState} from "react";
import VotingStars from "../components/VotingStars/VotingStars";
import { StandModel } from "../model/Stand";
import "./StandPage.css";
import Backbtn from "../components/Backbtn/Backbtn";
import logo from "./exhibitor/logoer.jpg";

const StandPage = () => {
    const [stand, setStand] = useState<StandModel | null>(null);
    const url = window.location.href.split("?")[1];
    const params = new URLSearchParams(url);

    const getStandInfo = async() => {

        const result = await restApi({url: "/api/stand?id="+params.get("id"), method: "GET"});
        if (result.status === 200) {

            const stand:StandModel = result.body as StandModel;
            //TODO StandPage
            if(params.get('event') != stand?.eventID.toString()){
                //const url = (`/stand?id=${params.get("id")}&event=${stand?.eventID.toString()}`);
                params.append("event", stand?.eventID.toString())
                //document.location.href = url;
            }else {
                setStand(stand);
            }
        }
    }

    useLayoutEffect(() => {
        getStandInfo();
    }, []);

    return params.has("id") ?
        <div className={"stand-info-parent box"}>
            <Backbtn />
            <h2>{stand?.title}</h2>
            <VotingStars />
            <p>{stand?.description}</p>
            <img src={logo} alt={"logo"} style={{width:"80%"}} />
        </div>
        :
        <div>Mangler stand id i url</div>
}

export default StandPage;