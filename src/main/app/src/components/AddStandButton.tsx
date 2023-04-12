import { StandModel } from "../model/Stand";
import restApi from "../utils/restApi";
import notification from "../utils/notification";

interface AddStandProps extends StandModel {
    onSubmit?: () => void;
}

/**
 * Komponent for å legge til en stand.
 * @param props
 * @constructor
 */
const AddStand = (props: AddStandProps) => {

    const add = async () => {

        const form = document.getElementById("add-stand-form") as HTMLFormElement;
        if (!form.checkValidity()) {
            notification({ type: "error", text: "Fyll ut røde felt!" });
            return;
        }

        const result = await restApi({
            url: "/api/stand/add", method: "POST",
            body:
                {
                    "title": props.title,
                    "description": props.description,
                    "image": props.image,
                    "url": props.url,
                    "eventID": props.eventID,
                    "responsibleID": props.responsibleID
                }
        });
        if (result.status === 200) {
            notification({ type: "success", text: "Stand lagt til!" });
            props.onSubmit?.();
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Stand ble ikke lagt til!" });
        }
    };

    return <button type="submit" onClick={ add }>Legg til stand</button>;
};

export default AddStand;