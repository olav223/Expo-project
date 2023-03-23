import {restApiResponseProps} from "../model/restApiRespone";

interface RestApiProps {
    url: String;
    method: "POST" | "GET" | "PUT" | "PATCH" | "DELETE";
    body?: Object;
}

const restApi = async(props:RestApiProps):Promise<restApiResponseProps> => {
    const url = (process.env.REACT_APP_PROXY_HOST ?? "") + props.url;
    const headers = {'Accept': 'application/json','Content-Type': 'application/json'};
    const config:RequestInit = {method: props.method, body: JSON.stringify(props.body), headers: headers};

    return await fetch(url, config)
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