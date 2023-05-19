
import React, {useEffect, useState} from "react";
import {useParams} from "react-router";
import {StandList} from "../../components/StandList/StandList";
import restApi from "../../utils/restApi";
import {StandModel} from "../../model/Stand";

const QrCodePage = () => {
    const {eventId } = useParams();
    const [stands, setStands] = useState<StandModel [] | null>([]);


    const getStands = async() => {
        const result = await restApi({url: "/api/stand/all?eventID="+eventId, method: "GET"});
        if(result.status === 200){
            setStands(result.body)
        }
    }

    useEffect(() => {
        if (eventId !== undefined){
            getStands();
        }
    }, []);


      function QRCode(standId: number) {
          const url = "https://api.qrserver.com/v1/create-qr-code/?data="+ process.env.REACT_APP_PROXY_HOST + "/stand?id=";
          return (
              <div className="qr">
                  <img src={ url + standId } alt="QrCode" width={"100%"} />
              </div>
         );
      }

    return <div className={"standList"}>
        <h2 style={{textAlign:"center"}}>Qr codes for event</h2>
        {stands ? stands.length > 0 ? stands.map((item, i) => {
            return <div key={"stand-"+i} className={"standItem box"}>
                <div>
                    <h2 style={{marginTop: 0, textAlign:"center"}}>{item["title"]}</h2>
                    <div style={{alignItems:"center"}}>
                        {QRCode(i)}
                    </div>
                </div>
            </div>
        }) :<div>ingen stands</div>: <div> noe gikk galt</div>}


    </div>

}

export default QrCodePage;