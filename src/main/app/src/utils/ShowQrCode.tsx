// @ts-ignore
import restApi from "/restApi";
import {useState} from "react";

export const QrCodeButton = () => {
    const [qrcode, setQrcode] = useState([])


    const getQr = async (id: number) => {
        const result = await restApi({url: "/api/admin/stand/qr?standID=" + id, method: "POST"});
        if (result.status == 200) {
            setQrcode(result.body);
        }
    }

    function openQr() {
        // @ts-ignore
        window.open(getQr(2));
    }


    return <div>
        <button onClick={openQr}>ClickMe</button>
    </div>
}