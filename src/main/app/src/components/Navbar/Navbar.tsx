import "./Navbar.css"
import {Link} from "react-router-dom";
const Navbar = () => {
return <nav className={"box"}>
        <img alt="logo" src="https://www.hvl.no/globalassets/hvl-internett/it/logo/hvl_logo.png" />
        <ul className={"ui-menu"}>
            <Link to={"/"}><li>Hjem</li></Link>
            <Link to={"/stand"}><li>Stand</li></Link>
            <Link to={"/login"}><li className={"login-btn"}>Logg inn</li></Link>
        </ul>
    </nav>
}

export default Navbar;