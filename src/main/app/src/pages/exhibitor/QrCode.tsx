import React, {forwardRef, useEffect, useState} from "react";
import type {StandModel} from "../../model/Stand";
import restApi from "../../utils/restApi";
import notification from "../../utils/notification";
import "./QrCode.css";


const QRCode  = ({ standId }: {standId?: number | string }) => {
    if(!standId) return null;

    const url = "https://api.qrserver.com/v1/create-qr-code/?data=" +
        process.env.REACT_APP_PROXY_HOST +
        "/stand?id=";
        return (
            <div className={"qr"}>
                <img src={url + standId} alt={"QrCode"} width={"100%"}/>
            </div>
        );
};

export const OneQrCode = forwardRef(
    (props: { standId?: number}, ref: React.ForwardedRef<HTMLDivElement>) => {
        const [stand, setStand] = useState<StandModel>();

        useEffect(() => {
            async function getStand() {
                const response =
                    await restApi({url: "/api/stand?id=" + props.standId, method: "GET"});
                if (response.status === 200 && response.body) {
                    const stand = response.body;
                    setStand(stand);
                } else {
                    console.error(response)
                    notification({type: "error", text: "Kunne ikke hente stand"})
                }
            }

            void getStand();
        }, [props.standId]);

        if (!props.standId) return null;

        return (
            <div ref={ref} className={"a4-div"}>
                <h2>{stand?.title}</h2>
                <QRCode standId={stand?.id}/>
            </div>
        )
    }
)

export const AllQrCodes = forwardRef(
    (props: { eventId?: number | string}, ref: React.ForwardedRef<HTMLDivElement>) => {

    const [stands, setStands] = useState<StandModel[]>();

    useEffect(() => {
        async function getStands() {
            const response =
                await restApi({url: "/api/stand/all?eventID=" + props.eventId, method:"GET"});
            if(response.status === 200 && response.body instanceof Array){
                const stands = response.body as StandModel[];
                setStands(stands);
            }
            else {
                console.error(response)
                notification({type: "error", text: "Kunne ikke hente stand"})
            }
        }

        void getStands();
    }, []);

    return (
        <div ref={ref}>
            {stands?.map(stand => (
                <div className={"a4-div"}  key={stand.id}>
                    <h2 style={{textAlign: "center"}}>{stand.title}</h2>
                    <QRCode standId={ stand.id} />
                </div>
            ))}
        </div>
    );
});

export default QRCode;