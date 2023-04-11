import {Link, Router, Routes, useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import ExhibitorEditPage from "./ExhibitorEditPage";
// @ts-ignorem
import axios, { AxiosError, AxiosResponse } from 'axios';
import {StandModel} from "../../model/Stand";
import restApi from "../../utils/restApi";
import {useParams} from "react-router";


interface StandData {
    standName: string;
    standDescription: string;
    standImage: string;
}
const ExhibitorPage = () => {
    const [stand, setExhibitor] = useState<StandModel | null>(null)

    useEffect(() => {
        getExhibitor();
    }, []);

    const {id} = useParams()

    const getExhibitor = async() => {
        const result = await restApi({url : `/api/stand?id=${id}`, method : "GET"})

        if(result.status === 200){
            setExhibitor(result.body)
        }
    }
    const update = async () => {

        await restApi({url : `/api/stand/update`, method : "POST", body :
                {
                    "id" : id,
                    "title" : stand!.title,
                    "description" : stand!.description,
                    "image" : stand!.image,
                    "url" : stand!.url,
                    "eventID" : stand!.eventID,
                    "responsibleID" : stand!.responsibleID
                }
        })}

    return <div>
        <div style={{display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column"}}>
            <div style={{marginTop: "10px" }}>
            <Link style={{position: "absolute", right: "10px"}} to="edit">
                <button type={"submit"}>Exhibitor edit page</button>
            </Link>
                </div>
            <h1 style={{textAlign:"center", width: "fit-content"}}>ExhibitorPage</h1>
            <input type="text" placeholder={`${stand?.title}`} onChange={(e) => stand!.title = e.target.value}/>
            <input type="text" placeholder={`${stand?.description}`} onChange={(e) => stand!.description = e.target.value}/>
            <input type="text" placeholder={`${stand?.image}`} onChange={(e) => stand!.image = e.target.value}/>
            <input type="text" placeholder={`${stand?.title}`} onChange={(e) => stand!.title = e.target.value}/>
        </div>
    </div>
}
export default ExhibitorPage;