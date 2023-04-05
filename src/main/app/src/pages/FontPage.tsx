import {StandList} from "../components/StandList/StandList";
import HeroImage from "../components/HeroImage/HeroImage";
import EventList from "../components/EventList/EventList";

const FrontPage = () => {
    return <div>
        <HeroImage />
        <EventList />
        <StandList />
    </div>
}

export default FrontPage;