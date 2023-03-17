import {restApiResponseProps} from "../model/restApiRespone";

interface restAPiProps {
    url: string;
    method: "POST" | "GET" | "DELETE" | "PUT" | "PATCH";
}

const restApi = async(props:restAPiProps):Promise<restApiResponseProps> => {
    return await fetch(process.env.REACT_APP_PROXY_HOST + props.url, {method: props.method})
        .then((response) => response.json()
        .then((data) => {
            return {status: response.status, body: data}
        }))
        .catch((err) => {
            console.log("Error: "+err.message)
            return {status: 501, body: null};
        });
}

export default restApi;