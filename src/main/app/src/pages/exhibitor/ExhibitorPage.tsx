import {Link, Router, Routes, useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import ExhibitorEditPage from "./ExhibitorEditPage";
// @ts-ignorem
import axios, { AxiosError, AxiosResponse } from 'axios';
import {StandModel} from "../../model/Stand";
import restApi from "../../utils/restApi";
import {useParams} from "react-router";
import Auth from "../../utils/auth";
import VotingStars from "../../components/VotingStars/VotingStars";

const ExhibitorPage = () => {
    const [stand, setExhibitor] = useState<StandModel | null>(null)
    const auth = new Auth();

    useEffect(() => {
        getStand();
    }, []);

    const getStand = async() => {
        const result = await restApi({url : `/api/admin/exhibitor/stand?exhibitor=${auth.getUser()?.username}`, method : "GET"})

        if(result.status === 200){
            setExhibitor(result.body)
        }
    }

    return <div>
        <Link style={{position: "absolute", right: "10px"}} to="edit">
            <button type={"submit"}>Exhibitor edit page</button>
        </Link>
        <h1 style={{textAlign:"center"}}>Stand preview</h1>
        <div className={"stand-info-parent box"}>
            <h2>{stand?.title}</h2>
            <VotingStars />
            <p>{stand?.description}</p>
            <img src={stand?.image} />
        </div>
    </div>
}
export default ExhibitorPage;