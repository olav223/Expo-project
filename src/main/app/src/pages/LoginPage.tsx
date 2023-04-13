import LoginAuth from "../components/LoginAuth/LoginAuth";
import DevLogin from "../components/DevLogin";

const LoginPage = () => (
    <>
        <LoginAuth />
        { process.env.NODE_ENV === "development" && <DevLogin /> }
    </>
);

export default LoginPage;
