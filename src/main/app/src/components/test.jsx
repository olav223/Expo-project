import {useEffect, useState} from "react";

const Test = () => {
    const [Data, setData] = useState([]);
    const DemoDataFraSping = async () => {
        await fetch(process.env.REACT_APP_PROXY_HOST + '/api/demo', {method: "GET"},)
            .then((respons) => respons.json())
            .then((data) => {
                setData(data);
            }).catch((err) => console.log(err));


    }

    useEffect(() => {
        DemoDataFraSping();
    }, [])

    return (
        <div>
            <div>
                Hello World from React!
            </div>
            <div>
                {Data.map(item => item + " ")}
            </div>
        </div>
    )
}

export default Test;