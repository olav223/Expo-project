import Cookies from "universal-cookie";
import restApi from "./restApi";
import notification from "./notification";

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

    async login(username:string,password:string):Promise<UserModel | null> {
        const result = await restApi({url: `/api/user/login`,method:"POST", header: {
                "username": username,
                "password": password
            }});
        if (result.status === 200 && result.body) {
            const user:UserModel = JSON.parse(result.body)
            this.storeUser(JSON.parse(result.body));
            this.redirect(user);
            return JSON.parse(result.body);
        }
        return null;
    }

    redirect(user:UserModel) {
        let url = "";
        if (user.access === 0) url = "/admin";
        else if (user.access === 1) url = "/jury";
        else if (user.access === 2) url = "/exhibitor";
        window.location.href = process.env.REACT_APP_PROXY_HOST + url;
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

    signOut():void {
        cookies.remove("expo-user");
        window.sessionStorage.removeItem("expo-user");
        notification({text:"Logget ut", type: "success"});
        window.location.href = "/";
    }

    async verifyVoter(id:string):Promise<boolean> {
        const result = await restApi({url:"/api/vote/validate?voterId="+id,method:"GET"});
        return result.status === 200 && result.body;
    }
}