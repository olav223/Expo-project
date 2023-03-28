import {StandList} from "../components/StandList/StandList";
import Auth from "../utils/auth";

const FrontPage = () => {
    const auth = new Auth();

    const login = async() => {
        //await auth.createUser();
    }
    return <div>
        <StandList />
    </div>
}

export default FrontPage;