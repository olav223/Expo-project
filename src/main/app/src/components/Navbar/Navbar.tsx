import "./Navbar.css"
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import Auth from "../../utils/auth";
import Cookies from "universal-cookie";
const Navbar = () => {

    const [user,setUser] = useState<UserModel | null>(null);

    const auth = new Auth();
    const updateUser = () => setUser(auth.getUser());
    useEffect(() => {
        updateUser();
    }, []);

    return <nav className={"box"}>
        <img alt="logo" src="https://www.hvl.no/globalassets/hvl-internett/it/logo/hvl_logo.png" />
        <ul className={"ui-menu"}>
            <Link to={"/"}><li>Hjem</li></Link>
            <Link to={"/stands"}><li>Stand</li></Link>
            {user?.access === 0 && <Link to={"/admin"}><li>Admin</li></Link>}
            {user?.access === 1 && <Link to={"/jury"}><li>Jury</li></Link>}
            {user?.access === 2 && <Link to={"/exhibitor"}><li>Exhibitor</li></Link>}
            {user && user.access !== null ? <><li>Hei, {user.username}</li><li><button onClick={auth.signOut} type={"submit"}>Logg ut</button></li></> : <Link to={"/login"}><li><button type={"submit"}>Logg inn</button></li></Link>}
        </ul>
    </nav>
}

export default Navbar;