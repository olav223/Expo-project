import "./Backbtn.css"

const Backbtn = () => {
    return <button className={"backbtn"} onClick={() => window.history.go(-1)}>Tilbake</button>
}

export default Backbtn;