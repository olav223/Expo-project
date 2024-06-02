import { Link } from "react-router-dom";
import React, { useEffect, useState } from "react";
import type { StandModel } from "../../model/Stand";
import restApi from "../../utils/restApi";
import Auth from "../../utils/auth";
import VotingStars from "../../components/VotingStars/VotingStars";
import notification from "../../utils/notification";

/**
 * Hovedsiden til en utstiller. Viser et preview av standen deres, og en QR-kode som kan scannes for å komme til standen.
 * Inneholder en knapp for å redigere standen sin.
 * @constructor
 */
const ExhibitorPage = (props: {id: string}) => {
    const [stand, setExhibitor] = useState<StandModel | null>(null);
    const auth = new Auth();

    useEffect(() => {
        getStand();
    }, []);

    const getStand = async () => {
        const result = await restApi({
            url: `/api/admin/exhibitor/stand?exhibitor=${ auth.getUser()?.username }`,
            method: "GET"
        });

        if (result.status === 200) {
            setExhibitor(result.body);
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke hente standen" });
        }
    };

    function QRCode() {
        const url = "https://api.qrserver.com/v1/create-qr-code/?data="+ process.env.REACT_APP_PROXY_HOST + "/stand?id=";
        return (
            <div className="qr">
                <img src={ url + stand?.id } alt="QrCode" />
            </div>
        );
    }

    return (
        <>
            <Link style={ { position: "absolute", right: "10px" } } to="edit">
                <button type={ "submit" }>Rediger</button>
            </Link>
            <h1 style={ { textAlign: "center" } }>Stand forhåndsvisning</h1>
            <div className={ "stand-info-parent box" }>
                <h2>{ stand?.title }</h2>
                <VotingStars id={props.id} />
                <p>{ stand?.description }</p>
                <img src={ stand?.image } alt={ "Bilde for stand" } />
                <QRCode />
            </div>
        </>
    );
};
export default ExhibitorPage;