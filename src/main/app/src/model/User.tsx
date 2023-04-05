interface UserModel {
    username: string;
    phone?: string;
    email?: string;
    accessLevel?: number;
    salt?: string;
    hash?: string;
}