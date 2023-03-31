import Cookies from "universal-cookie";
import restApi from "./restApi";

const cookies = new Cookies();

export default class Auth {
    async createVoter(eventId:string):Promise<boolean> {
        const result = await restApi({url:"/api/vote/newvoter?eventID="+eventId,method:"GET"});
        if (result.status === 200 && result.body) {
            this.storeUser({username: result.body})
            return true;
        }
        return false;
    }

    async login(username:string,password:string):Promise<boolean> {
        const result = await restApi({url: `/api/user/login?username=${username}&password=${password}`,method:"POST"});
        if (result.status === 200 && result.body) {
            this.storeUser(JSON.parse(result.body));
            return true;
        }
        return false;
    }

    storeUser(user:UserModel):void {
        const expireDay = new Date();
        expireDay.setDate(expireDay.getDate() + 7);
        window.sessionStorage.setItem("expo-user",JSON.stringify(user));
        cookies.set('expo-user', user.username, { path: '/', expires: expireDay });
    }

    getUser():UserModel | null {
        const userCookie = cookies.get("expo-user");
        const user:string = window.sessionStorage.getItem("expo-user") ?? "";
        if (userCookie && user) {
            return JSON.parse(user);
        }
        return null;
    }

    async verifyVoter(id:string):Promise<boolean> {
        const result = await restApi({url:"/api/vote/validate?voterId="+id,method:"GET"});
        return result.status === 200 && result.body;
    }
}