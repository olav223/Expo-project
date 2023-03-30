import "./Navbar.css"
import {Link} from "react-router-dom";
const Navbar = () => {
return <nav className={"box"}>
        <Link to="/" className="home-link">
            <img alt="logo" src="https://www.hvl.no/globalassets/hvl-internett/it/logo/hvl_logo.png" />
        </Link>
        <ul className={"ui-menu"}>
            <Link to={"/"}><li>Hjem</li></Link>
            <Link to={"/stands"}><li>Stand</li></Link>
            <Link to={"/login"}><li><button type={"submit"}>Logg inn</button></li></Link>
        </ul>
    </nav>
}

export default Navbar;