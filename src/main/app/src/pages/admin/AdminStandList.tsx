import React, { ChangeEvent, useEffect, useState } from "react";
import Popup from "reactjs-popup";
import { Link } from "react-router-dom";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";
import AddStand from "../../components/AddStandButton";
import "./Admin.css";
import notification from "../../utils/notification";
import { useParams } from "react-router";

/**
 * Viser en liste over alle stands som er registrert på et gitt event.
 * Hvor man kan endre, legge til eller slette en stands.
 * @author Torbjørn Vatnelid
 */
const AdminStandList = () => {

    const [stands, setStands] = useState<StandModel [] | null>(null);
    const [title, setTitle] = useState("");
    const [desciption, setDesciption] = useState("");
    const [image, setImage] = useState("");
    const [url, setUrl] = useState("");
    const [responsible, setResponsible] = useState("");

    const sEventID = useParams();
    const eventID = parseInt(sEventID.id ?? "1");

    const handleTitle = (event: ChangeEvent<HTMLInputElement>) => {
        setTitle(event.target.value);
    };

    const handleDescription = (event: ChangeEvent<HTMLInputElement>) => {
        setDesciption(event.target.value);
    };

    const handleImage = (event: ChangeEvent<HTMLInputElement>) => {
        setImage(event.target.value);
    };

    const handleUrl = (event: ChangeEvent<HTMLInputElement>) => {
        setUrl(event.target.value);
    };
    const handleResponsible = (event: ChangeEvent<HTMLInputElement>) => {
        setResponsible(event.target.value);
    };

    const getStands = async () => {
        const result = await restApi({ url: `/api/stand/all?eventID=${ eventID }`, method: "GET" });
        if (result.status === 200) {
            setStands(result.body);
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke hente stands" });
        }
    };

    const deleteStand = async (id: number) => {
        const result = await restApi({
            url: `/api/stand`, method: "DELETE", body: id
        });
        if (result.status === 200) {
            notification({ type: "success", text: "Stand slettet" });
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke slette stand" });
        }
        getStands();
    };

    useEffect(() => {
        getStands();
    }, []);

    return (
        <div className="standList">
            <h2>Stands</h2>
            <Popup trigger={ <button type="submit">Legg til en ny stand</button> }
                   position="bottom center"
                   contentStyle={ {
                       background: "lightgray",
                       borderRadius: "10px",
                       padding: "10px",
                   } }>
                <>
                    <form id={ "add-stand-form" }>
                        <label>
                            Tittel:
                            <input type="text" onChange={ handleTitle } minLength={ 3 } required />
                        </label>
                        <label>
                            Beskrivelse:
                            <input type="desciption" onChange={ handleDescription } minLength={ 5 } required />
                        </label>
                        <label>
                            Bilde:
                            <input type="text" onChange={ handleImage } />
                        </label>
                        <label>
                            URL:
                            <input type="text" onChange={ handleUrl } />
                        </label>
                        <label>
                            Ansvarlig:
                            <input type="text" onChange={ handleResponsible } required />
                        </label>
                    </form>
                    <AddStand id={ 0 } title={ title } description={ desciption } image={ image } url={ url }
                              eventID={ eventID } responsibleID={ responsible } onSubmit={ getStands } />
                </>
            </Popup>
            { stands !== null ? stands.map((stand) =>
                <div key={ stand.id } className="standItem box">
                    <div>
                        <h4>{ stand.title }</h4>
                        <p>{ stand.description }</p>
                        <Link to={ `/admin/stand/edit/${ stand.id }` }>
                            <button type="submit" className="delete-button">
                                Endre
                            </button>
                        </Link>
                        <button type="submit" className="delete-button" onClick={ () => deleteStand(stand.id) }>
                            Slett
                        </button>
                    </div>
                </div>
            ) : <div>Ingen stands</div> }
        </div>
    );
};

export default AdminStandList;
