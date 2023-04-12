import { ChangeEvent, FormEvent, useEffect, useState } from "react";
import { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";
import { useParams } from "react-router";
import "./Admin.css";
import notification from "../../utils/notification";
import Backbtn from "../../components/Backbtn/Backbtn";

/**
 * Side for å endre info om en stand.
 * @author Torbjørn Vatnelid & Martin Berg Alstad
 */
const AdminStandEdit = () => {

    const [stand, setStand] = useState<StandModel | null>(null);

    useEffect(() => {
        getStand().then((s) => {
            const form = document.querySelector("#edit-stand-form")!;
            const inputs = form.querySelectorAll("input");

            if (s) {
                inputs[0].value = s.title ?? "";
                inputs[1].value = s.description ?? "";
                inputs[2].value = s.image ?? "";
                inputs[3].value = s.url ?? "";
                inputs[4].value = s.responsibleID ?? "";
            }
        });
    }, []);

    const { id } = useParams();

    const update = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const form = document.getElementById("edit-stand-form") as HTMLFormElement;
        if (!form.checkValidity()) {
            notification({ type: "error", text: "Fyll ut røde felt!" });
            return;
        }

        const result = await restApi({
            url: `/api/stand/update`, method: "POST", body: stand ?? undefined
        });
        if (result.status === 200) {
            notification({ type: "success", text: "Stand oppdatert!" });
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Stand ble ikke oppdatert!" });
        }
    };

    const getStand = async (): Promise<StandModel | undefined> => {
        const result = await restApi({ url: `/api/stand?id=${ id }`, method: "GET" });

        if (result.status === 200) {
            setStand(result.body);
            return result.body;
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke hente stand" });
        }
    };

    function QRCode() {
        const url = "https://api.qrserver.com/v1/create-qr-code/?data=http://ider-database.westeurope.cloudapp.azure.com:8080/Prosjekt-Expo-0.0.1-SNAPSHOT/stand?id=";
        return (
            <div className="qr">
                <img src={ url + stand?.id } alt="QrCode" />
            </div>
        );
    }

    function updateStand(e: ChangeEvent<HTMLInputElement>, param: string) {
        setStand({ ...stand!, [param]: e.target.value });
    }

    return (
        <div>
            <Backbtn />
            <div className="wrapper">
                <form id={ "edit-stand-form" } onSubmit={ update }>
                    <label>
                        Tittel:
                        <input type="text" placeholder={ `${ stand?.title ?? "" }` }
                               onChange={ (e) => updateStand(e, "title") }
                               required />
                    </label>
                    <label>
                        Beskrivelse:
                        <input type="text" placeholder={ `${ stand?.description ?? "" }` }
                               onChange={ (e) => updateStand(e, "description") }
                               required />
                    </label>
                    <label>
                        Bilde:
                        <input type="text" placeholder={ `${ stand?.image ?? "" }` }
                               onChange={ (e) => updateStand(e, "image") } />
                    </label>
                    <label>
                        URL:
                        <input type="text" placeholder={ `${ stand?.url ?? "" }` }
                               onChange={ (e) => updateStand(e, "url") } />
                    </label>
                    <label>
                        Ansvarlig:
                        <input type="text" placeholder={ `${ stand?.responsibleID ?? "" }` }
                               onChange={ (e) => updateStand(e, "responsibleID") }
                               required />
                    </label>
                    <button type="submit" className="save-button">Save</button>
                </form>
                <QRCode />
            </div>
        </div>
    );
};

export default AdminStandEdit;
