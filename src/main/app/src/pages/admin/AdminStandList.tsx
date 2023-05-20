import { Link, useParams } from "react-router-dom";
import restApi from "../../utils/restApi";
import "./Admin.css";
import { StandList } from "../../components/StandList/StandList";
import notification from "../../utils/notification";
import React, {createRef, useRef, useState} from "react";
import ReactToPrint from "react-to-print";
import {AllQrCodes, OneQrCode} from "../exhibitor/QrCode";

/**
 * Viser en liste over alle stands som er registrert på et gitt event.
 * Hvor man kan endre, legge til eller slette en stands.
 * @author Torbjørn Vatnelid
 */
const AdminStandList = () => {
    const { id } = useParams();
    const qrCodesRef = useRef(null);
    const qrCodeRef = useRef(null);

    const deleteStand = async (id: number) => {
        const result = await restApi({ url: `/api/stand`, method: "DELETE", body: id });
        if (result.status ===200) {
            notification({ type: "success", text: "Stand slettet" });
            window.location.reload();
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke slette stand" });
        }
    };

    return <div className="standList">
        <h2>Stands</h2>
        <div style={{display: "flex", gap: "5px", justifyContent: "center"}}>
            <Link to={ `/admin/stand/edit/-1?eventId=${ id }` }>
                <button className={ "submit-btn" }>Legg til en ny stand</button>
            </Link>
            <Link to={`/admin/qr/${ id }`}>
            <button className={"submit-btn"}>Alle QR-Koder</button>
            </Link>
            <ReactToPrint content={() => qrCodesRef.current}
                          trigger={() =>
                              <button className={"submit-btn"}>Skriv ut alle QR-koder</button>
            } />
        </div>
        <div style={{display: "none"}}>
            <AllQrCodes ref={qrCodesRef} eventId={id}/>
        </div>
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
                    content={() => qrCodeRef.current}
                />
                <div style={{display:"none"}}>
                    <OneQrCode ref={qrCodeRef} standId={stand.id} /> {/* Pass the ref to the QRCode component */}
                </div>
                <button type="submit" className="delete-button" onClick={ () => deleteStand(stand.id) }>Slett</button>
            </div>
        } />
    </div>;
};

export default AdminStandList;
