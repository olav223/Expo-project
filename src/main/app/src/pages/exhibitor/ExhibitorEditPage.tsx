import {Link} from "react-router-dom";
import { useState } from "react";
const ExhibitorEditPage = () => {
    const [standName, setStandName] = useState("");
    const [standDescription, setStandDescription] = useState("");
    const [standImage, setStandImage] = useState(null);
    const handleImageUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
        const file = event.target.files ? event.target.files[0] : null; // add null check
        if (file) {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                const imageData = reader.result;
                console.log(imageData);
            }
        }
    }
    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        // Create a FormData object to send the data as multipart/form-data
        const formData = new FormData();
        formData.append("standName", standName);
        formData.append("standDescription", standDescription);
        if (standImage) {
            formData.append("standImage", standImage);
        }
        try {
            const response = await fetch("/api/stands", {
                method: "POST",
                body: formData,
            });
            if (response.ok) {
                console.log("Stand data uploaded successfully");
            } else {
                console.error("Failed to upload stand data");
            }
        } catch (error) {
            console.error(error);
        }
    }
    return  <div>
        <div style={{display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column"}}>
            <div style={{marginTop: "10px" }}>
            <Link style={{position: "absolute", left: "10px"}} to="/Exhibitor">
                <button type={"submit"}>Exhibitor page</button>
            </Link>
                </div>
            <h1 style={{textAlign:"center", width: "fit-content"}}>Exhibitor edit page</h1>
            <br/>
            <form onSubmit={handleSubmit}>
                <input type="text" style={{border: "1px solid black", padding: "5px"}} placeholder={"Enter stand name here:"} />
                <br />
                <textarea
                    rows={5}
                    cols={50}
                    style={{ border: "1px solid black", padding: "5px", height: "200px", width: "600px" }}
                    placeholder="Enter your stand description here:"
                ></textarea>
                <br />
                <input type="file" onChange={handleImageUpload} />
                <br />
                <button type="submit">Upload Stand Data</button>
            </form>
        </div>
    </div>
}
export default ExhibitorEditPage;