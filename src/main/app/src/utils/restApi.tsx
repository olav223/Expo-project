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
        .then((response) => formatData(response)
        .then((data) => {
            return {status: response.status, body: data}
        }))
        .catch((err) => {
            console.log("Error: "+err.message)
            return {status: 501, body: null};
        });
}

const formatData = async(response:Response) => {
    const data = response.text();
    try {
        return JSON.parse(await data)
        return response.text().catch((err) => response.text());
    } catch {
        return data;
    }
}

export default restApi;