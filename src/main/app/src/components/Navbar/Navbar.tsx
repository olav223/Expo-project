import "./Navbar.css"
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import Auth from "../../utils/auth";
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
        if (width < 500) {
            setIsmobile(true);
            toggleMenu(false);
        } else if (width >= 500) {
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
        {ismobile && (<img style={{maxHeight: "20px"}} onClick={() => toggleMenu(!isopen)} src={isopen ? "/assets/close-menu.png" : "/assets/menu.png"} />)}
        <ul id={"menu-dropdown"} onClick={() => ismobile ? toggleMenu(false) : null} className={"ui-menu"}>
            <Link to={"/"}><li>Hjem</li></Link>
            <Link to={"/stands"}><li>Stand</li></Link>
            {user?.access === 0 && <Link to={"/admin"}><li>Admin</li></Link>}
            {user?.access === 1 && <Link to={"/jury"}><li>Jury</li></Link>}
            {user?.access === 2 && <Link to={"/exhibitor"}><li>Exhibitor</li></Link>}
            {user && user.access !== undefined ? <><li>Hei, {user.username}</li><li><button onClick={auth.signOut} type={"submit"}>Logg ut</button></li></> : <Link to={"/login"}><li><button type={"submit"}>Logg inn</button></li></Link>}
        </ul>
    </nav>
}

export default Navbar;