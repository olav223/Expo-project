import Cookies from "universal-cookie";
import restApi from "./restApi";

const cookies = new Cookies();

export default class Auth {
    async createVoter(eventId:string):Promise<boolean> {
        const result = await restApi({url:"/api/vote/newvoter?eventID="+eventId,method:"GET"});
        if (result.status == 200 && result.body) {
            const expireDay = new Date();
            expireDay.setDate(expireDay.getDate() + 7);
            cookies.set('user', result.body["id"], { path: '/', expires: expireDay });
            return true;
        }
        return false;
    }

    getUser():Voter | null {
        const voter = cookies.get("user");
        if (voter) return {id: voter};
        return null;
    }

    async verifyUser(id:string):Promise<boolean> {
        const result = await restApi({url:"/api/vote/validate?voterId="+id,method:"GET"});
        return result.status == 200 && result.body;
    }
}