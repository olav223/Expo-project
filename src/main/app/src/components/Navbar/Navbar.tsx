import "./Navbar.css"
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import Auth from "../../utils/auth";
import {Bars3Icon} from "@heroicons/react/20/solid";
import {XMarkIcon} from "@heroicons/react/24/solid";
const Navbar = () => {
    const [user,setUser] = useState<UserModel | null>(null);
    const [isopen,setIsopen] = useState(true);
    const [ismobile,setIsmobile] = useState(false);

    const auth = new Auth();
    const updateUser = () => setUser(auth.getUser());

    const toggleMenu = (show:boolean) => {
        const elm = document.getElementById("menu-dropdown")!;
        if (show) elm.classList.remove("close");
        else elm.classList.add("close");
        setIsopen(show);
    }

    const resize = () => {
        const width = window.screen.width;
        if (width < 700) {
            setIsmobile(true);
            toggleMenu(false);
        } else if (width >= 700) {
            setIsmobile(false);
            toggleMenu(true);
        }
    }

    useEffect(() => {
        updateUser();
        resize();
        window.addEventListener('resize', resize);
        // eslint-disable-next-line
    }, []);

    return <nav className={"box"}>
        <Link to={"/"}><img alt="logo" src="https://www.hvl.no/globalassets/hvl-internett/it/logo/hvl_logo.png" /></Link>
        {ismobile && (<div style={{ margin: "auto 0", height: "fit-content" }} onClick={() => toggleMenu(!isopen)}>{isopen ? <XMarkIcon style={{ height: "38px" }} /> : <Bars3Icon style={{ height: "38px" }} />}</div>)}
        <ul id={"menu-dropdown"} onClick={() => ismobile ? toggleMenu(false) : null} className={"ui-menu"}>
            <Link to={"/"}><li>Hjem</li></Link>
            <Link to={"/stands"}><li>Stand</li></Link>
            {user?.accessLevel === 0 && <Link to={"/admin"}><li>Admin</li></Link>}
            {user?.accessLevel === 1 && <Link to={"/jury"}><li>Jury</li></Link>}
            {user?.accessLevel === 2 && <Link to={"/exhibitor"}><li>Exhibitor</li></Link>}
            {user && user.accessLevel !== undefined ? <><li>Hei, {user.username}</li><li><button onClick={auth.signOut} type={"submit"}>Logg ut</button></li></> : <Link to={"/login"}><li><button type={"submit"}>Logg inn</button></li></Link>}
        </ul>
    </nav>
}

export default Navbar;