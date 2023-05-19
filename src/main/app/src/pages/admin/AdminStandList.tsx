import { Link, useParams } from "react-router-dom";
import restApi from "../../utils/restApi";
import "./Admin.css";
import { StandList } from "../../components/StandList/StandList";
import notification from "../../utils/notification";
import React, {createRef, useRef, useState} from "react";
import ReactToPrint from "react-to-print";
import {type} from "os";

/**
 * Viser en liste over alle stands som er registrert på et gitt event.
 * Hvor man kan endre, legge til eller slette en stands.
 * @author Torbjørn Vatnelid
 */
const AdminStandList = () => {
    const { id } = useParams();
    const componentRef = useRef(null);

    const deleteStand = async (id: any) => {
        const result = await restApi({ url: `/api/stand`, method: "DELETE", body: id });
        if (result.status == 200) {
            notification({ type: "success", text: "Stand slettet" });
            window.location.reload();
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke slette stand" });
        }
    };
    const url =
        "https://api.qrserver.com/v1/create-qr-code/?data=" +
        process.env.REACT_APP_PROXY_HOST +
        "/stand?id=";

    type QRCodeProps = {
        name: string;
        id: number;
    } ;

    const QRCode = React.forwardRef<HTMLDivElement, QRCodeProps>((props, ref: React.Ref<HTMLDivElement>) => {
        const {name , id} = props;
        return (
            <div ref={ref} className="qr">
                <h2>{name}</h2>
                <img src={ url + id } alt="QrCode" width={"100%"} />
            </div>)
    })




    return <div className="standList">
        <h2>Stands</h2>
        <Link to={ `/admin/stand/edit/-1?eventId=${ id }` }>
            <button className={ "submit-btn" }>Legg til en ny stand</button>
        </Link>
        <Link to={`/admin/qr/${ id }`} style={{marginLeft:"10px"}}>
        <button className={"submit-btn"}>Alle QR-Koder</button>
        </Link>
        <StandList eventId={ id } diableHeader components={ (stand) =>
            <div style={ { textAlign: "left" } }>
                <Link to={ `/admin/stand/edit/${ stand.id }` }>
                    <button type="submit">
                        Endre
                    </button>
                </Link>
                <ReactToPrint
                    trigger={() => (
                        <button style={{marginLeft:"10px"}} type={"submit"} >
                            Skriv ut QR-Kode
                        </button>
                    )}
                    content={() => componentRef.current}
                />
                <QRCode ref={componentRef} name={stand.title} id={stand.id} /> {/* Pass the ref to the QRCode component */}

                <button type="submit" className="delete-button" onClick={ () => deleteStand(stand.id) }>Slett</button>
            </div>
        } />
    </div>;
};

export default AdminStandList;
