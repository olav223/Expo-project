import React, {forwardRef, useEffect, useState} from "react";
import type {StandModel} from "../../model/Stand";
import restApi from "../../utils/restApi";
import notification from "../../utils/notification";
import "./QrCode.css";
import logo from './logoer.jpg';
import {getCharactersUptoColon} from "../../utils/getCharactersUptoColon";


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
    (props: { id: string, standId?: number, stand:StandModel, type?: string}) => {
        return (
            <div>
                {props.type === 'small'? (
                    <div id={props.id} className={"small-div"}>
                        <h2 style={{textAlign: "center"}}>{props.stand.title ? getCharactersUptoColon(props.stand.title) : ''}</h2>
                        <QRCode standId={props.stand.id}/>
                    </div>
                    ) : (
                    <div id={props.id} className={"a4-div"}>
                        <h2 style={{textAlign: "center"}}>Skann meg og stem på</h2>
                        <h1 style={{textAlign:"center"}}>{props.stand.title}</h1>
                        <QRCode standId={props.stand.id}/>
                        <img src={logo} alt={logo} style={{width: "100%"}}/>
                    </div>
                )}
            </div>

        )
    }
)

export const AllQrCodes = forwardRef(
    (props: { eventId?: number | string, small?: boolean}, ref: React.ForwardedRef<HTMLDivElement>) => {

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
                <div>
                {props.small? (
                        <div ref={ref}>
                            {stands?.map(stand => (
                                <div className={"small-div"} key={stand.id + "small"}>
                                    <h2 style={{textAlign: "center"}}>{getCharactersUptoColon(stand.title)}</h2>
                                    <QRCode standId={stand.id}/>
                                </div>
                            ))}
                        </div>
                    ): (
                        <div className={"a4-div"}  key={stand.id}>
                            <h2 style={{textAlign: "center"}}>Skann meg og stem på</h2>
                            <h1 style={{textAlign: "center"}}>{stand.title}</h1>
                            <br />
                            <QRCode standId={ stand.id} />
                            <img src={logo} alt={logo} style={{width: "100%"}}/>
                        </div>
                    )}
                </div>
            ))}
        </div>
    );
});

export default QRCode;