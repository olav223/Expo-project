import { ChangeEvent, useEffect, useState } from "react";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";
import "./EditStand.css";
import notification from "../../utils/notification";

interface EditStandProps {
    getStandUrl: string;
    isAdd: boolean;
    eventId: number;
}

const emptyStand: StandModel = { id: 0, url: "", responsibleID: "", eventID: 1, image: "", description: "", title: "" };

const EditStand = (props: EditStandProps) => {
    const [stand, setStand] = useState(emptyStand);

    useEffect(() => {
        if (!props.isAdd) getStand();
        setStand({ ...stand, eventID: props.eventId });
    }, []);

    const handleImageUpload = (event: ChangeEvent<HTMLInputElement>) => {
        const file = event.target.files ? event.target.files[0] : null; // add null check
        if (file) {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                const imageData = reader.result;
                console.log(imageData);
            };
        }
    };

    const getStand = async () => {
        const result = await restApi({ url: props.getStandUrl, method: "GET" });

        if (result.status === 200) {
            setStand(result.body);
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke hente stand" });
        }
    };

    const update = async (e: ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        const result = await restApi({
            url: `/api/stand/update`, method: "POST", body:
                {
                    "id": stand.id,
                    "title": stand.title,
                    "description": stand.description,
                    "image": stand.image,
                    "url": stand.url,
                    "eventID": stand.eventID,
                    "responsibleID": stand.responsibleID
                }
        });
        if (result.status == 200) {
            notification({ type: "success", text: "Stand oppdatert" });
            window.history.back();
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke oppdatere stand" });
        }
    };

    const add = async (e: ChangeEvent<HTMLFormElement>) => {
        e.preventDefault();

        const result = await restApi({
            url: "/api/stand/add", method: "POST",
            body:
                {
                    "title": stand.title,
                    "description": stand.description,
                    "image": stand.image,
                    "url": stand.url,
                    "eventID": stand.eventID,
                    "responsibleID": stand.responsibleID === "" ? null : stand.responsibleID
                }
        });
        if (result.status == 200) {
            notification({ type: "success", text: "Stand lagt til" });
            window.history.back();
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke legge til stand" });
        }
    };

    return (
        <form className={ "modify-stand-parent" } id={ "modify-stand-form" } onSubmit={ props.isAdd ? add : update }>
            <label>Stand navn
                <input type="text" defaultValue={ stand.title }
                       onChange={ (e) => setStand({ ...stand, title: e.target.value }) }
                       required />
            </label>
            <label>Stand beskrivelse
                <textarea
                    rows={ 5 }
                    cols={ 50 }
                    style={ { height: "300px" } }
                    onChange={ (e) => setStand({ ...stand, description: e.target.value }) }
                    defaultValue={ stand.description } />
            </label>
            <label>Stand bilde
                <input type="file" onChange={ handleImageUpload } />
            </label>
            <button style={ { marginBottom: "100px" } } type="submit" className="save-button">
                Save
            </button>
        </form>
    );
};

export default EditStand;